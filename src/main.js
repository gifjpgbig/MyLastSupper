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
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import $ from 'jquery';
import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";
import VueClickAway from "vue3-click-away";
import Cookies from 'js-cookie';
import { NProgress } from 'nprogress';

const pinia = createPinia()
window.$ = $;
window.jQuery = $;

router.beforeEach(async(to, from, next) => {
  console.log(to)
  console.log(from)
  if(to.meta.requireAuth){
    const info = Cookies.get('login')
    console.log(info)
    if (info) {
      const token = JSON.parse(info)
      // 如果token不為空，且確實有這個欄位則讓路由變更
      if (token.length > 0 || token === undefined) {
        next()
      } else {
        // 未通過則導回login頁面
        next('/')
      }
    } else {
      next('/')
    }
  }else{
    next()
  }
  // NProgress.start();
})

// router.afterEach((to, from) => {
//   // ...
//   NProgress.end()
// })


createApp(App)
  .use(router)
  .use(VueSweetalert2)
  .use(pinia)
  .use(VueClickAway)
  .use(Toast, {
    transition: 'Vue-Toastification__bounce',
    maxToasts: 3,
    newestOnTop: true,
    position: 'top-right',
    timeout: 2000,
    closeOnClick: true,
    pauseOnFocusLoss: true,
    pauseOnHover: false,
    draggable: true,
    draggablePercent: 0.7,
    showCloseButtonOnHover: false,
    hideProgressBar: true,
    closeButton: 'button',
    icon: true,
    rtl: false
  })
  .mount('#app');

