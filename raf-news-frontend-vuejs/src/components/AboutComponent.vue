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
      
    }
  }
}
</script>

<style>

</style>
