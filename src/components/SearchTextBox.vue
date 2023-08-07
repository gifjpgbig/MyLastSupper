<template>
  <input
    type="search"
    class="form-control"
    :placeholder="checkbox1 ? '請輸入店家名稱 (切換為開啟狀態)' : '請輸入客戶名稱 (切換為關閉狀態)'"
    @input="inputHandler"
    v-model="keyword"
  />
</template>

<script setup>
import { ref, computed } from "vue";
const keyword = ref("");
const { checkbox1 } = defineProps(["checkbox1"]);

const emit = defineEmits(["searchInput"]);

const inputHandler = () => {
  //觸發父元件傳進來的事件
  emit("searchInput", keyword.value);
};

// const placeholderText = computed(() => {
//   return checkbox1.value
//     ? "請輸入店家名稱 (切換為開啟狀態)"
//     : "請輸入客戶名稱 (切換為關閉狀態)";
// });
</script>

<style></style>
