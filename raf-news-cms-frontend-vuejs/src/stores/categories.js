import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
import { Exceptions } from '../globals'
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
    async fetchAllCategories() {
      try {
        const token = Cookies.get('token')
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
          console.log(JSON.stringify(data))
        } else {
          this.exception = data
          console.log(JSON.stringify(this.exception))
        }
      } catch (error) {
        this.exception = Exceptions.ActionException
        console.log(error)
      }
    },
    async addCategory(categoryAddData) {
      try {
        const token = Cookies.get('token')
        const res = await fetch('http://95.180.97.206:8000/api/category/add/', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          },
          body: JSON.stringify(categoryAddData)
        })
        const data = await res.json()
        console.log('data = ' + data)
        if (res.status !== 500) {
          this.exception = {}
          this.fetchAllCategories()
          console.log(JSON.stringify(data))
        } else {
          this.exception = data
          console.log(JSON.stringify(this.exception))
        }
      } catch (error) {
        this.exception = Exceptions.ActionException
        console.log(error)
      }
    },
    async updateCategory(updateData) {
      try {
        const token = Cookies.get('token')
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
          this.exception = {}
          console.log(JSON.stringify(data))
        } else {
          this.exception = data
          console.log(JSON.stringify(this.exception))
        }
      } catch (error) {
        this.exception = Exceptions.ActionException
        console.log(error)
      }
    },
    async deleteCategory(categoryId) {
      try {
        const token = Cookies.get('token')
        const res = await fetch(`http://95.180.97.206:8000/api/category/deleteById/${categoryId}`,{
            method: 'DELETE',
            headers: {
              'Content-Type': 'application/json',
              Authorization: `Bearer ${token}`
            }
          }
        )
        const data = await res.json()
        if (res.status !== 500) {
          this.exception = {}
          console.log(JSON.stringify(data))
        } else {
          this.exception = data
          console.log(JSON.stringify(this.exception))
        }
      } catch (error) {
        this.exception = Exceptions.ActionException
        console.log(error)
      }
    }
  }
})
