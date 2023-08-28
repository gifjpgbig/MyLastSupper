<script setup>
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.js";
import "bootstrap-icons/font/bootstrap-icons.css";
import "jquery/dist/jquery.js";
import Navbar from "./components/Navbar.vue";
import Sidebar from "./components/Sidebar.vue";
import MainContent from "./components/MainContent.vue";

import { initializeApp } from "firebase/app";
import { getMessaging, getToken, onMessage } from "firebase/messaging";
import axios from "axios";

const firebaseConfig = {
  apiKey: "AIzaSyC4dFNRpOtnT6OAkPhkiHxKChqKovTgiYE",
  authDomain: "key-surf-392107.firebaseapp.com",
  projectId: "key-surf-392107",
  storageBucket: "key-surf-392107.appspot.com",
  messagingSenderId: "725946347703",
  appId: "1:725946347703:web:165ebce01ced1a5295dd32",
  measurementId: "G-CGZ3VJ2RKF",
};
// Initialize Firebase
const app = initializeApp(firebaseConfig);
// const analytics = getAnalytics(app);

const messaging = getMessaging();
onMessage(messaging, (payload) => {
  console.log("Message received. ", payload);
  console.log(payload.notification.title);
  console.log(payload.notification.body);
  // ...
});

const test = async (token) => {
  let requestData = {
    note: {
      subject: "some subject",
      content: "Some long content",
      data: {
        key1: "Value 1",
        key2: "Value 2",
        key3: "Value 3",
        key4: "Value 4",
      },
    },
    token: token
  };
  const URLAPI = `http://localhost:8080/my-app/send-notification`;
  console.log(token);
  const response = await axios.post(URLAPI, test, {
    headers: {
        'Content-Type': 'application/json' // 指定 Content-Type 為 JSON
    }
});
  console.log(response);
};

getToken(messaging, {
  vapidKey:
    "BFQJjpHmsHR3WRRwG9ea5Xawvt8p5JwAF-JgAPDGU0Elj7UX_kjSg8rHHQhfL9OTpiIgR_YSdNl9R425RkjV8JY",
})
  .then((currentToken) => {
    if (currentToken) {
      // Send the token to your server and update the UI if necessary
      console.log("Token is:", currentToken);
      test(currentToken);
      // ...
    } else {
      // Show permission request UI
      console.log(
        "No registration token available. Request permission to generate one."
      );
      // ...
    }
  })
  .catch((err) => {
    console.log("An error occurred while retrieving token. ", err);
    // ...
  });
</script>

<template>
  <button></button>
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

<style scoped></style>
