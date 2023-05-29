<template>
  <td
    :class="{ 'pushed-out': isPushedOut, 'regular-scale': isRegularScale, 'pushed-in': isPushedIn }"
    @mouseenter="pushOut"
    @mouseup="resetScale"
    @mouseleave="resetScale"
    @mousedown="pushIn"
    @click="goToFullArticlePage()"
  >
    {{ article.title }}
  </td>
  <td><button class="btn btn-primary" @click="editArticle()">Edit</button></td>
  <td><button class="btn btn-danger" @click="deleteArticle()">Delete</button></td>
</template>

<script>
import { ref } from 'vue'
import { useArticlesStore } from '../stores/articles'
import router from '../router'

export default {
  name: 'ArticleRowComponent',
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
      const articleId = this.article.id
      const url = `http://localhost:5174/fullArticle?articleId=${articleId}`
      window.open(url, '_blank')
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
    },
    editArticle() {
      this.$router.push({ name: 'editArticle', query: { articleId: this.article.id } })
    },
    async deleteArticle() {
      await this.articlesStore.deleteArticleById(this.article.id)
      await this.articlesStore.fetchAllArticlesFiltered(this.articlesStore.getSearchData)
    }
  }
}
</script>

<style scoped>
.pushed-in {
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
  transform: scale(0.95);
}
.regular-scale {
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
  transform: scale(1);
}
.pushed-out {
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
  transform: scale(1.05);
}
</style>
