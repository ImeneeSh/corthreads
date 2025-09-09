import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import AOS from 'aos'
import './main.css'

if (import.meta.env.PROD) {
    window.__VUE_DEVTOOLS_GLOBAL_HOOK__ = undefined;
}

createApp(App)
    .use(router)
    .mount('#app')


AOS.init()
AOS.refresh()
