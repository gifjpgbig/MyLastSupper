import { createRouter, createWebHistory } from 'vue-router'
import Home from './views/Home.vue';
import About from './views/About.vue';
import Contact from './views/Contact.vue';
import Orders from './views/orders.vue';
import Add from './views/Add.vue';
import Edit from './views/Edit.vue';
import Deliver from './views/deliver.vue'
import Login from './views/login.vue'
const routes = [
    {
        //http://localhost/
        path: '/',
        component: Home
        , meta: { showNavbar: true }

    },
    {
        //http://localhost/about
        path: '/about',
        component: About, meta: { showNavbar: true }
    },
    {
        //http://localhost/contact
        path: '/contact',
        component: Contact, meta: { showNavbar: true }
    },
    {
        //http://localhost/orders
        path: '/orders',
        component: Orders, meta: { showNavbar: true }
    },
    {
        //http://localhost/orders/add
        path: '/orders/add',
        component: Add, meta: { showNavbar: true }
    },
    {
        //http://localhost/orders/edit/1
        path: '/orders/edit/:id',
        component: Edit, meta: { showNavbar: true }
    },
    {
        //http://localhost/deliver
        path: '/deliver',
        component: Deliver, meta: { showNavbar: true }
    },
    {
        //http://localhost/login
        path: '/login',
        component: Login,
        meta: { showNavbar: false } // 显示导航栏

    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router