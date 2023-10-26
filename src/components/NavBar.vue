<template>
    <transition name="nav-fade" appear>
        <nav>
            <div @click.prevent="this.$emit('myOrder')" href="" class="logo">
                    <span class="material-symbols-outlined">
                        shopping_cart
                    </span>
                    <p v-if="order.pizzas.length !== 0 || order.desserts.length !== 0" class="counter">
                        {{order.desserts.length + order.pizzas.length}}
                    </p>
            </div>
            
            <h4 v-if="!logged" href="" id="user" @click.prevent="this.$emit('login')">My Account</h4>
            <h4 v-else href="" @click.prevent="this.$emit('account')" id="user">{{username}}</h4>
        </nav>
    </transition>
</template>
<script>
export default {
    props: {
        username: {
            type: String,
            required: true
        }, logged :{
            type: Boolean,
            required: true
        }, order: {
            type: Object,
            required: false
        }
    }, data() {
        return {
            disabled: this.order.pizzas.length === 0 && this.order.desserts.length === 0
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
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    text-align: center;
    align-items: center;
    background-color: #e68028;
    padding: 15px;
    min-height: 60px;
    position: sticky;
    z-index: 100001;
    top: 0;

}

h4{
    color: black;
    text-decoration: none;
    cursor: pointer;
}
</style>