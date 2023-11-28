<template>
    <div class="order">
        <h2>Order#{{orders[0].id}}</h2>
        <h3>{{date}}</h3>
        <div class="position">
            <p style="color:#e68028">Name:</p>
            <p>Size:</p>
            <p>Q-ty:</p>
            <p>Price:</p>
        </div>
        <div class="details">
            <div class="positions">
                <div v-for="order in orders" class="position">
                    <p style="color:#e68028">{{ order.productName }} </p>
                    <p >{{ order.size }}</p>
                    <p>{{ order.productQuantity }}</p>
                    <p>{{order.price * 40}} UAH</p>
                </div>
            </div>
            <h2 class="totalPrice">Total price: {{totalPrice * 40}} UAH</h2>
        </div>
    </div>
</template>
<script>

export default {
    props: {
        orders: {
            type: Array,
            required: true
        }
    }, data() {
        return {
            totalPrice: 0,
            date: ""
        }
    }, methods: {
        calculateTotalPrice(){
            this.orders.forEach(order => {
                this.totalPrice += order.price * order.productQuantity;
                const transactionId = String(order.transactionId);
                this.date = `${transactionId.slice(0, 4)}-${transactionId.slice(4, 6)}-${
                    transactionId.slice(6, 8)} ${
                        transactionId.slice(8, 10)}:${
                            transactionId.slice(10, 12)}`
            })
        }
    }, mounted() {
        this.calculateTotalPrice()
    }
}
</script>
<style scoped>
.order{
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    width: 30%;
    height: 300px;
    margin: 10px;
    padding: 10px;
    background-repeat: none;
    background-size: cover;
    box-shadow: 0 0px 15px rgba(84, 83, 85, 0.295);

    border-radius: 5px;
}



.order h2{
    text-align: left;
    padding: 2px;
}
p{
    font-weight: 600
}
.details{
    z-index: 3;
    height: 190px;
    display: grid;
    grid-template-rows: 5fr 1fr;
}
.positions{
    display: flex;
    flex-direction: column;
    height: 100px;
    overflow-y: scroll;
}
.position{
    display: grid;
    grid-template-columns: 3fr 2fr 1.5fr 1fr;
    text-align: left;
    padding: 2px;
    width: 100%;

}
.totalPrice{
    text-decoration: overline dashed black 1px;
    color: #e68028;
}


@media only screen and (max-width: 1300px) {
    .order{
        width: 45%;
    }
}

@media only screen and (max-width: 1200px) and (max-height: 601px)  {
    .order{
        width: 45%;
    }
}

@media only screen and (max-width: 920px)and (max-height: 1400px) and (orientation: portrait)  {
    .order{
        width: 45%;
    }
}
@media only screen and (max-width: 920px) and (orientation: portrait)  {
    .order{
        width: 45%;
    }
}

@media only screen and (max-width: 550px) and (orientation: portrait)  {
    .order{
        width: 100%;
    }
}

@media only screen and (max-width: 281px) and (orientation: portrait) {
    .order{
        width: 100%;
    }
}
@media only screen and (max-width: 920px) and (orientation: landscape) {
    .order{
        width: 45%;
    }
}
</style>