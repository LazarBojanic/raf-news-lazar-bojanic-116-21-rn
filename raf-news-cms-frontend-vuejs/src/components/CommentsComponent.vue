<template>
  <div class="container d-flex justify-content-center align-items-center">
    <div class="col-md-8 text-center">
      <!-- Added 'text-center' class -->
      <h1 class="mt-4 mb-4">Comments</h1>
      <div>
        <div v-for="comment in commentsStore.getComments" :key="comment.id" class="border p-3 mb-3">
          <CommentComponent :comment="comment" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CommentComponent from './CommentComponent.vue'
import { useCommentsStore } from '../stores/comments'

export default {
  name: 'CommentsComponent',
  components: {
    CommentComponent
  },
  setup() {
    const commentsStore = useCommentsStore()
    return {
      commentsStore
    }
  },
  mounted() {
    this.commentsStore.fetchCommentsByArticleId(this.articleId)
  },
  props: {
    articleId: Number
  },
  methods: {}
}
</script>

<style>
.border {
  border: 1px solid #000000;
  border-radius: 5px;
}
</style>
