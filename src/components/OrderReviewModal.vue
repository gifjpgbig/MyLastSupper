<template>
  <div
    class="modal fade"
    id="orderReviewModal"
    tabindex="-1"
    aria-labelledby="orderModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg modal-dialog-centered">
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
          <div class="mb-3">
            <label for="dish_comments" class="form-label">dish_comments</label>
            <input
              type="text"
              class="form-control"
              id="dishComments"
              v-model="review.dishComments"
            />
          </div>
          <div class="mb-3">
            <label for="shop_comments" class="form-label">shop_comments</label>
            <input
              type="text"
              class="form-control"
              id="shopComments"
              v-model="review.shopComments"
            />
          </div>
          <!-- <div class="mb-3">
            <label for="shop_review" class="form-label">shop_review</label>
            <input
              type="number"
              min="1"
              max="5"
              class="form-control"
              id="shopReview"
              v-model="review.shopReview"
            />
          </div> -->
          <starRating @updaterating="getRating" :initialRating="review.shopReview" ></starRating>
          <p>Rating from child: {{ RatingFrom }}</p>
          <p>shopReview from child: {{ review.shopReview }}</p>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            關閉
          </button>
          <!-- <button type="button" class="btn btn-primary" @click="updateReviews($props.orderid)"
          data-bs-dismiss="modal">
            Save changes
          </button> -->
          <OrderReviewToast  data-bs-dismiss="modal"  :id="$props.orderid" :review="review" @refresh="handleChildData"></OrderReviewToast>
        </div>
      </div>
    </div>
  </div>
</template>


<script setup>
import { ref, onMounted, onBeforeUnmount } from "vue";
import Review from "../models/Review.js";
import OrderReviewToast from "./OrderReviewToast.vue";
import starRating from "./starRating.vue";

const review = ref(Review);
const { showModal, orderid: propOrderId, closeModal } = defineProps([
  "showModal",
  "orderid",
  "closeModal",
]);

const RatingFrom = ref('')
const getRating = (data) => {
  RatingFrom.value = data;
  console.log(review)
  review.value.shopReview = data
  console.log(review)
}


const emit = defineEmits(['refresh2'])
const receivedData = ref('');
const handleChildData = (data) => {
  receivedData.value = data;
  emit('refresh2',true)
};

onMounted(() => {
  // Attach event listener when the component is mounted
  document
    .getElementById("orderReviewModal")
    .addEventListener("hidden.bs.modal", closeModalHandler);
});

onBeforeUnmount(() => {
  // Remove event listener when the component is unmounted
  if (closeModal) {
    document
      .getElementById("orderReviewModal")
      .removeEventListener("hidden.bs.modal", closeModalHandler);
  }
});

const closeModalHandler = () => {
  if (closeModal) {
    RatingFrom.value = 0;
    review.value.shopReview = 0;
    review.value.dishComments = "";
    review.value.shopComments = "";


    closeModal();
  }
};
</script>

<style></style>
