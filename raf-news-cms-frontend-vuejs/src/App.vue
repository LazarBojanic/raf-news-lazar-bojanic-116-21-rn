<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <RouterLink class="nav-link" to="/home">Home</RouterLink>
          </li>
          <li v-if="!validToken" class="nav-item">
            <router-link class="nav-link" to="/login">Login</router-link>
          </li>
          <li v-if="!validToken" class="nav-item">
            <router-link class="nav-link" to="/register">Register</router-link>
          </li>
          <li v-if="validToken" class="nav-item">
            <a class="nav-link" href="#" @click.prevent="logoutButton">Logout</a>
          </li>
        </ul>
      </div>
    </nav>
    <br />
    <div class="container">
      <div class="row justify-content-center">
        <RouterView @loggedIn="updateToken" />
      </div>
    </div>
  </div>
</template>

<script>
import { useUsersStore } from './stores/users'
import Cookies from 'js-cookie'
import jwtDecode from 'jwt-decode'
import { ref } from 'vue'
export default {
  setup() {
    const usersStore = useUsersStore()
    const validToken = ref(false)
    return {
      usersStore,
      validToken
    }
  },
  data() {
    return {}
  },
  mounted() {
    this.loginWithTokenFromComponent()
  },
  methods: {
    async logoutButton() {
      await this.usersStore.logout()
      this.updateToken()
      if (this.handleExceptions()) {
        this.$router.push('login')
        console.log('logout successful')
      } else {
        console.log('logout failed')
      }
    },
    async loginWithTokenFromComponent() {
      await this.usersStore.loginWithToken()
      this.updateToken()
      if (this.handleExceptions()) {
        this.$router.push({ name: 'home' })
        console.log('login successful')
      } else {
        console.log('login failed')
      }
    },
    handleExceptions() {
      if (Object.keys(this.usersStore.getException).length !== 0) {
        console.log(JSON.stringify(this.usersStore.getException.message))
        return false
      } else {
        //TODO handle exceptions
        return true
      }
    },
    updateToken() {
      this.validToken = jwtDecode(Cookies.get('token')).email !== ''
    }
  },
  computed: {
    /*validToken(){
      return jwtDecode(Cookies.get('token')).email !== ''
    }*/
  }
}
</script>

<style></style>
