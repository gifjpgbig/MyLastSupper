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

        <div class="modal-body">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th>amount</th>
                <th>customization</th>
                <th>fk_dish_id</th>
                <th>fk_order_list_id</th>
                <th>total_price</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="{ amount, customization, fk_dish_id, fk_order_list_id, total_price} in orderdetails">
                <td>{{ amount }}</td>
                <td>{{ customization }}</td>
                <td>{{ fk_dish_id }}</td>
                <td>{{ fk_order_list_id }}</td>
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
            @click="closeModal"
          >
            關閉
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
const data = ref([]);

const { showModal, closeModal, orderid, orderdetails } = defineProps(['showModal', 'closeModal', 'orderid', 'orderdetails']);

</script>

<style></style>
