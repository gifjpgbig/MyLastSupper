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

// Vue.use(Toast, {
//   transition: 'Vue-Toastification__bounce',
//   maxToasts: 3,
//   newestOnTop: true,
//   position: 'top-right',
//   timeout: 2000,
//   closeOnClick: true,
//   pauseOnFocusLoss: true,
//   pauseOnHover: false,
//   draggable: true,
//   draggablePercent: 0.7,
//   showCloseButtonOnHover: false,
//   hideProgressBar: true,
//   closeButton: 'button',
//   icon: true,
//   rtl: false
// })


const pinia = createPinia()
window.$ = $;
window.jQuery = $;

createApp(App)
  .use(router)
  .use(VueSweetalert2)
  .use(pinia)
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

