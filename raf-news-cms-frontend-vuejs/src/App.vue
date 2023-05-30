<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" to="/home">Home</router-link>
          </li>
          <li v-if="!validToken" class="nav-item">
            <router-link class="nav-link" to="/login">Login</router-link>
          </li>
          <li v-if="!validToken" class="nav-item">
            <router-link class="nav-link" to="/register">Register</router-link>
          </li>
          <li v-if="userIsAdmin" class="nav-item">
            <router-link class="nav-link" to="/users">Users</router-link>
          </li>
          <li v-if="validToken" class="nav-item">
            <a class="nav-link" href="#" @click.prevent="logoutButton">Logout</a>
          </li>
        </ul>
      </div>
    </nav>
    <br />
    <div class="container d-flex justify-content-center align-items-center">
      <div class="col-md-8">
        <router-view @loggedIn="updateToken" />
      </div>
    </div>
  </div>
</template>

<script>
import { useUsersStore } from './stores/users'
import Cookies from 'js-cookie'
import jwtDecode from 'jwt-decode'
import { isEmpty, isNil } from 'ramda'
import { ref } from 'vue'

export default {
  setup() {
    const usersStore = useUsersStore()
    const validToken = ref(false)
    const userIsAdmin = ref(false)
    return {
      usersStore,
      validToken,
      userIsAdmin
    }
  },
  mounted() {
    this.updateToken()
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
        this.$router.push('home')
        console.log('login successful')
      } else {
        console.log('login failed')
        await this.usersStore.logout()
      }
    },
    handleExceptions() {
      if (isEmpty(this.usersStore.getException)) {
        return true
      } else {
        //display error on screen through this.usersStore.getException.message
      }
    },
    updateToken() {
      const token = Cookies.get('token')
      if (!isNil(token) && !isEmpty(token)) {
        this.validToken = jwtDecode(token).email !== ''
        this.userIsAdmin = jwtDecode(token).user_role === 'admin'
      }
    }
  }
}
</script>

<style></style>
