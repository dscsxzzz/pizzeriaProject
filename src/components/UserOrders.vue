<template>
  <transition name="login-fade">
    <div class="orders">
      <PrevOrder v-for="orderss in orders" :orders="orderss" />
    </div>
  </transition>
</template>
<script>
import PrevOrder from "./PrevOrder.vue";
import baseUrl from "../config.json";
import { store } from "../store/store";
export default {
  components: { PrevOrder },
  data() {
    return {
      orders: [],
      store,
    };
  },
  methods: {
    async getOrders() {
      if (store.user.id !== "") {
        const res = await fetch(
          `${baseUrl.baseUrl}/user/order-get/${store.user.id}`,
          {
            method: "GET",
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
              "Authorization": `Bearer ${localStorage.getItem("token")}`,
            },
            cache: "default",
          }
        );
        const orders = await res.json();
        console.log(orders, store);
        for (let i = 0; i < orders.length; i++) {
          this.orders.push(orders[i]);
        }
      }
    },
  },
  mounted() {
    this.getOrders();
    console.log(this.orders);
  },
};
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
.orders {
  display: flex;
  flex-wrap: wrap;
  overflow-y: scroll;
  height: 80svh;
  height: 80cqh;
  height: 80vh;
  flex-direction: column;
}
@media only screen and (max-width: 1281px) {
  .orders {
    flex-direction: row;
  }
}
</style>
