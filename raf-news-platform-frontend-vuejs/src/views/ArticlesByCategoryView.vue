<template>
  <div class="container d-flex justify-content-center align-items-center">
    <div class="col-md-8 text-center">
      <h1 class="mt-4 mb-4">Articles by Category</h1>
      <div>
        <ArticlesComponent :articleMode="'articlesByCategory'" />
      </div>
    </div>
  </div>
</template>

<script>
import ArticlesComponent from '../components/ArticlesComponent.vue'

export default {
  name: 'ArticlesByCategoryView',
  components: {
    ArticlesComponent
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
