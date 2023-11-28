<template>
    <transition name="login-fade" appear>
        <form @submit.prevent="tryChangePass">
            <h2>{{ message }}</h2>
            <input type="password" name="NewPassword" @input="validatePassword" v-model="newPassword"  placeholder="New Password">
            <input type="password" name="RepeatPassword" @input="validatePassword2" v-model="repeatPassword"  placeholder="Repeat Password">
            <p>{{ messageForm }}</p>
            <button type="submit" :class="error ? 'disabled' : ''" :disabled="error">Change Password</button>
        </form>
    </transition>
</template>
<script>
export default {
    data() {
        return {
            error: false,
            message: ""
        }
    },
    methods: {
        async tryChangePass() {
            const jsonBody = JSON.stringify({
                username: this.username,
                password: this.newPassword
            })
            const response = await fetch(`${baseUrl.baseUrl}/user/change-pass/${store.user.id}`, {
                method: 'PUT',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                body: jsonBody,
                cache: 'default'
            });
            const result = await response.json()
            if (response.status === 200) {
                this.message = result.message
                setTimeout(() => {
                    router.push("/login")
                }, 1000)
            }
            this.message = result.message



        }, async validatePassword() {
            if (this.newPassword.length < 8) {
                this.messageForm = "Password is too short";
                this.error = true;
                return 0;
            }
            let big = true;
            let number = true;
            for (let i = 0; i < this.newPassword.length; i++) {
                if (Number(this.newPassword[i])) {
                    number = false;
                }
                else if (this.newPassword[i] === this.newPassword[i].toUpperCase()) {
                    big = false;
                }
            }
            if (big) {
                this.messageForm = "Password does not include upper case character";
                this.error = true;
                return 0;
            }
            else if (number) {
                this.messageForm = "Password does not include number";
                this.error = true;
                return 0;
            }
            else {
                this.messageForm = "";
                this.error = false;
                this.validatePassword2();
                return 0;
            }
        },
        async validatePassword2() {
            if (this.newPassword !== this.repeatPassword) {
                this.messageForm = "Passwords do not match";
                this.error = true;
            }
            else {
                this.messageForm = "";
                this.error = false;
            }
        }
    }
}
</script>
<style>
    
form {
    display: flex;
    flex-direction: column;
    width: 90%;
}

form button {
    background-color: rgb(218, 215, 213);
    cursor: pointer;
    padding: 10px;
    color: #000000;
    font-weight: 600;
    border: none;
    transition: all .5s;
    margin-top: 10px;
}



.rndr{
    width: 20px;
    height: 20px;
    border: 1px solid black;
    border-radius: 10%;
    text-align: center;
    background-color: white;
    color: black;
    cursor: pointer;
    transition: all .5s;
}

form button:hover, .rndr:hover{
    background-color: #000000;
    color: antiquewhite;
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
.disabled{
    background-color: red;
    color: aliceblue;
    cursor: not-allowed;
}

h2 {
    font-weight: 900;
    padding: 20px;
    text-align: center;
    margin: 0;
}

p{
    color: red;
    text-align: center;
    font-weight: 400;
    margin-top: 10px;
    margin-bottom: 10px;
}
</style>