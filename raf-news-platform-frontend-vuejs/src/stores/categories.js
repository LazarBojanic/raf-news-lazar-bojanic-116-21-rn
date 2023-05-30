import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
import { Exceptions } from '../globals'
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
        const token = Cookies.get('platform_token')
        const res = await fetch('http://95.180.97.206:8000/api/category/getAll', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          }
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.categories = data
          this.exception = {}
        } else {
          this.exception = data
        }
      } catch (error) {
        this.exception = Exceptions.ActionException
      }
    }
  }
})
