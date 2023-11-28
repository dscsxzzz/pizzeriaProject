<template>
    <transition name="login-fade" appear>
        <form v-if="!gotCode" @submit.prevent="tryGetCode">
            
            <h2>{{message}}</h2>
            <input type="text" name="username" v-model="username"  placeholder="Username">
            <button type="submit">Send Me Email</button>
            <button type="button" @click="gotCode = true">I have code</button>
        </form>
        <form v-else @submit.prevent="tryConfirmCode">
            
            <h2>{{ message }}</h2>
            <input type="text" name="Code" v-model="code"  placeholder="Code">
            <button type="submit">Send Code</button>
            <button type="button" @click="gotCode = false">I do not have code</button>
        </form>
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
            gotCode: false,
            canChangePassword: false,
            message: "",
            code: "",
            store,
            router,
        }
    }, methods: {
        async tryGetCode() {
            store.logging = true;
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
                this.gotCode = true,
                store.user.id = result.id
            }
            else this.message = "Something went wrong, try one more time"
            store.logging = false



        }, async tryConfirmCode() {
            store.logging = true;
            const response = await fetch(`${baseUrl.baseUrl}/auth/forgot-password-code/${this.code}`, {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                cache: 'default'
            });
            const result = await response.json()
            if (response.status === 200) {
                this.message = result.message
                setTimeout(() => {
                    store.logging = false
                    router.push("/forgot-password/change-password")
                }, 500)
            }


        }, 
    }
}
</script>
<style scoped>

.head{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    width: 90%;
}

    
.formContainer{
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    width: 50%;
    height: 50svh;
    height: 50cqh;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    padding: 1%;
    background-color: white;
}

form {
    display: flex;
    flex-direction: column;
    width: 90%;
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



.rndr{
    width: 20px;
    height: 20px;
    border: 1px solid black;
    border-radius: 10%;
    text-align: center;
    background-color: white;
    color: black;
    cursor: pointer;
    transition: all .5s;
}

form button:hover, .rndr:hover{
    background-color: #000000;
    color: antiquewhite;
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

.disabled{
    background-color: red;
    color: aliceblue;
    cursor: not-allowed;
}

h2 {
    font-weight: 900;
    padding: 20px;
    text-align: center;
    margin: 0;
}

p{
    color: red;
    text-align: center;
    font-weight: 400;
    margin-top: 10px;
    margin-bottom: 10px;
}
</style>