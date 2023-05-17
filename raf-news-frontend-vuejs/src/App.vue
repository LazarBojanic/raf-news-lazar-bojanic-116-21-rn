<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <RouterLink class="nav-link" to="/home">Home</RouterLink>
          </li>
          <li class="nav-item">
            <RouterLink class="nav-link" to="/about">About</RouterLink>
          </li>
          <li v-if="!checkEmail(this.decodedToken.email)" class="nav-item">
            <RouterLink class="nav-link" to="/register">Register</RouterLink>
          </li>
          <li v-if="!checkEmail(this.decodedToken.email)" class="nav-item">
            <RouterLink class="nav-link" to="/login">Login</RouterLink>
          </li>
          <li v-if="checkEmail(this.decodedToken.email)" class="nav-item">
            <a class="nav-link" href="#" @click.prevent="logoutButton">Logout</a>
          </li>
        </ul>
      </div>
    </nav>
    <br />
    <div>
      <RouterView @loggedIn="updateToken" />
    </div>
  </div>
</template>

<script>
import { mapStores } from 'pinia'
import { useNewsStore } from './stores'
import Cookies from 'js-cookie'
import jwtDecode from 'jwt-decode'

export default {
  data() {
    return {
      decodedToken: {}
    }
  },
  mounted() {
    this.decodedToken = jwtDecode(Cookies.get('token'))
  },
  methods: {
    async logoutButton() {
      const store = useNewsStore()
      await store.logout()
      this.decodedToken = jwtDecode(Cookies.get('token'))
      this.$router.push({ name: 'login' })
    },
    async loginWithToken() {
      const store = useNewsStore()
      this.decodedToken = jwtDecode(Cookies.get('token'))
      const loginData = {
        email: this.token.email,
        pass: this.token.pass
      }
      await store.login(loginData)
      this.$router.push({ name: 'home' })
    },
    updateToken() {
      this.decodedToken = jwtDecode(Cookies.get('token'))
    },
    checkEmail(email) {
      return email !== null && email !== ''
    }
  },
  computed: {
    ...mapStores(useNewsStore)
  }
}
</script>

<style>

</style>
