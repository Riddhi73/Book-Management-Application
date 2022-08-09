<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h4 class="pt-3">Your BookList</h4>
            </div>
        </div>
        <!-- display product -->
        <div class="row">
            <div v-for="product of products" :key="product.id" class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
            <product-box :product="product">

            </product-box>
            </div>
        </div>

    </div>
</template>
<script>
import axios from "axios";
import ProductBox from '../../components/ProductBox.vue';
export default {
  components: { ProductBox },
    data(){
        return {
            token: null,
            products: null,
        };
    },
    props: ["baseURL"],
    methods: {
        fetchWishList(){
            axios.get(`$(this.baseURL)wishlist/${this.token}`).then((data)=>{
                this.product=data.data;
            })
            .catch((err) => {
                console.log("err",err)
            });

        },
    },
    mounted(){
        this.token= localStorage.getItem("token");
        this.fetchWishList();
    },
};
</script>