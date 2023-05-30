<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col text-center">
        <h1 class="display-4">{{ articlesStore.getArticle.title }}</h1>
        <p class="lead">{{ articlesStore.getArticle.body }}</p>
        <div class="my-4">
          <CommentsComponent :articleId="articlesStore.getArticle.id" />
        </div>
        <div class="my-4">
          <button class="btn btn-primary" @click="toggleCommentForm">Add Comment</button>
        </div>
        <form v-if="showCommentForm" @submit.prevent="submitComment">
          <div class="form-group">
            <textarea
              class="form-control"
              v-model="body"
              rows="4"
              placeholder="Enter your comment"
            ></textarea>
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
          <button class="btn btn-secondary" @click="showCommentForm = false">Cancel</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { useArticlesStore } from '../stores/articles'
import CommentsComponent from './CommentsComponent.vue'
import { isNil, isEmpty } from 'ramda'
import { ref } from 'vue'
import Cookies from 'js-cookie'
import jwtDecode from 'jwt-decode'
import { useCommentsStore } from '../stores/comments'

export default {
  name: 'FullArticleComponent',
  components: {
    CommentsComponent
  },
  setup() {
    const articlesStore = useArticlesStore()
    const showCommentForm = ref(false)
    const commentsStore = useCommentsStore()
    const body = ref('')

    const toggleCommentForm = () => {
      showCommentForm.value = !showCommentForm.value
    }

    return {
      articlesStore,
      commentsStore,
      showCommentForm,
      body,
      toggleCommentForm
    }
  },
  async mounted() {
    await this.checkArticleIdAndFetchArticle()
    await this.commentsStore.fetchCommentsByArticleId(this.articlesStore.getArticle.id)
  },
  data() {
    return {}
  },
  props: {},
  computed: {},
  methods: {
    async checkArticleIdAndFetchArticle() {
      const urlParams = new URLSearchParams(window.location.search)
      const articleIdParam = urlParams.get('articleId')
      if (!isNil(articleIdParam) || !isEmpty(articleIdParam)) {
        await this.articlesStore.incrementArticleNumberOfViewsById(articleIdParam)
        await this.articlesStore.fetchArticle(articleIdParam)
      }
    },
    async submitComment() {
      const token = Cookies.get('token')
      const decodedToken = jwtDecode(token)
      const addCommentData = {
        service_user_id: decodedToken.id,
        article_id: this.articlesStore.getArticle.id,
        body: this.body
      }
      await this.commentsStore.addCommentToArticle(addCommentData)
      await this.commentsStore.fetchCommentsByArticleId(this.articlesStore.getArticle.id)
    }
  }
}
</script>

<style></style>
