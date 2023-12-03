<template>
    <transition name="login-fade" appear>
        <div class="formContainer">
            <aside>
                <button :class="$route.name === 'UserAccount'? 'choosen' : ''" @click.prevent="router.push('/details/account')">Account Details</button>
                <button :class="$route.name === 'UserOrders' ? 'choosen' : ''" @click.prevent="router.push('/details/orders')">Previous Orders</button>
            </aside>
            <div class="main">
                <div class="head">
                    <span></span>
                    <button @click.prevent="router.push('/')">X</button>
                </div>
                <router-view></router-view>
            </div>
        </div>
    </transition>
</template>
<script>
import UserOrders from '../components/UserOrders.vue';
import UserAccount from '../components/UserAccount.vue';
import { store } from '../store/store';
import router from '../router';

function isLoggedIn() {
    return store.logged
}

router.beforeEach((to, from) => {
    if (to.meta.requiresAuth && !isLoggedIn()) {
        return {
            path: '/',
        }
    }
})


export default {
    components: { UserOrders, UserAccount },
    data() {
        return {
            store,
            router
        }
    }
}
</script>
<style scoped>
.formContainer{
    display: grid;
    grid-template-columns: 1fr 10fr;
    min-height: 100svh;
    min-height: 100cqh;
    min-height: 100vh;
    width: 100%;
    background-color: white;
    transition: all 0.2s;
}

.head{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-bottom: 10px;
}
.main{
    display: flex;
    flex-direction: column;
    padding: 10px 5% 10px 5%;
    height: 100svh;
    height: 100cqh;
    transition: all 0.2s;
}

.choosen{
    background-color: black;
    color: white;
}
aside{
    padding: 10px;
    background-color: rgb(241, 237, 232);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    transition: all 0.2s;
}
button{
    border: none;
    padding: 10px;
    margin-bottom: 20px;
    max-width: 100px;
    font-weight: 600;
    background-color: #e68028;
    color: white;
    border-radius: 5px;
    box-shadow: 0px 0px 10px black;
    transition: all 0.2s ease-in;
}

button:hover{
    background-color: black;
    cursor: pointer;
}
@media only screen and (max-width: 550px) {
  .formContainer{
    display: flex;
    flex-direction: column;
  }
  aside{
    flex-direction: row;
    justify-content: center;
    align-items: center;
  }
  aside button{
    margin-left: 5px;
    align-self: center;
  }
  button{
    margin-bottom: 0;
  }
}
</style>