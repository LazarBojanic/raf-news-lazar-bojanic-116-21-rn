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
    async register(registerData) {
      try {
        const res = await fetch('http://95.180.97.206:8081/api/service_user/register', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(registerData)
        })
        const data = await res.json()
        console.log(data)
        console.log(res.status + ' ' + res.statusText + ' ' + res.text)
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
    async login(loginData) {
      try {
        const res = await fetch('http://95.180.97.206:8081/api/service_user/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(loginData)
        })
        const data = await res.json()
        if (res.status !== 500) {
          Cookies.set('token', data.token)
          this.exception = {}
          console.log(JSON.stringify(data))
        } else {
          await this.logout()
          this.exception = data
          console.log(JSON.stringify(this.exception))
        }
      } catch (error) {
        this.exception = Exceptions.ActionException
        console.log(error)
      }
    },
    async loginWithToken() {
      try {
        const token = Cookies.get('token')
        if (!isEmpty(token) && !isNil(token)) {
          const res = await fetch('http://95.180.97.206:8081/api/service_user/loginWithToken', {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${token}`
            }
          })
          const data = await res.json()
          if (res.status !== 500) {
            Cookies.set('token', data.token)
            this.exception = {}
            console.log(JSON.stringify(data))
          } else {
            await this.logout()
            this.exception = data
            console.log(JSON.stringify(this.exception))
          }
        }
      } catch (error) {
        this.exception = Exceptions.ActionException
        console.log(error)
      }
    },
    async logout() {
      try {
        const res = await fetch('http://95.180.97.206:8081/api/service_user/logout', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json'
          }
        })
        const data = await res.json()
        if (res.status !== 500) {
          Cookies.set('token', data.token)
          //Cookies.remove('token')
          this.exception = {}
          console.log(JSON.stringify(data))
        } else {
          Cookies.set('token', {})
          this.exception = data
          console.log(JSON.stringify(this.exception))
        }
      } catch (error) {
        Cookies.set('token', {})
        this.exception = Exceptions.ActionException
        console.log(error)
      }
    },
    clearException() {
      this.exception = {}
    }
  }
})
