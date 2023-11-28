<template lang="">
    <transition name="login-fade">
        <Wrapper @click.stop="renderForm">
            <div @click.stop class="formContainer">
                <div class="head">
                    <h2>Change Password</h2>
                    <button class="rndr" @click="renderForm">X</button>
                </div>
                <h2>{{message}}</h2>
                <form @submit.prevent="tryChangePass">
                    <input type="password" name="password" v-model="password"  placeholder="Password">
                    <input type="password" name="password" v-model="newPassword" @input='validatePassword' placeholder="New Password">
                    <input type="password" name="repeatpassword" v-model="repeatPassword" @input='validatePassword' placeholder="Repeat New Password">
                    <p>{{messageForm}}</p>
                    <button type="submit" :class="error? 'disabled':'' " :disabled="error">Save Changes</button>
                </form>
            </div>
        </Wrapper>
    </transition>
</template>
<script>
import Wrapper from './Wrapper.vue';
import { store } from '../store/store';
import router from '../router';
import baseUrl from '../config.json'
export default {
    components: {
        Wrapper
    }, data() {
        return {
            store,
            password: "",
            newPassword: "",
            repeatPassword: "",
            messageForm: "",
            message: "",
            error: false
        }
    },
    methods: {
        renderForm() {
            router.push("/")
        }, async tryChangePass() {
           
            const body = {
                "username": store.user.username,
                "password": this.password
            }
            const jsonBody1 = JSON.stringify(body);
            console.log(jsonBody1)
            const response1 = await fetch(`${baseUrl.baseUrl}/login`, {
                method: 'POST',
                headers: {
                    Accept: 'application.json',
                    'Content-Type': 'application/json'
                },
                body: jsonBody1,
                cache: 'default'
            })
            if (response1.status === 200) {
                const user = {
                    id: store.user.id,
                    username: store.user.username,
                    email: store.user.email,
                    name: store.user.name,
                    surname: store.user.surname,
                    address: store.user.address,
                    phone: store.user.phone,
                    password: this.newPassword
                }
                const jsonBody = JSON.stringify(user)
                const response = await fetch(`${ baseUrl.baseUrl }/login/${user.id}`, {
                    method: 'PUT',
                    headers: {
                        Accept: 'application.json',
                        'Content-Type': 'application/json'
                    },
                    body: jsonBody,
                    cache: 'default'
                })
                if (response.status === 204) {
                    this.message = "Success"
                    setTimeout(() => {
                        this.renderForm()
                    }, 1000)
                } else {
                    this.message = "Something went wrong"
                }
            } else {
                this.message = "Something went wrong"
            }
        }, async validatePassword() {
            if (this.newPassword.length < 8) {
                this.messageForm = "Password is too short";
                this.error = true;
                return 0;
            }
            let big = true;
            let number = true;
            for (let i = 0; i < this.newPassword.length; i++) {
                if (Number(this.newPassword[i])) {
                    number = false;
                }
                else if (this.newPassword[i] === this.newPassword[i].toUpperCase()) {
                    big = false;
                }
            }
            if (big) {
                this.messageForm = "Password does not include upper case character";
                this.error = true;
                return 0;
            }
            else if (number) {
                this.messageForm = "Password does not include number";
                this.error = true;
                return 0;
            }
            else {
                this.messageForm = "";
                this.error = false;
                this.validatePassword2();
                return 0;
            }
            
        },
        async validatePassword2() {
            if (this.newPassword !== this.repeatPassword) {
                this.messageForm = "Passwords do not match";
                this.error = true;
            }
            else {
                this.messageForm = "";
                this.error = false;
            }
        }
    }
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
.head{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
}

.rndr{
    width: 20px;
    height: 20px;
    border: none;
    background-color: #000000;
    color: white;
    cursor: pointer;
}
    
.formContainer{
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 30%;
    max-height: max-content;
    padding: 1%;
    position: absolute;
    background-color: white;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
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
    transition: all .5s;
}



input{
    padding: 10px;
    background-color: rgb(218, 215, 213);
    margin-bottom: 5px;
    color: rgb(0, 0, 0);
    border: none;
  }
  input::placeholder{
    color: rgb(0, 0, 0);
    
}

p{
    color: red;
    text-align: center;
    font-weight: 400;
    margin-top: 10px;
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
}
</style>