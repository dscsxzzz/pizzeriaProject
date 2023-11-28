import { createWebHistory, createRouter } from 'vue-router'

const routes = [
    {
        path: "/",
        component: () => import ("../views/MainView.vue"),
        name: "Main"
    },
    {
        path: "/login",
        component: () => import ("../components/LoginForm.vue"),
        name: "Login"
    },
    {
        path: "/register",
        component: () => import ("../components/CreateAccount.vue"),
        name: "Register"
    },
    ,
    {
        path: "/forgot-password",
        component: () => import ("../components/ForgotPassword.vue"),
        name: "ForgotPassword"
    },
    {
        path: "/details",
        component: () => import ("../views/UserAccountView.vue"),
        name: "UserAccountView",
        children: [
            {
                path: "account",
                component: () => import ("../components/UserAccount.vue"),
                name: "UserAccount",
                meta: {
                    requiresAuth: true
                }
            },
            {
                path: "orders",
                component: () => import ("../components/UserOrders.vue"),
                name: "UserOrders",
                meta: {
                    requiresAuth: true
                }
            }
        ]
    },
    {
        path: "/changePass",
        component: () => import("../components/ChangePass.vue"),
        name : "changePass"
    }
        
]

const router = createRouter({
    routes,
    history: createWebHistory()
});

export default router;