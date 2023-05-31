import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
export const useUsersStore = defineStore('users', {
  state: () => {
    return {
      exception: {}
    }
  },
  getters: {
    getException: (state) => state.exception
  },
  actions: {
    clearException() {
      this.exception = {}
    },
    clearToken(){
      Cookies.set('platform_token', {})
    },
    async logout() {
      try {
        const res = await fetch('http://95.180.97.206:8000/api/service_user/logout', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json'
          }
        })
        const data = await res.json()
        if (res.status !== 500) {
          Cookies.set('platform_token', data.token)
          this.clearException()
        } else {
          this.clearToken()
          this.exception = data
        }
      } catch (error) {
        this.clearToken()
        this.exception = error
        console.log(this.exception)
      }
    }
  }
})
