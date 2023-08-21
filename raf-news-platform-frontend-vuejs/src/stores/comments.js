import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
export const useCommentsStore = defineStore('comments', {
  state: () => {
    return {
      comments: {},
      comment: {},
      exception: {}
    }
  },
  getters: {
    getComments: (state) => state.comments,
    getComment: (state) => state.comment,
    getException: (state) => state.exception
  },
  actions: {
    clearException() {
      this.exception = {}
    },
    async fetchCommentsByArticleIdFiltered(articleId, searchData) {
      try {
        const token = Cookies.get('platform_token') || ''
        const res = await fetch(
          `http://94.189.193.50:8000/api/comment/getAllByArticleIdFiltered/${articleId}`,
          {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
              Authorization: `Bearer ${token}`
            },
            body: JSON.stringify(searchData)
          }
        )
        const data = await res.json()
        if (res.status !== 500) {
          this.comments = data
          this.clearException()
        } else {
          this.exception = data
        }
      } catch (error) {
        this.exception = error
        console.log(this.exception)
      }
    },
    async addCommentToArticle(addCommentData) {
      try {
        const token = Cookies.get('platform_token') || ''
        const res = await fetch('http://94.189.193.50:8000/api/comment/addToArticle', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          },
          body: JSON.stringify(addCommentData)
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.comment = data
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
