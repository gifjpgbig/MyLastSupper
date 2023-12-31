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
import Deliverer from './views/Deliverer.vue';
import AddDeliverer from './views/AddDeliverer.vue';
import EditDeliverer from './views/EditDeliverer.vue';
import GuestForm from './views/GuestForm.vue'
import WaitingPage from './views/WaitingPage.vue'
import Manager from './views/Manager.vue'
import ClientChat from './views/ClientChat.vue'

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
        meta: { showNavbar: true, requireAuth: true } // 显示导航栏

    },
    {
        //http://localhost/deliverer
        path: '/deliverer',
        component: Deliverer,
        meta: { showNavbar: true, requireAuth: true }

    },
    {
        //http://localhost/deliverer/add
        path: '/deliverer/add',
        component: AddDeliverer,
        meta: { showNavbar: true, requireAuth: true }

    },
    {
        //http://localhost/deliverer/edit/1
        path: '/deliverer/edit/:id',
        component: EditDeliverer,
        meta: { showNavbar: true, requireAuth: true }

    },
    {
        //http://localhost/guestForm
        path: '/guestForm',
        component: GuestForm,
        meta: { showNavbar: false, requireAuth: false }

    },
    {
        //http://localhost/waitingPage
        path: '/waitingPage',
        component: WaitingPage,
        meta: { showNavbar: false, requireAuth: false }

    },
    {
        //http://localhost/manager
        path: '/manager',
        component: Manager,
        meta: { showNavbar: true, requireAuth: true }

    }, 
    {
        //http://localhost/clientChat
        path: '/clientChat',
        component: ClientChat,
        meta: { showNavbar: true, requireAuth: true }

    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router