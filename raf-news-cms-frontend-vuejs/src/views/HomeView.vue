<template>
  <div class="container d-flex justify-content-center align-items-center">
    <div class="col-md-8 text-center">
      <!-- Added 'text-center' class -->
      <h1 class="mt-4 mb-4">Home</h1>
      <div>
        <CategoriesComponent />
      </div>
      <button class="btn btn-primary" @click="getFile">Get File</button>
    </div>
  </div>
</template>

<script>
import Cookies from 'js-cookie'
import CategoriesComponent from '../components/CategoriesComponent.vue'
export default {
  name: 'HomeView',
  components: {
    CategoriesComponent
  },
  mounted() {
    // Add any logic here if needed
  },
  data() {
    return {}
  },
  props: {},
  computed: {},
  methods: {
    async getFile() {
        try {
          const token = Cookies.get('token')
            const response = await fetch('http://95.180.97.206:8081/api/article/getFile', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/octet-stream',
                    'Authorization': `Bearer ${token}`
                },
                responseType: 'blob'
            });

            if (!response.ok) {
                throw new Error('File download failed.');
            }

            const blob = await response.blob();

            const url = URL.createObjectURL(blob);
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', 'testFile.txt');
            link.style.display = 'none';
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
            URL.revokeObjectURL(url);
        } catch (error) {
            console.error(error);
        }
    }

  }
}
</script>

<style></style>
