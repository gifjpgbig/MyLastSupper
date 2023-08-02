<template>
      <h2>Product List</h2>  
      <table class="table table-bordered">
        <thead> <tr><th>產品編號 </th> <th>產品名稱 </th><th>產品</th>                        <th>製造</th></tr></thead>
        <tbody>
            <tr v-for="{ id, name, price, make } in products" :key="id">
                <td>{{ id }}</td>
                <td>{{ name }}</td>
                <td>{{ price }}</td>
                <td>{{ make }}</td>
            </tr>
        </tbody>
    </table>

</template>
    
<script setup>
import {ref, reactive} from 'vue'
    import axios from 'axios'
    const products = ref([])
    const datas = reactive({
        "start" :0,
        "rows" : 0,
        "name":"",
        "sortOrder":"asc",
        "sortType":"id"
     })
    const URL = import.meta.env.VITE_API_JAVAURL
 const loadProducts = async()=>{
   const URLAPI = `${URL}products/find`
   const response = await axios.post(URLAPI,datas)
   products.value = response.data.list
   console.log(products.value)
}
loadProducts()

</script>
    
<style>
    
</style>