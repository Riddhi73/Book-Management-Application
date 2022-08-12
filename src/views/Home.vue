<template>
  <div id="home">
    <div id="background-div" class="page-holder bg-cover">
      <div class="container py-5">
        <header>
           <h3 class="mb-4 rounded " id="heading">
             <a href="$start-shopping" class="bg-white px-2 py-2 rounder">Rent Book</a></h3>
             <p class="lead mb-0 bg-white p-1 rounded">Book Be Your Favourite Companion</p>
           
        </header>
      </div>
      <hr/>
    </div>
    <!-- display categories -->
    <div class="container">
      <div class="row">
        <div class="col-12 text-center">
          <h2 class="pt-3">Top Categories</h2>
        </div>
      </div>
      <div class="row">
        <div v-for="index in this.categorySize" :key="index" class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
        <CategoryBox :category="categories[index-1]"></CategoryBox>
        </div>
      </div>
    </div>
    <!-- Display Top Products -->
     <div class="container py-2">
       <div class="row">
         <div class="col-12 text-center">
           <h2 class="pt-3">Top Books</h2>
         </div>
       </div>
       <!-- display products -->
       <div class="row">
        <div v-for="index in this.productSize" :key="index" class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
        <ProductBox :product="products[index-1]"></ProductBox>
        </div>
      </div>
     </div>
  </div>
</template>

<script>
import CategoryBox from '../components/Category/CategoryBox.vue';
// import ProductBoxVue from '../components/ProductBox.vue';
import ProductBox from '../components/ProductBox.vue';
export default {
  name: "Home",
  components: { CategoryBox, ProductBox },
  props: ["categories" , "products"],
  data(){
    return{
      categorySize: 0,
      productSize: 0
    }
  },
  mounted(){
    this.categorySize = Math.min(6, this.categories.length );
    this.productSize = Math.min(8,this.products.length);
  }
};
</script>
<style>
  .page-holder{
    min-height: 100vh;
  }
  .bg-cover{
    background-size: cover !important ;
  }
  #background-div{
    background: url("../assets/Book-rent.jpg");
  }
  #heading{
    font-weight: 400;
  }
</style>