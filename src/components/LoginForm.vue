<template>
  <transition name="login-fade" appear>
    <Wrapper @click.stop="$router.push('/')">
      <div @click.stop class="formContainer" >
          <h2>User Login</h2>
          <transition name="loading-fade">
            <div v-if="logging" class="lds-ellipsis"><div></div><div></div><div></div><div></div></div>
          </transition>
        <transition  name="loading-fade">
          <transition name="loading-fade" v-if="logged">
              <h2 v-if="logged2" >Welcome, {{ username }}!</h2>
              <h2 v-else >Incorrect password or username!</h2>
          </transition>
        </transition>
          <form @submit.prevent="tryLogin">
            <div class="loginHolder">
              <span class="material-symbols-outlined">
                login
              </span>
              <input 
                type="text"
                name="login" 
                :value="username" 
                id="" 
                placeholder="Login"
                @change="username = $event.target.value"
              >
            </div>
            <div class="loginHolder">
              <span class="material-symbols-outlined">
                password
              </span>
              <input type="password" 
               name="password"
               :value="password"
               placeholder="Password"
               @change="password = $event.target.value"
              >
            </div>
              <button type="submit">Log In</button>
          </form>
          <div class="btns">
              <button @click="$router.push('/forgot-password')" class="formDialogBtn"> Forgot password?</button>
              <button @click="$router.push('/register')" class="formDialogBtn"> Create account</button>
          </div>
      </div>
    </Wrapper>
  </transition>

</template>
<script>
import Wrapper from './Wrapper.vue';
import router from '../router';
import { store } from '../store/store';
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
      password: "",
      logged2: false,
      logging: false,
      logged: false,
      store
    }
  },
  methods: {
    async tryLogin() {
      this.logging = true
      const body = {
        "username": this.username,
        "password" : this.password
      }
      const jsonBody = JSON.stringify(body);
      console.log(jsonBody)
      const response = await fetch(`${baseUrl.baseUrl}/auth/login`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json'
        },
        body: jsonBody,
        cache: 'default'
      })
      const user = await response.json()
      console.log(user)
      if (response.status === 200) {
        setTimeout(() => {
          this.logging = false;
        }, 650)
        this.logged2 = true;
        setTimeout(() => {
          this.logged = true
        }, 1500)
        setTimeout(() => {
          store.user = user
          store.logged = true
          router.push("/")
        }, 2500)
      }
      else {
        setTimeout(() => {
          this.logging = false;
        }, 650)
        setTimeout(() => {
          this.logged = true
        }, 1500)
        setTimeout(() => {
          this.logged = false
        }, 2500)
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
.loginHolder{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}
.loginHolder input{
width: 90%;
}
.material-symbols-outlined {
  font-variation-settings:
  'FILL' 0,
  'wght' 400,
  'GRAD' 0,
  'opsz' 48
}
.loading-fade-enter-active,
.loading-fade-leave-active {
  transition: opacity 0.8s ease-in-out;
}

.loading-fade-enter-from,
.loading-fade-leave-to {
  opacity: 0;
}

.loading-fade-enter-active .inner,
.loading-fade-leave-active .inner{
  transition: opacity 0.8s ease-in-out;
}

.loading-fade-enter-from .inner,
.loading-fade-leave-to .inner {
  opacity: 0;
}



.formContainer{
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 25%;
    max-height: max-content;
    padding: 1%;
    position: absolute;
    background-color: white;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}
form{
    display: flex;
    flex-direction: column;
    padding: 20px;
    margin-top: 10px;
    
}
form button{
    background-color: rgb(218, 215, 213);;
    padding: 10px;
    color: rgb(0, 0, 0);
    font-weight: 600;
}
.btns{
    display: flex;
    text-align: center;
    justify-content: space-around;
    padding: 10px;
}
    button{
        border: none;
        background-color: rgb(218, 215, 213);
        color: rgb(0, 0, 0);
        transition: all 0.5s;
    }
    button:hover{
      background-color: black;
      color: antiquewhite;
        cursor: pointer;
    }
input{
    padding: 10px;
    background-color: rgb(218, 215, 213);
    
    color: rgb(0, 0, 0);
    border: none;
  }
  input::placeholder{
    color: rgb(0, 0, 0);
    
}
h2{
  text-align: center;
  padding-top: 15px;
  padding-bottom: 15px;
}
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