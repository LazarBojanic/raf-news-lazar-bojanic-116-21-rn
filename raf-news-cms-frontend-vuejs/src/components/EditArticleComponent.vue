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
          <div class="form-group">
            <label for="body">Body:</label>
            <input type="text" class="form-control" id="body" v-model="body" required />
          </div>
          <div class="form-group">
            <label for="tag_names">Tags:</label>
            <input type="text" class="form-control" id="tag_names" v-model="tag_names" required />
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
      category_name: '',
      title: '',
      body: '',
      tag_names: ''
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
      const tagNamesArray = this.tag_names.trim().split(' ')
      const tagList = tagNamesArray.map((tag_name) => {
        return { id: 0, tag_name }
      })
      const updateData = {
        id: this.id,
        category_name: this.category_name,
        title: this.title,
        body: this.body,
        tag_list: tagList
      }
      await this.articlesStore.updateArticleById(updateData.id, updateData)
      if (isEmpty(this.articlesStore.exception) || isNil(this.articlesStore.exception)) {
        this.$router.push({ name: 'articles', query: { category_name: this.category_name } })
      }
    },
    getTagNames() {
      for (let i = 0; i < this.tagsStore.getTags.length; i++) {
        this.tag_names += this.tagsStore.getTags[i].tag.tag_name + ' '
      }
      this.tag_names = this.tag_names.trim()
    }
  }
}
</script>

<style></style>
