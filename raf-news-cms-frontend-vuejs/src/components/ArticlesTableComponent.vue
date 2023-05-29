<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col text-center">
        <h1 class="mt-4 mb-4">Articles</h1>
        <div>
          <label for="category_name" class="form-label">Category:</label>
          <select v-model="searchData.category_name" id="category_name" class="form-select">
            <option value="">All Categories</option>
            <option
              v-for="category in categoriesStore.getCategories"
              :value="category.category_name"
              :key="category.id"
            >
              {{ category.category_name }}
            </option>
          </select>
        </div>
        <table class="table">
          <thead>
            <tr>
              <th>Title</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="article in articlesStore.getArticles" :key="article.id">
              <ArticleRowComponent :article="article" />
            </tr>
          </tbody>
        </table>
        <button class="btn btn-success" @click="goToAddArticlePage">Add Article</button>
      </div>
    </div>
  </div>
</template>

<script>
import { useArticlesStore } from '../stores/articles'
import { useCategoriesStore } from '../stores/categories'
import ArticleRowComponent from './ArticleRowComponent.vue'
import { isNil, isEmpty } from 'ramda'

export default {
  name: 'ArticlesTableComponent',
  components: { ArticleRowComponent },
  setup() {
    const articlesStore = useArticlesStore()
    const categoriesStore = useCategoriesStore()
    return {
      articlesStore,
      categoriesStore
    }
  },
  data() {
    return {
      searchData: {
        page: 1,
        page_size: 15,
        category_name: 'gaming'
      }
    }
  },
  mounted() {
    this.categoriesStore.fetchAllCategories()

    const receivedCategoryName = this.$route.query.category_name
    console.log(receivedCategoryName)
    if (!isNil(receivedCategoryName)) {
      this.searchData.category_name = receivedCategoryName
    }

    this.articlesStore.fetchAllArticlesFiltered(this.searchData)
  },
  methods: {
    goToAddArticlePage() {
      this.$router.push({
        name: 'addArticle',
        query: { category_name: this.searchData.category_name }
      })
    }
  },
  watch: {
    'searchData.category_name'(newCategoryName) {
      this.articlesStore.fetchAllArticlesFiltered(this.searchData)
    }
  }
}
</script>

<style>
.pushed-in {
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
  transform: scale(0.95);
}
.regular-scale {
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
  transform: scale(1);
}
.pushed-out {
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
  transform: scale(1.05);
}
</style>
