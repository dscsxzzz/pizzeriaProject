<template>
    <transition name="login-fade">
            <div @click.stop class="formContainer">
                <h2>Create Account</h2>
                <form @submit.prevent="tryCreateAccount">
                    <label for="username">Username:</label>
                    <input type="text" name="username" :value="username" placeholder="Username"
                    @change="username = $event.target.value">
                    <label for="username">Name:</label>
                    <input type="text" name="name" :value="name" @change="name = $event.target.value" placeholder="Name">
                    <label for="username">Surname:</label>
                    <input type="text" name="surname" :value="surname" @change="surname = $event.target.value" placeholder="Surname">
                    <label for="username">Address:</label>
                    <input type="text" name="address" :value="address" @change="address = $event.target.value" placeholder="Address">
                    <label for="username">Phone Number:</label>
                    <input type="text" name="phonenumber" v-model="phone" @change="Numberchek" placeholder="Phone Number">
                    <label for="username">E-mail:</label>
                    <input type="text" name="email" :value="email" @change="email = $event.target.value" placeholder="E-mail">
                    <label for="username">Password:</label>
                    <input type="password"  name="password" v-model="password" @change="validatePassword" placeholder="Password">
                    <label for="username">Repeat Password:</label>
                    <input type="password" name="repeatpassword" v-model="repeatpassword" @input="validatePassword"  placeholder="Repeat Password">
                    <p>{{message}}</p>
                    <button type="submit" :class="error ? 'disabled' : ''" :disabled="error">Create Account</button>
                </form>
            </div>
    </transition>
</template>
<script>
import { store } from '../store/store';
import Wrapper from './Wrapper.vue';
import router from '../router';
import baseUrl from '../config.json'
export default {
    components: {
        Wrapper
    },
    props: {
        show: {
            type: Boolean,
            default: false
        }
    },
    data() {
        return {
            router,
            username: "",
            email: "",
            name: "",
            surname: "",
            address: "",
            phone: "",
            password: "",
            repeatpassword: "",
            message: "",
            error: false,
            errorPhone: false,
            errorPass: false,
            messagePassError: "",
            store
        };
    },
    methods: {
        async validatePassword() {
            
            if (this.password.length < 8) {
                this.message = "Password is too short";
                this.messagePassError = "Password is too short";
                this.error = true;
                this.errorPass = true;
                return 0;
            }
            let big = true;
            let number = true;
            for (let i = 0; i < this.password.length; i++) {
                if (Number(this.password[i])) {
                    number = false;
                }
                else if (this.password[i] === this.password[i].toUpperCase()) {
                    big = false;
                }
            }
            if (big) {
                this.message = "Password does not include upper case character";
                this.messagePassError = "Password does not include upper case character";
                this.error = true;
                this.errorPass = true;

                return 0;
            }
            else if (number) {
                this.message = "Password does not include number";
                this.messagePassError = "Password does not include number";

                this.error = true;
                this.errorPass = true;
                return 0;
            }
            else {
                this.message = "";
                this.error = false;
                this.errorPass = false;
                await this.validatePassword2();
            }
        },
        async validatePassword2() {
            if (this.password !== this.repeatpassword) {
                this.message = "Passwords do not match";
                this.messagePassError = "Passwords do not match";
                this.error = true;
                this.errorPass = true;
                
            }
            else {
                this.message = "";
                this.error = false;
                this.errorPass = false;
                
            }
            if (this.errorPhone) {
                this.message = "Not valid number";
                this.error = true;
            }

        }, Numberchek() {
            if (this.phone[0] === "+" || !isNaN(this.phone[0])) {
                console.log('first chracter is okay');
                for (let i = 1; i < this.phone.length; i++) {
                    if (isNaN(this.phone[i])) {
                        this.message = "Not valid number";
                        this.error = true;
                        this.errorPhone = true;
                        return 0;
                    }
                }
            }
            else {
                this.message = "Not valid number";
                this.error = true;
                this.errorPhone = true;
                return 0;
            }
            this.message = "";
            this.error = false;
            this.errorPhone = false;
            if (this.errorPass) {
                this.message = this.messagePassError;
                this.error = true;
            }
        },
        async tryCreateAccount() {
            this.message = ""
            if (!this.error) {
                const body = {
                    "username": this.username,
                    "email": this.email,
                    "name": this.name,
                    "surname": this.surname,
                    "address": this.address,
                    "phone": this.phone,
                    "password": this.password,
                };
                const jsonBody = JSON.stringify(body);
                console.log(jsonBody);
                const response = await fetch(`${baseUrl.baseUrl}/user`, {
                    method: 'POST',
                    headers: {
                        Accept: 'application.json',
                        'Content-Type': 'application/json'
                    },
                    body: jsonBody,
                    cache: 'default'
                });
                const result = await response.json();
                this.message = result.message;
                if (response.status === 200) {
                    setTimeout(() => {
                        store.user.id        = this.id ,
                        store.user.username  = this.username ,
                        store.user.email     = this.email,
                        store.user.name      = this.name,
                        store.user.surname   = this.surname,
                        store.user.address   = this.address,
                        store.user.phone     = this.phone
                        this.renderForm();
                    }, 600);
                } 
            }
            else {
                return 0;
            }
        },
        renderForm() {
            router.push("/login")
        }
    },
    components: { Wrapper }
}
</script>
<style scoped>

.login-fade-enter-active,
.login-fade-leave-active {
  transition: opacity 0.8s ease-in-out;
}

.login-fade-enter-from,
.login-fade-leave-to {
  opacity: 0;
}
.loginHolder {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
}
.material-symbols-outlined {
  font-variation-settings:
  'FILL' 0,
  'wght' 400,
  'GRAD' 0,
  'opsz' 48
}
.loginHolder input {
    width: 90%;
}

.material-symbols-outlined {
    background-color: rgb(218, 215, 213);
    padding: 6.5px;
    font-variation-settings:
        'FILL' 0,
        'wght' 400,
        'GRAD' 0,
        'opsz' 48
}

.wrapper {
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    position: fixed;
    z-index: 2000000;
    background-color: rgb(0, 0, 0, 0.5);
}

.formContainer {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 25%;
    margin-top: 60px;
    height: max-content;
    padding: 1%;
    position: absolute;
    background-color: #ffffff;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}

p{
    color: red;
    text-align: center;
    font-weight: 400;
}

form {
    display: flex;
    flex-direction: column;
    padding: 20px;

}

form button {
    background-color: rgb(218, 215, 213);
    cursor: pointer;
    padding: 10px;
    color: #000000;
    font-weight: 600;
    border: none;
}


input {
    padding: 11px;
    background-color: rgb(218, 215, 213);
    border: none;
    margin-bottom: 10px;
}

h2 {
    font-weight: 900;
    padding: 20px;
    text-align: center;
    margin: 0;
}

.disabled{
    background-color: red;
    color: aliceblue;
    cursor: not-allowed;
}


@media only screen and (max-width: 1200px) {
    .formContainer {
        width: 40%;
    }
}

@media only screen and (max-width: 1200px) and (max-height: 601px) {
    .formContainer {
        width: 40%;
    }
}

@media only screen and (max-width: 900px) and (orientation: portrait) {
    .formContainer {
        width: 50%;
    }
}

@media only screen and (max-width: 550px) and (orientation: portrait) {
    .formContainer {
        width: 90%;
    }
}

@media only screen and (max-width: 281px) and (orientation: portrait) {
    .formContainer {

        width: 100%;
    }
    
}

@media only screen and (max-width: 920px) and (orientation: landscape) {
    .formContainer {

        width: 410px;
    }
}</style>