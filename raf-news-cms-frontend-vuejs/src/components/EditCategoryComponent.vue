<template>
  <div class="container">
    <div class="row justify-content-center">
      <h1>Edit Category</h1>
      <form @submit.prevent="updateCategory">
        <div class="form-group">
          <label for="category_name">Name:</label>
          <input
            type="text"
            class="form-control"
            id="category_name"
            v-model="category_name"
            required
          />
        </div>
        <div class="form-group">
          <label for="description">Description:</label>
          <input type="text" class="form-control" id="description" v-model="description" required />
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <button class="btn btn-secondary ml-2" @click="cancelEdit">Cancel</button>
      </form>
    </div>
  </div>
</template>

<script>
import { useCategoriesStore } from '../stores/categories'
import router from '../router'
export default {
  name: 'EditCategoryComponent',
  setup() {
    const categoriesStore = useCategoriesStore()
    const category = {}
    return {
      categoriesStore,
      category
    }
  },
  data() {
    return {
      id: 0,
      category_name: '',
      description: ''
    }
  },
  mounted() {
    this.category_name = this.categoriesStore.getCategory.category_name
    this.description = this.categoriesStore.getCategory.description
  },
  methods: {
    async updateCategory() {
      const updateData = {
        id: this.categoriesStore.getCategory.id,
        category_name: this.category_name,
        description: this.description
      }
      await this.categoriesStore.updateCategory(updateData)
      router.push('home')
    },
    cancelEdit() {
      router.push('home')
    }
  }
}
</script>

<style></style>
