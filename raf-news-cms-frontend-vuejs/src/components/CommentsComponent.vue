<template>
    <div class="container">
      <div class="row justify-content-center">
        <h1>Articles</h1>
        <button class="btn btn-primary" @click="fetchCategoriesFromComponent">
          Fetch categories
        </button>
        <div class="row justify-content-center">
          <div v-for="comment in commentsStore.getComments" :key="comment.id" class="col-md justify-content-center" >
            <CommentComponent :comment="comment"></CommentComponent>
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
    data() {
      return {}
    },
    props: {
        articleId: Number
    },
    computed: {},
    methods: {
      async fetchCategoriesFromComponent() {
        //await this.categoriesStore.fetchCategories()
      }
    }
  }
  </script>
  
  <style></style>
  