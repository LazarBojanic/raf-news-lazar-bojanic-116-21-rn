<template>
  <div class="container d-flex justify-content-center align-items-center">
    <div class="col-md-8 text-center">
      <h1 class="mt-4 mb-4">Comments</h1>
      <div v-for="comment in commentsStore.getComments" :key="comment.id" class="border p-3 mb-3">
        <CommentComponent :comment="comment" />
      </div>

      <div class="my-4">
          <button class="btn btn-primary" @click="showCommentForm = !showCommentForm">
            Add Comment
          </button>
        </div>
        <form v-if="showCommentForm" @submit.prevent="submitComment">
          <div class="form-group">
            <label for="author">Author:</label>
            <input
              type="text"
              class="form-control"
              id="author"
              v-model="author"
              placeholder="Author"
              required
            />
          </div>
          <div class="form-group">
            <label for="body">Body:</label>
            <textarea
              class="form-control"
              v-model="body"
              rows="4"
              placeholder="Body"
              required
            ></textarea>
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
          <button class="btn btn-secondary" @click="showCommentForm = !showCommentForm">
            Cancel
          </button>
        </form>
        <br/>

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
import CommentComponent from './CommentComponent.vue'
import { useCommentsStore } from '../stores/comments'
import { useArticlesStore } from '../stores/articles'
import {ref} from 'vue'
export default {
  name: 'CommentsComponent',
  components: {
    CommentComponent
  },
  setup() {
    const commentsStore = useCommentsStore()
    const articlesStore = useArticlesStore()
    const showCommentForm = ref(false)
    return {
      commentsStore,
      articlesStore,
      showCommentForm
    }
  },
  data() {
    return {
      searchData: {
        page: 1,
        page_size: 10
      },
      author: '',
      body: ''
    }
  },
  async mounted() {
    console.log(this.articleId)
    await this.commentsStore.fetchCommentsByArticleIdFiltered(this.articleId, this.searchData)
  },
  props: {
    articleId: Number
  },
  methods: {
    async previousPage() {
      if (this.searchData.page > 1) {
        this.searchData.page--
        await this.commentsStore.fetchCommentsByArticleIdFiltered(this.articleId, this.searchData)
      }
    },
    async nextPage() {
      this.searchData.page++
      await this.commentsStore.fetchCommentsByArticleIdFiltered(this.articleId, this.searchData)
    },
    async submitComment() {
      const addCommentData = {
        article_id: this.articleId,
        author: this.author,
        body: this.body
      }
      await this.commentsStore.addCommentToArticle(addCommentData)
      await this.commentsStore.fetchCommentsByArticleIdFiltered(this.articleId, this.searchData)
    }
  },
  watch: {
    articleId: {
      immediate: true,
      async handler(newArticleId) {
        await this.commentsStore.fetchCommentsByArticleIdFiltered(newArticleId, this.searchData)
      }
    }
  },
}
</script>

<style>
.border {
  border: 1px solid #000000;
  border-radius: 5px;
}
</style>
