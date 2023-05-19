<template>
    <div class="container">
      <div class="row justify-content-center">
        <p>{{ comment.body }}</p>
      </div>
    </div>
  </template>
  <script>
  export default {
    name: 'CommentComponent',
    mounted() {},
    data() {
      return {}
    },
    props: {
      comment: Object
    },
    computed: {},
    methods: {}
  }
  </script>
  <style></style>
  