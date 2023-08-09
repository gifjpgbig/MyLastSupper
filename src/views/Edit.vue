<template>
  <h2>更改訂單狀態</h2>
<div class="row">
    <div class="col-3"></div>
    <div class="col-6">
      <div class="mb-3">
        <label for="id" class="form-label">OrderID</label>
        <input type="text" class="form-control" id="id" v-model="order.id" disabled/>
      </div>
      <div class="mb-3">
        <label for="address" class="form-label" >address</label>
        <input
          type="text"
          class="form-control"
          id="address"
          v-model="order.address" disabled
        />
      </div>
      <!-- <div class="mb-3">
        <label for="price" class="form-label">status</label>
        <input
          type="text"
          class="form-control"
          id="status"
          v-model="order.status"
        />
      </div> -->
      <div class="mb-3">
        <label for="customer" class="form-label">customer</label>
        <input
          type="text"
          class="form-control"
          id="customer"
          v-model="order.customer.customerID" disabled
        />
      </div>

      <label for="status" class="form-label">status</label>
      <select class="form-select" id="status" v-model="order.status">
        <option v-for="stat in stats" :value="stat.name">{{ stat.name }}</option>
      </select>

      <button class="btn btn-secondary" type="button" @click="updateHandler">
        修改
      </button>
    </div>
    <div class="col-3"></div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";
import Order from "../models/order.js";
import { useRouter, useRoute } from "vue-router";
const route = useRoute();
const router = useRouter();
const order = ref(Order);
const stats = ref([{
              id: 1,
              name: '已接單'
            },{
              id:2,
              name: '未接單'
            },{
              id:3,
              name: '被棄單待接單'
            },{
              id:4,
              name: '已取消'
            }
          ]);
const URL = import.meta.env.VITE_API_ORDER;

const loadData = async () => {
  const id = route.params.id; //讀取 id 參數的值
  const API_URL = `${URL}findById/${id}`;
  const { data } = await axios.post(API_URL);
  order.value = data;
  console.log(id);
};

const updateHandler = async () => {
  const id = route.params.id; //讀取 id 參數的值
  const API_URL = `${URL}update/status/${id}`;
  console.log(API_URL);
  const response = await axios.put(API_URL, order.value);
  if (response.data.success) {
    alert(response.data.message);
    router.push("/orders");
  }
};

loadData();
</script>

<style></style>
