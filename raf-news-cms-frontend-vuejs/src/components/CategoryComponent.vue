<template>
  <td
    :class="{ 'pushed-out': isPushedOut, 'regular-scale': isRegularScale, 'pushed-in': isPushedIn }"
    @mouseenter="pushOut"
    @mouseup="resetScale"
    @mouseleave="resetScale"
    @mousedown="pushIn"
    @click="goToArticlesWithCategoryPage()"
  >
    {{ category.category_name }}
  </td>
  <td
    :class="{ 'pushed-out': isPushedOut, 'regular-scale': isRegularScale, 'pushed-in': isPushedIn }"
    @mouseenter="pushOut"
    @mouseup="resetScale"
    @mouseleave="resetScale"
    @mousedown="pushIn"
    @click="goToArticlesWithCategoryPage()"
  >
    {{ category.description }}
  </td>
  <td><button class="btn btn-primary" @click="editCategory()">Edit</button></td>
  <td><button class="btn btn-danger" @click="deleteCategory()">Delete</button></td>
</template>

<script>
import { useCategoriesStore } from '../stores/categories'
import router from '../router'
import { ref } from 'vue'
export default {
  name: 'CategoryComponent',
  setup() {
    const categoriesStore = useCategoriesStore()
    const isPushedOut = ref(false)
    const isRegularScale = ref(true)
    const isPushedIn = ref(false)
    return {
      categoriesStore,
      isPushedOut,
      isRegularScale,
      isPushedIn
    }
  },
  data() {
    return {}
  },
  mounted() {},
  props: {
    category: Object
  },
  methods: {
    editCategory() {
      this.categoriesStore.category = this.category
      router.push('editCategory')
    },
    async deleteCategory() {
      //console.log(this.category.id)
      await this.categoriesStore.deleteCategory(this.category.id)
      await this.categoriesStore.fetchAllCategories()
    },
    goToArticlesWithCategoryPage() {
      router.push({ path: '/articles', query: { category_name: this.category.category_name } })
    },
    pushOut() {
      this.isPushedOut = true
      this.isRegularScale = false
      this.isPushedIn = false
    },
    resetScale() {
      this.isPushedOut = false
      this.isRegularScale = true
      this.isPushedIn = false
    },
    pushIn() {
      this.isPushedOut = false
      this.isRegularScale = false
      this.isPushedIn = true
    }
  }
}
</script>

<style>
.pushed-in {
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
  transform: scale(0.95);
}
.regular-scale {
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
  transform: scale(1);
}
.pushed-out {
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
  transform: scale(1.05);
}
</style>
