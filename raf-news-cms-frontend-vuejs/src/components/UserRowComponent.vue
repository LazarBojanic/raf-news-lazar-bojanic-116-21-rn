<template>
  <td
    :class="{ 'pushed-out': isPushedOut, 'regular-scale': isRegularScale, 'pushed-in': isPushedIn }"
    @mouseenter="pushOut"
    @mouseup="resetScale"
    @mouseleave="resetScale"
    @mousedown="pushIn"
  >
    {{ service_user.username }}
  </td>
  <td
    :class="{ 'pushed-out': isPushedOut, 'regular-scale': isRegularScale, 'pushed-in': isPushedIn }"
    @mouseenter="pushOut"
    @mouseup="resetScale"
    @mouseleave="resetScale"
    @mousedown="pushIn"
  >
    {{ service_user.email }}
  </td>
  <td
    :class="{ 'pushed-out': isPushedOut, 'regular-scale': isRegularScale, 'pushed-in': isPushedIn }"
    @mouseenter="pushOut"
    @mouseup="resetScale"
    @mouseleave="resetScale"
    @mousedown="pushIn"
  >
    {{ service_user.first_name }}
  </td>
  <td
    :class="{ 'pushed-out': isPushedOut, 'regular-scale': isRegularScale, 'pushed-in': isPushedIn }"
    @mouseenter="pushOut"
    @mouseup="resetScale"
    @mouseleave="resetScale"
    @mousedown="pushIn"
  >
    {{ service_user.last_name }}
  </td>
  <td>
    <button
      :disabled="!validToken"
      :class="{
        'btn-primary': service_user.is_enabled === 'true',
        'btn-secondary': service_user.is_enabled === 'false'
      }"
      @click="switchEnabled()"
    >
      {{ service_user.is_enabled === 'true' ? 'Disable' : 'Enable' }}
    </button>
  </td>
  <td><button :disabled="!validToken" class="btn btn-danger" @click="editUser()">Edit</button></td>
  <td>
    <button :disabled="!validToken" class="btn btn-danger" @click="deleteUser()">Delete</button>
  </td>
</template>

<script>
import { ref } from 'vue'
import { useUsersStore } from '../stores/users'
import router from '../router'
import Cookies from 'js-cookie'
import jwtDecode from 'jwt-decode'
import { isNil, isEmpty } from 'ramda'
export default {
  name: 'UsersTableComponent',
  setup() {
    const usersStore = useUsersStore()
    const isPushedOut = ref(false)
    const isRegularScale = ref(true)
    const isPushedIn = ref(false)
    const validToken = ref(false)
    return {
      usersStore,
      isPushedOut,
      isRegularScale,
      isPushedIn,
      validToken
    }
  },
  mounted() {
    this.validateToken()
  },
  data() {
    return {}
  },
  props: {
    service_user: Object
  },
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
    validateToken() {
      const token = Cookies.get('token')
      if (!isNil(token) && !isEmpty(token)) {
        const decodedToken = jwtDecode(token)
        if (
          decodedToken.user_role === 'admin' &&
          this.service_user.user_role === 'content_creator'
        ) {
          this.validToken = true
        } else {
          this.validToken = false
        }
      } else {
        this.validToken = false
      }
    },
    async editUser() {
      this.$router.push({ name: 'editUser', query: { userId: this.service_user.id } })
    },
    async deleteUser() {
      await this.usersStore.deleteUserById(this.service_user.id)
      await this.usersStore.fetchAllUsersFiltered(this.usersStore.getSearchData)
    },
    async switchEnabled() {
      const switchEnabledData = {
        is_enabled: ''
      }
      if (this.service_user.is_enabled === 'true') {
        switchEnabledData.is_enabled = 'false'
        await this.usersStore.switchUserEnabled(this.service_user.id, switchEnabledData)
      } else if (this.service_user.is_enabled === 'false') {
        switchEnabledData.is_enabled = 'true'
        await this.usersStore.switchUserEnabled(this.service_user.id, switchEnabledData)
      }
      await this.usersStore.fetchAllUsersFiltered(this.usersStore.getSearchData)
    }
  }
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
</style>
