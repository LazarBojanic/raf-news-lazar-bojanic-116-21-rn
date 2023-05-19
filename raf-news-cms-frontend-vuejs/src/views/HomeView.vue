<template>
  <div class="container">
    <div class="row justify-content-center">
      <h1>Home</h1>
      <CategoriesComponent />
    </div>
  </div>
</template>

<script>
//import ArticlesComponent from '../components/ArticlesComponent.vue'
import CategoriesComponent from '../components/CategoriesComponent.vue'
export default {
  name: 'HomeView',
  components: {
    //ArticlesComponent,
    CategoriesComponent
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
