import { createRouter, createWebHistory } from 'vue-router'
import Home from './views/Home.vue';
import About from './views/About.vue';
import Contact from './views/Contact.vue';
import Orders from './views/orders.vue';
import Add from './views/Add.vue';
import Edit from './views/Edit.vue';
import Deliver from './views/deliver.vue'
import Login from './views/login.vue'
import Toast from './views/Toast.vue';
import Chat from './views/Chat.vue';

const routes = [
    {
        //http://localhost/
        path: '/',
        component: Login
        , meta: { showNavbar: false, requireAuth: false }

    },
    {
        //http://localhost/
        path: '/home',
        component: Home
        , meta: { showNavbar: true, requireAuth: true }

    },
    {
        //http://localhost/about
        path: '/about',
        component: About,
        name: 'about',
         meta: { showNavbar: true, requireAuth: true }
    },
    {
        //http://localhost/contact
        path: '/contact',
        component: Contact, 
        meta: { showNavbar: true, requireAuth: true }

    },
    {
        //http://localhost/orders
        path: '/orders',
        component: Orders,
        meta: { showNavbar: true, requireAuth: true }

    },
    {
        //http://localhost/orders/add
        path: '/orders/add',
        component: Add, 
        meta: { showNavbar: true, requireAuth: true }

    },
    {
        //http://localhost/orders/edit/1
        path: '/orders/edit/:id',
        component: Edit, 
        meta: { showNavbar: true, requireAuth: true }

    },
    {
        //http://localhost/deliver
        path: '/deliver',
        component: Deliver, 
        meta: { showNavbar: true, requireAuth: true }

    },
    {
        //http://localhost/login
        path: '/login',
        component: Login,
        meta: { showNavbar: false } // 显示导航栏

    },
    {
        //http://localhost/login
        path: '/toast',
        component: Toast,
        meta: { showNavbar: true, requireAuth: true }


    },
    {
        //http://localhost/chat
        path: '/chat',
        component: Chat,
        meta: { showNavbar: true,  requireAuth: true } // 显示导航栏

    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router