<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <h5>{{ formattedTime }}</h5>
      </div>
      <div class="col-md-6">
        <h4>{{ comment.service_user.username }}</h4>
      </div>
      <div class="col-md-12">
        <p>{{ comment.body }}</p>
      </div>
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
  computed: {
    formattedTime() {
      // Format the timestamp to a human-readable date
      const timestamp = new Date(this.comment.time_published)
      return timestamp.toLocaleString()
    }
  },
  methods: {}
}
</script>

<style></style>
