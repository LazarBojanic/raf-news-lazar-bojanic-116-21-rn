import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
import { Exceptions } from '../globals'
import { isNil, isEmpty } from 'ramda'
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
          this.exception = {}
        } else {
          Cookies.set('platform_token', {})
          this.exception = data
        }
      } catch (error) {
        Cookies.set('platform_token', {})
        this.exception = Exceptions.ActionException
      }
    },
    clearException() {
      this.exception = {}
    }
  }
})
