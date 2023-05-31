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
            <CategoryRowComponent :category="category" />
          </tr>
        </tbody>
      </table>
      <div class="col">
        <div>
        <button
          class="btn btn-primary"
          :disabled="categoriesSearchData.page === 1"
          @click="previousPage"
        >
          Previous Page
        </button>
        <span class="current-page">Page {{ categoriesSearchData.page }}</span>
        <button class="btn btn-primary" @click="nextPage">Next Page</button>
      </div>
      <br/>
      <div>
       
        <button
          :disabled="!userIsAdmin"
          class="btn btn-success"
          @click="changeAddCategoryFormVisibility"
        >
          Add Category
        </button>
      </div>
      <div v-if="addCategoryFormVisible">
        <div class="col-md-12">
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
      
    </div>
  </div>
</template>

<script>
import router from '../router'
import { useCategoriesStore } from '../stores/categories'
import { ref } from 'vue'
import CategoryRowComponent from '../components/CategoryRowComponent.vue'
import Cookies from 'js-cookie'
import jwtDecode from 'jwt-decode'
import { isNil, isEmpty } from 'ramda'
export default {
  name: 'CategoriesTableComponent',
  setup() {
    const categoriesStore = useCategoriesStore()
    const addCategoryFormVisible = ref(false)
    const category_name = ref('')
    const description = ref('')
    const userIsAdmin = ref(false)

    return {
      categoriesSearchData: {
        page: 1,
        page_size: 5
      },
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
    changeAddCategoryFormVisibility() {
      this.addCategoryFormVisible = !this.addCategoryFormVisible
    },
    cancelAddCategory() {
      this.category_name = ''
      this.description = ''
      this.addCategoryFormVisible = false
    },
    addCategory() {
      const categoryAddData = {
        id: 0,
        category_name: this.category_name,
        description: this.description
      }
      this.categoriesStore.addCategory(categoryAddData)
    },
    checkIfAdmin() {
      const token = Cookies.get('token')
      if (!isNil(token) && !isEmpty(token)) {
        this.userIsAdmin = jwtDecode(token).user_role === 'admin'
      }
    },
    async previousPage() {
      if (this.categoriesSearchData.page > 1) {
        this.categoriesSearchData.page--
        await this.categoriesStore.fetchAllCategoriesFiltered(this.categoriesSearchData)
      }
    },

    async nextPage() {
      this.categoriesSearchData.page++
      await this.categoriesStore.fetchAllCategoriesFiltered(this.categoriesSearchData)
    }
  },
  components: { CategoryRowComponent }
}
</script>

<style></style>
