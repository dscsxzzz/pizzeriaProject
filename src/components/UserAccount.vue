<template>
    <Wrapper v-if="show" @click.stop="renderForm">
        <div @click.stop class="formContainer">
            <h2>Account Info</h2>
            <h2 v-show="!error">{{ message }}</h2>
            <form @submit.prevent="tryChangeAccount">
                <label for="username">Username:</label>
                    <input type="text" name="username" :value="username" placeholder="Username"
                    @change="username = $event.target.value">
                    <label for="username">Name:</label>
                    <input type="text" name="name" :value="name" @change="name = $event.target.value" placeholder="Name">
                    <label for="username">Surname:</label>
                    <input type="text" name="surname" :value="surname" @change="surname = $event.target.value" placeholder="Surname">
                    <label for="username">Address:</label>
                    <input type="text" name="address" :value="address" @change="address = $event.target.value" placeholder="Address">
                    <label for="username">Phone Number:</label>
                    <input type="text" name="phone" :value="phone" @change="Numberchek($event.target.value)" placeholder="Phone Number">
                    <label for="username">E-mail:</label>
                    <input type="text" name="email" :value="email" @change="email = $event.target.value" placeholder="E-mail">
                    <p v-show="error">*{{ message }}</p>
                <button type="submit" :class='error ? "disabled": ""'  :disabled="error">Save Changes</button>
            </form>
            <div class="accountBtns">
                <button type="button" @click="this.$emit('tryChangePass')">Change Password</button>
                <button type="submit"  @click="logOut">Log Out</button>
            </div>
        </div>
    </Wrapper>
</template>
<script>
import Wrapper from './Wrapper.vue';

export default {
    components: { Wrapper },
    props: {
        user: {
            type: Object,
            required: true
        }, show : {
            type: Boolean,
            required: true
        }
    },
    data() {
        return {
            error: false,
            message: "",
            id: this.user.id,
            username: this.user.username,
            email: this.user.email,
            name: this.user.name,
            surname: this.user.surname,
            address: this.user.address,
            phone: this.user.phone
        }
    },
    methods: {
        renderForm() {
            this.$emit('update:show', false)
             this.username = this.user.username,
                this.email = this.user.email,
                this.name = this.user.name,
                this.surname = this.user.surname,
                this.address = this.user.address,
                this.phone = this.user.phone,
                this.message = "",
                this.error = false
        }, Numberchek(phone) {
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
            location.reload()
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
                const jsonBody = JSON.stringify(user)
                const response = await fetch(`https://localhost:7043/user/${user.id}`, {
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
                    this.$emit('tryChangeAccount', user)
                    this.message = "Sucesss"
                } else {
                    this.message = res.message
                }
            } else {
                return 0;
            }
            }
    }, watch: {
        user: {
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

    .formContainer{
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 25%;
    max-height: max-content;
    padding: 2%;
    position: absolute;
    background-color: white;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
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
    color: rgb(0, 0, 0);
    
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



@media only screen and (max-width: 1200px) {
  .formContainer {
    width: 40%;
  }
}
@media only screen and (max-width: 1200px) and (max-height: 601px)  {
  .formContainer {
    width: 40%;
  }
}

@media only screen and (max-width: 900px) and (orientation: portrait)  {
  .formContainer {
    width: 50%;
  }
}

@media only screen and (max-width: 550px) and (orientation: portrait)  {
  .formContainer {
    width: 90%;
  }
}

@media only screen and (max-width: 281px) and (orientation: portrait) {
  .formContainer {
    
    width: 100%;
  }
}
@media only screen and (max-width: 920px) and (orientation: landscape) {
  .formContainer {
    
    width: 410px;
  }
}
</style>