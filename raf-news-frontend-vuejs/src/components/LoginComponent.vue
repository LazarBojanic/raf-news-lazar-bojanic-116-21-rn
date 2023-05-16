<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-header">Login</div>
          <div class="card-body">
            <form @submit.prevent="loginForm">
              <div class="form-group">
                <label>Email</label>
                <input type="text" v-model="email" class="form-control" required />
              </div>
              <div class="form-group">
                <label>Password</label>
                <input type="password" v-model="pass" class="form-control" required />
              </div>
              <br />
              <button class="btn btn-primary" type="submit">Login</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
import Cookies from 'js-cookie';
export default {
  name: 'LoginComponent',
  data() {
    return {
      email: '',
      pass: ''
    }
  },
  methods: {
    ...mapActions(['login']),
    async loginForm() {
      const loginData = {
        email: this.email,
        pass: this.pass,
      }
      await this.login(loginData);
      this.$emit('loggedIn');
      this.$router.push({ name: 'home' });
    }
  }
}
</script>

<style scoped></style>
