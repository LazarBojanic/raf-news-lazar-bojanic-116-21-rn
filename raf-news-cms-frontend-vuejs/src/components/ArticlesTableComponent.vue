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
        <div class="centered">
          <button class="btn btn-primary" :disabled="searchData.page === 1" @click="previousPage">
            Previous Page
          </button>
          <span class="current-page">Page {{ searchData.page }}</span>
          <button class="btn btn-primary" @click="nextPage">Next Page</button>
        </div>
        <button :disabled="!validToken" class="btn btn-success" @click="goToAddArticlePage">
          Add Article
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useArticlesStore } from '../stores/articles'
import { useCategoriesStore } from '../stores/categories'
import ArticleRowComponent from './ArticleRowComponent.vue'
import { isNil, isEmpty } from 'ramda'
import Cookies from 'js-cookie'
import jwtDecode from 'jwt-decode'

export default {
  name: 'ArticlesTableComponent',
  components: { ArticleRowComponent },
  setup() {
    const articlesStore = useArticlesStore()
    const categoriesStore = useCategoriesStore()
    const validToken = ref(false)
    return {
      articlesStore,
      categoriesStore,
      validToken
    }
  },
  data() {
    return {
      searchData: {
        page: 1,
        page_size: 5,
        category_name: 'gaming',
        trending: false,
        tag_name: null
      }
    }
  },
  mounted() {
    this.validateToken()
    this.categoriesStore.fetchAllCategories()

    const receivedCategoryName = this.$route.query.category_name
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
    },
    async previousPage() {
      if (this.searchData.page > 1) {
        this.searchData.page--
        await this.articlesStore.fetchAllArticlesFiltered(this.searchData)
      }
    },

    async nextPage() {
      this.searchData.page++
      await this.articlesStore.fetchAllArticlesFiltered(this.searchData)
    },
    validateToken() {
      const token = Cookies.get('token')
      if (!isNil(token) && !isEmpty(token)) {
        const decodedToken = jwtDecode(token)
        if (decodedToken.user_role === 'admin' || decodedToken.user_role === 'content_creator') {
          this.validToken = true
        } else {
          this.validToken = false
        }
      } else {
        this.validToken = false
      }
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
.centered {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}
</style>
