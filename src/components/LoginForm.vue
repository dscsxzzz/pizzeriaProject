<template>
  <Wrapper v-if="show" @click.stop="renderForm">
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
            <button @click="this.$emit('forgotPassword')" class="formDialogBtn"> Forgot password?</button>
            <button @click="this.$emit('createAccount')" class="formDialogBtn"> Create account</button>
        </div>
    </div>
  </Wrapper>

</template>
<script>
import Wrapper from './Wrapper.vue';

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
            username: "",
          password: "",
            logged2: false,
      logged: false,
      logging: false
        }
  },
  
    methods: {
        async tryLogin() {
      this.logging = true
      const response = await fetch("/users.json")
      const { users } = await response.json()
      console.log(users)
      const foundUser = users.find(
        (user) => user.username === this.username && user.password === this.password
      );
      setTimeout(() => {
        this.logging = false;
      }, 650)
      if (foundUser) {
        this.logged2 = true;
        setTimeout(() => {
          this.logged = true
        }, 1500)
        setTimeout(() => {
          this.$emit("logged", foundUser)
        }, 2500)
      } else {
        setTimeout(() => {
          this.logged = true
        }, 1500)
        setTimeout(() => {
          this.logged = false
        }, 2500)
      }
    },renderForm() { 
        this.$emit('update:show', false)
      }
    }
    
}
</script>
<style scoped>
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
  background-color: rgb(218, 215, 213);
  padding: 6.5px;
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
    background-color: #e68028;
    padding: 10px;
    color: white;
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
        background-color: transparent;
        color: blue;
    }
    button:hover{
        cursor: pointer;
    }
input{
    padding: 10px;
    background-color: #e68028;
    color: white;
    border: none;
  }
  input::placeholder{
    color: white;
    
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