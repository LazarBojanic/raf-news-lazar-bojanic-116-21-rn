<template>
  <div class="container d-flex justify-content-center align-items-center">
    <div class="col-md-8 text-center">
      <h1 class="mt-4 mb-4">Home</h1>
      <div>
        <ArticlesComponent />
      </div>
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
  mounted() {
    console.log('home instantiated')
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
