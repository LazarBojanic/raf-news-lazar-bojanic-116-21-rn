<template>
  <div class="container">
    <div class="row justify-content-center">
      <h1>Articles</h1>
      <button class="btn btn-primary" @click="fetchCategoriesFromComponent">
        Fetch categories
      </button>
      <div class="row justify-content-center">
        <div
          v-for="category in categoriesStore.getCategories"
          :key="category.id"
          class="col-md justify-content-center"
        >
          <CategoryComponent :category="category"></CategoryComponent>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CategoryComponent from './CategoryComponent.vue'
import { useCategoriesStore } from '../stores/categories'
export default {
  name: 'CategoriesComponent',
  components: {
    CategoryComponent
  },
  setup() {
    const categoriesStore = useCategoriesStore()
    return {
      categoriesStore
    }
  },
  mounted() {},
  data() {
    return {}
  },
  props: {},
  computed: {},
  methods: {
    async fetchCategoriesFromComponent() {
      await this.categoriesStore.fetchCategories()
    }
  }
}
</script>

<style></style>
