<template>
  <form @submit.prevent="addArticleButton" class="container">
    <div class="row mb-3">
      <div class="col">
        <label for="category" class="form-label">Category:</label>
        <select v-model="category_name" id="category" class="form-select">
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
      <div class="col">
        <input v-model="title" type="text" class="form-control" placeholder="Title" required />
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <textarea v-model="body" class="form-control" placeholder="Body" required></textarea>
      </div>
    </div>
    <div class="row mb-3"></div>
    <div class="row mb-3">
      <div class="col">
        <input
          v-model="tag_name_list"
          type="text"
          class="form-control"
          placeholder="Tag list (separated by spaces)"
        />
      </div>
    </div>
    <button type="submit" class="btn btn-primary">Add article</button>
  </form>
</template>

<script>
import { useArticlesStore } from '../stores/articles'
import { useCategoriesStore } from '../stores/categories'
import { isNil, isEmpty } from 'ramda'
import Cookies from 'js-cookie'
import jwtDecode from 'jwt-decode'

export default {
  name: 'AddArticleComponent',
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
      category_name: '',
      title: '',
      body: '',
      tag_name_list: ''
    }
  },
  mounted() {
    this.categoriesStore.fetchAllCategories()
    const receivedCategoryName = this.$route.query.category_name
    if (!isNil(receivedCategoryName)) {
      this.category_name = receivedCategoryName
    }
  },
  methods: {
    getDecodedToken() {
      const token = Cookies.get('token')
      if (!isNil(token) && !isEmpty(token)) {
        return jwtDecode(token)
      }
    },
    async addArticleButton() {
      const addData = {
        id: 0,
        service_user_id: this.getDecodedToken().id,
        category_name: this.category_name,
        title: this.title,
        body: this.body,
        tag_name_list: this.tag_name_list.trim().split(' ')
      }
      await this.articlesStore.addArticle(addData)
      if (isEmpty(this.articlesStore.getException)) {
        this.$router.push('articles')
      }
    }
  }
}
</script>

<style scoped></style>
