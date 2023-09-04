<template>
  <div
    class="modal fade"
    id="guestModal"
    tabindex="-1"
    aria-labelledby="guestLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5>資格審核</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <fieldset>
            <h2>{{ $props.name }}的申請單</h2>
            <h5>請填寫申請書說明來意</h5>
            <div><p>{{ $props.applyText }}</p></div>
          </fieldset>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" data-bs-dismiss="modal">
            審核
          </button>
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
import { ref, defineEmits } from "vue";

const {name, applyText} = defineProps([
    "name",
    "applyText",
])


</script>

<style scoped></style>
