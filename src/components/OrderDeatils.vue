<template>
    <Wrapper @click.stop="renderOrder">
            <div @click.stop class="order">
              <div class="head">
                <h2>Input Details</h2>
                <button @click.stop="renderOrder">X</button>
              </div>
                <transition name="loading-fade">
                  <transition v-if="submitted" name="loading-fade">
                    <h1 class="confirmed">Order Submitted</h1>
                  </transition>
                </transition>
                <transition name="loading-fade">
                  <transition v-if="error" name="loading-fade">
                      <h1 class="confirmed">Please fill all necessery blanks</h1>
                  </transition>
                </transition>
                <transition name="loading-fade">
                    <transition v-if="orderEmpty" name="loading-fade">
                        <h1 class="confirmed">Order is Empty!!!</h1>
                    </transition>
                  </transition>
                <input type="text" :value="name" @change="name=$event.target.value" placeholder="Name">
                <input type="text" :value="surname" @change="surname=$event.target.value" placeholder="Surname">
                <input type="text" :value="address" @change="address=$event.target.value" placeholder="Address">
                <input type="text" :value="phone" @change="Numberchek($event.target.value)" placeholder="Phone Number">
                <input type="text" :value="email" @change="email=$event.target.value" placeholder="E-mail">
                <input type="text" :value="details" @change="details = $event.target.value" placeholder="Details">
                <div class="carSpace">
                  <span class="material-symbols-outlined box" id="orderSUBMITTED">
    local_shipping
    </span>
                </div>
                <button @click="Check" :disabled="error" >Submit Order</button>
            </div>
        </Wrapper>

</template>

<script >
import Wrapper from './Wrapper.vue';
import { Email } from "../smtp.js";
import { store } from '../store/store';
import baseUrl from '../config.json'
export default {
    components: {
        Wrapper
  },
  props: {
    show: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      store,
      submitted: false,
      error: false,
      orderEmpty: false,
      email: store.user.email,
      name: store.user.name,
      surname: store.user.surname,
      address: store.user.address,
      details: "",
      phone: store.user.phone
      }
    }
    , methods: {
        renderOrder() {
            store.orderDetails = false
      }, animation() {
        const animatedBox = document.getElementById("orderSUBMITTED");
        animatedBox.style.display = "block";
        animatedBox.classList.add("animated");

        animatedBox.addEventListener("animationend", () => {
          animatedBox.style.display = "none";
          animatedBox.classList.remove("animated");
        })
        
      
      }, Numberchek(phone) {
        this.phone = phone
        if (this.phone[0] === "+" || !isNaN(this.phone[0])) {
          console.log('first chracter is okay')
          for (let i = 1; i < this.phone.length; i++){
            if (isNaN(this.phone[i])) {
              this.error = true;
              return 0;
            }
          }
        } else {
          this.error = true;
          return 0;
        }
        this.error = false;
      }, async Check() {
        if (store.order.pizzas.length !== 0 || this.store.desserts.length !== 0) {
          if (this.name !== "") {
            if (this.surname !== "") {
              if (this.address !== "") {
                if (this.phone !== "") {
                  if (this.email !== "") {
                    if (this.error !== true) {
                      Email.send({
                        Host: "smtp.elasticemail.com",
                        Username: "krechuniak.a@gmail.com",
                        Password: "B383CBC5AE75DFD0D580C8B628280B9A7228",
                        To: this.email,
                        From: "krechuniak.a@gmail.com",
                        Subject: "Your Order ",
                        Body: `This is your order, ${this.name} ${this.surname}, to address ${this.address}: ${store.order.pizzas.length !== 0 ? store.order.pizzas.map((element) => {
                          return `${element.pizza.name}, ${element.size}, ${element.price * 40} UAH, ${element.amount}. position price: ${element.price * element.amount * 40} UAH \n`
                        }) : ''}\n ${store.order.desserts.length !== 0 ? store.order.desserts.map((element) => {
                          return `${element.dessert.name}, ${element.price * 40} UAH, ${element.amount}.position price: ${element.price * element.amount * 40
                            } UAH \n`
                        }) : ''}Total Price ${store.totalPrice * 40}UAH
                \nThanks for Choosing us!
                `
                      }).then(
                      await this.PostOrderToServer(),
                      this.animation(),
                      this.submitted = true
                      );
                      setTimeout(() => {
                        this.submitted = false;
                      }, 3000)
                      setTimeout(() => {
                        store.orderSideBar = false
                      }, 2000)
                      setTimeout(() => {
                        store.orderDetails = false
                        this.$emit("cleanOrder")
                      }, 1200)
                      
                    }
                  } else {
                      this.error = true;
                      console.log("Problem in email")
                      setTimeout(() => {
                        this.error = false;
                    }, 2000)
                }
                
                } else {
                  this.error = true;
                  console.log("Problem in phone")
                  setTimeout(() => {
                    this.error = false;

                }, 2000)
                }
              } else {
                this.error = true;
                      console.log("Problem in address")

              setTimeout(() => {
                this.error = false;
              }, 2000)
              }
            } else {
              this.error = true;
                      console.log("Problem in surname")

            setTimeout(() => {
              this.error = false;
            }, 2000)
            }
          } else {
            this.error = true;
                      console.log("Problem in name")

          setTimeout(() => {
            this.error = false;
          }, 2000)
          }
        } else {
          this.orderEmpty = true;
          setTimeout(() => {
          this.orderEmpty = false;
        }, 2000)
        }
      }, async PostOrderToServer() {
      const id = store.logged ? store.user.id : 1
      const jsonBody = JSON.stringify(store.order);
      console.log(jsonBody)
      const response = await fetch(`${ baseUrl.baseUrl }/Order/${id}`, {
        method: 'POST',
        headers: {
          Accept: 'application.json',
          'Content-Type': 'application/json'
        },
        body: jsonBody,
        cache: 'default'
      })
        return response;
      }
  }
  , watch: {
    "store.user": {
      handler(newUser) {
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

.material-symbols-outlined {
  color: white;
  font-variation-settings:
  'FILL' 0,
  'wght' 400,
  'GRAD' 0,
  'opsz' 48
}

.carSpace{
  width: 100%;
  margin-top: -10px;
  margin-bottom: 50px;
}

.head{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.head button{
  height: 40px;
  justify-self: flex-end;
}

.box {
    padding: 10px;
    background-color: black;
        box-shadow: 0 10px 40px rgba(0,0,0,0.03);

    border-radius: 100%;
    z-index: 3000000;
    position: absolute;
    display: none;
}

.animated {
    animation: moveAndCurve 1.5s ease-in-out;
}

@keyframes moveAndCurve {
    0% {
        left: 5%
    }
    100% {
        left: 90%;
    }
}

@keyframes moveAndCurve2 {
    0% {
        left: 10%
    }
    100% {
        left: 80%;
    }
}

.loading-fade-enter-active,
.loading-fade-leave-active {
  transition: opacity 0.8s ease-in-out;
}

.loading-fade-enter-from,
.loading-fade-leave-to {
  opacity: 0;
}

    .order{
        background-color: white;
        min-height: max-content;
        max-height: 90%;
        overflow-y: scroll;
        min-width: 50%;
        display: flex;
        flex-direction: column;
        top: 50%;
        left:50%;
        padding: 40px;
    position: absolute;
    transform: translate(-50%, -50%);
    text-align: center;
    box-shadow: 0 10px 40px rgba(0,0,0,0.03);
    }
    .confirmed{
      margin-top: -50px;
      margin-bottom: 15px;
    }
    h2{
      padding-bottom: 60px;
    }
    input{
        border: none;
        background-color: #d6d4d3ef;
        color: black;
        margin-bottom:20px;
        padding: 10px;
        font-weight: 500;
    }
    input::placeholder{
        color: black;
    }

    button{
        background-color: #e68128;
        box-shadow: 0 10px 40px rgba(0,0,0,0.03);
    padding: 10px;
    color: rgb(255, 255, 255);
    font-weight: 500;
    border: none;
    transition: all 0.2s ease-in;
    }
    button:hover{
        cursor: pointer;
      background-color: black;

    }

    @media only screen and (max-width: 1200px) {
  .order {
    width: 40%;
  }
}
@media only screen and (max-width: 1200px) and (max-height: 601px)  {
  .order {
    width: 40%;
  }
}

@media only screen and (max-width: 900px) and (orientation: portrait)  {
  .order {
    width: 50%;
  }
  .animated{
    animation: moveAndCurve2 1.5s ease-in-out;
  }
}

@media only screen and (max-width: 550px) and (orientation: portrait)  {
  .order {
    width: 90%;
  }
  .animated{
    animation: moveAndCurve2 1.5s ease-in-out;
  }
}

@media only screen and (max-width: 281px) and (orientation: portrait) {
  .order {
    
    width: 100%;
  }
  .animated{
    animation: moveAndCurve2 1.5s ease-in-out;
  }
}
@media only screen and (max-width: 920px) and (orientation: landscape) {
  .order {
    
    width: 410px;
  }
  
}
</style>