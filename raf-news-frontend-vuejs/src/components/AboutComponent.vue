<template>
  <div class="row justify-content-center">
    <h1>About</h1>
    <button class="btn btn-primary" @click.prevent="testButton">Test</button>
  </div>
</template>

<script>
export default {
  name: 'AboutComponent',
  mounted() {},
  data() {
    return {}
  },
  props: {},
  computed: {},
  methods: {
    async testButton() {
      try {
        const res = await fetch('http://95.180.97.206:8081/api/hello-world')
        const data = await res.json()
        if (data) {
          console.log(data)
        }
      } catch (error) {
        console.log(error)
      }
    }
  }
}
</script>

<style>

</style>
