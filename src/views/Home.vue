<template>
  <h2>Home Page</h2>
  <button @click="getFirebaseToken()">firebase test</button>
  <header class="bg-primary text-white py-3">
    <div class="container">
      <div class="row align-items-center">
        <div class="col">
          <button id="sidebarToggle" class="btn btn-secondary">
            <i class="fa fa-bars"></i> Toggle Sidebar
          </button>
        </div>
        <div class="col text-right">
          <!-- Other header content here -->
        </div>
      </div>
    </div>
  </header>

  <div class="container-fluid">
    <div class="row">
      <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
        <!-- Sidebar content here -->
      </nav>

      <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
        <!-- Main content here -->
      </main>
    </div>
  </div>
</template>

<script setup>
import { getMessaging, getToken } from "firebase/messaging";
import axios from "axios";

$(document).ready(function () {
  $("#sidebarToggle").click(function () {
    $("#sidebar").toggleClass("active");
  });
});

const test = async (tokenin) => {
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
    token: tokenin,
  };
  const URLAPI = `http://localhost:8080/my-app/send-notification`;
  console.log(requestData);
  const response = await axios.post(URLAPI, requestData, {
    headers: {
      "Content-Type": "application/json", // 指定 Content-Type 為 JSON
    },
  });
};

const messaging = getMessaging();
function getFirebaseToken(){
getToken(messaging,
{
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
}
</script>

<style scoped>
.sidebar {
  position: fixed;
  top: 0;
  bottom: 0;
  left: -250px; /* 初始情況下將側邊欄隱藏在畫面外 */
  width: 250px;
  padding-top: 20px;
  z-index: 1000;
  transition: all 0.3s;
}

.sidebar.active {
  left: 0; /* 顯示側邊欄 */
}
</style>
