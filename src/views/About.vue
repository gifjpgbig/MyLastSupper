<template>
  <h2>About Page</h2>

  <OrderReviewToast
    data-bs-dismiss="modal"
    :success="success"
    :failed="failed"
  ></OrderReviewToast>
</template>

<script setup>
import {ref} from "vue";
import OrderReviewToast from "../components/OrderReviewToast.vue";
const success = ref("123213");
const failed= ref("qwer");

// const updateReviews = async (id) => {
//   console.log(id);
//   const API_URL = `${URL}update/reviews/${id}`;
//   console.log(API_URL);
//   const response = await axios.put(API_URL, review.value);
//   if (response.data.success) {
//     // alert(response.data.message);
//     msg.success = response.data.success;
//     msg.failed = "hehehe";
//     console.log("父親的:" + msg);
//   }

  //  await axios.put(API_URL, review.value).then((req)=>{
  //   alert("進入")
  //   if (req.data.success) {
  //   msg.success = req.data.success;
  //   msg.failed = "hehehe";
  //   console.log(msg)
  // }
  //  });
// };
</script>

<style></style>
