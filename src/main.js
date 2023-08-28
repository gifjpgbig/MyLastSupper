import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import $ from 'jquery';
// Import the functions you need from the SDKs you need
// import { getAnalytics } from "firebase/analytics";
// import { initializeApp } from "firebase/app";
// import { getMessaging } from "firebase/messaging";

const pinia = createPinia()
window.$ = $;
window.jQuery = $;

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
// const firebaseConfig = {
//   apiKey: "AIzaSyC4dFNRpOtnT6OAkPhkiHxKChqKovTgiYE",
//   authDomain: "key-surf-392107.firebaseapp.com",
//   projectId: "key-surf-392107",
//   storageBucket: "key-surf-392107.appspot.com",
//   messagingSenderId: "725946347703",
//   appId: "1:725946347703:web:165ebce01ced1a5295dd32",
//   measurementId: "G-CGZ3VJ2RKF"
// };
// Initialize Firebase
// const app = initializeApp(firebaseConfig);
// const analytics = getAnalytics(app);
// const messaging = getMessaging(app);


createApp(App).use(router, VueSweetalert2, pinia).mount('#app')






