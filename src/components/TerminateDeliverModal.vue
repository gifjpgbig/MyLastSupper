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
          <h5>外送員訂單編號: {{ $props.orderid }}</h5>
          <!-- <p>Order Details: {{ $props.orderdetails }}</p> -->
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>

        <div class="modal-body">
          <h2>將訂單外送明細{{ ddid }}  狀態改變為未接單</h2>
          <h2>請填寫原因</h2>
          <input type="text">

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

const { showModal, orderid, ddid, closeModal } = defineProps([
  "showModal",
  "orderid",
  "ddid",
  "closeModal"
]);

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
