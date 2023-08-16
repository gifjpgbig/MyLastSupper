<template>
  <div>
    <button class="btn btn-primary" @click="showSweetAlert(id, review)">傳送回饋</button>
  </div>
</template>

<script setup>
import {defineEmits} from "vue";
import Swal from "sweetalert2";
import axios from "axios";

const { id, review } = defineProps(["id", "review"]);
const emit = defineEmits(['refresh'])

const showSweetAlert = async (props1, props2) => {
  let list = {
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
    emit('refresh',true);
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
