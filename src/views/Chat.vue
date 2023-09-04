<template>
  <h1>{{ title }}</h1>
  <div v-if="loggedin">
    <div v-for="chat in chats" class="p-2">
      <div
        @click="openedChats.push(chat)"
        style="text-align: left"
        class="w-100 btn my-1"
        :class="chat.seen ? 'btn-secondary' : 'btn-primary'"
      >
        <small>{{ chat.name }}</small> <br />
        <strong>{{ chat.latestMessage }}</strong>
      </div>
    </div>
    <div v-for="chat in openedChats">
      <forChat :client="chat"></forChat>
    </div>
    <!-- <button @click="logout">logout</button> -->
  </div>
  <div v-else>
    <button @click="login">login</button>
  </div>
</template>

<script>
import { db, auth } from "../firebase";
import { GoogleAuthProvider, signInWithPopup, signOut } from "firebase/auth";

import {
  onSnapshot,
  collection,
  doc,
  deleteDoc,
  setDoc,
  addDoc,
  orderBy,
  query,
} from "firebase/firestore";
import { ref, onUnmounted } from "vue";
import forChat from "../components/forChat.vue";

export default {
  name: "App",
  components: {
    forChat,
  },
  data: () => {
    return {
      chats: ref([]),
      openedChats: ref([]),
      loggedin: false,
      title: "",
      userInfo: {
        displayName: null,
        email: null,
        phoneNumber: null,
        photoURL: null,
        providerId: null,
        uid: null,
      },
    };
  },
  methods: {
    login: function () {
      signInWithPopup(auth, new GoogleAuthProvider());
    },
    logout: function () {
      signOut(auth);
      this.chats = [];
    },
  },
  mounted() {
    const loginListener = auth.onAuthStateChanged((user) => {
      console.log("userInChat:"+user)
      if (user != null) {
        this.loggedin = true;
        this.title = "welcome";
        this.userInfo.displayName = user.displayName;
        this.userInfo.email = user.email;
        this.userInfo.phoneNumber = user.phoneNumber;
        this.userInfo.photoURL = user.photoURL;
        this.userInfo.providerId = user.providerId;
        this.userInfo.uid = user.uid;
        console.log("userInfoInChat="+this.userInfo.photoURL)
        const chatSnapshot = onSnapshot(
          query(collection(db, "chats"), orderBy("date", "desc")),
          (snapshot) => {
            this.chats = snapshot.docs.map((doc) => {
              return { ...doc.data(), id: doc.id };
            });
          },
          (error) => {
            console.log(error)
            console.log(error.code)
            if (this.loggedin && error.code.includes("permission")) {
              this.title = "you are not admin !";
            }
          }
        );
        onUnmounted(chatSnapshot);
        console.log(this.userInfo)
      } else {
        this.loggedin = false;
        this.title = "please login";
      }
    });
  },
};
</script>

<style></style>
