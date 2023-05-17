import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
//import store from './store';
import { createPinia } from 'pinia'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
// Import Bootstrap and BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
const pinia = createPinia()
const app = createApp(App)
app.use(BootstrapVue)
app.use(IconsPlugin)
app.use(router)
app.use(pinia)
//app.use(store)

app.mount('#app')
