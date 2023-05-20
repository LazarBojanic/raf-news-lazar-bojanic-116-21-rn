<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-header">Register</div>
          <div class="card-body">
            <div>
              <form @submit.prevent="registerForm">
                <div class="form-group">
                  <label>First Name</label>
                  <input type="text" v-model="first_name" class="form-control" required />
                </div>
                <div class="form-group">
                  <label>Last Name</label>
                  <input type="text" v-model="last_name" class="form-control" required />
                </div>
                <div class="form-group">
                  <label>Username</label>
                  <input type="text" v-model="username" class="form-control" required />
                </div>
                <div class="form-group">
                  <label>Email</label>
                  <input type="text" v-model="email" class="form-control" required />
                </div>
                <div class="form-group">
                  <label>Password</label>
                  <input type="password" v-model="pass" class="form-control" required />
                </div>
                <br />
                <button type="submit" class="btn btn-primary">Register</button>
              </form>
            </div>
          </div>
          <div class="card-footer text-center">
            Already have an account? <router-link to="/login">Login</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import jwtDecode from 'jwt-decode'
import Cookies from 'js-cookie'
import { useUsersStore } from '../stores/users'
export default {
  name: 'RegisterComponent',
  setup() {
    const usersStore = useUsersStore()
    return {
      usersStore
    }
  },
  data() {
    return {
      first_name: '',
      last_name: '',
      username: '',
      email: '',
      pass: ''
    }
  },
  mounted() {},
  methods: {
    async registerForm() {
      const registerData = {
        username: this.username,
        email: this.email,
        pass: this.pass,
        first_name: this.first_name,
        last_name: this.last_name
      }
      console.log(registerData)
      await this.usersStore.register(registerData)
      if (this.handleExceptions()) {
        this.$router.push('login')
        console.log('registration successful')
      } else {
        console.log('registration failed')
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
    }
  },

  computed: {}
}
</script>

<style></style>
