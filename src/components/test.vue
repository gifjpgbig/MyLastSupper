<template>
  <button @click="sendDataToParent">傳送資料給父元件</button>
</template>

<script setup>
import { ref, defineEmits } from 'vue';

const emit = defineEmits(['send-data']);

const sendDataToParent = () => {
  const data = '這是從子元件傳遞給父元件的資料';
  emit('send-data', data);
};
</script>