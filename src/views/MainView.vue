<template lang="">
    <transition name="menu-transition" appear>
        <div class="mainContainer">
            <Banner/>
            <div class="chooseMenu" v-if="originalPizzas.length !== 0">
                <div :class="content === 'pizzas' ? 'choosen' : 'chooser'">
                    <img 
                        v-if="originalPizzas.length != 0" 
                        :src="originalPizzas[1].img"
                        @click.prevent="handleMenuChangeToPizza"
                        :class="'chooserimg'"
                        alt="Pizzas"
                    >
                    <h4>Pizzas</h4>
                </div>
                <div :class="content === 'desserts' ? 'choosen' : 'chooser'" >
                    <img 
                        :class="'chooserimg'"
                        v-if="desserts.length != 0" 
                        :src="desserts[1].img" 
                        @click.prevent="handleMenuChangeToDesserts"
                        alt="Desserts"
                    >
                    <h4>Desserts</h4>
                </div>
            </div>
            <PizzaFilter v-if="content === 'pizzas' && originalPizzas.length !== 0" @filterPizzas="handleFilterPizzas"/>

            <Menu
                v-if="originalPizzas.length !== 0"
                :pizzas="pizzas"
                :desserts="desserts"
                @addToCart="this.emit$('addToCart', pizza, amount, price, size, type)"
                @addToCartDessert="this.$emit('addToCartDessert' , dessert, amount, price, type)"
                :content="content"
            />
            <div v-else class="loader">
                <h1>Loading</h1> <div class="lds-ellipsis"><div></div><div></div><div></div><div></div></div>
            </div>
            <div v-if="pizzas.length === 0" style="height: 50vh;">
                <h1 style="dispal: flex; margin-top: 20px;">Nothing To Display</h1>
            </div>
        </div>
    </transition>
</template>
<script>
import NavBar from '../components/NavBar.vue';
import baseUrl from "../config.json"
import Menu from '../components/Menu.vue';
import Banner from '../components/Banner.vue';
import PizzaFilter from '../components/PizzaFilter.vue';
export default {
    components: {
        Menu, Banner, NavBar, PizzaFilter
    },
    data() {
        return {
            pizzas: [],
            desserts: [],
            content: "pizzas",
            originalPizzas: []
        }
    },
    methods: {
        
        async getPizzas() {
            const res = await fetch(`${baseUrl.baseUrl}/pizzas`)
            const pizzas = await res.json()
            console.log(pizzas)
            for (let i = 0; i < pizzas.length; i++) {
                this.pizzas.push(pizzas[i])
            }
            this.originalPizzas = pizzas;
        },
        async getDesserts() {
            const res = await fetch(`${baseUrl.baseUrl}/desserts`)
            const desserts = await res.json()
            for (let i = 0; i < desserts.length; i++) {
                this.desserts.push(desserts[i])
            }
        }, handleMenuChangeToPizza() {
            this.content = "pizzas";
            window.scrollTo({
                top: 0,
                behavior: 'smooth',
            })
        }, handleMenuChangeToDesserts() {
            this.content = "desserts";
            window.scrollTo({
                top: 0,
                behavior: 'smooth',
            })
        }, handleFilterPizzas(selectedIngredients) {
            this.pizzas = [...this.originalPizzas];

            // Implement the logic to filter pizzas based on selected ingredients
            if (selectedIngredients.length > 0) {
                this.pizzas = this.pizzas.filter(pizza => {
                    // Implement your filtering logic here, for example:
                    return selectedIngredients.every(ingredient => pizza.description.includes(ingredient));
                });
            }
        }
    }, mounted() {
        this.getDesserts();
        this.getPizzas();
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

.menu-transition-enter-from,
.menu-transition-leave-to {
    transform: translate(0, -1000px);
}


.menu-transition-enter-active,
.menu-transition-leave-active {
    transition: all 2s ease-in-out;
}

.chooseMenu {
    display: flex;
    flex-direction: row;
    width: 80%;
    max-width: 300px;
    justify-content: space-between;
    align-items: center;
    text-align: center;
}
.chooser{
    display: flex;
    flex-direction: column;
    padding: 10px;
    width: 100px;
    height: 150px;
    box-shadow: 0 10px 15px rgba(211,209,216,0.251);
    background-color: white;
    border-radius: 30px;
    align-items: center;

}
.choosen{
    display: flex;
    flex-direction: column;
    padding: 10px;
    width: 100px;
    height: 150px;
    box-shadow: 0 10px 15px rgba(74, 73, 75, 0.637);
    border-radius: 30px;
    align-items: center;
    background-color: #e68028;
}
.chooserimg {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    margin-bottom: 30px;
    box-shadow: 0 0px 15px rgba(84, 83, 85, 0.295);
    border: 5px solid rgba(255, 255, 255, 0.295);
    cursor: pointer;
}

.mainContainer {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.loader {
    margin-top: 20px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
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
    background: #e68028;
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
}</style>