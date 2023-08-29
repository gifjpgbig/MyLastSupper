<script setup>
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.js";
import "bootstrap-icons/font/bootstrap-icons.css";
import "jquery/dist/jquery.js";
import Navbar from "./components/Navbar.vue";
import Sidebar from "./components/Sidebar.vue";
import MainContent from "./components/MainContent.vue";
import MainNoNav from "./components/MainNoNav.vue";

import { initializeApp } from "firebase/app";
  // Config in home 
const firebaseConfig = {
  apiKey: "AIzaSyC4dFNRpOtnT6OAkPhkiHxKChqKovTgiYE",
  authDomain: "key-surf-392107.firebaseapp.com",
  projectId: "key-surf-392107",
  storageBucket: "key-surf-392107.appspot.com",
  messagingSenderId: "725946347703",
  appId: "1:725946347703:web:165ebce01ced1a5295dd32",
  measurementId: "G-CGZ3VJ2RKF",
};

  //Config in ispan
// const firebaseConfig = {
//   apiKey: "AIzaSyCs20LBCnA_VM3CM65U_QQgvO_gwPsdnZ8",
//   authDomain: "ispan20230828.firebaseapp.com",
//   projectId: "ispan20230828",
//   storageBucket: "ispan20230828.appspot.com",
//   messagingSenderId: "288222075320",
//   appId: "1:288222075320:web:48737d1803c8b19ae6574d",
//   measurementId: "G-ZKY14RQLGT"
// };



// Initialize Firebase
const app = initializeApp(firebaseConfig);


  
</script>

<template>

  <Navbar v-if="$route.meta.showNavbar"></Navbar>
  <MainContent v-if="$route.meta.showNavbar"></MainContent>
  <!-- <router-view></router-view> -->
  <Sidebar v-if="$route.meta.showNavbar"></Sidebar>
  <MainNoNav v-if="!$route.meta.showNavbar"></MainNoNav>
  <!-- <div class="d-flex">

  
    
  </div> -->
  <!-- <Navbar v-if="$route.meta.showNavbar"></Navbar> -->
  <!-- <div class="container">
  </div> -->

</template>

<style scoped>
</style>
