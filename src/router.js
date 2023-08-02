import {createRouter, createWebHistory} from 'vue-router'
import Home from './views/Home.vue';
import About from './views/About.vue';
import Contact from './views/Contact.vue';
const routes = [
    {
        //http://localhost/
        path:'/',
        component:Home
    },
    {
        //http://localhost/about
        path:'/about',
        component:About
    },
    {
        //http://localhost/contact
        path:'/contact',
        component:Contact
    }
]

const router = createRouter({
    history:createWebHistory(),
    routes
})

export default router