<template >
    <div class="dessert">
        <img :src="dessert.img" class="pizzaimg" :alt="dessert.name">
        <div class="name_des">
            <h1>{{ dessert.name }}</h1>
            <p>{{ dessert.description }}</p>
            <img :src="dessert.img" class="box" :id="dessert.id" alt="">
            <div class="buy">
                <h3>{{ price * 40 }} UAH</h3>
                <div class="counter">
                    <button @click="decrement">-</button>
                    <p>{{ amount }}</p>
                    <button @click="amount += 1">+</button>
                    <button @click="animation">Buy</button>
                </div>
            </div>
        </div>


    </div>
</template>
<script scoped>
export default {

    data() {
        return {
            price: this.dessert.price,
            show: false,
            amount: 1
        }
    },
    props: {
        dessert: {
            type: Object,
            required: true
        }
    }, methods: {
        decrement() {
            if (this.amount - 1 !== 0) {
                this.amount -= 1
            }
        }, animation() {
            const animatedBox = document.getElementById(this.dessert.id);
            animatedBox.style.display = "block";
            animatedBox.classList.add("animated");

            animatedBox.addEventListener("animationend", () => {
                animatedBox.style.display = "none";
                animatedBox.classList.remove("animated");
            });
            this.$emit('addToCartDessert', this.dessert, this.amount, this.price, 'dessert');
            this.amount = 1
        }
    }
}
</script>
<style scoped >
.box {
    width: 100px;
    height: 100px;
    padding: 10px;
    background-color: antiquewhite;
    border-radius: 100%;
    z-index: 3000000;
    position: fixed;
    display: none;
}

.animated {
    animation: moveAndCurve 1s ease-in-out;
}

@keyframes moveAndCurve {
    0% {
        top: 80%;
        left: 10%
    }

    100% {
        top: 0;
        left: 0;
    }
}

@keyframes moveAndCurve2 {
    0% {
        top: 40%;
        left: 10%
    }

    100% {
        top: 0;
        left: 0;
    }
}

.dessert {
    margin: 10px;
    padding: 15px;
    display: flex;
    flex-direction: row;
    width: 100%;
    max-width: 1500px;
    min-height: 200px;
    color: rgb(0, 0, 0);
    box-shadow: 0 10px 40px rgba(0, 0, 0, 0.03);
}

.pizzaimg {
    width: 200px;
    height: 150px;
    border-radius: 20px;
    align-self: center;
}

.name_des {
    margin-left: 10px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    width: 100%;
}

.crust_switch {
    display: flex;
    flex-direction: row;
    max-width: 132px;
    background-color: rgb(201, 198, 198);
    box-shadow: 0 10px 40px rgba(0, 0, 0, 0.03);
    border-radius: 10px;

}

.counter {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    width: 130px;
}

.counter button {
    width: 30px;
    height: 30px;
    border-radius: 100%;
    border: none;
    cursor: pointer;
}

.p2 {
    border-radius: 0px 10px 10px 0px;
    color: black;
    padding: 4.6px;
    cursor: pointer;
    color: white;

}

.p1 {
    border-radius: 10px 0px 0px 10px;
    color: black;
    padding: 4.6px;
    cursor: pointer;
    color: white;

}

.buy {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
}

.choice {
    background-color: #e68028;
    transition: all .5s ease-in;
}

p {
    max-width: 200px;
}

@media only screen and (max-width: 550px) {
    .pizzaimg {
        width: 120px;
        height: 90px;
    }

    .animated {
        animation: moveAndCurve2 1s ease-in-out;
    }
}</style>