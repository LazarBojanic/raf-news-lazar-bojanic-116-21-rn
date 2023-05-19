<template>
  <div class="container">
    <div class="row justify-content-center">
      <h1>Articles</h1>
      <div class="row justify-content-center">
        <div v-for="smallArticle in articlesStore.getArticles" :key="smallArticle.id" class="col-sm-4">
          <SmallArticleComponent :smallArticle="smallArticle"></SmallArticleComponent>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useArticlesStore } from '../stores/articles'
import SmallArticleComponent from './SmallArticleComponent.vue'
export default {
  name: 'SmallArticlesComponent',
  components:{
    SmallArticleComponent
  },
  setup() {
    const articlesStore = useArticlesStore()
    return {
      articlesStore
    }
  },
  mounted() {
    this.articlesStore.fetchArticles()
  },
  data() {
    return {}
  },
  props: {},
  computed: {},
  methods: {}
}
</script>

<style></style>
