<template>
  <div
    :class="{ 'pushed-out': isPushedOut, 'regular-scale': isRegularScale, 'pushed-in': isPushedIn }"
    @mouseenter="pushOut"
    @mouseup="resetScale"
    @mouseleave="resetScale"
    @mousedown="pushIn"
    @click="goToFullArticlePage()"
  >
    <h1>{{ article.title }}</h1>
    <h3>{{ article.body }}</h3>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useArticlesStore } from '../stores/articles'
import router from '../router'

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
  computed: {},
  methods: {
    goToFullArticlePage() {
      //router.push()
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
  }
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
