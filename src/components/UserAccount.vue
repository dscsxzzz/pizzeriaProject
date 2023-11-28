<template>
<transition name="login-fade">
    <div class="UserAccount">
        <h2>Account Info</h2>
        <h2 v-show="!error">{{ message }}</h2>
        <form @submit.prevent="tryChangeAccount">
            <label for="username">Username:</label>
            <input type="text" name="username" :value="username" placeholder="Username"
            @change="username = $event.target.value">
            <label for="name">Name:</label>
            <input type="text" name="name" :value="name" @change="name = $event.target.value" placeholder="Name">
            <label for="surname">Surname:</label>
            <input type="text" name="surname" :value="surname" @change="surname = $event.target.value" placeholder="Surname">
            <label for="address">Address:</label>
            <input type="text" name="address" :value="address" @change="address = $event.target.value" placeholder="Address">
            <label for="phone">Phone Number:</label>
            <input type="text" name="phone" :value="phone" @change="Numberchek($event.target.value)" placeholder="Phone Number">
            <label for="email">E-mail:</label>
            <input type="text" name="email" :value="email" @change="email = $event.target.value" placeholder="E-mail">
            <p v-show="error">*{{ message }}</p>
            <button type="submit" :class='error ? "disabled": ""'  :disabled="error">Save Changes</button>
        </form>
        <div class="accountBtns">
            <button type="button" @click="router.push('/changePass')">Change Password</button>
            <button type="submit"  @click="logOut">Log Out</button>
        </div>
    </div>
</transition>

</template>
<script>
import PrevOrder from './PrevOrder.vue';
import { store } from '../store/store';
import router from '../router';
import baseUrl from '../config.json'
export default {
    components: { PrevOrder},
    
    data() {
        return {
            store,
            router,
            error: false,
            message: "",
            id: store.user.id,
            username: store.user.username,
            email: store.user.email,
            name: store.user.name,
            surname: store.user.surname,
            address: store.user.address,
            phone: store.user.phone,
        }
    },
    methods: {
         Numberchek(phone) {
            this.phone = phone;
            if (this.phone[0] === "+" || !isNaN(this.phone[0])) {
                console.log('first chracter is okay');
                for (let i = 1; i < this.phone.length; i++) {
                    if (isNaN(this.phone[i])) {
                        this.message = "Not valid number";
                        this.error = true;
                        return 0;
                    }
                }
            }
            else {
                this.message = "Not valid number";
                this.error = true;
                return 0;
            }
            this.message = "";
            this.error = false;
        }, logOut() {
            store.order = {
                pizzas: [],
                desserts: []
            },
            store.totalPrice= 0,
            store.user= {
                id: "",
                username: "",
                email: "",
                name: "",
                surname: "",
                address: "",
                phone: ""
                }
            store.logged = false
            router.push("/")
        }, async tryChangeAccount() {
            if (!this.error) {
                const user = {
                    id: this.id,
                    username: this.username,
                    email: this.email,
                    name: this.name,
                    surname: this.surname,
                    address: this.address,
                    phone: this.phone
                }
                console.log(user)
                const jsonBody = JSON.stringify(user)
                const response = await fetch(`${ baseUrl.baseUrl }/user/${store.user.id}`, {
                    method: 'PUT',
                    headers: {
                        Accept: 'application.json',
                        'Content-Type': 'application/json'
                    },
                    body: jsonBody,
                    cache: 'default'
                })
                const res = await response.json();
                if (response.status === 200) {
                    store.user = user
                    this.message = "Sucesss"
                } else {
                    this.message = res.message
                }
            } else {
                return 0;
            }
        }
        
        
    }, watch: {
        "store.user": {
            handler(newUser) {
                this.id = newUser ? newUser.id : '';
                this.username = newUser ? newUser.username : '';
                this.email = newUser ? newUser.email : '';
                this.name = newUser ? newUser.name : '';
                this.address = newUser ? newUser.address : '';
                this.surname = newUser ? newUser.surname : '';
                this.phone = newUser ? newUser.phone : '';
            },
            immediate: true
        }
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

p{
    color: red;
    text-align: center;
    font-weight: 400;
}

h2{
    text-align: center;
}

form {
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    padding: 20px;
}

form button {
    background-color: rgb(218, 215, 213);
    cursor: pointer;
    padding: 10px;
    color: #000000;
    font-weight: 600;
    border: none;
    transition: all .5s;
}

form button:hover{
    background-color: rgb(0, 0, 0);
    color: #ffffff;
}


input{
    padding: 10px;
    background-color: rgb(218, 215, 213);
    margin-bottom: 5px;
    color: rgb(0, 0, 0);
    border: none;
  }
  input::placeholder{
    color: rgb(0, 0, 0, 0.5);
    
}

.accountBtns{
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-around;
}

.accountBtns button{
    border: none;
    padding: 5px;
    margin-left: 5px;
    min-width: 150px;
    cursor: pointer;
    transition: all .5s;
}

.accountBtns button:hover{
    color: white;
    background-color: #000000;
}

.disabled{
    background-color: red;
    color: aliceblue;
    cursor: not-allowed;
}

</style>