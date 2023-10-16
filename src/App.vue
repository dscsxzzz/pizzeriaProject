<template lang="">
    <div>
        <NavBar
        @login="visibleFORM = true"
        @myOrder="visibleORDER = true"
        :logged="logged"
        :order="order"
        :username="username"
        @pizzas="content = 'pizzas'"
        @desserts="content = 'desserts'"
        />
    <transition name="menu-transition" appear>

        <div class="mainContainer">
            <Banner/>
            <Menu
            v-if="pizzas.length !== 0"
            :pizzas="pizzas"
            :desserts="desserts"
            @addToCart="addToCart"
            @addToCartDessert="addToCartDessert"
            :content="content"
            />
            
        </div>
    </transition>
        <transition name="login-fade">
            <LoginForm
            v-model:show="visibleFORM"
            @logged="setUser"
            @createAccount="visibleFORM = false; createAccount = true"
            />
        </transition>
        <transition name="login-fade">
            <CreateAccount
            v-model:show="createAccount"
            />
        </transition>

        <MyOrder
        v-if="visibleORDER"
        @decrement="decrementOrder"
        @increment="incrementOrder"
        :order="order"
        :totalPrice="totalPrice"
        @renderOrder="visibleORDER = !visibleORDER"
        @orderDetails="orderDeatilsShow = true"
        />
        
        <OrderDeatils
        v-model:show="orderDeatilsShow"
        @cleanOrder="cleanOrder"
        :user="user"
        :order="order"
        :totalPrice="totalPrice"
        />
    </div>
</template>
<script>
import LoginForm from './components/LoginForm.vue';
import NavBar from './components/NavBar.vue';
import Menu from './components/Menu.vue';
import MyOrder from './components/MyOrder.vue';
import CreateAccount from './components/CreateAccount.vue';
import OrderDeatils from './components/OrderDeatils.vue';
import Banner from './components/Banner.vue';
export default {
    components: {
        LoginForm, NavBar, Menu, MyOrder, CreateAccount, OrderDeatils, Banner
    },
    data() {
        return {
            visibleFORM: false,
            user: {
                email: "",
                name: "",
                surname: "",
                address: "",
                phone: ""
            },
            username: "My Order",
            pizzas: [],
            desserts: [],
            order: {
                pizzas: [],
                desserts: []
            },
            content: "pizzas",
            logged: false,
            createAccount: false,
            visibleORDER: false,
            totalPrice: 0,
            orderDeatilsShow: false
        }
    },
    methods: {
        cleanOrder() {
            this.order = {
                pizzas: [],
                desserts: []
            } 
          this.totalPrice = 0
        },
        setUser(user) {
            this.visibleFORM = false
            this.user = {
                email: user.email,
                name: user.name,
                surname: user.surname,
                address: user.address,
                phone: user.phone,
            }
            this.username = user.username
            this.logged = true
        },
        async getPizzas() {
            const res = await fetch("/pizzas.json")
            const pizzas = await res.json()
            for (let i = 0; i < pizzas.length; i++){
                this.pizzas.push(pizzas[i])
            }
        },
        async getDesserts() {
            const res = await fetch("/desserts.json")
            const desserts = await res.json()
            for (let i = 0; i < desserts.length; i++) {
                this.desserts.push(desserts[i])
            }
        },
        addToCart(pizza, amount,price, size, type) {
            for (let i = 0; i < this.order.pizzas.length; i++) {
                if (this.order.pizzas[i].pizza.name === pizza.name) {
                    if (this.order.pizzas[i].size === size) {
                        this.order.pizzas[i].amount += amount;
                        this.totalPrice += price;
                        return 0;
                    } 
                } 
            }
            this.order.pizzas.push({
                "pizza": pizza,
                "amount": amount,
                "price" : price,
                "size": size
            })
            this.totalPrice += price * amount
        },
        addToCartDessert(dessert, amount, price, type) {
            for (let i = 0; i < this.order.desserts.length; i++) {
                if (this.order.desserts[i].dessert.name === dessert.name) {
                        this.order.desserts[i].amount += amount;
                        this.totalPrice += price;
                        return 0;
                }
            }
            this.order.desserts.push({
                "dessert": dessert,
                "amount": amount,
                "price": price,
            })
            this.totalPrice += price * amount
        }
        , decrementOrder(position, type) {
            if (type === 'pizza') {
                for (let i = 0; i < this.order.pizzas.length; i++){
                    if (this.order.pizzas[i].pizza.name === position.pizza.name) {
                        if (this.order.pizzas[i].size === position.size) {
                            if (this.order.pizzas[i].amount - 1 !== 0) {
                                this.order.pizzas[i].amount -= 1
                                this.totalPrice -= position.price
                            } else {
                                this.totalPrice -= position.price
                                this.order.pizzas.splice(i, 1)
                            }
                        }
                    }
                }
            } else {
                for (let i = 0; i < this.order.desserts.length; i++) {
                    if (this.order.desserts[i].dessert.name === position.dessert.name) {
                            if (this.order.desserts[i].amount - 1 !== 0) {
                                this.order.desserts[i].amount -= 1
                                this.totalPrice -= position.price
                            } else {
                                this.totalPrice -= position.price
                                this.order.desserts.splice(i, 1)
                            }
                        }
                    }
                }
            
        }, incrementOrder(position, type) {
            if (type === 'pizza') {
                for (let i = 0; i < this.order.pizzas.length; i++) {
                    if (this.order.pizzas[i].pizza.name === position.pizza.name) {
                        if (this.order.pizzas[i].size === position.size) {
                            this.order.pizzas[i].amount += 1
                            this.totalPrice += position.price
                            return 0;
                        }
                    }
                }
            } else {
                for (let i = 0; i < this.order.desserts.length; i++) {
                    if (this.order.desserts[i].dessert.name === position.dessert.name) {
                        if (this.order.desserts[i].size === position.size) {
                            this.order.desserts[i].amount += 1
                            this.totalPrice += position.price
                            return 0;
                        }
                    }
                }
            }
        }
    },
    mounted() {
        this.getPizzas()
        this.getDesserts()
    },
    
}
</script>
<style>

.menu-transition-enter-from,
.menu-transition-leave-to{
    transform: translate(0, -1000px);
}


.menu-transition-enter-active,
.menu-transition-leave-active{
    transition: all 2s ease-in-out;
}

    .login-fade-enter-active,
.login-fade-leave-active {
  transition: opacity 0.8s ease-in-out;
}

.login-fade-enter-from,
.login-fade-leave-to {
  opacity: 0;
}

.mainContainer{
    display: flex;
    flex-direction: column;
    align-items: center;
}

</style>