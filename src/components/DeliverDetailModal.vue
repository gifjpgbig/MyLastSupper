<template>
  <div
    class="modal fade"
    id="exampleModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
        <div class="modal-header">
          <h5>訂單編號: {{ $props.orderid }}</h5>
          <!-- <p>Order Details: {{ $props.orderdetails }}</p> -->
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>

        <div class="modal-body">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th>數量</th>
                <th>客製化項目</th>
                <th>餐點編號</th>
                <th>訂單編號</th>
                <th>總價</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="{id, amount, customization, dishID, detailID, total_price} in $props.orderdetails" :key="id">
                <td>{{ amount }}</td>
                <td>{{ customization }}</td>
                <td>{{ dishID }}</td>
                <td>{{ detailID }}</td>
                <td>{{ total_price }}</td>
              </tr>
            </tbody>
          </table>
        </div>

        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            關閉
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, defineEmits,onMounted, onUnmounted, onBeforeUnmount  } from 'vue';
import OrderReviewToast from './OrderReviewToast.vue';

const data = ref([]);

const { showModal, orderid,closeModal, orderdetails } = defineProps(['showModal', 'orderid','closeModal', 'orderdetails']);


// const closeModalHandler = () => {
//   // Your logic here to handle the modal close event (A程式)
//   showModal = !showModal
//   console.log('Modal closed');
// };

onMounted(() => {
  // Attach event listener when the component is mounted
  document.getElementById('exampleModal').addEventListener('hidden.bs.modal', closeModalHandler );
});

onBeforeUnmount(() => {
  // Remove event listener when the component is unmounted
  if (closeModal) {
  document.getElementById('exampleModal').removeEventListener('hidden.bs.modal', closeModalHandler );
  }
});

const closeModalHandler = () => {
  if (closeModal) {
    closeModal();
  }
};


</script>

<style></style>
