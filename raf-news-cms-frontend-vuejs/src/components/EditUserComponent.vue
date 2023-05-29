<template>
  <div class="container">
    <div class="row justify-content-center">
      <h1>Edit Category</h1>
      <form @submit.prevent="updateUser">
        <div class="form-group">
          <label for="first_name">First Name:</label>
          <input type="text" class="form-control" id="first_name" v-model="first_name" required />
        </div>
        <div class="form-group">
          <label for="last_name">Last Name:</label>
          <input type="text" class="form-control" id="last_name" v-model="last_name" required />
        </div>
        <div class="form-group">
          <label for="username">Username:</label>
          <input type="text" class="form-control" id="username" v-model="username" required />
        </div>
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="text" class="form-control" id="email" v-model="email" required />
        </div>
        <div class="form-group">
          <label for="user_role">Role:</label>
          <input type="text" class="form-control" id="user_role" v-model="user_role" required />
        </div>
        <div class="form-group">
          <label for="is_enabled">Is Enabled:</label>
          <input type="text" class="form-control" id="is_enabled" v-model="is_enabled" required />
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <button class="btn btn-secondary ml-2" @click="cancelEdit">Cancel</button>
      </form>
    </div>
  </div>
</template>

<script>
import { useUsersStore } from '../stores/users'
import router from '../router'
import { isEmpty, isNil } from 'ramda'

export default {
  name: 'EditUserComponent',
  setup() {
    const usersStore = useUsersStore()
    return {
      usersStore
    }
  },
  data() {
    return {
      id: 0,
      username: '',
      email: '',
      user_role: '',
      is_enabled: '',
      first_name: '',
      last_name: ''
    }
  },
  async mounted() {
    const receivedUserId = this.$route.query.userId
    if (!isNil(receivedUserId)) {
      this.id = receivedUserId
    }
    await this.usersStore.fetchUserById(this.id)
    this.username = this.usersStore.getUser.username
    this.email = this.usersStore.getUser.email
    this.user_role = this.usersStore.getUser.user_role
    this.is_enabled = this.usersStore.getUser.is_enabled
    this.first_name = this.usersStore.getUser.first_name
    this.last_name = this.usersStore.getUser.last_name
  },
  methods: {
    async updateUser() {
      const updateData = {
        id: this.id,
        username: this.username,
        email: this.email,
        user_role: this.user_role,
        is_enabled: this.is_enabled,
        first_name: this.first_name,
        last_name: this.last_name
      }
      await this.usersStore.updateUserById(this.id, updateData)
      router.push('home')
    },
    cancelEdit() {
      router.push('home')
    }
  }
}
</script>

<style></style>
