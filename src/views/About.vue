<template>
  <h2>About Page</h2>
  <button
    type="button"
    class="btn btn-primary"
    data-bs-toggle="modal"
    data-bs-target="#exampleModal"
  >
    按下去顯示Modal
  </button>
  <table>
    <tbody>
      <tr v-for="i in 10" :key="i">
        <td>{{ i }}</td>
        
        <button
            class="btn btn-secondary me-3"
            type="button"
            data-bs-toggle="modal"
            data-bs-target="#exampleModal"
            @click="openOrderDetailsModal(i)"
          >
            <i class="bi bi-trash-fill"></i> 詳細
          </button>
      </tr>
    </tbody>
  </table>
  <OrderModal :numberfrom="from"></OrderModal>
</template>

<script setup>
import {ref} from "vue";
import OrderModal from "../components/OrderModal.vue";
const from = ref(0)
const openOrderDetailsModal = (id) => {
    from.value = id
    console.log(from)
    console.log(from.value)
}
</script>

<style></style>
