import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
import { Exceptions } from '../globals'
export const useCategoriesStore = defineStore('categories', {
  state: () => {
    return {
      categories: {}
    }
  },
  getters: {
    getCategories: (state) => state.categories
  },
  actions: {
    async fetchCategories() {
      try {
        const token = Cookies.get('token')
        console.log('fetching categories with token: ' + token)
        const res = await fetch('http://95.180.97.206:8081/api/category/getAll', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
          }
        })
        console.log('aaaaaaaaaaaa')
        const data = await res.json()
        console.log('Response status:', res.status)
        console.log('Response data:', data)

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
    }
  }
})