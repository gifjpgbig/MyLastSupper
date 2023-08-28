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

const pinia = createPinia()
window.$ = $;
window.jQuery = $;

createApp(App)
  .use(router)
  .use(VueSweetalert2)
  .use(pinia)
  .use(Toast)
  .mount('#app');

