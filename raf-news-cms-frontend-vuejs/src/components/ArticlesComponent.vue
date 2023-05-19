<template>
  <div class="container">
    <div class="row justify-content-center">
      <h1>Articles</h1>
      <div class="row justify-content-center">
        <div v-for="article in articlesStore.getArticles" :key="article.id" class="col-sm-4">
          <Article :article="article"></Article>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useArticlesStore } from '../stores/articles'
export default {
  name: 'ArticlesComponent',
  setup() {
    const articlesStore = useArticlesStore()
    return {
      articlesStore
    }
  },
  async mounted() {
    await this.articlesStore.fetchArticles()
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
