import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import Cookies from 'js-cookie'
export default createStore({
  state: {},
  getters: {},
  mutations: {},
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
        if (data) {
          return true
        }
      } catch (error) {
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
        if (data.token) {
          Cookies.set('token', data.token)
          return data.token
        }
      } catch (error) {
        console.log(error)
        return error
      }
    }
  },
  modules: {},
  plugins: [createPersistedState()]
})
