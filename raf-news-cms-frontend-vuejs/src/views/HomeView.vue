<template>
  <div class="container d-flex justify-content-center align-items-center">
    <div class="col-md-8 text-center"> <!-- Added 'text-center' class -->
      <h1 class="mt-4 mb-4">Home</h1>
      <div>
        <CommentsComponent :articleId="3" />
      </div>
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
  mounted() {
    // Add any logic here if needed
  },
  data() {
    return {}
  },
  props: {},
  computed: {},
  methods: {}
}
</script>

<style></style>
