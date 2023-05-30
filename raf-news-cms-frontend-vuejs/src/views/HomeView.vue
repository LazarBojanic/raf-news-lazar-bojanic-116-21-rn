<template>
  <div class="container d-flex justify-content-center align-items-center">
    <div class="col-md-8 text-center">
      <h1 class="mt-4 mb-4">Home</h1>
      <div>
        <CategoriesTableComponent />
      </div>
    </div>
  </div>
</template>

<script>
import CategoriesTableComponent from '../components/CategoriesTableComponent.vue'
export default {
  name: 'HomeView',
  components: {
    CategoriesTableComponent
  },
  mounted() {
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
