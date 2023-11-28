<template lang="">
    <div class="app">
        <NavBar
        />
        <router-view/>
        <transition name="login-fade">
            <MyOrder
                v-if="store.orderSideBar"
            />
        </transition>
        <transition name="loading-fade" >
            <OrderDeatils
                v-if="store.orderDetails"
                @cleanOrder="cleanOrder"
            />
        </transition>
        <MyFooter/>
    </div>
</template>
<script>
import MyOrder from './components/MyOrder.vue';
import OrderDeatils from './components/OrderDeatils.vue';
import NavBar from './components/NavBar.vue';
import MyFooter from './components/MyFooter.vue';
import ForgotPassword from './components/ForgotPassword.vue';
import { store } from './store/store';
import router from './router';

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
    components: {
        MyOrder, OrderDeatils, NavBar, MyFooter, ForgotPassword
    },
    data() {
        return {
            store
        }
    },
    methods: {
        cleanOrder() {
            store.order = {
                pizzas: [],
                desserts: []
            };
            store.totalPrice = 0
        }
    }
    
}
</script>
<style scoped>
.app{
    min-height: 100svh;
    min-height: 100cqh;
    min-height: 100vh;
}
.login-fade-enter-active,
.login-fade-leave-active {
  transition: opacity 0.8s ease-in-out;
}

.login-fade-enter-from,
.login-fade-leave-to {
  opacity: 0;
}
</style>