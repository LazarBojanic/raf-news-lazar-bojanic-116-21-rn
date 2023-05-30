import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
import { Exceptions } from '../globals'
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
        const token = Cookies.get('platform_token')
        const res = await fetch(
          `http://95.180.97.206:8000/api/comment/getAllByArticleIdFiltered/${articleId}`,
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
          this.exception = {}
        } else {
          this.exception = data
        }
      } catch (error) {
        this.exception = Exceptions.ActionException
      }
    },
    async addCommentToArticle(addCommentData) {
      try {
        const token = Cookies.get('platform_token')
        const res = await fetch('http://95.180.97.206:8000/api/comment/addToArticle', {
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
