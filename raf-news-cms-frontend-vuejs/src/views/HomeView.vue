<template>
  <div class="container d-flex justify-content-center align-items-center">
    <div class="col-md-8 text-center">
      <!-- Added 'text-center' class -->
      <h1 class="mt-4 mb-4">Home</h1>
      <div>
        <CategoriesComponent />
      </div>
    </div>
  </div>
</template>

<script>
import CategoriesComponent from '../components/CategoriesComponent.vue'

export default {
  name: 'HomeView',
  components: {
    CategoriesComponent
  },
  mounted() {
    // Add any logic here if needed
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
