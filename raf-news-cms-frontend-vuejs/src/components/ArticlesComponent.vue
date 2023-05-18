<template>
  <div class="container">
    <div class="row justify-content-center">
      <h1>Articles</h1>
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
  mounted() {},
  data() {
    return {}
  },
  props: {},
  computed: {},
  methods: {}
}
</script>

<style></style>
