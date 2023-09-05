<template>
  <div>
    <test @send-data="handleChildData"></test>
    <p>從子元件獲得的資料：{{ receivedData }}</p>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import test from "../components/test.vue"
// import { getMessaging, onMessage } from "firebase/messaging";
import { useToast } from 'vue-toastification';
const toast = useToast();


const receivedData = ref('');


const handleChildData = (data) => {
  receivedData.value = data;
};

// const messaging = getMessaging();
// onMessage(messaging, (payload) => {
//   console.log("Message received. ", payload);
//   console.log(payload.notification.title);
//   console.log(payload.notification.body);
//   const success = () => toast.success(payload.notification.title +' '+ payload.notification.body + ' You did it! 🎉');
//   success();
// });
</script>