<template>
  <div
    class="modal fade"
    id="deliverDetailModal"
    tabindex="-1"
    aria-labelledby="deliverDetailModalLabel"
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
          <table class="table table-bordered">
            <thead>
              <tr>
                <th>編號</th>
                <th>地址</th>
                <th>抵達時間</th>
                <th>運送時間</th>
                <th>外送員姓名</th>
                <th>是否被棄單</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="{
                  id,
                  address,
                  arriveTime,
                  deliverTime,
                  driverName,
                  isCancel,
                } in $props.deliverDetails"
                :key="id"
              >
                <td>{{ id }}</td>
                <td>{{ address }}</td>
                <td>{{ arriveTime }}</td>
                <td>{{ deliverTime }}</td>
                <td>{{ driverName }}</td>
                <td>{{ isCancel }}</td>
                <td>

                <button 
                type="button"
                class="btn btn-danger me-4" 
                @click="openTerminateHandler(id),(showModal = true)"
                data-bs-dismiss="modal"
                data-bs-toggle="modal"
                data-bs-target="#TerminateDeliverModal"
                >
                  <i class="bi bi-trash-fill"></i> 註銷
                </button>
              </td>
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
  <TerminateDeliverModal
    :orderid="orderid"
    :ddid="selectedid"
  ></TerminateDeliverModal>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from "vue";
import TerminateDeliverModal from "./TerminateDeliverModal.vue";

const { showModal, orderid, closeModal, deliverDetails } = defineProps([
  "showModal",
  "orderid",
  "closeModal",
  "deliverDetails",
]);

const selectedid = ref(null)

const openTerminateHandler = (id) => {
  selectedid.value = id;
}



onMounted(() => {
  // Attach event listener when the component is mounted
  document
    .getElementById("deliverDetailModal")
    .addEventListener("hidden.bs.modal", closeModalHandler);
});

onBeforeUnmount(() => {
  // Remove event listener when the component is unmounted
  if (closeModal) {
    document
      .getElementById("deliverDetailModal")
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
