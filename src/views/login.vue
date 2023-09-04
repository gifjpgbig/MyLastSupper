<template>
  <body class="text-center wrap">
    <main class="form-signin" v-if="!openModal">
      <div>
        <img src="../img/LastSupper.png" alt="" />
      </div>
      <h1 class="h3 mb-3 fw-normal">客服人員管理系統 登入畫面</h1>
      <div>
        <button
          class="btn"
          type="button"
          data-bs-toggle="dropdown"
          aria-expanded="false"
          @click="login"
        >
          <img
            class="avatar"
            src="../img/google.png"
            alt="google login"
            style="width: 100px; height: 100px"
          />
        </button>
        <!-- <button @click="logout">logout</button> -->
        <!-- <img
          src="https://lh3.googleusercontent.com/a/AAcHTtcOkq1WRJ8ahZyXQoZ7AH8My-kC_25hBgzYomJXjOM0VTNj=s96-c"
          alt=""
        /> -->
      </div>
      <!-- <form>
        <div class="form-floating">
          <input
            type="email"
            class="form-control form-size"
            id="floatingInput"
            placeholder="name@example.com"
            v-model="loginForm.account"
          />
          <label for="floatingInput">Email address</label>
        </div>
        <div class="form-floating">
          <input
            type="password"
            class="form-control form-size"
            id="floatingPassword"
            placeholder="Password"
            v-model="loginForm.password"
          />
          <label for="floatingPassword">Password</label>
        </div>

        <div class="checkbox mb-3 form-size">
          <label>
            <input type="checkbox" value="remember-me" /> Remember me
          </label>
        </div>
        <button
          class="w-100 btn btn-lg btn-primary form-size"
          type="button"
          @click="loginHandler"
        >
          Sign in
        </button>
        <p class="mt-5 mb-3 text-muted">© 2017–2021</p>
      </form> -->
    </main>

    <div v-if="openModal && !isApply" class="wrap">
      <fieldset>
        <!-- <legend>訪客首次登入</legend> -->
        <h2>{{ userInfo.displayName }}您好，歡迎來訪</h2>
        <h5>請填寫申請書說明來意</h5>
        <textarea    name="textarea"
              placeholder="請在這輸入..."
              id="applyText"
              v-model="userInfo.applyText"
           >
        </textarea>
            <button class="btn btn-primary me-3"
            type = "button"
            @click="sendApplyText()"
            >提交申請</button>
      </fieldset>
    </div>

    <div v-if="isApply" class="wrap">
      <fieldset>
        <!-- <legend>訪客首次登入</legend> -->
        <h2>{{ userInfo.displayName }}，歡迎來訪</h2>
        <h5>非常感謝您的申請，請耐心等待管理員審核您的資格</h5>

            <button class="btn btn-danger me-3"
            type = "button"
            @click="logout()"
            >登出</button>
      </fieldset>
    </div>

    <!-- <button @click="openGuestFormModal()"></button> -->
  </body>
</template>

<script setup>
import axios from "axios";
import Cookies from "js-cookie";
import { useRouter } from "vue-router";
import { db, auth } from "../firebase";
import { reactive, ref } from "vue";
import GuestFormModal from "../components/GuestFormModal.vue";

const router = useRouter();
const openModal = ref(false);
const isApply = ref(false);
// const applyText = ref('');
const loginForm = {
  account: "",
  password: "",
};

const openGuestFormModal = () => {
  openModal.value = true;
};

const openGuestWaiting = () => {
  isApply.value = true;
};
const sendApplyText = async() => {
  const URL = import.meta.env.VITE_API_MANAGE;
    const URLAPI = `${URL}cs/sendApply`;

  const response = await axios.put(URLAPI, userInfo)
  if(response.data.success){
    isApply.value = true;
  }else{
    alert(response.data.message)
  }

};

let userInfo = {
  displayName: "",
  email: "",
  phoneNumber: "",
  photoURL: "",
  providerId: "",
  uid: "",
  applyText: "",
  auth : "",
};

const login = async function () {
  try {
    const result = await signInWithPopup(auth, new GoogleAuthProvider());
    const user = result.user;

    userInfo.displayName = user.displayName;
    userInfo.email = user.email;
    userInfo.phoneNumber = user.phoneNumber;
    userInfo.photoURL = user.photoURL;
    userInfo.providerId = user.providerId;
    userInfo.uid = user.uid;
    userInfo.auth = user.authorizations;
    console.log("使用者資訊:", user);

    const URL = import.meta.env.VITE_API_MANAGE;
    const URLAPI = `${URL}cs/loginWithAuth`;
    const response = await axios.post(URLAPI, userInfo);
    console.log(response);
    if (response.data.userAuth === "guestTo") {
      console.log("router should push to form page");
      openGuestFormModal();
    } else if (response.data.userAuth === "guest") {
      openGuestWaiting();
    } else if (response.data.userAuth === "deny") {
      console.log("router should push to form page for n times");
      openGuestFormModal();
    } else {
      Cookies.set("login", JSON.stringify(userInfo.uid), {
        expires: 1,
      });
      Cookies.set("auth", JSON.stringify(response.data.userAuth), {
        expires: 1,
      });
      Cookies.set("photo", JSON.stringify(userInfo.photoURL), {
        expires: 1,
      });
      Cookies.set("name", JSON.stringify(userInfo.displayName), {
        expires: 1,
      });
      //是否是第一次登入
      router.push("/contact");
    }
  } catch (error) {
    console.error("登入失敗:", error);
  }
};

// const loginListener = auth.onAuthStateChanged((user) => {
//   console.log(user);

//   if (user != null) {

//   } else {
//     alert("nothing gonna change my love for you")
//   }
// });

const logout = function () {
  signOut(auth);
  openModal.value=false
  isApply.value=false
};

import { GoogleAuthProvider, signInWithPopup, signOut } from "firebase/auth";

const loginHandler = async () => {
  const URL = import.meta.env.VITE_API_MANAGE;
  const URLAPI = `${URL}cs/login`;
  const response = await axios.post(URLAPI, loginForm);
  if (response.data.success) {
    alert(response.data.message);
    Cookies.set("login", JSON.stringify("asds32adsavrAS3Fadf5567"), {
      expires: 1,
    });
    Cookies.set("customerServiceID", JSON.stringify(response.data.csID), {
      expires: 1,
    });
    Cookies.set("customerServiceName", JSON.stringify(response.data.csName), {
      expires: 1,
    });
    alert(response.data.csName);
    if (Cookies.get("login")) {
      router.push("/contact");
    }
  } else {
    alert(response.data.message);
  }
};
</script>

<style scoped>
.bd-placeholder-img {
  font-size: 1.125rem;
  text-anchor: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
}

@media (min-width: 768px) {
  .bd-placeholder-img-lg {
    font-size: 3.5rem;
  }
}

.form-size {
  width: 300px;
  justify-content: center;
}

fieldset {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 500px;
  border: 2px solid black; /* 蓝色虚线边框 */
        border-radius: 5px; /* 圆角边框 */
        padding: 15px;
}
.wrap{
	display: flex;
	justify-content: center;
	align-items:center;
  position: absolute; /* 使用绝对定位 */
            top: 50%; /* 从顶部50%的位置开始 */
            left: 50%; /* 从左侧50%的位置开始 */
            transform: translate(-50%, -50%); /* 使用transform属性将div居中 */
            background-color: #f2f2f2; /* 为了演示，添加一个背景颜色 */
            padding: 20px; /* 可选，根据需要添加内边距 */
}

textarea{
  width:100%;
  height: 500px;
  box-sizing: border-box;
}

legend{
	  margin: 0 auto;
}

</style>
