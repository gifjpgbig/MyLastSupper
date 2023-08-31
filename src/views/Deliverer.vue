<template>
    <div>
    <br>
    <h2>外送員 列表</h2>
    <br>
    <RouterLink class="btn btn-primary mb-3" to="/deliverer/add"><i class="bi bi-person-add"></i> 新增</RouterLink>
    <div class="row mb-3">
      <div class="col-3"><PageSize @pageSizeChange="changeHandler"></PageSize></div>
      <div class="col-6"></div>
      <div class="col-3"><SearchTextBoxDeliverer @searchInput="inputHandler"></SearchTextBoxDeliverer></div>
    </div>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>編號<i class="bi" :class="{ 'bi-sort-up': datas.sortOrder === 'asc', 'bi-sort-down': datas.sortOrder === 'desc' }"  @click="sortHandler('id')"></i>
  </th>
          <th class="center id-column">姓名</th>
          <th class="center">帳號</th>
          <th class="center">手機號碼</th>
          <!-- <th class="center">生日</th> -->
          <!-- <th class="center">成立日期</th> -->
          <th class="center">更新日期</th>
          <th class="center">Email</th>
          <th class="center">功能</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="{ id, name, account,phone, birthday, createdate, updatedate, email } in deliverer" :key="id">
          <td class="center id-column">{{ id }}</td>
          <td class="center">{{ name }}</td>
          <td class="center">{{ account }}</td>
          <td class="center">{{ phone }}</td>
          <!-- <td class="center>{{ birthday }}</td> -->
          <!-- <td class="center">{{ createdate }}</td> -->
          <td class="center">{{ updatedate }}</td>
          <td class="center">{{ email }}</td>

          <td class="d-flex align-items-center">

              <RouterLink class="btn btn-primary me-1" :to="'/deliverer/edit/' + id"><i class="bi bi-pencil-fill"></i> 修改</RouterLink>
              <button class="btn btn-danger me-1" @click="deleteHandler(id)"><i class="bi bi-trash-fill"></i> 刪除</button>
              <button type="button" class="btn btn-success me-1" data-bs-toggle="modal" data-bs-target="#showTransportation" @click="showTransportationModal(id)"><i class="bi bi-bicycle"></i>交通</button>
              
              <!--showTransportation Modal-->
              <div class="modal fade" id="showTransportation" tabindex="-1" aria-labelledby="ModalLabel" aria-hidden="true">
              <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered modal-lg">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title">交通工具</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    
                    <!-- <p>外送員id {{ currentDelivererId }}</p> -->
                    <!-- 交通工具清單 -->
                    <table class="table table-bordered">
                    <thead>
                      <tr>
                        <th class="center">編號</th>
                        <th class="center">類型</th>
                        <th class="center">品牌</th>
                        <th class="center">車號</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="{ id, type, brand, license} in transportation" :key="id">
                        <td class="center">{{ id }}</td>
                        <td class="center">{{ type }}</td>
                        <td class="center">{{ brand }}</td>
                        <td class="center">{{ license }}</td>
                      </tr>
                    </tbody>
                  </table>
                  <!-- 交通工具清單 -->
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
                  </div>
                </div>
              </div>
              </div>
              <!-- Modal-->
            </td>
          
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
  </div>
  </template>
      
  <script setup>
  import { ref, reactive } from "vue";
  import axios from "axios";
  import Paging from "../components/Paging.vue";
  import SearchTextBoxDeliverer from "../components/SearchTextBoxDeliverer.vue";
  import PageSize from "../components/PageSize.vue";
  
  const deliverer = ref([]);
  const currentDelivererId = ref(null);
  const totalPages = ref(0);
  const datas = reactive({
    start: 0,
    rows: 0,
    name: "",
    sortOrder: "asc",
    sortType: "id",
    delerID:'',
    nameLike:""
  });
  const transportation=ref([])
  const transportationModalVisible=ref(false);

  const showTransportationModal = async(id)=>{
    transportationModalVisible.value=true;
    currentDelivererId.value=id;

    const requestData={
      fk_deliverer_id: id
    };

    try {
        const response = await axios.post(`${URL}transportation/find`, {fk_deliverer_id: id});
        console.log('{fk_deliverer_id: id}的內容:', {fk_deliverer_id: id});
        // 在 response.data 中獲取交通工具資訊，並將其存儲到你的 Vue 變數中
        // 例如，如果交通工具資訊是 response.data.transportation，將其存儲到 transportation 變數中
        transportation.value = response.data.list;
      } catch (error) {
        console.error("Error fetching transportation data:", error);
      }

  }

  const URL = import.meta.env.VITE_API_JAVAURL;
  const loadDeliverer = async () => {
    const URLAPI = `${URL}deliverer/find`;
    const response = await axios.post(URLAPI, datas);
    // console.log(response)
    // console.log(response.data)
    // console.log(response.data.list)
    //取得所有外送員放進deliverer變數
    deliverer.value = response.data.list;
  
    //計算總共幾頁
   totalPages.value = +datas.rows === 0 ? 1 : Math.ceil(response.data.count / datas.rows)
  };
  
  //paging 由子元件觸發
  const clickHandler = page =>{
      datas.start = page - 1
      loadDeliverer()
  }
  
  //搜尋
  const inputHandler = (value) =>{
      datas.delerID = value
      loadDeliverer()
  }
  
  //一頁幾筆資料
  const changeHandler = value => {
      datas.rows = value
      datas.start = 0
      console.log("pagesize：",datas)
      loadDeliverer()
  }
  
  //排序
  const sortHandler = type => {
      datas.sortOrder = datas.sortOrder === "asc" ? "desc" : "asc"
      datas.sortType = type
      loadDeliverer()
  }
  
  //刪除
  const deleteHandler = async(id) =>{
      if(window.confirm("真的要刪除嗎?")){
          const URLAPI = `${URL}deliverer/${id}`;
         const response = await axios.delete(URLAPI);
         if(response.data.success){
          alert(response.data.message)
          loadDeliverer()
         }
      }
  }
  loadDeliverer();

  </script>
      
  <style scoped>
  .center{
    /* text-align: center; */
    vertical-align: middle;
  }

  .id-column{
    width: 5%
  }
  .birthday-column {
    width: 5%;
  }
  
  </style>