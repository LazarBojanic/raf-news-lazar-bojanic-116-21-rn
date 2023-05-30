<template>
  <div class="container">
    <div class="col">
      <div v-if="searchData.category_name != null">
        <label for="category" class="form-label">Category:</label>
        <select v-model="searchData.category_name" id="category" class="form-select">
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
      <div v-for="article in articlesStore.getArticles" :key="article.id">
        <ArticleComponent :article="article" />
      </div>
      <div class="pagination">
        <button class="btn btn-primary" :disabled="searchData.page === 1" @click="previousPage">
          Previous Page
        </button>
        <span class="current-page">Page {{ searchData.page }}</span>
        <button class="btn btn-primary" @click="nextPage">Next Page</button>
      </div>
    </div>
  </div>
</template>

<script>
import { useArticlesStore } from '../stores/articles'
import { useCategoriesStore } from '../stores/categories'
import ArticleComponent from './ArticleComponent.vue'
import { isNil, isEmpty } from 'ramda'
export default {
  name: 'ArticlesComponent',
  components: { ArticleComponent },
  props: {
    articleMode: String
  },
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
        page_size: 10,
        category_name: null,
        trending: false,
        tag_name: null
      }
    }
  },
  async mounted() {
    await this.categoriesStore.fetchAllCategories()
    if (this.articleMode === 'home') {
      this.searchData.trending = false
      this.searchData.category_name = null
      this.searchData.tag_name = null
    } else if (this.articleMode === 'trending') {
      this.searchData.trending = true
      this.searchData.category_name = null
      this.searchData.tag_name = null
    } else if (this.articleMode === 'articlesByCategory') {
      this.searchData.trending = false
      this.searchData.category_name = this.categoriesStore.getCategories[0].category_name
      this.searchData.tag_name = null
    }
    const receivedTagName = this.$route.query.tag_name
    if (!isNil(receivedTagName)) {
      this.searchData.trending = false
      this.searchData.category_name = null
      this.searchData.tag_name = receivedTagName
    }
    await this.articlesStore.fetchAllArticlesFiltered(this.searchData)
  },
  methods: {
    async previousPage() {
      if (this.searchData.page > 1) {
        this.searchData.page--
        await this.articlesStore.fetchAllArticlesFiltered(this.searchData)
      }
    },
    async nextPage() {
      this.searchData.page++
      await this.articlesStore.fetchAllArticlesFiltered(this.searchData)
    }
  },
  watch: {
    'searchData.category_name'(newCategoryName) {
      this.searchData.page = 1
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
