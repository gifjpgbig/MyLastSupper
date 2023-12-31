// import { createApp } from 'vue'
// import { createPinia } from 'pinia'
// import App from './App.vue'
// import router from './router'
// import VueSweetalert2 from 'vue-sweetalert2';
// import 'sweetalert2/dist/sweetalert2.min.css';
// import $ from 'jquery';

// const pinia = createPinia()
// window.$ = $;
// window.jQuery = $;

// import Vue from "vue";
// import Toast from "vue-toastification";
// import "vue-toastification/dist/index.css";

// Vue.config.productionTip = false;

// const options = {};
// Vue.use(Toast, options);

// createApp(App).use(router, VueSweetalert2, pinia, Toast, options).mount('#app')
import { createApp } from "vue";
import { createPinia } from "pinia";
import App from "./App.vue";
import router from "./router";
import VueSweetalert2 from "vue-sweetalert2";
import "sweetalert2/dist/sweetalert2.min.css";
import $ from "jquery";
import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";
import VueClickAway from "vue3-click-away";
import Cookies from "js-cookie";
import { NProgress } from "nprogress";
// import Vueform from '@vueform/vueform/plugin'
// import vueformConfig from './../vueform.config'
import './index.scss'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'



const pinia = createPinia();
window.$ = $;
window.jQuery = $;

router.beforeEach(async (to, from, next) => {
  console.log(to);
  console.log(from);
  if (to.meta.requireAuth) {
    const info = Cookies.get("login");
    console.log(info);
    //SyntaxError: "[object Object]" is not valid JSON
    //在登入過後，登出我沒做到完全刪除cookie，所以在嘗試繞過去會報錯，且卡在空白畫面，所以用try catch跳轉回登入畫面
    try {
      if (info) {
        const token = JSON.parse(info);
        // 如果token不為空，且確實有這個欄位則讓路由變更
        if (token.length > 0 || token === undefined) {
          next();
        } else {
          // 未通過則導回login頁面
          next("/");
        }
      } else {
        next("/");
      }
    } catch (e) {
      console.error(e);
      next("/");
    }
  } else {
    next();
  }
  // NProgress.start();
});

// router.afterEach((to, from) => {
//   // ...
//   NProgress.end()
// })

createApp(App)
  .use(router)
  .use(VueSweetalert2)
  .use(pinia)
  .use(VueClickAway)
  // .use(Vueform, vueformConfig)
  .use(ElementPlus, {size: 'small', zIndex:3000})
  .use(Toast, {
    transition: "Vue-Toastification__bounce",
    maxToasts: 3,
    newestOnTop: true,
    position: "top-right",
    timeout: 5000,
    closeOnClick: true,
    pauseOnFocusLoss: true,
    pauseOnHover: false,
    draggable: true,
    draggablePercent: 0.7,
    showCloseButtonOnHover: false,
    hideProgressBar: true,
    closeButton: "button",
    icon: true,
    rtl: false,
  })
  .mount("#app");
