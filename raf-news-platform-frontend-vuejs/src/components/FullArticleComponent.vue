<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col text-center">
        <h1>{{ articlesStore.getArticle.title }}</h1>
        <p>{{ articlesStore.getArticle.body }}</p>
        <br />
        <CommentsComponent :articleId="articlesStore.getArticle.id" />
      </div>
    </div>
  </div>
</template>

<script>
import { useArticlesStore } from '../stores/articles'
import CommentsComponent from './CommentsComponent.vue'
import { isNil, isEmpty } from 'ramda'
export default {
  name: 'FullArticleComponent',
  components: {
    CommentsComponent
  },
  setup() {
    const articlesStore = useArticlesStore()
    return {
      articlesStore
    }
  },
  mounted() {
    this.checkArticleIdAndFetchArticle()
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
      if (!isNil(articleIdParam) && !isEmpty(articleIdParam)) {
        console.log('fetching article details')
        await this.articlesStore.fetchArticle(articleIdParam)
      }
    }
  }
}
</script>

<style></style>
