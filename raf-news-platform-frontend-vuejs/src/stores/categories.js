import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
export const useCategoriesStore = defineStore('categories', {
  state: () => {
    return {
      categories: {},
      category: {},
      exception: {}
    }
  },
  getters: {
    getCategories: (state) => state.categories,
    getCategory: (state) => state.category,
    getException: (state) => state.exception
  },
  actions: {
    clearException() {
      this.exception = {}
    },
    async fetchAllCategories() {
      try {
        const token = Cookies.get('platform_token') || ''
        const res = await fetch('http://94.189.193.50:8000/api/category/getAll', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          }
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.categories = data
          this.clearException()
        } else {
          this.exception = data
        }
      } catch (error) {
        this.exception = error
        console.log(this.exception)
      }
    }
  }
})
