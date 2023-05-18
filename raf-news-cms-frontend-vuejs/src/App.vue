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
          <li v-else-if="validToken" class="nav-item">
            <a class="nav-link" href="#" @click.prevent="logoutButton">Logout</a>
          </li>
        </ul>
      </div>
    </nav>
    <br />
    <div class="container">
      <div class="row justify-content-center">
        <RouterView @loggedIn="updateToken"/>
      </div>
    </div>
  </div>
</template>

<script>
import { useUsersStore } from './stores/users'
import Cookies from 'js-cookie'
import jwtDecode from 'jwt-decode'
import { isNil, isEmpty } from 'ramda';

export default {
  setup() {
    const usersStore = useUsersStore()
    return {
      usersStore
    }
  },
  data(){
    return{
      token: Cookies.get('token'),
      user_role: jwtDecode(Cookies.get('token')).user_role
    }
  },
  async mounted(){
    await this.loginWithToken();
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
      console.log('user_role 1 ' + this.user_role);
    },
    async loginWithToken() {
      await this.usersStore.loginWithToken()
      this.updateToken()
      if (this.handleExceptions()) {
        this.$router.push({ name: 'home' })
        console.log('login successful')
      } else {
        console.log('login failed')
      }
      console.log('user_role 2 ' + this.user_role);
    },
    updateToken() {
      this.token = Cookies.get('token');
      this.user_role = jwtDecode(this.token).user_role
      console.log('user_role 3 ' + this.user_role);
    },
    handleExceptions() {
      if (Object.keys(this.usersStore.getException).length !== 0) {
        console.log(JSON.stringify(this.usersStore.getException.message))
        return false
      } else {
        //TODO handle exceptions
        return true
      }
    }
  },
  computed:{
    validToken(){
      return this.user_role == 'admin' || this.user_role == 'content_creator';
    }
  },
  watch: {
    token(newToken){
      this.token = newToken;
      this.user_role = jwtDecode(this.token).user_role;
      console.log('user_role 4 ' + this.user_role);
    }
  }
}
</script>

<style></style>
