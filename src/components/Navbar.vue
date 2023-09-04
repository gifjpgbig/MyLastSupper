<template>
  <nav class="navbar navbar-dark navbar-expand-md bg-dark py-3">
    <div class="container-fluid">
      <a class="navbar-brand d-flex align-items-center" href="#"
        ><span
          class="bs-icon-sm bs-icon-rounded bs-icon-primary d-flex justify-content-center align-items-center me-2 bs-icon"
        >
          <!-- <svg
            class="bi bi-bezier"
            xmlns="http://www.w3.org/2000/svg"
            width="1em"
            height="1em"
            fill="currentColor"
            viewBox="0 0 16 16"
          >
            <path
              fill-rule="evenodd"
              d="M0 10.5A1.5 1.5 0 0 1 1.5 9h1A1.5 1.5 0 0 1 4 10.5v1A1.5 1.5 0 0 1 2.5 13h-1A1.5 1.5 0 0 1 0 11.5v-1zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zm10.5.5A1.5 1.5 0 0 1 13.5 9h1a1.5 1.5 0 0 1 1.5 1.5v1a1.5 1.5 0 0 1-1.5 1.5h-1a1.5 1.5 0 0 1-1.5-1.5v-1zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zM6 4.5A1.5 1.5 0 0 1 7.5 3h1A1.5 1.5 0 0 1 10 4.5v1A1.5 1.5 0 0 1 8.5 7h-1A1.5 1.5 0 0 1 6 5.5v-1zM7.5 4a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1z"
            ></path>
            <path
              d="M6 4.5H1.866a1 1 0 1 0 0 1h2.668A6.517 6.517 0 0 0 1.814 9H2.5c.123 0 .244.015.358.043a5.517 5.517 0 0 1 3.185-3.185A1.503 1.503 0 0 1 6 5.5v-1zm3.957 1.358A1.5 1.5 0 0 0 10 5.5v-1h4.134a1 1 0 1 1 0 1h-2.668a6.517 6.517 0 0 1 2.72 3.5H13.5c-.123 0-.243.015-.358.043a5.517 5.517 0 0 0-3.185-3.185z"
            ></path></svg> -->
          <i class="bi bi-headset"></i>
        </span>
        <span>客服人員系統</span></a
      ><button
        class="navbar-toggler"
        data-bs-toggle="collapse"
        data-bs-target="#navcol-5"
      >
        <span class="visually-hidden">Toggle navigation</span
        ><span class="navbar-toggler-icon"></span>
      </button>
      <div
        id="navcol-5"
        class="collapse navbar-collapse d-flex align-items-center"
      >
        <ul class="navbar-nav ms-auto">
          <!-- <li class="nav-item">
            <a class="nav-link active" href="#">First Item</a>
          </li>
          <li class="nav-item"><a class="nav-link" href="#">Second Item</a></li>
          <li class="nav-item"><a class="nav-link" href="#">Third Item</a></li> -->
          <li
            class="nav-item d-flex align-items-center justify-content-center m-2"
            style="color: white; text-align: center"
          >
          <!-- {{ csName }}, 早上好!  -->
          {{ matches[1] }}, 早上好! 
          </li>
          <!-- <li>
            <label for="photo" class="form-label">Photo</label>
            <img class="avatar" :src="photoDisplay" alt="User Avatar" />
            <input type="file" class="form-control" id="photo" @change="fileChange($event)">

          </li> -->
          <li>
            <button
              class="btn  dropdown-toggle"
              type="button"
              id="dropdownMenuButton1"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
            <img class="avatar" :src=photo[1] alt="User Avatar" />
            <!-- <img class="avatar" src="../img/avatar.jpg" alt="User Avatar" /> -->
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1"
            style="position: absolute; top: 100%; right: 0; left: auto;">
              <!-- <li><a class="dropdown-item" href="#">修改大頭貼</a></li> -->
              <!-- <li><a class="dropdown-item" href="/login">登出</a></li> -->
              <!-- <li><a class="dropdown-item" href="javascript:void(0);" @click="logout()">登出</a></li> -->
              <li><a class="dropdown-item" href="javascript:void(0);" @click="logout_google()">登出</a></li>
            </ul>
          </li>
        </ul>
        <!-- <a class="btn btn-primary ms-md-2" role="button" href="#">Button</a> -->
      </div>
    </div>
  </nav>
</template>

<script setup>

import { ref, onMounted } from "vue";
import Cookies from 'js-cookie';
import axios from 'axios';
import { useRouter } from 'vue-router';
import {signOut } from "firebase/auth";
import { db, auth } from "../firebase";



const router = useRouter()

const photoFile = ref(null);
const photoDisplay = ref(null);
// const csID = Cookies.get('customerServiceID')
const csName = Cookies.get('name')
const regex = /"([^"]*)"/;
const matches = csName.match(regex);
const photo = Cookies.get('photo').match(regex)



const URL = import.meta.env.VITE_API_MANAGE;
const URLAPI= `${URL}cs/findPhoto/`;

const logout_google = function () {
  signOut(auth);
  // Cookies.set('login',{ expires: new Date(0) });
  // Cookies.set('photo', { expires: new Date(0) });
  // Cookies.set('name', { expires: new Date(0) });
  // Cookies.set('photo', { expires: new Date(0) });
  Cookies.remove('login');
  Cookies.remove('photo');
  Cookies.remove('name');
  Cookies.remove('photo');
  router.push('/login');
};

const logout = function(){
  console.log('logout')

  
  router.push('/login');
}

// const loadPhoto = async() => {
//   console.log('hello')
//   const response = await axios.get(URLAPI+csID);
//   console.log('photo-response', response)
//   if(response.data.success === true){
//     photoDisplay = 'data:image/png;base64,' + response.data.photo
//   }
// }
const fileChange = function(event) {
  console.log('Original photoDisplay is:'+photoDisplay.value)
  photoFile.value = event.target.files[0]
  console.log('In function photoFile is:'+photoFile.value)
  photoDisplay.value = URL.createObjectURL(photoFile.value)
  console.log('In function photoDisplay is:'+photoDisplay)

}

// onMounted(
//   loadPhoto
// )



</script>


<style scoped>
.navbar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000; /* 使 NavBar 始終位於頂層 */
}

.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
}

.dropdown-content {
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
  right: 0; /* 將下拉選單靠右對齊 */
}
</style>
