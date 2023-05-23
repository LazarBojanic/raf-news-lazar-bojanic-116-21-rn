import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
import { Exceptions } from '../globals'
export const useArticlesStore = defineStore('articles', {
  state: () => {
    return {
      articles: {},
      article: {}
    }
  },
  getters: {
    getArticles: (state) => state.articles,
    getArticle: (state) => state.article
  },
  actions: {
    async fetchArticle(articleId) {
      try {
        const token = Cookies.get('token')
        const res = await fetch(`http://95.180.97.206:8081/api/article/getById/${articleId}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
          }
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.article = data
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
    async fetchAllArticles() {
      try {
        const token = Cookies.get('token')
        const res = await fetch('http://95.180.97.206:8081/api/article/getAll', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
          }
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.articles = data
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
    async fetchAllArticlesFiltered(searchData) {
      try {
        const token = Cookies.get('token')
        const res = await fetch('http://95.180.97.206:8081/api/article/getAllFiltered', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
          },
          body: JSON.stringify(searchData)
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.articles = data
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
