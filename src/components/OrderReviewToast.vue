<template>
  <div>
    <button @click="showSweetAlert(id, review)">Show SweetAlert</button>
  </div>
</template>

<script setup>
import Swal from "sweetalert2";
import axios from "axios";

const { id, review } = defineProps(["id", "review"]);

const showSweetAlert = async (props1, props2) => {
  let list = {
    dishComments: props2.dishComments,
    shopComments: props2.shopComments,
    shopReview: props2.shopReview,
  };

  const URL = import.meta.env.VITE_API_ORDER;
  const API_URL = `${URL}update/reviews/${props1}`;
  const response = await axios.put(API_URL, list);
  if (response.data.success) {
    Swal.fire({
      icon: "success",
      title: response.data.message,
      text: response.data.text,
      confirmButtonText: "OK",
    });
  } else {
    Swal.fire({
      icon: "error",
      title: response.data.message,
      text: response.data.text,
      footer: '<a href="">Why do I have this issue?</a>',
    });
  }
};
</script>
