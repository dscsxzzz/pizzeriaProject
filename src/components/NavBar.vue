<template>
    <transition name="nav-fade" appear>
        <nav>
            <div @click.prevent="store.orderSideBar = true" href="" class="logo">
                    <span class="material-symbols-outlined">
                        shopping_cart
                    </span>
                    <p v-if="store.order.pizzas.length !== 0 || store.order.desserts.length !== 0" class="counter">
                        {{store.order.desserts.length + store.order.pizzas.length}}
                    </p>
            </div>
            <h4 @click.prevent="$router.push('/')">MENU</h4>
            <h4 v-if="!store.logged" href="" id="user" @click.prevent="$router.push('/login')">My Account</h4>
            <h4 v-else href="" @click.prevent="$router.push('/details/account')" id="user">{{store.user.username}}</h4>
        </nav>
    </transition>
</template>
<script>
import { store } from '../store/store';
import router from '../router';
export default {
    data() {
        return {
            disabled: store.order.pizzas.length === 0 && store.order.desserts.length === 0,
            store,
            router
        }
    }
    
}
</script>
<style scoped>
    .nav-fade-enter-from,
.nav-fade-leave-to{
    transform: translate(500px, 0);
    opacity: 0;
}

.counter{
    border-radius: 100%;
    height: 20px;
    width: 20px;
    background-color: rgb(255, 0, 0);
    color: white;
    font-weight: 600;
    text-align: center;
    transform: translate(-10px, 20px);
}

.nav-fade-enter-active,
.nav-fade-leave-active{
    transition: all 1s ease-in-out;
}

.material-symbols-outlined {
    color: rgb(0, 0, 0);
    background-color: white;
    padding: 5px;
    border-radius: 5px;
    transition: .5s;
  font-variation-settings:
  'FILL' 0,
  'wght' 400,
  'GRAD' 0,
  'opsz' 48
}

.material-symbols-outlined:hover {
    cursor: pointer;
    color: rgb(255, 255, 255);
    background-color: rgb(0, 0, 0);
    padding: 5px;
  font-variation-settings:
  'FILL' 0,
  'wght' 400,
  'GRAD' 0,
  'opsz' 48
}
.logo{
    justify-self: flex-start;
    display: flex;
    flex-direction: row;
}
nav{
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    background-color: #e68028;
    padding: 15px;
    min-height: 60px;
    position: sticky;
    z-index: 100001;
    top: 0;
}


h4{
    color: rgb(0, 0, 0);
    border-radius: 5px;
    background-color: rgb(255, 255, 255);
    min-width: 80px;
    max-width: max-content;
    font-weight: 600;
    box-shadow: 0 0 15px rgba(0, 0, 0, 0.438);
    padding: 8px;
    text-decoration: none;
    justify-self: center;
    align-self: center;
    text-align: center;
    cursor: pointer;
    transition: all 0.2s ease-in;
}

h4:hover{
    background-color: #e68028;
    color: azure;
}

#user{
    justify-self: flex-end;
}
</style>