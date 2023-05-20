<template>
  <td>{{ category.category_name }}</td>
  <td>{{ category.description }}</td>
  <td>
    <button class="btn btn-primary" @click="editCategory()">Edit</button>
    <button class="btn btn-danger" @click="deleteCategory()" > Delete</button>
  </td>
</template>

<script>
import { useCategoriesStore } from '../stores/categories'
import router from '../router'
export default {
  name: 'CategoryComponent',
  setup() {
    const categoriesStore = useCategoriesStore()

    return {
      categoriesStore
    }
  },
  data() {
    return {}
  },
  mounted(){
    
  },
  props: {
    category: Object
  },
  methods: {
    editCategory() {
      this.categoriesStore.category = this.category;
      router.push('editCategory')
    },
    deleteCategory() {
      this.categoriesStore.deleteCategory(this.category.id)
    }
  }
}
</script>

<style></style>
