<template>
  <div class="container">
    <div class="row justify-content-center">
      <h1 class="mt-4 mb-4">Categories</h1>
      <div>
        <label for="category">Category:</label>
        <select v-model="searchData.category_name" id="category">
          <option value="">All Categories</option>
          <option v-for="category in categoriesStore.getCategories" :value="category.category_name" :key="category.id">{{ category.category_name }}</option>
        </select>
      </div>
      <table class="table">
        <thead>
          <tr>
            <th>Category Name</th>
            <th>Description</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="smallArticle in articlesStore.getArticles" :key="smallArticle.id">
            <SmallArticleComponent :smallArticle="smallArticle" />
          </tr>
        </tbody>
      </table>
      <button class="btn btn-success" @click="goToAddArticlePage">Add Category</button>
    </div>
  </div>
</template>

<script>
import { ref } from 'joi-browser'
import { useArticlesStore } from '../stores/articles'
import { useCategoriesStore } from '../stores/categories'
import SmallArticleComponent from './SmallArticleComponent.vue'
import { isNil, isEmpty } from 'ramda'
export default {
  name: 'ArticlesComponent',
  components: { SmallArticleComponent },
  setup() {
    const articlesStore = useArticlesStore()
    const categoriesStore = useCategoriesStore()
    const searchData = {
      page: 2,
      page_size: 10,
      category_name: 'gaming',
    }

    return {
      articlesStore,
      categoriesStore,
      searchData
    }
  },
  mounted() {
    if (!isNil(this.categoriesStore.getCategories) && !isEmpty(this.categoriesStore.getCategories)) {
      this.categoriesStore.fetchAllCategories()
    }
    const receivedCategoryName = this.$route.query.category_name
    console.log(receivedCategoryName)
    if (!isNil(receivedCategoryName)) {
      this.searchData.category_name = receivedCategoryName
    }

    this.articlesStore.fetchAllArticlesFiltered(this.searchData)
  },
  methods: {
    goToAddArticlePage() {
      // Implement your goToAddArticlePage method here
    }
  },
}
</script>

<style>
/* Add your custom styles here */
</style>
