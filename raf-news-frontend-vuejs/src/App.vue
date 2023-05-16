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
          <li  v-if="!checkEmail(this.decodedToken.email)" class="nav-item">
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
    <body>
      <RouterView @loggedIn="updateToken"/>
    </body>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import Cookies from 'js-cookie';
import jwtDecode from 'jwt-decode';
export default {
  data() {
    return {
      decodedToken: {}
    }
  },
  mounted() {
    this.decodedToken = jwtDecode(Cookies.get('token'));
  },
  methods: {
    ...mapActions(['login']),
    ...mapActions(['logout']),
    async logoutButton() {
      await this.logout();
      this.decodedToken = jwtDecode(Cookies.get('token'));
      console.log(this.decodedToken.email);
      this.$router.push({ name: 'login' });
    },
    async loginWithToken(){
      this.decodedToken = jwtDecode(Cookies.get('token'));
      const loginData = {
        email: this.token.email,
        pass: this.token.pass,
      }
      await this.login(loginData);
      this.$router.push({ name: 'home' });
    },
    updateToken(){
      this.decodedToken = jwtDecode(Cookies.get('token'));
    },
    checkEmail(email){
      if(email == null || email == ''){
        return false;
      }
      else{
        return true;
      }
    }
  },
  computed: {
  }
}
</script>

<style></style>
