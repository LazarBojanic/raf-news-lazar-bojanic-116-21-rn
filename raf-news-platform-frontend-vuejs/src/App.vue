<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" to="/home">Home</router-link>
          </li>
        </ul>
      </div>
    </nav>
    <br />
    <div class="container d-flex justify-content-center align-items-center">
      <div class="col-md-8">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import { useUsersStore } from './stores/users'
export default {
  setup() {
    const usersStore = useUsersStore()
    return {
      usersStore
    }
  },
  mounted() {
    this.usersStore.logout()
  },
  methods: {}
}
</script>

<style></style>
