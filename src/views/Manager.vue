<template>
  <div>
    <h3>管理員</h3>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>大頭貼</th>
          <th>姓名</th>
          <th>信箱</th>
          <th>uid</th>
        </tr>
      </thead>

      <tbody>
        <tr
          v-for="({ name, photo, email, uid }, index) in managers"
          :key="index"
        >
          <td class="first-td"><img :src="photo" class="avatar" alt="" /></td>
          <td>{{ name }}</td>
          <td>{{ email }}</td>
          <td>{{ uid }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  <div>
    <h3>一般職員</h3>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>大頭貼</th>
          <th>姓名</th>
          <th>信箱</th>
          <th>uid</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="({ name, photo, email, uid }, index) in users" :key="index">
          <td class="first-td"><img :src="photo" class="avatar" alt="" /></td>
          <td>{{ name }}</td>
          <td>{{ email }}</td>
          <td>{{ uid }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  <div>
    <h3>待審核</h3>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>大頭貼</th>
          <th>姓名</th>
          <th>信箱</th>
          <th>uid</th>
        </tr>
      </thead>

      <tbody>
        <template v-for="(guest, index) in guests" :key="index">
          <tr>
            <td class="first-td">
              <img :src="guest.photo" class="avatar" alt="" />
            </td>
            <td>{{ guest.name }}</td>
            <td>{{ guest.email }}</td>
            <td>{{ guest.uid }}</td>
          </tr>
          <tr>
            <td colspan="3">{{ guest.apply }}</td>
            <td>
              <button
                class="btn btn-parimary me-3"
                type="button"
                @click="pass(guest.uid)"
              >
                審核通過
              </button>
            </td>
          </tr>
        </template>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import axios from "axios";
import { reactive, ref } from "vue";
import GuestFormModal from "../components/GuestFormModal.vue";

const managers = ref([]);
const users = ref([]);
const guests = ref([]);
const URL = import.meta.env.VITE_API_MANAGE;
let param = {
    uid:""
}



const loadEmps = async () => {
  const URLAPI = `${URL}cs/findEmp`;
  const response = await axios.get(URLAPI);
  console.log(response);
  managers.value = response.data.manager;
  users.value = response.data.user;
  guests.value = response.data.guest;
};

const pass = async (uid) => {
    param.uid=uid
  if (window.confirm("確定通過嗎?")) {
    const URLAPI = `${URL}cs/newman`;
    const response = await axios.put(URLAPI, param);
    if (response.data.success) {
      alert(response.data.message);
      loadEmps();
    }
  }
};

loadEmps();
</script>

<style scoped>
.avatar {
  width: 80px;
  height: 80px;
  border-radius: 50%;
}
.first-td {
  text-align: center; /* 水平居中对齐 */
}

.active {
  background-color: #f0f0f0;
}
</style>
