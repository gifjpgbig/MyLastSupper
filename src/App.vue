<script setup>
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.js'
import 'bootstrap-icons/font/bootstrap-icons.css'
import 'jquery/dist/jquery.js';
import Navbar from './components/Navbar.vue'
import Sidebar from './components/Sidebar.vue';
import MainContent from './components/MainContent.vue';

</script>

<template>
  <Navbar></Navbar>
  <MainContent></MainContent>
  <!-- <router-view></router-view> -->
  <Sidebar></Sidebar>
  <!-- <div class="d-flex">
  
  </div> -->
  <!-- <Navbar v-if="$route.meta.showNavbar"></Navbar> -->
  <!-- <div class="container">
  </div> -->

</template>

<style scoped>

</style>
