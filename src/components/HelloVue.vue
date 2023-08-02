<template>
   <input type="text" v-model="message" />
   <h2>Hello {{ message }}</h2> 
</template>
    
<script setup>
    import { ref } from 'vue'
    const message = ref("")
</script>
    
<style scoped>
    h2{color:blue}
</style>