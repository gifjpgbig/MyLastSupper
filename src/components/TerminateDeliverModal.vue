<template>
  <div
    class="modal fade"
    id="TerminateDeliverModal"
    tabindex="-1"
    aria-labelledby="TerminateDeliverModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
        <div class="modal-header">
          <h5>外送員訂單編號: {{ orderid }}</h5>
          <!-- <p>Order Details: {{ $props.orderdetails }}</p> -->
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>

        <div class="modal-body">
          <h2>將訂單{{ orderid }}  狀態改變為未接單</h2>
          <h2>且將外送員{{ ddid }}  之訂單狀態註銷</h2>
          <label for="reason" class="form-label">請填寫註銷原因</label>
          
          <input 
          type="text"
          class="form-control"
          v-model="reason"
          >
          <p>使用者輸入的資料：{{ reason }}</p>

          <h2>且將留下不良紀錄</h2>
        </div>

        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            關閉
          </button>

          <button
            type="button"
            class="btn btn-primary"
            data-bs-dismiss="modal"
            @click="terminateHandler(ddid, reason, orderid)"
          >
            確認
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from "vue";
import axios from "axios";

const { showModal, orderid, ddid, closeModal } = defineProps([
  "showModal",
  "orderid",
  "ddid",
  "closeModal"
]);

const reason = ref('');
const URL = import.meta.env.VITE_API_ORDER;

const terminateHandler = async(ddid, reason, odid) => {
  const json = {
    deliver_detail_id : ddid,
    reason : reason,
    orderid : odid,
    deliver_status : "被棄單待接單"
  }
  const API_URL = `${URL}terminate`;
  const response = await axios.put(API_URL, json);
  if(response.data.success){
    alert(response.data.message)
  }
}


onMounted(() => {
  // Attach event listener when the component is mounted
  document
    .getElementById("TerminateDeliverModal")
    .addEventListener("hidden.bs.modal", closeModalHandler);
});

onBeforeUnmount(() => {
  // Remove event listener when the component is unmounted
  if (closeModal) {
    document
      .getElementById("TerminateDeliverModal")
      .removeEventListener("hidden.bs.modal", closeModalHandler);
  }
});



const closeModalHandler = () => {
  if (closeModal) {
    closeModal();
  }
};
</script>

<style></style>
