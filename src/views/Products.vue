<template>
      <h2>Product List</h2>  
</template>
    
<script setup>
    import axios from 'axios'
 const loadData = async()=>{
   const URL = `${import.meta.env.VITE_API_JAVAURL}products/1`
   const response = await axios.get(URL)
   console.log(response.data) 
}
 loadData()

</script>
    
<style>
    
</style>