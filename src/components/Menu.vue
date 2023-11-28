<template>
        <div class="menu">
                <MenuItem
                v-if="content === 'pizzas'"
                v-for="pizza in pizzas.slice(start, end)"
                :pizza="pizza"
                :key="pizza"
                />
                <Dessert
                v-else
                v-for="dessert in desserts.slice(start, end)"
                :dessert="dessert"
                :key="dessert"
                />
            <div class="loadmore">
                <Choicer v-for="page in pages" 
                @handlePage="handlePage"
                class="pages"
                :class="choice === page? 'choice' : '' "
                :page="page"
                />
            </div>
        </div>
        
</template>
<script>
import MenuItem from './MenuItem.vue';
import Dessert from './Dessert.vue';
import Choicer from './Choicer.vue';
export default {
    components: {
    MenuItem, Dessert, Choicer
    },
    props: {
        content: {
            type: String,
            required: true
        }, pizzas: {
            type:Array
        }, desserts: {
            type:Array
        }
    },
    data() {
        return {
            start: 0,
            end: 10,
            pages: this.content === "pizzas" ? Math.ceil(this.pizzas.length / 10) : Math.ceil(this.desserts.length / 10),
            choice: 1
    }
},
     methods: {
        handlePage(page) {
            window.scrollTo({
                top: 0,
                behavior: 'smooth',
            })
            this.start = page * 10 - 10;
            this.end = page * 10;
            this.choice = page;
        }
    }, watch: {
        content: {
            handler(content) {
                this.pages = content === "pizzas" ? Math.ceil(this.pizzas.length / 10) : Math.ceil(this.desserts.length / 10), this.start = 0, this.end = 10, this.choice = 1;
            },
            immediate: true
         }, pizzas: {
            handler(pizzas) {
                this.pages = this.content === "pizzas" ? Math.ceil(pizzas.length / 10) : Math.ceil(this.desserts.length / 10), this.start = 0, this.end = 10, this.choice = 1;
            }, immediate: true
        }
    }
}
</script>
<style scoped>


    .menu{
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        padding: 20px;
        width: 100%;
        max-width: 1300px;
        min-height: 50vh;
    }
    .login-fade-enter-active,
.login-fade-leave-active {
  transition: opacity 0.8s ease-in-out;
}

.login-fade-enter-from,
.login-fade-leave-to {
  opacity: 0;
}

.loadmore{
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        width: 80%;
        max-width: 200px;
        padding: 20px;
        text-align: center;
}
    .choice{
        background-color: black !important;;
        color: white !important;
        transition: all 0.5s ease-in;
    }

    .pages{
        background-color: #e68028;
        color: black;
        font-weight: 500;
        padding: 8px;
        width: 35px;
        border-radius: 100%;
        cursor: pointer;
        margin-left: 10px;
    }

</style>