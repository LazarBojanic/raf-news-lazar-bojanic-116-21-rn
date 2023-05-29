<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <table class="table">
          <thead>
            <tr>
              <th>Username</th>
              <th>Email</th>
              <th>First Name</th>
              <th>Last Name</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="service_user in usersStore.getUsers" :key="service_user.id">
              <UserRowComponent :service_user="service_user" />
            </tr>
          </tbody>
        </table>
        <div class="pagination">
          <button
            class="btn btn-primary"
            :disabled="usersSearchData.page === 1"
            @click="previousPage"
          >
            Previous Page
          </button>
          <span class="current-page">Page {{ usersSearchData.page }}</span>
          <button class="btn btn-primary" @click="nextPage">Next Page</button>
        </div>
        <div>
          <button
            class="btn btn-primary mb-2"
            @click="addUserFormIsVisible = !addUserFormIsVisible"
          >
            Add User
          </button>
        </div>
        <div v-if="addUserFormIsVisible">
          <div class="card">
            <div class="card-header">Add User</div>
            <div class="card-body">
              <div>
                <form @submit.prevent="addUser">
                  <div class="form-group">
                    <label>First Name</label>
                    <input type="text" v-model="first_name" class="form-control" required />
                  </div>
                  <div class="form-group">
                    <label>Last Name</label>
                    <input type="text" v-model="last_name" class="form-control" required />
                  </div>
                  <div class="form-group">
                    <label>User Role</label>
                    <input type="text" v-model="user_role" class="form-control" required />
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
                  <div class="form-group">
                    <label>Confirm Password</label>
                    <input type="password" v-model="confirm_pass" class="form-control" required />
                  </div>
                  <br />
                  <button type="submit" class="btn btn-primary">Add User</button>
                  <button
                    class="btn btn-secondary"
                    @click="addUserFormIsVisible = !addUserFormIsVisible"
                  >
                    Cancel
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useUsersStore } from '../stores/users'
import router from '../router'
import UserRowComponent from './UserRowComponent.vue'

export default {
  name: 'UsersTableComponent',
  setup() {
    const usersStore = useUsersStore()
    const isPushedOut = ref(false)
    const isRegularScale = ref(true)
    const isPushedIn = ref(false)
    const addUserFormIsVisible = ref(false)
    return {
      usersStore,
      isPushedOut,
      isRegularScale,
      isPushedIn,
      addUserFormIsVisible
    }
  },
  async mounted() {
    await this.usersStore.fetchAllUsersFiltered(this.usersSearchData)
  },
  data() {
    return {
      usersSearchData: {
        page: 1,
        page_size: 5
      },
      first_name: '',
      last_name: '',
      user_role: '',
      username: '',
      email: '',
      pass: '',
      confirm_pass: ''
    }
  },
  props: {},
  computed: {},
  methods: {
    pushOut() {
      this.isPushedOut = true
      this.isRegularScale = false
      this.isPushedIn = false
    },
    resetScale() {
      this.isPushedOut = false
      this.isRegularScale = true
      this.isPushedIn = false
    },
    pushIn() {
      this.isPushedOut = false
      this.isRegularScale = false
      this.isPushedIn = true
    },
    async addUser() {
      const registerData = {
        username: this.username,
        email: this.email,
        pass: this.pass,
        confirm_pass: this.confirm_pass,
        user_role: this.user_role,
        first_name: this.first_name,
        last_name: this.last_name
      }
      console.log(registerData)
      await this.usersStore.registerFromAdmin(registerData)
      if (this.handleExceptions()) {
        console.log('registration successful')
      } else {
        console.log('registration failed')
      }
    },
    previousPage() {
      if (this.usersSearchData.page > 1) {
        this.usersSearchData.page--
        this.usersStore.fetchAllUsersFiltered(this.usersSearchData)
      }
    },

    nextPage() {
      this.usersSearchData.page++
      this.usersStore.fetchAllUsersFiltered(this.usersSearchData)
    }
  },
  components: { UserRowComponent }
}
</script>

<style scoped>
.pushed-in {
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
  transform: scale(0.95);
}
.regular-scale {
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
  transform: scale(1);
}
.pushed-out {
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
  transform: scale(1.05);
}
.users-table-container {
  flex: 1;
}

.add-user-form-container {
  margin-left: 20px;
}
</style>
