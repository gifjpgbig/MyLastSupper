import {createRouter, createWebHistory} from 'vue-router'
import Home from './views/Home.vue';
import About from './views/About.vue';
import Contact from './views/Contact.vue';
import Orders from './views/orders.vue';
import Add from './views/Add.vue';
import Edit from './views/Edit.vue';
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
    },
    {
        //http://localhost/orders
        path:'/orders',
        component:Orders
    },
    {
        //http://localhost/orders/add
        path:'/orders/add',
        component:Add
    },
    {
        //http://localhost/orders/edit/1
        path:'/orders/edit/:id',
        component:Edit
    }
]

const router = createRouter({
    history:createWebHistory(),
    routes
})

export default router