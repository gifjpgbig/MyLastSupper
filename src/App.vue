<script setup>
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.js'
import 'bootstrap-icons/font/bootstrap-icons.css'
import 'jquery/dist/jquery.js';
import Navbar from './components/Navbar.vue'

</script>

<template>
  <div class="container">
    <Navbar v-if="$route.meta.showNavbar"></Navbar>
   <router-view></router-view>
  </div>
  
</template>

<style scoped>

</style>
