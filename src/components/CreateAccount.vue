<template>
    <div v-if="show" @click.stop="renderForm" class="wrapper">
        <div @click.stop class="formContainer">
            <h2>Create Account</h2>
            <p>{{message}}</p>
            <form @submit.prevent="tryCreateAccount">
                    <input type="text" name="login" :value="username" placeholder="Username"
                        @change="validateUser($event.target.value)">
                    <input type="text" :value="name" @change="name = $event.target.value" placeholder="Name">
                    <input type="text" :value="surname" @change="surname = $event.target.value" placeholder="Surname">
                    <input type="text" :value="address" @change="address = $event.target.value" placeholder="Address">
                    <input type="text" :value="phone" @change="Numberchek($event.target.value)" placeholder="Phone Number">
                    <input type="text" :value="email" @change="email = $event.target.value" placeholder="E-mail">
                    <input type="password" name="password" v-model="password" placeholder="Password"
                        @input="validatePassword">
                    <input type="password" name="repeatpassword" v-model="repeatpassword" placeholder="Repeat"
                        @input="validatePassword2">
                <button type="submit">Create Account</button>
            </form>
        </div>
    </div>
</template>
<script>
export default {
    props: {
        show: {
            type: Boolean,
            default: false
        }
    },

    data() {
        return {
            username: "",
            email: "",
            name: "",
            surname: "",
            address: "",
            phone: "",
            password: "",
            repeatpassword: "",
            message: ""
        }
    },
    methods: {
        async validateUser(user) {
            this.username = user;
            const response = await fetch("/users.json")
            const { users } = await response.json()
            console.log(users)
            const foundUser = users.find(
                (user) => user.username === this.username
            );
            if (foundUser) {
                this.message = "Username already taken"
            } else {
                this.message = ""
            }
        },
        async validatePassword() {
            if (this.password.length < 8) {
                this.message = "Password is too short"
                return 0;
            }
            let big = true;
            let number = true;
            for (let i = 0; i < this.password.length; i++){
                if (Number(this.password[i])) {
                    number = false;
                } else if(this.password[i] === this.password[i].toUpperCase()) {
                    big = false;
                }
            }
            if (big) {
                this.message = "Password does not include upper case character"
                return 0;

            } else if(number) {
                this.message = "Password does not include number"
                return 0;
            } else {
                this.message = ""
                return 0;
            }
        },
        async validatePassword2() {
            if (this.password !== this.repeatpassword) {
                this.message = "Passwords do not match"
            } else {
                this.message = ""
            }
        }, Numberchek(phone) {
            this.phone = phone
            if (this.phone[0] === "+" || !isNaN(this.phone[0])) {
                console.log('first chracter is okay')
                for (let i = 1; i < this.phone.length; i++) {
                    if (isNaN(this.phone[i])) {
                        this.message = "Not valid number";
                        return 0;
                    }
                }
            } else {
                this.message = "Not valid number";
                return 0;
            }
            this.message = "";
        },
        tryCreateAccount() {
            this.renderForm()
        },

        renderForm() {
            this.$emit('update:show', false)
        }
    }

}
</script>
<style scoped>
.loginHolder {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
}
.material-symbols-outlined {
  font-variation-settings:
  'FILL' 0,
  'wght' 400,
  'GRAD' 0,
  'opsz' 48
}
.loginHolder input {
    width: 90%;
}

.material-symbols-outlined {
    background-color: rgb(218, 215, 213);
    padding: 6.5px;
    font-variation-settings:
        'FILL' 0,
        'wght' 400,
        'GRAD' 0,
        'opsz' 48
}

.wrapper {
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    position: fixed;
    z-index: 2000000;
    background-color: rgb(0, 0, 0, 0.5);
}

.formContainer {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 25%;
    height: max-content;
    padding: 1%;
    position: absolute;
    background-color: #eae2b7;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}

form {
    display: flex;
    flex-direction: column;
    padding: 20px;

}

form button {
    background-color: #d62828;
    padding: 10px;
    color: white;
    font-weight: 600;
}

button{
        border: none;
        background-color: transparent;
        color: blue;
    }
    button:hover{
        cursor: pointer;
    }

input {
    padding: 11px;
    background-color: rgb(218, 215, 213);
    border: none;
    margin-bottom: 10px;
}

h2 {
    font-weight: 900;
    padding: 20px;
    text-align: center;
    margin: 0;
}


@media only screen and (max-width: 1200px) {
    .formContainer {
        width: 40%;
    }
}

@media only screen and (max-width: 1200px) and (max-height: 601px) {
    .formContainer {
        width: 40%;
    }
}

@media only screen and (max-width: 900px) and (orientation: portrait) {
    .formContainer {
        width: 50%;
    }
}

@media only screen and (max-width: 550px) and (orientation: portrait) {
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
}</style>