import { defineStore } from 'pinia'
import Cookies from 'js-cookie'

export const useNewsStore = defineStore('news', {
  state: () => ({}),
  getters: {},
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
        console.log('data' + data);
        if (data) {
          console.log(data)
          return true
        }
      } 
      catch (error) {
        console.log(error)
        return false
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
        if(data.token){
          Cookies.set('token', data.token)
        }
        else{
          console.log(data);
        }
      } 
      catch (error) {
        console.log(error)
      }
    },
    async loginWithToken() {
      try {
        const token = Cookies.get('token');
        const res = await fetch('http://95.180.97.206:8081/api/service_user/loginWithToken', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
          }
        })
        const data = await res.json()
        if(data.token){
          Cookies.set('token', data.token)
        }
        else{
          console.log(data);
        }
      } 
      catch (error) {
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
        if(data.token){
          Cookies.set('token', data.token)
        }
        else{
          console.log(data);
        }
      } 
      catch (error) {
        console.log(error)
      }
    }
  }
})
