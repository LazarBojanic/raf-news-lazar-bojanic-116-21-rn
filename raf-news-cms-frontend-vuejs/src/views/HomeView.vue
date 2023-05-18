<template>
  <div class="container">
    <div class="row justify-content-center">
      <h1>Home</h1>
      <ArticlesComponent />
    </div>
  </div>
</template>

<script>
import ArticlesComponent from '../components/ArticlesComponent.vue'
export default {
  name: 'HomeView',
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
