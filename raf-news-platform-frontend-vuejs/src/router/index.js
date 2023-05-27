import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/HomeView.vue'
import ArticlesComponent from '../components/ArticlesComponent.vue'
import FullArticleComponent from '../components/FullArticleComponent.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'init',
      redirect: '/home'
    },
    {
      path: '/home',
      name: 'home',
      component: HomeView
    },
    {
      path: '/articles',
      name: 'articles',
      component: ArticlesComponent
    },
    {
      path: '/fullArticle',
      name: 'fullArticle',
      component: FullArticleComponent
    }
  ]
})

export default router
