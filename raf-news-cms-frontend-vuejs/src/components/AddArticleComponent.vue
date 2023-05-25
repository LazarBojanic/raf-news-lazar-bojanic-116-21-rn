<template>
  <form @submit.prevent="addArticleButton" class="container">
    <div class="row mb-3">
      <div class="col">
        <input
          v-model="category_name"
          type="text"
          class="form-control"
          placeholder="Category name"
        />
      </div>
      <div class="col">
        <input v-model="title" type="text" class="form-control" placeholder="Title" />
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <textarea v-model="body" class="form-control" placeholder="Body"></textarea>
      </div>
    </div>
    <div class="row mb-3"></div>
    <div class="row mb-3">
      <div class="col">
        <input
          v-model="tag_list"
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
import router from '../router'
import { isNil, isEmpty } from 'ramda'
import Cookies from 'js-cookie'
import jwtDecode from 'jwt-decode'

export default {
  name: 'AddArticleComponent',
  setup() {
    const articlesStore = useArticlesStore()
    return {
      articlesStore
    }
  },
  data() {
    return {
      category_name: '',
      title: '',
      body: '',
      tag_list: ''
    }
  },
  mounted() {},
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
        tag_list: this.tag_list.split(' ').map((tagName) => ({ id: 0, tag_name: tagName }))
      }
      console.log(addData)
      await this.articlesStore.addArticle(addData)
      if (isNil(this.articlesStore.getException) || isEmpty(this.articlesStore.getException)) {
        router.push('articles')
      } else {
        console.log(this.articlesStore.getException)
      }
    }
  }
}
</script>

<style scoped></style>
