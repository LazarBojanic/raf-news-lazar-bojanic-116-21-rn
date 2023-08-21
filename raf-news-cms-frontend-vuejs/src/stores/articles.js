import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
export const useArticlesStore = defineStore('articles', {
  state: () => {
    return {
      searchData: {},
      articles: {},
      article: {},
      exception: {}
    }
  },
  getters: {
    getSearchData: (state) => state.searchData,
    getArticles: (state) => state.articles,
    getArticle: (state) => state.article,
    getException: (state) => state.exception
  },
  actions: {
    clearException() {
      this.exception = {}
    },
    async fetchArticleById(articleId) {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch(`http://94.189.193.50:8000/api/article/getById/${articleId}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          }
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.article = data
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
    async fetchAllArticles() {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch('http://94.189.193.50:8000/api/article/getAll', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          }
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.articles = data
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
    async fetchAllArticlesFiltered(searchData) {
      try {
        this.searchData = searchData
        const token = Cookies.get('token') || ''
        const res = await fetch('http://94.189.193.50:8000/api/article/getAllFiltered', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          },
          body: JSON.stringify(searchData)
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.articles = data
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
    async addArticle(addData) {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch('http://94.189.193.50:8000/api/article/add', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          },
          body: JSON.stringify(addData)
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.article = data
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
    async updateArticleById(articleId, updateData) {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch(`http://94.189.193.50:8000/api/article/updateById/${articleId}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          },
          body: JSON.stringify(updateData)
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.article = data
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
    async deleteArticleById(articleId) {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch(`http://94.189.193.50:8000/api/article/deleteById/${articleId}`, {
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
