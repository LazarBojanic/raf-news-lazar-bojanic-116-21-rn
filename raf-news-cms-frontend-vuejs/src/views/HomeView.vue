<template>
  <div class="container">
    <div class="row justify-content-center">
      <h1>Home</h1>
      <CommentsComponent :articleId = "3"/>
    </div>
  </div>
</template>

<script>
import CommentsComponent from '../components/CommentsComponent.vue';
export default {
  name: 'HomeView',
  components: {
    CommentsComponent
},
  mounted() {},
  data() {
    return {}
  },
  props: {},
  computed: {},
  methods: {}
}
</script>

<style></style>
