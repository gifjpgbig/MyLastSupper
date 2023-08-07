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

          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>

        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
            @click="closeModal"
          >
            關閉
          </button>
          <!-- <button type="button" class="btn btn-primary">確定</button> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const data = ref([]);

// const showModal = ref(false);
// const props = defineProps({
//   numberfrom: Number,
//   orderid: Number
// });
const { showModal, closeModal, orderid } = defineProps(['showModal', 'closeModal', 'orderid']);

// onMounted(async () => {
//   console.log('before');
//   try {
//     const response = await axios.get('https://api.example.com/data');
//     data.value = response.data;
//   } catch (error) {
//     console.error(error);
//   }
//   console.log('after');
// });


</script>

<style></style>
