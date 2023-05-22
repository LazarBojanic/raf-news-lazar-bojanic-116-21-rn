<template>
  <div class="container">
    <div class="row justify-content-center">
      <h1>Full Article</h1>
      <h2>{{ article.title }}</h2>
      <br />
      <CommentsComponent :articleId="article.id" />
    </div>
  </div>
</template>

<script>
import CommentsComponent from './CommentsComponent.vue'
export default {
  name: 'FullArticleComponent',
  components: {
    CommentsComponent
  },
  mounted() {},
  data() {
    return {}
  },
  props: {
    article: Object
  },
  computed: {},
  methods: {}
}
</script>

<style></style>
