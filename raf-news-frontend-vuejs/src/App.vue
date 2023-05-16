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
          <li class="nav-item">
            <RouterLink class="nav-link" to="/register">Register</RouterLink>
          </li>
          <li class="nav-item">
            <RouterLink class="nav-link" to="/login">Login</RouterLink>
          </li>
        </ul>
      </div>
    </nav>
    <br />
    <body>
      <RouterView @loginSuccess="updateToken" />
    </body>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import Cookies from 'js-cookie'
import jwtDecode from 'jwt-decode'
import router from './router'
export default {
  data() {
    return {
      token: '',
      userRole: ''
    }
  },
  mounted() {
    this.token = Cookies.get('token');
    this.userRole = jwtDecode(this.token).userRole;
  },
  methods: {
    ...mapActions(['logout']),
    logoutButton() {
      this.logout().then(() => {
        this.updateToken(Cookies.get('token'))
        router.push({ name: 'login' })
      })
    },
    updateToken(token) {
      this.token = token
      this.userRole = jwtDecode(this.token).userRole
    }
  },
  watch: {
    token(newToken) {
      this.token = newToken
      this.userRole = jwtDecode(this.token).userRole
    },
    userRole(newUserRole) {
      this.userRole = newUserRole
    }
  }
}
</script>

<style>

</style>
