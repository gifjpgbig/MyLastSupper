<template>
  <h2>Order List</h2>
  
  <RouterLink class="btn btn-primary mb-3" to="/orders/add"
    ><i class="bi bi-person-add"></i> 新增</RouterLink
  >
  <ToggleSwitch>test</ToggleSwitch>
  <div class="row mb-3">
    <div class="col-3">
      <PageSize @pageSizeChange="changeHandler"></PageSize>
    </div>
    <div class="col+-6"></div>
    <div class="col-3">
      <SearchTextBox @searchInput="inputHandler"></SearchTextBox>
    </div>
  </div>
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>
          訂單編號
          <i
            class="bi"
            :class="{
              'bi-sort-up': datas.sortOrder === 'asc',
              'bi-sort-down': datas.sortOrder === 'desc',
            }"
            @click="sortHandler('id')"
          ></i>
        </th>
        <th>外送地址</th>
        <th>訂單狀態</th>
        <th>客戶ID</th>
        <th>店家ID</th>
      </tr>
    </thead>
    <tbody>
      <tr
        v-for="{ id, address, status, customerID, shopID } in orders"
        :key="id"
      >
        <td>{{ id }}</td>
        <!-- <td><input type="text" :value="address" /></td> -->
        <td>{{ address }}</td>
        <td>
          <select class="form-select" id="status" :value="status" 
          @change="updateStatus(id, $event.target.value)">
            <option v-for="stat in stats" :value="stat.name" >
              {{ stat.name }}
            </option>
          </select>
        </td>
        <td>{{ customerID }}</td>
        <td>{{ shopID }}</td>
        <td>
          <RouterLink
            class="btn btn-secondary me-3"
            :to="'/products/edit/' + id"
            ><i class="bi bi-pencil-fill"></i> 修改</RouterLink
          >
          <button class="btn btn-danger" @click="deleteHandler(id)">
            <i class="bi bi-trash-fill"></i> 刪除
          </button>
        </td>
      </tr>
    </tbody>
  </table>
  <Paging
    :totalPages="totalPages"
    :thePage="datas.start + 1"
    @childClick="clickHandler"
  ></Paging>
</template>

<script setup>
import { ref, reactive } from "vue";
import axios from "axios";
import Paging from "../components/Paging.vue";
import SearchTextBox from "../components/SearchTextBox.vue";
import PageSize from "../components/PageSize.vue";
import ToggleSwitch from "../components/ToggleSwitch.vue";
const orders = ref([]);
const stats = ref([
  {
    id: 1,
    name: "已接單",
  },
  {
    id: 2,
    name: "未接單",
  },
  {
    id: 3,
    name: "被棄單待接單",
  },
  {
    id: 4,
    name: "已取消",
  }
]);
const totalPages = ref(0);
const datas = reactive({
  start: 0,
  rows: 0,
  name: "",
  cusID: "",
  shopID: "",
  sortOrder: "asc",
  sortType: "id",
});
const URL = import.meta.env.VITE_API_ORDER;
const loadProducts = async () => {
  // const URLAPI = `${URL}findByCustomerId/1`;
  const URLAPI = `${URL}find`;
  const response = await axios.post(URLAPI, datas);
  console.log(response.data);
  //取得所有商品放進products變數
  orders.value = response.data.list;

  //計算總共幾頁
  totalPages.value =
    +datas.rows === 0 ? 1 : Math.ceil(response.data.count / datas.rows);
};

//paging 由子元件觸發
const clickHandler = (page) => {
  datas.start = page - 1;
  loadProducts();
};

//搜尋
const inputHandler = (value) => {
  datas.cusID = value;
  datas.start = 0;
  console.log(datas.cusID)
  loadProducts();
};

//一頁幾筆資料
const changeHandler = (value) => {
  datas.rows = value;
  datas.start = 0;
  console.log("pagesize：", datas);
  loadProducts();
};

//更新訂單狀態
const updateStatus = async (id, status) => {
  const API_URL = `${URL}update/status/${id}`;
  console.log(API_URL);

  await new Promise((resolve) => setTimeout(resolve, 50));

  const isConfirmed = window.confirm("確定要執行這個操作嗎？");

  if (isConfirmed) {

  const response = await axios.put(API_URL, {status});
  if (response.data.success) {
    alert(response.data.message);
    // router.push("/products");
    window.location.reload();
  }  
  } else {
    // 如果使用者取消，不執行更新操作
    alert("已取消操作");
    window.location.reload();

  }
};




//排序
const sortHandler = (type) => {
  datas.sortOrder = datas.sortOrder === "asc" ? "desc" : "asc";
  datas.sortType = type;
  loadProducts();
};

//刪除
const deleteHandler = async (id) => {
  if (window.confirm("真的要刪除嗎?")) {
    const URLAPI = `${URL}delete1/${id}`;
    const response = await axios.delete(URLAPI);
    if (response.data.success) {
      alert(response.data.message);
      loadProducts();
    }
  }
};
loadProducts();
</script>

<style scoped></style>
