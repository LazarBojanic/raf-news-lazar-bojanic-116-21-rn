<template>
  <div class="container" @click="goToFullArticlePage">
    <div class="row justify-content-center">
      <div
        class="article-card"
        :class="{
          'pushed-out': isPushedOut,
          'regular-scale': isRegularScale,
          'pushed-in': isPushedIn
        }"
        @mouseenter="pushOut"
        @mouseup="resetScale"
        @mouseleave="resetScale"
        @mousedown="pushIn"
        @click="goToFullArticlePage()"
      >
        <div class="article-info">
          <h3>Number of Views: {{ article.number_of_views }}</h3>
          <h1>{{ article.title }}</h1>
          <h3>{{ article.category.category_name }}</h3>
          <h1>{{ formattedTime }}</h1>
        </div>
        <div class="article-body">
          <h3>{{ article.body.slice(0, 10) }}{{ article.body.length > 10 ? '...' : '' }}</h3>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useArticlesStore } from '../stores/articles'

export default {
  name: 'ArticleComponent',
  setup() {
    const articlesStore = useArticlesStore()
    const isPushedOut = ref(false)
    const isRegularScale = ref(true)
    const isPushedIn = ref(false)

    return {
      articlesStore,
      isPushedOut,
      isRegularScale,
      isPushedIn
    }
  },
  mounted() {},
  data() {
    return {}
  },
  props: {
    article: Object
  },
  methods: {
    goToFullArticlePage() {
      this.$router.push({ path: '/fullArticle', query: { articleId: this.article.id } })
    },
    pushOut() {
      this.isPushedOut = true
      this.isRegularScale = false
      this.isPushedIn = false
    },
    resetScale() {
      this.isPushedOut = false
      this.isRegularScale = true
      this.isPushedIn = false
    },
    pushIn() {
      this.isPushedOut = false
      this.isRegularScale = false
      this.isPushedIn = true
    }
  },
  computed: {
    formattedTime() {
      const timestamp = new Date(this.article.time_published)
      return timestamp.toLocaleString()
    }
  }
}
</script>

<style scoped>
.pushed-in {
  transform: scale(0.95);
}
.regular-scale {
  transform: scale(1);
}
.pushed-out {
  transform: scale(1.05);
}
.article-card {
  border: 1px solid #000;
  padding: 10px;
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
  width: 800px; /* Adjust the width as per your preference */
  margin: 10px; /* Add some margin to prevent overlapping */
}
.article-info {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.article-body {
  margin-top: 10px;
}

.article-body h3 {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
