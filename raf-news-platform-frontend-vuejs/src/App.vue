<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" to="/home">Home</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/trending">Trending</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/articlesByCategory"
              >Articles by Category</router-link
            >
          </li>
        </ul>
      </div>
    </nav>
    <br />
    <div class="container d-flex justify-content-center align-items-center">
      <div class="col-md-8">
        <router-view />
      </div>
    </div>
    <div class="text-center">
      <h1 v-if="exceptionOccured(articlesStore.getException)"> {{ articlesStore.getException.message }}</h1>
      <h1 v-if="exceptionOccured(categoriesStore.getException)"> {{ categoriesStore.getException.message }}</h1>
      <h1 v-if="exceptionOccured(commentsStore.getException)"> {{ commentsStore.getException.message }}</h1>
      <h1 v-if="exceptionOccured(usersStore.getException)"> {{ usersStore.getException.message }}</h1>
    </div>
  </div>
</template>

<script>
import { isEmpty, isNil } from 'ramda'
import { useArticlesStore } from './stores/articles'
import { useCategoriesStore } from './stores/categories'
import { useCommentsStore } from './stores/comments'
import { useUsersStore } from './stores/users'
export default {
  setup() {
    const articlesStore = useArticlesStore()
    const categoriesStore = useCategoriesStore()
    const commentsStore = useCommentsStore()
    const usersStore = useUsersStore()
    return {
      articlesStore,
      categoriesStore,
      commentsStore,
      usersStore
    }
  },
  async mounted() {
    await this.usersStore.logout()
  },
  methods: {
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
    'usersStore.getException'(newException) {
      if (this.exceptionOccured(newException)) {
        setTimeout(() => { this.usersStore.clearException(); }, 5000);
      }
    },
  }
}
</script>

<style></style>
