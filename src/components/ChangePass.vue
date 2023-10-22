<template lang="">
    <Wrapper v-if="show" @click.stop="renderForm">
        <div @click.stop class="formContainer">
            <h2>{{message}}</h2>
            <form @submit.prevent="tryChangePass">
                <input type="password" name="password" v-model="password"  placeholder="Password">
                <input type="password" name="password" v-model="newPassword" @input='validatePassword' placeholder="New Password">
                <input type="password" name="repeatpassword" v-model="repeatPassword" @input='validatePassword' placeholder="Repeat New Password">
                <button type="submit" :class="error? 'disabled':'' " :disabled="error">Save Changes</button>
            </form>
        </div>
    </Wrapper>
</template>
<script>
import Wrapper from './Wrapper.vue';
export default {
    components: {
        Wrapper
    }, props: {
        show: {
            type: Boolean,
            required: true
        }, user: {
            type: Object,
            required: true
        }
    }, data() {
        return {
            password: "",
            newPassword: "",
            repeatPassword: "",
            message: "",
            error: false
        }
    },methods:{
        renderForm() {
            this.$emit('update:show', false)
        }, async tryChangePass() {
           
            const body = {
                "username": this.user.username,
                "password": this.password
            }
            const jsonBody1 = JSON.stringify(body);
            console.log(jsonBody1)
            const response1 = await fetch("https://localhost:7043/login", {
                method: 'POST',
                headers: {
                    Accept: 'application.json',
                    'Content-Type': 'application/json'
                },
                body: jsonBody1,
                cache: 'default'
            })
            if (response1.status === 200) {
                const user = {
                    id: this.user.id,
                    username: this.user.username,
                    email: this.user.email,
                    name: this.user.name,
                    surname: this.user.surname,
                    address: this.user.address,
                    phone: this.user.phone,
                    password: this.newPassword
                }
                const jsonBody = JSON.stringify(user)
                const response = await fetch(`https://localhost:7043/login/${user.id}`, {
                    method: 'PUT',
                    headers: {
                        Accept: 'application.json',
                        'Content-Type': 'application/json'
                    },
                    body: jsonBody,
                    cache: 'default'
                })
                if (response.status === 204) {
                    this.message="Success"
                } else {
                    this.message = "Something went wrong"
                }
            } else {
                this.message = "Something went wrong"
            }
        }, async validatePassword() {
            if (this.newPassword.length < 8) {
                this.message = "Password is too short";
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
                this.message = "Password does not include upper case character";
                this.error = true;
                return 0;
            }
            else if (number) {
                this.message = "Password does not include number";
                this.error = true;
                return 0;
            }
            else {
                this.message = "";
                this.error = false;
                this.validatePassword2();
                return 0;
            }
            
        },
        async validatePassword2() {
            if (this.newPassword !== this.repeatPassword) {
                this.message = "Passwords do not match";
                this.error = true;
            }
            else {
                this.message = "";
                this.error = false;
            }
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
    padding: 1%;
    position: absolute;
    background-color: white;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}
form{
    display: flex;
    flex-direction: column;
    padding: 20px;
    margin-top: 10px;
    
}
form button{
    background-color: #e68028;
    cursor: pointer;
    padding: 10px;
    color: white;
    font-weight: 600;
}
.disabled{
    background-color: red;
    color: aliceblue;
    cursor: not-allowed;
}
</style>