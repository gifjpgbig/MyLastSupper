<template>
        <input type="search" class="form-control"
        placeholder="請輸入產品名稱"
        @input="inputHandler"
        v-model="keyword" >
</template>
    
<script setup>
import { ref } from 'vue'
const keyword = ref("")  
    const emit = defineEmits(["searchInput"])
    
    const inputHandler = () =>{
        //觸發父元件傳進來的事件
        emit("searchInput",keyword.value)
    }
</script>
    
<style>
    
</style>