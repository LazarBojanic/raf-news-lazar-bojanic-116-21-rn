<template>
    <div class="container">
      <div class="row justify-content-center">
        <h1>Edit Category</h1>
        <form @submit.prevent="updateArticle">
          <div class="form-group">
            <label for="category_name">Name:</label>
            <input
              type="text"
              class="form-control"
              id="category_name"
              v-model="category_name"
              required
            />
          </div>
          <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" class="form-control" id="description" v-model="description" required />
          </div>
          <button type="submit" class="btn btn-primary">Save</button>
          <button class="btn btn-secondary ml-2" @click="cancelEdit">Cancel</button>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  import { useArticlesStore } from '../stores/articles'
  import router from '../router'
  export default {
    name: 'EditArticleComponent',
    setup() {
      const articlesStore = useArticlesStore()
      return {
        articlesStore
      }
    },
    data() {
      return {
        category_name: this.articlesStore.getArticle.category.category_name,
        title: this.articlesStore.getArticle.title,
        body: this.articlesStore.getArticle.body,
        

      }
    },
    mounted() {

    },
    methods: {

    }
  }
  </script>
  
  <style></style>
  