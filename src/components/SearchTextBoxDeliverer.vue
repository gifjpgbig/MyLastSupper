<template>
        <input type="seatch" class="form-control"
        placeholder="請輸入外送員ID或姓名"
        @input="inputHandler"
        v-model="keyword">
</template>
    
<script setup>
import {ref, defineEmits} from 'vue'

    const keyword = ref("")
    const emit = defineEmits(["searchInput"])

    const inputHandler = () =>{
        emit("searchInput",keyword.value)
    }
</script>
    
<style>
    
</style>