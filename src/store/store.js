import { reactive } from 'vue'

export const store = reactive({
    orderSideBar: false,
    orderDetails: false,
    order: {
        pizzas: [],
        desserts: []
    },
    user: {
        id: "",
        username: "",
        email: "",
        name: "",
        surname: "",
        address: "",
        phone: ""
    },
    logged: false,
    totalPrice: 0,
    logging: false,
    addToCart(pizza, amount,price, size, type) {
            for (let i = 0; i < this.order.pizzas.length; i++) {
                if (this.order.pizzas[i].pizza.name === pizza.name) {
                    if (this.order.pizzas[i].size === size) {
                        this.order.pizzas[i].amount += amount;
                        this.totalPrice += price;
                        return 0;
                    } 
                } 
            }
            this.order.pizzas.push({
                "pizza": pizza,
                "amount": amount,
                "price" : price,
                "size": size
            })
        this.totalPrice += price * amount
    },
    addToCartDessert(dessert, amount, price, type) {
            for (let i = 0; i < this.order.desserts.length; i++) {
                if (this.order.desserts[i].dessert.name === dessert.name) {
                        this.order.desserts[i].amount += amount;
                        this.totalPrice += price;
                        return 0;
                }
            }
            this.order.desserts.push({
                "dessert": dessert,
                "amount": amount,
                "price": price,
            })
            this.totalPrice += price * amount
        },decrementOrder(position, type) {
            if (type === 'pizza') {
                for (let i = 0; i < this.order.pizzas.length; i++){
                    if (this.order.pizzas[i].pizza.name === position.pizza.name) {
                        if (this.order.pizzas[i].size === position.size) {
                            if (this.order.pizzas[i].amount - 1 !== 0) {
                                this.order.pizzas[i].amount -= 1
                                this.totalPrice -= position.price
                            } else {
                                this.totalPrice -= position.price
                                this.order.pizzas.splice(i, 1)
                            }
                        }
                    }
                }
            } else {
                for (let i = 0; i < this.order.desserts.length; i++) {
                    if (this.order.desserts[i].dessert.name === position.dessert.name) {
                            if (this.order.desserts[i].amount - 1 !== 0) {
                                this.order.desserts[i].amount -= 1
                                this.totalPrice -= position.price
                            } else {
                                this.totalPrice -= position.price
                                this.order.desserts.splice(i, 1)
                            }
                        }
                    }
                }
        },incrementOrder(position, type) {
            if (type === 'pizza') {
                for (let i = 0; i < this.order.pizzas.length; i++) {
                    if (this.order.pizzas[i].pizza.name === position.pizza.name) {
                        if (this.order.pizzas[i].size === position.size) {
                            this.order.pizzas[i].amount += 1
                            this.totalPrice += position.price
                            return 0;
                        }
                    }
                }
            } else {
                for (let i = 0; i < this.order.desserts.length; i++) {
                    if (this.order.desserts[i].dessert.name === position.dessert.name) {
                        if (this.order.desserts[i].size === position.size) {
                            this.order.desserts[i].amount += 1
                            this.totalPrice += position.price
                            return 0;
                        }
                    }
                }
            }
        }
})