import { createRouter, createWebHistory } from 'vue-router'
import ProfileView from '@/views/ProfileView.vue'
import ShopView from '@/views/ShopView.vue'
import RankView from '@/views/RankView.vue'
import OauthView from '@/views/OauthView.vue'
import RegisterView from '@/views/RegisterView.vue'
import GameView from '@/views/GameView.vue'

import module from '@/router/module.js'

const routes = [
  {
    path: "/",
    name: 'start',
    component: () => import("@/views/StartView.vue")
  },
  {
      path: "/login",
      name: "login",
      component: () => import("@/views/LoginView.vue")
  },
  {
    path: "/search",
    name: "search",
    component: () => import("@/views/SearchView.vue")
  },
  {
    path: "/home",
    name: "home",
    component: () => import("@/views/HomeView.vue")
  },
  {
    path: '/profile/:uid',
    name: 'Profile',
    component: ProfileView
  },
  {
    path: '/shop',
    name: 'Shop',
    component: ShopView
  },
  {
    path: '/rank',
    name: 'Rank',
    component: RankView
  },
  {
    path: '/oauth',
    name: 'Oauth',
    component: OauthView
  },
  {
    path: '/register/:email',
    name: 'Register',
    component: RegisterView
  },
  {
    path: '/game/:roomId',
    name: 'game',
    component: GameView
  },

  ...module

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router