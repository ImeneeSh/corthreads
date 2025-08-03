import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import AOS from 'aos'
import './main.css'

createApp(App)
    .use(router)
    .mount('#app')

AOS.init()
AOS.refresh()
