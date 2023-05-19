<template>
  <div class="container">
    <div class="row justify-content-center">
      <router-link class="small-article" :to="{ name: 'fullArticle', params: { article: smallArticle } }" >
        <h1>Small Article</h1>
        <h2>{{ smallArticle.title }}</h2>
      </router-link>
    </div>
  </div>
</template>

<script>
import useArticlesStore from '../stores/articles'

export default {
  name: 'SmallArticleComponent',
  setup() {
    const articlesStore = useArticlesStore()
    return {
      articlesStore
    }
  },
  mounted() {},
  data() {
    return {}
  },
  props: {
    smallArticle: Object
  },
  computed: {},
  methods: {}
}
</script>

<style scoped>
.small-article {
  border: 1px solid #ccc;
  padding: 10px;
  transition: transform 0.3s ease;
}

.small-article:hover {
  transform: scale(1.1);
}

.small-article:active {
  transform: scale(0.9);
}
</style>
