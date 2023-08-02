<template>
  <h2>Product List</h2>
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>產品編號</th>
        <th>產品名稱</th>
        <th>產品價格</th>
        <th>製造日期</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="{ id, name, price, make } in products" :key="id">
        <td>{{ id }}</td>
        <td>{{ name }}</td>
        <td>{{ price }}</td>
        <td>{{ make }}</td>
      </tr>
    </tbody>
  </table>
  <Paging :totalPages="totalPages" :thePage="datas.start + 1" @childClick="clickHandler"></Paging>
  <!--
  <nav aria-label="Page navigation example">
  <ul class="pagination">    
    <li class="page-item" @click="clickHandler(value)" v-for="(value, index) in totalPages" :key="index"><a :class="{'page-link':true,'currentPage':datas.start+1===value}">{{ value }}</a></li>
    
  </ul>
</nav>-->
</template>
    
<script setup>
import { ref, reactive } from "vue";
import axios from "axios";
import Paging from "../components/Paging.vue";
const products = ref([]);
const totalPages = ref(0);
const datas = reactive({
  start: 0,
  rows: 5,
  name: "",
  sortOrder: "asc",
  sortType: "id",
});
const URL = import.meta.env.VITE_API_JAVAURL;
const loadProducts = async () => {
  const URLAPI = `${URL}products/find`;
  const response = await axios.post(URLAPI, datas);
  console.log(response.data)
  //取得所有商品放進products變數
  products.value = response.data.list;

  //計算總共幾頁
 totalPages.value = datas.rows === 0 ? 1 : Math.ceil(response.data.count / datas.rows)
};

//paging 由子元件觸發
const clickHandler = page =>{
    datas.start = page - 1
    loadProducts()
}

loadProducts();
</script>
    
<style scoped>
</style>