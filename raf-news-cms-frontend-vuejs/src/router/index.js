import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/HomeView.vue'
import RegisterComponent from '../components/RegisterComponent.vue'
import LoginComponent from '../components/LoginComponent.vue'
import ArticlesTableComponent from '../components/ArticlesTableComponent.vue'
import FullArticleComponent from '../components/FullArticleComponent.vue'
import EditCategoryComponent from '../components/EditCategoryComponent.vue'
import UsersTableComponent from '../components/UsersTableComponent.vue'
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
      path: '/register',
      name: 'register',
      component: RegisterComponent
    },
    {
      path: '/login',
      name: 'login',
      component: LoginComponent
    },
    {
      path: '/articles',
      name: 'articles',
      component: ArticlesTableComponent
    },
    {
      path: '/fullArticle',
      name: 'fullArticle',
      component: FullArticleComponent
    },
    {
      path: '/editCategory',
      name: 'editCategory',
      component: EditCategoryComponent
    },
    {
      path: '/users',
      name: 'users',
      component: UsersTableComponent
    }
  ]
})

export default router
