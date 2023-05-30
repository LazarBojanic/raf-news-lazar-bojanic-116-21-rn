<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" to="/home">Home</router-link>
          </li>
          <li v-if="!validToken" class="nav-item">
            <router-link class="nav-link" to="/login">Login</router-link>
          </li>
          <li v-if="!validToken" class="nav-item">
            <router-link class="nav-link" to="/register">Register</router-link>
          </li>
          <li v-if="userIsAdmin" class="nav-item">
            <router-link class="nav-link" to="/users">Users</router-link>
          </li>
          <li v-if="validToken" class="nav-item">
            <a class="nav-link" href="#" @click.prevent="logoutButton">Logout</a>
          </li>
        </ul>
      </div>
    </nav>
    <br />
    <div class="container d-flex justify-content-center align-items-center">
      <div class="col-md-8">
        <router-view @loggedIn="updateToken" />
      </div>
    </div>
    <div class="text-center">
      <h1 v-if="exceptionOccured(articlesStore.getException)"> {{ articlesStore.getException.message }}</h1>
      <h1 v-if="exceptionOccured(categoriesStore.getException)"> {{ categoriesStore.getException.message }}</h1>
      <h1 v-if="exceptionOccured(commentsStore.getException)"> {{ commentsStore.getException.message }}</h1>
      <h1 v-if="exceptionOccured(tagsStore.getException)"> {{ tagsStore.getException.message }}</h1>
      <h1 v-if="exceptionOccured(usersStore.getException)"> {{ usersStore.getException.message }}</h1>
    </div>
  </div>
</template>

<script>

import Cookies from 'js-cookie'
import jwtDecode from 'jwt-decode'
import { isEmpty, isNil } from 'ramda'
import { ref } from 'vue'
import { useArticlesStore } from './stores/articles'
import { useCategoriesStore } from './stores/categories'
import { useCommentsStore } from './stores/comments'
import { useTagsStore } from './stores/tags'
import { useUsersStore } from './stores/users'
export default {
  setup() {
    const articlesStore = useArticlesStore()
    const categoriesStore = useCategoriesStore()
    const commentsStore = useCommentsStore()
    const tagsStore = useTagsStore()
    const usersStore = useUsersStore()
    const validToken = ref(false)
    const userIsAdmin = ref(false)
    return {
      articlesStore,
      categoriesStore,
      commentsStore,
      tagsStore,
      usersStore,
      validToken,
      userIsAdmin
    }
  },
  async mounted() {
    this.updateToken()
    await this.loginWithTokenFromComponent()
  },
  methods: {
    async logoutButton() {
      await this.usersStore.logout()
      this.updateToken()
      if (isEmpty(this.usersStore.getException)) {
        this.$router.push('login')
        console.log('logout successful')
      } else {
        console.log('logout failed')
      }
    },
    async loginWithTokenFromComponent() {
      await this.usersStore.loginWithToken()
      this.updateToken()
      if (isEmpty(this.usersStore.getException)) {
        this.$router.push('home')
        console.log('login successful')
      } else {
        console.log('login failed')
        await this.usersStore.logout()
      }
    },
    updateToken() {
      const token = Cookies.get('token')
      if (!isNil(token) && !isEmpty(token)) {
        this.validToken = jwtDecode(token).email !== ''
        this.userIsAdmin = jwtDecode(token).user_role === 'admin'
      }
    },
    exceptionOccured(exception){
      return !isEmpty(exception)
    }
  },
  watch: {
    'articlesStore.getException'(newException) {
      if (this.exceptionOccured(newException)) {
        setTimeout(() => { this.articlesStore.clearException(); }, 5000);
      }
    },
    'categoriesStore.getException'(newException) {
      if (this.exceptionOccured(newException)) {
        setTimeout(() => { this.categoriesStore.clearException(); }, 5000);
      }
    },
    'commentsStore.getException'(newException) {
      if (this.exceptionOccured(newException)) {
        setTimeout(() => { this.commentsStore.clearException(); }, 5000);
      }
    },
    'tagsStore.getException'(newException) {
      if (this.exceptionOccured(newException)) {
        setTimeout(() => { this.tagsStore.clearException(); }, 5000);
      }
    },
    'usersStore.getException'(newException) {
      if (this.exceptionOccured(newException)) {
        setTimeout(() => { this.usersStore.clearException(); }, 5000);
      }
    },
  }
}
</script>

<style></style>
