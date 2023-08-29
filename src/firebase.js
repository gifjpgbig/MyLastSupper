// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyC4dFNRpOtnT6OAkPhkiHxKChqKovTgiYE",
  authDomain: "key-surf-392107.firebaseapp.com",
  projectId: "key-surf-392107",
  storageBucket: "key-surf-392107.appspot.com",
  messagingSenderId: "725946347703",
  appId: "1:725946347703:web:165ebce01ced1a5295dd32",
  measurementId: "G-CGZ3VJ2RKF"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);


import {getFirestore} from 'firebase/firestore'

export const db = getFirestore(app);