<template>
    <div v-for="chat in chats" class="p-2">
        <div @click="openedChats.push(chat)" style="text-align:left"  class="w-100 btn my-1" :class="chat.seen ? 'btn-secondary' : 'btn-primary'">
            <small>{{ chat.name }}</small> <br> <strong>{{ chat.latestMessage }}</strong>
        </div>
    </div>
    <div v-for="chat in openedChats">
        <forChat :client="chat"></forChat>
    </div>

</template>
    
<script>
    import {db} from '../firebase';
    import {onSnapshot, collection, doc, deleteDoc, setDoc, addDoc, orderBy, query} from 'firebase/firestore';
    import {ref, onUnmounted } from 'vue';
    import forChat from '../components/forChat.vue';

    export default {
        name: 'App',
        components: {
            forChat
        },
        data:()=>{
            return {
                chats:ref([]),
                openedChats:ref([])
            }
        }
        ,mounted(){
            const chatSnapshot = onSnapshot(
                query(collection(db,'chats'), orderBy('date','desc'))
                ,
                (snapshot)=>{
                    this.chats = snapshot.docs.map((doc)=>{
                        return {... doc.data(), id:doc.id}
                    })
                }
            );
            onUnmounted(chatSnapshot)
        }
    }
</script>
    
<style>
    
</style>