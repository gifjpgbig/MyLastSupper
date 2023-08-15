import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import $ from 'jquery';

const pinia = createPinia()
window.$ = $;
window.jQuery = $;



createApp(App).use(router, VueSweetalert2,pinia ).mount('#app')
