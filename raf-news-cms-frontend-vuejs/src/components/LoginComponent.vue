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
import { useUsersStore } from '../stores/users'
import { isNil, isEmpty } from 'ramda'
export default {
  name: 'LoginComponent',
  setup() {
    const usersStore = useUsersStore()
    return {
      usersStore
    }
  },
  data() {
    return {
      email: '',
      pass: ''
    }
  },
  mounted() {},
  methods: {
    async loginForm() {
      const loginData = {
        email: this.email,
        pass: this.pass
      }
      await this.usersStore.login(loginData)
      if (isEmpty(this.usersStore.getException)) {
        this.$emit('loggedIn')
        this.$router.push({ name: 'home' })
        console.log('login successful')
      } else {
        console.log('login failed')
      }
    }
  },
  computed: {}
}
</script>

<style></style>
