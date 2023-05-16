<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-header">Register</div>
          <div class="card-body">
            <div>
              <form @submit.prevent="registerForm" novalidate>
                <div class="form-group">
                  <label>First Name</label>
                  <input type="text" v-model="firstName" class="form-control" required />
                </div>
                <div class="form-group">
                  <label>Last Name</label>
                  <input type="text" v-model="lastName" class="form-control" required />
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
import { mapActions } from 'vuex'
export default {
  name: 'RegisterComponent',
  data() {
    return {
      firstName: '',
      lastName: '',
      email: '',
      pass: ''
    }
  },
  methods: {
    ...mapActions(['register']),
    async registerForm() {
      const registerData = {
        id: 0,
        email: this.email,
        pass: this.pass,
        userRole: 'contentCreator',
        enabled: 'true',
        firstName: this.firstName,
        lastName: this.lastName
      }
      const registrationSuccess = await this.register(registerData)
      if (registrationSuccess == true) {
        console.log('registration successful')
      } else {
        console.log('registration successful')
      }
    }
  }
}
</script>

<style scoped>
</style>
