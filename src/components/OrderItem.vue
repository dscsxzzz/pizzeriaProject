<template>
    <div v-if="type === 'pizza'" class="item">
        <img :src="position.pizza.img" alt="">
        <div class="positionDescription">
            <p>{{ position.pizza.name }}</p>
            <p>{{position.size}}</p>
        </div>
        <div class="btns">
            <div class="amount">
                <button @click="store.decrementOrder(position, 'pizza')">-</button>
                <p>{{ position.amount }}</p>
                <button @click="store.incrementOrder(position, 'pizza')">+</button>
            </div>
            <p>{{ position.price * position.amount * 40}} UAH</p>
        </div>
    </div>
    <div v-if="type === 'dessert'" class="item">
            <img :src="position.dessert.img" alt="">
            <div class="positionDescription">
                <p>{{ position.dessert.name }}</p>
            </div>
            <div class="btns">
                <div class="amount">
                    <button @click="store.decrementOrder(position, 'dessert')">-</button>
                    <p>{{ position.amount }}</p>
                    <button @click="store.incrementOrder(position, 'dessert')">+</button>
                </div>
                <p>{{ position.price * position.amount * 40 }} UAH</p>
            </div>
        </div>
</template>
<script>
import { store } from '../store/store';
export default {
    emits: ['decrement', 'increment'],
    props: {
        position: {
            type: Object,
            required: true
        }, type:{
            type:String
        }
    }, data() {
        return {
            store
        }
    }
    
}
</script>
<style scoped>
    .item{
display: flex;
flex-direction: row;
padding: 10px;
background-color: white;
box-shadow: 0 10px 40px rgba(0,0,0,0.03);
border: 1px solid rgba(0, 0, 0, 0.058);
border-radius: 3px;
margin-bottom: 10px;
}
img{
    width: 120px;
    border-radius: 3px;
}
.p2{
        border-radius: 0px 10px 10px 0px;
        color: black;
        padding: 4.6px;
        cursor: pointer;
    }
    .p1{
        border-radius: 10px 0px 0px 10px;
        color: black;
        padding: 4.6px;
        cursor: pointer;
    }
    .choice{
        background-color: brown;
        transition: all .5s ease-in;
    }
    .crust_switch{
        display: flex;
        flex-direction: row;
        max-width: 132px;
        background-color: rgba(255, 255, 255, 0.378);
        border-radius: 10px;
        
    }
.positionDescription{
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    padding: 10px;
    width: 100%
}
.amount{
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    width: 100%;
}
.btns{
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    width: 100%;
}
.btns button{
    width: 30px;
        height: 30px;
        border-radius: 100%;
        border: none;
        cursor: pointer;
        background-color: #fefae0;
        transition: all 0.2s ease-in;
    }
.btns button:hover{
        background-color: #bc6c25;
        color: #fefae0;
}
</style>