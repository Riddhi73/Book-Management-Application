<template>
  <div class="div_class">
    <h3>You will be redirected to payment page</h3>
    <div class="alert alert-primary">
      While making payment use visa number 4242 4242 4242 4242 and enter random date
    </div>

    <button class="btn btn-primary" @click="goToCheckout">Make Payment</button>
  </div>
</template>

<script>
import axios from 'axios';
export default { 
  data() {
    return {
      stripeAPIToken:
      'pk_test_51LJeaUKJhIHb14C8TyDk18DGzkygtspkxq05Y1QW4ZY0gBHUcXIedPlOUsw2qICdIVFd6UadJGPRcUeqHobxZsT300nuNnkP1s',
       stripe: '',
      token: null,
      checkoutBodyArray: [],
    };
  },
  name: 'Checkout',
  props: ['baseURL'],
  methods: {
    getAllItems() {
      axios
        .get(`${this.baseURL}cart/allitem/?token=${this.token}`)
        .then((response) => {
          if (response.status == 200) {
            let products = response.data;
            for (let i = 0; i < products.cartItemDtoList.length; i++) {
              this.checkoutBodyArray.push({
                price: products.cartItemDtoList[i].book.price,
                quantity: products.cartItemDtoList[i].quantity,
                bookName: products.cartItemDtoList[i].book.name,
                bookId: products.cartItemDtoList[i].book.id,
              });
            }
          }
        })
        .catch((err) => console.log(err));
    },
    goToCheckout() {
      console.log('checkoutBodyArray', this.checkoutBodyArray);
      axios
        .post(
          `${this.baseURL}order/create-checkout-session`,
          this.checkoutBodyArray
        )
        .then((response) => {
          localStorage.setItem('sessionId', response.data.sessionId);
          console.log('session', response.data);
          this.stripe.redirectToCheckout({
            sessionId: response.data.sessionId,
          });
        })
        .catch((err) => console.log(err));
    },
  },
  mounted() {
    this.token = localStorage.getItem('token');
    this.stripe = window.Stripe(this.stripeAPIToken);
    this.getAllItems();
  },
};
</script>
<style scoped>
.alert {
  width: 50%;
}
.div_class {
  margin-top: 5%;
  margin-left: 30%;
}
.checkout_button {
  background-color: #5d3dec;
  margin: 10px;
}
</style>