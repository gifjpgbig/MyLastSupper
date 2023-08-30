<template>
    <body class="text-center">
  <main class="form-signin">
    <form>
      <img
        class="mb-4"
        src="/docs/5.0/assets/brand/bootstrap-logo.svg"
        alt=""
        width="72"
        height="57"
      />
      <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

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
          class="form-control"
          id="floatingPassword"
          placeholder="Password"
          v-model="loginForm.password"
        />
        <label for="floatingPassword">Password</label>
      </div>

      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me" /> Remember me
        </label>
      </div>
      <button class="w-100 btn btn-lg btn-primary" type="button"
      @click="loginHandler"
      >
        Sign in
      </button>
      <p class="mt-5 mb-3 text-muted">© 2017–2021</p>
    </form>
  </main>
</body>
</template>

<script setup>
import axios from 'axios';
import Cookies from 'js-cookie';
import { useRouter } from 'vue-router';

const router = useRouter()

const loginForm = {
  "account":"",
  "password":""
}



const loginHandler = async() => {
  const URL = import.meta.env.VITE_API_MANAGE;
  const URLAPI= `${URL}cs/login`;
  const response = await axios.post(URLAPI, loginForm)
  if(response.data.success){
    alert(response.data.message)
    Cookies.set('login', JSON.stringify('asds32adsavrAS3Fadf5567'), { expires: 1 })
    if (Cookies.get('login')) {
      router.push('/contact')
    }
  }else{
    alert(response.data.message)
  }
}





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

    </style>
