import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
export const useCategoriesStore = defineStore('categories', {
  state: () => {
    return {
      categories: {},
      category: {},
      category_name: '',
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
        const token = Cookies.get('token') || ''
        console.log(token)
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
          this.clearException()
        } else {
          this.exception = data
          console.log(this.exception)
        }
      } catch (error) {
        this.exception = error
        console.log(this.exception)
      }
    },
    async fetchAllCategoriesFiltered(categoriesSearchData) {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch('http://95.180.97.206:8000/api/category/getAllFiltered', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          },
          body: JSON.stringify(categoriesSearchData)
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.categories = data
          this.clearException()
        } else {
          this.exception = data
          console.log(this.exception)
        }
      } catch (error) {
        this.exception = error
        console.log(this.exception)
      }
    },
    async addCategory(categoryAddData) {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch('http://95.180.97.206:8000/api/category/add/', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          },
          body: JSON.stringify(categoryAddData)
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.clearException()
          this.fetchAllCategories()
        } else {
          this.exception = data
          console.log(this.exception)
        }
      } catch (error) {
        this.exception = error
        console.log(this.exception)
      }
    },
    async updateCategory(updateData) {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch(
          `http://95.180.97.206:8000/api/category/updateById/${updateData.id}`,
          {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
              Authorization: `Bearer ${token}`
            },
            body: JSON.stringify(updateData)
          }
        )
        const data = await res.json()
        if (res.status !== 500) {
          this.clearException()
        } else {
          this.exception = data
          console.log(this.exception)
        }
      } catch (error) {
        this.exception = error
        console.log(this.exception)
      }
    },
    async deleteCategory(categoryId) {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch(`http://95.180.97.206:8000/api/category/deleteById/${categoryId}`, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          }
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.clearException()
        } else {
          this.exception = data
          console.log(this.exception)
        }
      } catch (error) {
        this.exception = error
        console.log(this.exception)
      }
    }
  }
})
