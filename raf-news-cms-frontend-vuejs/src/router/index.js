import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/HomeView.vue'
import RegisterComponent from '../components/RegisterComponent.vue'
import LoginComponent from '../components/LoginComponent.vue'
import AddArticleComponent from '../components/AddArticleComponent.vue'
import ArticlesTableComponent from '../components/ArticlesTableComponent.vue'
import FullArticleComponent from '../components/FullArticleComponent.vue'
import EditCategoryComponent from '../components/EditCategoryComponent.vue'
import UsersTableComponent from '../components/UsersTableComponent.vue'
import EditArticleComponent from '../components/EditArticleComponent.vue'
import EditUserComponent from '../components/EditUserComponent.vue'
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
      path: '/addArticle',
      name: 'addArticle',
      component: AddArticleComponent
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
      path: '/editUser',
      name: 'editUser',
      component: EditUserComponent
    },
    {
      path: '/editCategory',
      name: 'editCategory',
      component: EditCategoryComponent
    },
    {
      path: '/editArticle',
      name: 'editArticle',
      component: EditArticleComponent
    },
    {
      path: '/users',
      name: 'users',
      component: UsersTableComponent
    }
  ]
})

export default router
