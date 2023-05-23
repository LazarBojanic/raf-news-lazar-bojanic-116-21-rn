<template>
  <div class="container">
    <div class="row justify-content-center">
      <h1 class="mt-4 mb-4">Categories</h1>
      <table class="table">
        <thead>
          <tr>
            <th>Category Name</th>
            <th>Description</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="category in categoriesStore.getCategories" :key="category.id">
            <CategoryComponent :category="category" />
          </tr>
        </tbody>
      </table>
      <div>
        <button :disabled="!userIsAdmin" class="btn btn-success" @click="changeAddCategoryFormVisibility">Add Category</button>
      </div>
      <div v-if="addCategoryFormVisible">
        <h2>Add New Category</h2>
        <form :disabled="!userIsAdmin" @submit.prevent="addCategory">
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
            <input
              type="text"
              class="form-control"
              id="description"
              v-model="description"
              required
            />
          </div>
          <button type="submit" class="btn btn-primary">Save</button>
          <button class="btn btn-secondary" @click="cancelAddCategory">Cancel</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import router from '../router'
import { useCategoriesStore } from '../stores/categories'
import { ref } from 'vue'
import CategoryComponent from './CategoryComponent.vue'
import Cookies from 'js-cookie'
import jwtDecode from 'jwt-decode'

export default {
  name: 'CategoriesComponent',
  setup() {
    const categoriesStore = useCategoriesStore()
    const addCategoryFormVisible = ref(false)
    const category_name = ref('')
    const description = ref('')
    const userIsAdmin = ref(false)
    
    return {
      categoriesStore,
      addCategoryFormVisible,
      category_name,
      description,
      userIsAdmin
    }
  },
  mounted() {
    this.checkIfAdmin()
    this.categoriesStore.fetchAllCategories()
  },
  methods: {
    goToNewsWithCategoryPage(categoryId) {
      console.log(categoryId)
    },
    changeAddCategoryFormVisibility(){
      this.addCategoryFormVisible = !this.addCategoryFormVisible;
    },
    cancelAddCategory() {
      this.category_name = ''
      this.description = ''
      this.addCategoryFormVisible = false
    },
    addCategory(){
      const categoryAddData = {
        id : 0,
        category_name : this.category_name,
        description : this.description
      }
      //console.log(categoryAddData)
      this.categoriesStore.addCategory(categoryAddData);
    },
    checkIfAdmin(){
      this.userIsAdmin = jwtDecode(Cookies.get('token')).user_role === 'admin'
    }
  },
  components: { CategoryComponent }
}
</script>

<style></style>
