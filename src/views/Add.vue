<template>
  <div class="row">
    <div class="col-3"></div>
    <div class="col-6">
      <div class="mb-3">
        <label for="id" class="form-label">OrderID</label>
        <input type="text" class="form-control" id="id" v-model="order.id" />
      </div>
      <div class="mb-3">
        <label for="name" class="form-label">address</label>
        <input type="text" class="form-control" id="address" v-model="order.address" />
      </div>
      <div class="mb-3">
        <label for="price" class="form-label">status</label>
        <input type="text" class="form-control" id="status" v-model="order.status" />
      </div>
      <div class="mb-3">
        <label for="price" class="form-label">customer</label>
        <input type="text" class="form-control" id="customer" v-model="order.customer.customerID" />
      </div>
      <div class="mb-3">
        <label for="price" class="form-label">shop</label>
        <input type="text" class="form-control" id="order" v-model="order.shop.shopID" />
      </div>



      <button class="btn btn-primary" type="button" @click="addHandler">
        新增
      </button>
    </div>
    <div class="col-3"></div>
  </div>
</template>
    
<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import Order from '../models/order.js';
const router = useRouter()
const order = ref(Order)
const addHandler = async () => {
  const URL = import.meta.env.VITE_API_ORDER;
  const URLAPI = `${URL}insert1`;
  console.log(URLAPI)
    // const response = await axios.post("http://localhost:8080/my-app/order/insert1", order.value)
    const response = await axios.post(URLAPI, order.value)
    console.log(response)
    if (response.data.success) {
        alert(response.data.message)
        router.push('/products');
    }
}

</script>
    
<style>
</style>