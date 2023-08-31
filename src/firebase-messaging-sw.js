// Give the service worker access to Firebase Messaging.
// Note that you can only use Firebase Messaging here. Other Firebase libraries
// are not available in the service worker.
importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-messaging.js');

// Initialize the Firebase app in the service worker by passing in
// your app's Firebase config object.
// https://firebase.google.com/docs/web/setup#config-object
firebase.initializeApp({
  apiKey: "AIzaSyC4dFNRpOtnT6OAkPhkiHxKChqKovTgiYE",
  authDomain: "key-surf-392107.firebaseapp.com",
  projectId: "key-surf-392107",
  storageBucket: "key-surf-392107.appspot.com",
  messagingSenderId: "725946347703",
  appId: "1:725946347703:web:165ebce01ced1a5295dd32",
  measurementId: "G-CGZ3VJ2RKF"
});

// Retrieve an instance of Firebase Messaging so that it can handle background
// messages.
const messaging = firebase.messaging();

messaging.onBackgroundMessage((payload) => {
    console.log(
      '[firebase-messaging-sw.js] Received background message ',
      payload
    );
    // Customize notification here
    const notificationTitle = payload.notification.title;
    const notificationOptions = {
      body: payload.notification.body,
      icon: '../public/icon.png'
    };

    self.registration.showNotification(notificationTitle, notificationOptions);
  });