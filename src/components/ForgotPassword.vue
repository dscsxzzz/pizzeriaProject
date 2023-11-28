<template>
    <transition name="login-fade" appear>
            <div @click.stop class="formContainer">
                <div class="head">
                    <h2>Change Password</h2>
                    <button class="rndr" @click="renderForm">X</button>
                </div>
                <h2>{{ message }}</h2>
                <transition v-if="!canChangePassword" name="login-fade" appear>
                    <form v-if="!gotCode" @submit.prevent="tryGetCode">
                        <input type="text" name="username" v-model="username"  placeholder="Username">
                        <button type="submit">Send Me Email</button>
                        <button type="button" @click="gotCode = true">I have code</button>
                    </form>
                    <form v-else @submit.prevent="tryConfirmCode">
                        <input type="text" name="Code" v-model="code"  placeholder="Code">
                        <button type="submit" @click="canChangePassword = true">Send Code</button>
                        <button type="button" @click="gotCode = false">I do not have code</button>
                    </form>
                </transition>
                <transition v-else name="login-fade" appear>
                    <form @submit.prevent="tryChangePass">
                        <input type="password" name="NewPassword" v-model="newPassword"  placeholder="New Password">
                        <input type="password" name="RepeatPassword" v-model="repeatPassword"  placeholder="Repeat Password">
                        <button type="submit">Change Password</button>
                    </form>
                </transition>
            </div>
    </transition>
</template>
<script>
import Wrapper from "./Wrapper.vue";
import baseUrl from "../config.json";
import { store } from "../store/store";
import router from "../router"
export default {
    components: {
        Wrapper
    },
    data() {
        return {
            username: "",
            id: undefined,
            gotCode: false,
            canChangePassword: false,
            newPassword: "",
            repeatPassword: "",
            message: "",
            code: "",
            store,
            router,
            message: ""
        }
    }, methods: {
        async tryGetCode(){
            const response = await fetch(`${baseUrl.baseUrl}/auth/forgot-password/${this.username}`, {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                cache: 'default'
            });
            const result = await response.json()
            console.log(result);
            if (response.status === 200) {
                this.message = result.message,
                this.id = result.id
            }
            else this.message = "Somethimg went wrong, try one more time"


        }, async tryConfirmCode() {
            const response = await fetch(`${baseUrl.baseUrl}/auth/forgot-password-code/${this.code}`, {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                cache: 'default'
            });
            const result = await response.json()

            if (response.status === 200) this.message = result.message

        }, async tryChangePass() {
            const jsonBody = JSON.stringify({
                username: this.username,
                password: this.newPassword
            })
            const response = await fetch(`${baseUrl.baseUrl}/user/change-pass/${this.id}`, {
                method: 'PUT',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                body: jsonBody,
                cache: 'default'
            });
            const result = await response.json()
            if (response.status === 200) {
                this.message = result.message
                setTimeout(() => {
                    router.push("/login")  
                }, 1500)
            }
            this.message = result.message



        }
    }
}
</script>
<style scoped>

.head{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    width: 50%;
}

    
.formContainer{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100svh;
    height: 100cqh;
    padding: 1%;
    background-color: white;
}

form {
    display: flex;
    flex-direction: column;
    padding: 20px;
    width: 50%;
}

form button {
    background-color: rgb(218, 215, 213);
    cursor: pointer;
    padding: 10px;
    color: #000000;
    font-weight: 600;
    border: none;
    transition: all .5s;
    margin-top: 10px;
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



h2 {
    font-weight: 900;
    padding: 20px;
    text-align: center;
    margin: 0;
}
</style>