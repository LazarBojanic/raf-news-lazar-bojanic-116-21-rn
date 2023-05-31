import { defineStore } from 'pinia'
import Cookies from 'js-cookie'
import { isNil, isEmpty } from 'ramda'
export const useUsersStore = defineStore('users', {
  state: () => {
    return {
      searchData: {},
      user: {},
      users: {},
      exception: {}
    }
  },
  getters: {
    getSearchData: (state) => state.searchData,
    getUser: (state) => state.user,
    getUsers: (state) => state.users,
    getException: (state) => state.exception
  },
  actions: {
    clearException() {
      this.exception = {}
    },
    clearToken(){
      Cookies.set('token', {})
    },
    async register(registerData) {
      try {
        const res = await fetch('http://95.180.97.206:8000/api/service_user/register', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(registerData)
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
    },
    async registerFromAdmin(registerData) {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch('http://95.180.97.206:8000/api/service_user/registerFromAdmin', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          },
          body: JSON.stringify(registerData)
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.fetchUsers()
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
    async login(loginData) {
      try {
        const res = await fetch('http://95.180.97.206:8000/api/service_user/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(loginData)
        })
        const data = await res.json()
        if (res.status !== 500) {
          Cookies.set('token', data.token)
          this.clearException()
        } else {
          await this.logout()
          this.exception = data
          console.log(this.exception)
        }
      } catch (error) {
        this.exception = error
        console.log(this.exception)
      }
    },
    async loginWithToken() {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch('http://95.180.97.206:8000/api/service_user/loginWithToken', {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json',
              Authorization: `Bearer ${token}`
            }
          })
          const data = await res.json()
          if (res.status !== 500) {
            Cookies.set('token', data.token)
            this.clearException()
          } else {
            await this.logout()
            this.exception = data
            console.log(this.exception)
          }
      } catch (error) {
        this.exception = error
        console.log(this.exception)
      }
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
          Cookies.set('token', data.token)
          this.clearException()
        } else {
          this.clearToken()
          this.exception = data
          console.log(this.exception)
        }
      } catch (error) {
        this.clearToken()
        this.exception = error
        console.log(this.exception)
      }
    },
    async fetchAllUsers() {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch('http://95.180.97.206:8000/api/service_user/getAll', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          }
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.users = data
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
    async fetchAllUsersFiltered(usersSearchData) {
      try {
        this.searchData = usersSearchData
        const token = Cookies.get('token') || ''
        const res = await fetch('http://95.180.97.206:8000/api/service_user/getAllFiltered', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          },
          body: JSON.stringify(usersSearchData)
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.users = data
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
    async fetchUserById(userId) {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch(`http://95.180.97.206:8000/api/service_user/getById/${userId}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          }
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.user = data
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
    async updateUserById(userId, updateData) {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch(`http://95.180.97.206:8000/api/service_user/updateById/${userId}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          },
          body: JSON.stringify(updateData)
        })
        const data = await res.json()
        if (res.status !== 500) {
          this.user = data
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
    async deleteUserById(userId) {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch(`http://95.180.97.206:8000/api/service_user/deleteById/${userId}`, {
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
    },
    async switchUserEnabled(userId, switchEnabledData) {
      try {
        const token = Cookies.get('token') || ''
        const res = await fetch(
          `http://95.180.97.206:8000/api/service_user/switchEnabledById/${userId}`,
          {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
              Authorization: `Bearer ${token}`
            },
            body: JSON.stringify(switchEnabledData)
          }
        )
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
