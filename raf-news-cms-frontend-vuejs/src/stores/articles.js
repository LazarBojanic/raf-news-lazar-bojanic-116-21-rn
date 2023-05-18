import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
export const useArticlesStore = defineStore('articles', {
  state: () => {
    return {
      // all these properties will have their type inferred automatically
      articles: {}
    }
  },
  getters: {
    getArticles: (state) => state.articles
  },
  actions: {
    async fetchArticles() {
      const token = Cookies.get('token')
      const res = await fetch('http://95.180.97.206:8081/api/article/getAll', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`
        }
      })
      if (res.status === 500) {
        console.log(data.message)
        return
      }
      const data = await res.json()
      this.articles = data
    }
  }
})
