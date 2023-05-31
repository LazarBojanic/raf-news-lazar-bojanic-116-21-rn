<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col">
        <h1>Edit Article</h1>
        <form @submit.prevent="updateArticle">
          <div class="form-group">
            <label for="category_name" class="form-label">Category:</label>
            <select v-model="category_name" id="category_name" class="form-select">
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
          <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" id="title" v-model="title" required />
          </div>
          <textarea
            class="form-control"
            v-model="body"
            rows="4"
            placeholder="Body"
            required
          ></textarea>
          <div class="form-group">
            <label for="tag_name_list">Tags:</label>
            <input type="text" class="form-control" id="tag_name_list" v-model="tag_name_list" required />
          </div>
          <button type="submit" class="btn btn-primary">Save</button>
          <button class="btn btn-secondary ml-2" @click="cancelEdit">Cancel</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { useArticlesStore } from '../stores/articles'
import { useCategoriesStore } from '../stores/categories'
import { useTagsStore } from '../stores/tags'
import { isEmpty, isNil } from 'ramda'
import Cookies from 'js-cookie'
import jwtDecode from 'jwt-decode'
export default {
  name: 'EditArticleComponent',
  setup() {
    const articlesStore = useArticlesStore()
    const tagsStore = useTagsStore()
    const categoriesStore = useCategoriesStore()
    return {
      articlesStore,
      tagsStore,
      categoriesStore
    }
  },
  data() {
    return {
      id: 0,
      service_user_id: 0,
      category_name: '',
      title: '',
      body: '',
      tag_name_list: ''
    }
  },
  async mounted() {
    const receivedArticleId = this.$route.query.articleId
    if (!isNil(receivedArticleId)) {
      this.id = receivedArticleId
    }
    await this.categoriesStore.fetchAllCategories()
    await this.articlesStore.fetchArticleById(this.id)
    await this.tagsStore.fetchTagsForArticle(this.id)
    this.category_name = this.articlesStore.getArticle.category.category_name
    this.title = this.articlesStore.getArticle.title
    this.body = this.articlesStore.getArticle.body
    this.getTagNames()
  },
  methods: {
    async updateArticle() {
      const updateData = {
        id: this.id,
        service_user_id: this.getDecodedToken().id,
        category_name: this.category_name,
        title: this.title,
        body: this.body,
        tag_name_list: this.tag_name_list.trim().split(' ')
      }
      await this.articlesStore.updateArticleById(updateData.id, updateData)
      if (isEmpty(this.articlesStore.exception) || isNil(this.articlesStore.exception)) {
        this.$router.push({ name: 'articles', query: { category_name: this.category_name } })
      }
    },
    getTagNames() {
      for (let i = 0; i < this.articlesStore.getArticle.tag_list.length; i++) {
        this.tag_name_list += this.articlesStore.getArticle.tag_list[i].tag.tag_name + ' '
      }
      this.tag_name_list = this.tag_name_list.trim()
    },
    getDecodedToken() {
      const token = Cookies.get('token')
      if (!isNil(token) && !isEmpty(token)) {
        return jwtDecode(token)
      }
    },
  }
}
</script>

<style></style>
