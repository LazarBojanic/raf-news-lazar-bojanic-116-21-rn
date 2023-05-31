import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
export const useCommentsStore = defineStore('comments', {
  state: () => {
    return {
      comments: {},
      exception: {}
    }
  },
  getters: {
    getComments: (state) => state.comments,
    getException: (state) => state.exception
  },
  actions: {
    clearException() {
      this.exception = {}
    },
    async fetchCommentsByArticleId(articleId) {
      try {
        const token = Cookies.get('token')
        const res = await fetch(
          `http://95.180.97.206:8000/api/comment/getAllByArticleId/${articleId}`,
          {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json',
              Authorization: `Bearer ${token}`
            }
          }
        )
        const data = await res.json()
        if (res.status !== 500) {
          this.comments = data
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
