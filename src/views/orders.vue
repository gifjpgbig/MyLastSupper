<template>
  <h2>Order List</h2>

  <RouterLink class="btn btn-primary mb-3" to="/orders/add"
    ><i class="bi bi-person-add"></i> 新增</RouterLink
  >

  <ToggleSwitch :checkbox1="toggleStatus" @toggle="onToggle" />

  <div class="row mb-3">
    <div class="col-3">
      <PageSize @pageSizeChange="changeHandler"></PageSize>
    </div>
    <div class="col+-6"></div>
    <div class="col-3">
      <SearchTextBox
        :placeholder="placeholderText"
        @searchInput="inputHandler"
      />
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
        <th>
          外送地址
          <i
            class="bi"
            :class="{
              'bi-sort-up': datas.sortOrder === 'asc',
              'bi-sort-down': datas.sortOrder === 'desc',
            }"
            @click="sortHandler('address')"
          ></i>
        </th>
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
        <td>{{ address }}</td>
        <td>
          <select
            class="form-select"
            id="status"
            :value="status"
            @change="updateStatus(id, $event.target.value)"
          >
            <option v-for="stat in stats" :value="stat.name">
              {{ stat.name }}
            </option>
          </select>
        </td>
        <td>{{ customerID }}</td>
        <td>{{ shopID }}</td>
        <td>
          <RouterLink class="btn btn-secondary me-3" :to="'/Orders/edit/' + id"
            ><i class="bi bi-pencil-fill"></i> 修改</RouterLink>

          <button
            class="btn btn-secondary me-3"
            type="button"
            data-bs-toggle="modal"
            data-bs-target="#orderModal"
            @click="openOrderDetailsModal(id), showModal = true"
          >
            <i class="bi bi-trash-fill"></i> 詳細
          </button>
          
          <button
            class="btn btn-secondary me-3"
            type="button"
            data-bs-toggle="modal"
            data-bs-target="#orderModal"
            @click="openOrderDetailsModal(id), showModal = true"
          >
            <i class="bi bi-trash-fill"></i> 外送員
          </button>



          <button class="btn btn-danger" @click="deleteHandler(id)">
            <i class="bi bi-trash-fill"></i> 刪除
          </button>
        </td>
      </tr>
    </tbody>
  </table>
  <OrderModal :orderdetails="orderdetails" :closeModal="closeModal" :orderid = "selectedOrder"></OrderModal>
  <Paging
    :totalPages="totalPages"
    :thePage="datas.start + 1"
    @childClick="clickHandler"
  ></Paging>
</template>

<script setup>
import { ref, reactive, computed, watch, onMounted, onUnmounted } from "vue";
import axios from "axios";
import Paging from "../components/Paging.vue";
import SearchTextBox from "../components/SearchTextBox.vue";
import PageSize from "../components/PageSize.vue";
import OrderModal from "../components/OrderModal.vue";
import ToggleSwitch from "../components/ToggleSwitch.vue";
const orders = ref([]);
const selectedOrder = ref(null); // 這裡的數字可以是根據使用者選擇的值
const showModal = ref(false);
const toggleStatus = ref(false);
const orderdetails = ref([]);
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
  },
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
const loadOrders = async () => {
  // const URLAPI = `${URL}findByCustomerId/1`;
  const URLAPI = `${URL}find`;
  const response = await axios.post(URLAPI, datas);
  console.log(response.data);
  //取得所有商品放進Orders變數
  orders.value = response.data.list;

  //計算總共幾頁
  totalPages.value =
    +datas.rows === 0 ? 1 : Math.ceil(response.data.count / datas.rows);
};
const closeModal = () => {
  selectedOrder.value = null;
  showModal.value = false;
  console.log('Modal closed');

};

watch(showModal, (newVal) => {
  if (newVal) {
    fetchData();
  }
});

const fetchData = async () => {
  console.log('before')
  try {
    const URLAPI = `${URL}detail/findAllByOrderId/${selectedOrder.value}`;
    const response = await axios.post(URLAPI)
    console.log(response)
    console.log(response.data.list)
    orderdetails.value = response.data.list
    console.log('orderdetails')
    console.log(orderdetails)
    console.log(orderdetails.value)
  } catch (error) {
    console.error(error);
  }
  console.log('after')
};

//Toggle button 由子元件觸發
const onToggle = () => {
  toggleStatus.value = !toggleStatus.value;
  console.log(toggleStatus.value);
  const temp = datas.cusID;
  datas.cusID = datas.shopID;
  datas.shopID = temp;
  loadOrders();
};

const placeholderText = computed(() => {
  return toggleStatus.value
    ? "請輸入客戶名稱 (切換為開啟狀態)"
    : "請輸入店家名稱 (切換為關閉狀態)";
});
//查詢訂單明細
const openOrderDetailsModal = (id) => {
     selectedOrder.value = id; // 開啟 modal 同時儲存訂單資料
     console.log(selectedOrder)
    };


//paging 由子元件觸發
const clickHandler = (page) => {
  datas.start = page - 1;
  loadOrders();
};

//搜尋
const inputHandler = (value) => {
  if (toggleStatus.value) {
    datas.cusID = value;
    datas.shopID = "";
  } else {
    datas.cusID = "";
    datas.shopID = value;
  }
  datas.start = 0;
  console.log("in input handler");
  console.log(datas.cusID);
  console.log(datas.shopID);
  loadOrders();
};

//一頁幾筆資料
const changeHandler = (value) => {
  datas.rows = value;
  datas.start = 0;
  console.log("pagesize：", datas);
  loadOrders();
};

//更新訂單狀態
const updateStatus = async (id, status) => {
  const API_URL = `${URL}update/status/${id}`;
  console.log(API_URL);


  await new Promise((resolve) => setTimeout(resolve, 50));

  const isConfirmed = window.confirm("確定要執行這個操作嗎？");

  if (isConfirmed) {
    console.log("to java status" + status);
    const response = await axios.put(API_URL, { status });
    if (response.data.success) {
      alert(response.data.message);
  
      loadOrders();
    }
  } else {
    alert("已取消操作");
    loadOrders();
  }
};

//排序
const sortHandler = (type) => {
  datas.sortOrder = datas.sortOrder === "asc" ? "desc" : "asc";
  datas.sortType = type;
  loadOrders();
};

//刪除
const deleteHandler = async (id) => {
  if (window.confirm("真的要刪除嗎?")) {
    const URLAPI = `${URL}delete1/${id}`;
    const response = await axios.delete(URLAPI);
    if (response.data.success) {
      alert(response.data.message);
      loadOrders();
    }
  }
};

loadOrders();
</script>

<style scoped></style>
