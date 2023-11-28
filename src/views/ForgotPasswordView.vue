<template>
    <transition name="login-fade" appear>
        <Wrapper @click.stop="$router.push('/login')">
            <div @click.stop class="formContainer">
                <div class="head">
                    <h2>Change Password</h2>
                    <button class="rndr" @click="$router.push('/login')">X</button>
                </div>
                <transition name="loading-fade" appear>
                    <div v-if="store.logging" class="lds-ellipsis"><div></div><div></div><div></div><div></div></div>
                  </transition>
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

.lds-ellipsis {
  display: inline-block;
  align-self: center;
  margin-left: -80px;
  justify-content: center;
  align-content: center;
  position: relative;
  width: 0px;
  height: 0px;
}
.lds-ellipsis div {
  position: absolute;
  top: 0px;
  width: 13px;
  height: 13px;
  border-radius: 50%;
  background: #283618;
  animation-timing-function: cubic-bezier(0, 1, 1, 0);
}
.lds-ellipsis div:nth-child(1) {
  left: 8px;
  animation: lds-ellipsis1 0.6s infinite;
}
.lds-ellipsis div:nth-child(2) {
  left: 8px;
  animation: lds-ellipsis2 0.6s infinite;
}
.lds-ellipsis div:nth-child(3) {
  left: 32px;
  animation: lds-ellipsis2 0.6s infinite;
}
.lds-ellipsis div:nth-child(4) {
  left: 56px;
  animation: lds-ellipsis3 0.6s infinite;
}
@keyframes lds-ellipsis1 {
  0% {
    transform: scale(0);
  }
  100% {
    transform: scale(1);
  }
}
@keyframes lds-ellipsis3 {
  0% {
    transform: scale(1);
  }
  100% {
    transform: scale(0);
  }
}
@keyframes lds-ellipsis2 {
  0% {
    transform: translate(0, 0);
  }
  100% {
    transform: translate(24px, 0);
  }
}
.head {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    padding: 20px;
}


.formContainer {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    width: 50%;
    min-height: 400px;
    height: max-content;
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
    width: 100%;
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



@media only screen and (max-width: 1200px) {
  .formContainer {
    width: 40%;
  }
}
@media only screen and (max-width: 1200px) and (max-height: 601px)  {
  .formContainer {
    width: 40%;
  }
}

@media only screen and (max-width: 900px) and (orientation: portrait)  {
  .formContainer {
    width: 50%;
  }
}

@media only screen and (max-width: 550px) and (orientation: portrait)  {
  .formContainer {
    width: 90%;
  }
}

@media only screen and (max-width: 281px) and (orientation: portrait) {
  .formContainer {
    
    width: 100%;
  }
  .btns{
    flex-direction: column;
    justify-content: space-between;
  }
}
@media only screen and (max-width: 920px) and (orientation: landscape) {
  .formContainer {
    
    width: 410px;
  }
  .btns{
    flex-direction: row;
    justify-content: space-around;
  }
}
</style>