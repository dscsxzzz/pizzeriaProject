<template>
    <Wrapper  @click.stop="renderForm" class="wrapper">
        <transition appear name="order"  mode="out-in">
            <div v-if="close" @click.stop class="formContainer">
                <div class="orderHeader">
                    <h1>Order</h1>
                    <a @click.prevent="renderForm">X</a>
                </div>
                <div class="orderItems">
                    <OrderItem v-for="position in store.order.pizzas"
                    :position="position"
                    :type="'pizza'"
                    />
                    <OrderItem v-for="position in store.order.desserts"
                    :position="position"
                    :type="'dessert'"
                    />
                </div>
                <div class="orderManage">
                    <h3 class="totalPrice">Total price: {{store.totalPrice * 40}} UAH</h3>
                    <button 
                    @click="store.orderDetails = true"
                    >Order</button>
                </div>
            </div>
        </transition>
    </Wrapper>
</template>
<script>
import OrderItem from './OrderItem.vue';
import Wrapper from './Wrapper.vue';
import { store } from '../store/store';
export default {
    emits: ['decrement', 'increment', 'orderDetails', 'renderOrder'],
    components: {
      OrderItem, Wrapper
    },
    data() {
        return {
            close: {
                default: true
            },
            store

        }
    },
     methods: {
        renderForm() {
            this.close = false;
            setTimeout(() => {
                store.orderSideBar = false;
            }, 200);
        }
    }
    
    
}
</script>
<style scoped>
.order-enter-active,
.order-leave-active{
    transition: all .5s ease-in-out;
}
.order-enter-from,
.order-leave-to{
    transform: translate(-500px, 0);
}



.formContainer{
    display: flex;
    flex-direction: column;
    overflow-y: scroll;
    justify-content: space-between;
    width: 25%;
    height: 100%;
    padding: 1%;
    background-color: white;
    text-align: center;
}


.orderItems{
    overflow-y: auto;
    border-radius: 5px;
    margin-top: 10px;
    height: 70%;
}
.orderHeader{
    display: flex;
    flex-direction: row;
    padding: 10px;
    max-width: 100%;
    align-items: center;
    justify-content: space-between;
}
a{
    font-weight: 900;
    border-radius: 2px;
    padding: 10px 15px 10px 15px;
    background-color: #e68028;
    text-decoration: none;
    color: #fefae0;
    text-align: center;
    transition: all 0.2s ease-in;
}

a:hover{
    background-color: black;
    color: #fefae0;
    cursor: pointer;
}

.orderManage{
    justify-self: flex-end;
    display: flex;
    flex-direction: row;
    text-align: center;
    justify-content: space-between;
    margin-top: 10px;
}
.totalPrice{
    padding: 10px;
}
.orderManage button{
    border: none;
    border-radius: 2px;
    background-color: #e68028;
    color: #fefae0;
    font-weight: 600;
    height: 40px;
    padding: 10px;
    cursor: pointer;
    transition: all 0.2s ease-in;
}

.orderManage button:hover{
    background-color: #000000;
    color: #fefae0;
}

@media only screen and (max-width: 1300px) {
    .formContainer{
        width: 50%;
    }
}

@media only screen and (max-width: 1200px) and (max-height: 601px)  {
    .formContainer{
        width: 50%;
    }
}

@media only screen and (max-width: 920px)and (max-height: 1400px) and (orientation: portrait)  {
    .formContainer{
        width: 50%;
    }
}
@media only screen and (max-width: 920px) and (orientation: portrait)  {
    .formContainer{
        width: 50%;
    }
}

@media only screen and (max-width: 550px) and (orientation: portrait)  {
    .formContainer{
        width: 100%;
    }
}

@media only screen and (max-width: 281px) and (orientation: portrait) {
    .formContainer{
        width: 100%;
    }
}
@media only screen and (max-width: 920px) and (orientation: landscape) {
    .formContainer{
        width: 100%;
    }
}
</style>