import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/login',
    component: () => import(/* webpackChunkName: "about" */ '../views/login.vue')
  },
  {
    path: '/',
    name:'main',
    component: ()=>import('../views/main.vue'),
    children:[
      {
        path: '/welcome',
        component:()=>import('../views/main/welcome.vue')
      },
      {
        path: 'passenger',
        component:()=>import('../views/main/passenger.vue')
      }
    ]
  },
  {
    path: '',
    redirect: '/welcome'
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
