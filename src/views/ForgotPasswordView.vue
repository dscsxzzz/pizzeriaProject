<template>
    <transition name="login-fade" appear>
        <Wrapper>
            <div @click.stop class="formContainer">
                <div class="head">
                    <h2>Change Password</h2>
                    <button class="rndr" @click="$router.push('/login')">X</button>
                </div>
                <router-view/>
            </div>
        </Wrapper>
    </transition>
</template>
<script>
import Wrapper from "../components/Wrapper.vue";
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
            error: false,
            store,
            router,
        }
    }, methods: {
        async tryGetCode() {
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
                    store.user.id = result.id
            }
            else this.message = "Something went wrong, try one more time"


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

        },
    }
}
</script>
<style scoped>
.head {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    width: 90%;
}


.formContainer {
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



.rndr {
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

form button:hover,
.rndr:hover {
    background-color: #000000;
    color: antiquewhite;
}

input {
    padding: 10px;
    background-color: rgb(218, 215, 213);
    margin-bottom: 5px;
    color: rgb(0, 0, 0);
    border: none;
}

input::placeholder {
    color: rgb(0, 0, 0);

}

.disabled {
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

p {
    color: red;
    text-align: center;
    font-weight: 400;
    margin-top: 10px;
    margin-bottom: 10px;
}
</style>