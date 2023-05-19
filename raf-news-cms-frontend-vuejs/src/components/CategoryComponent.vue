<template>
  <div class="container">
    <div class="row justify-content-center">
      <h2>{{ category.category_name }}</h2>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CategoryComponent',
  mounted() {},
  data() {
    return {}
  },
  props: {
    category: Object
  },
  computed: {},
  methods: {}
}
</script>

<style></style>
