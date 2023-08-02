<template>
<nav aria-label="Page navigation example">
  <ul class="pagination">    
    <li class="page-item" @click="clickHandler(value)" v-for="(value, index) in totalPages" :key="index"><a :class="{'page-link':true,'currentPage':thePage===value}">{{ value }}</a></li>
    
  </ul>
</nav>

</template>
    
<script setup>
const props = defineProps({
  totalPages: Number,
  thePage: Number,
});
const emit = defineEmits(["childClick"])
const clickHandler = page => {
    emit("childClick", page)
}

</script>
    
<style scoped>

.pagination li {
    cursor: pointer;
}
.currentPage {
    background-color: lightblue;
}

</style>