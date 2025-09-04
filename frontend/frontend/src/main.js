import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import AOS from 'aos'
import './main.css'
import { createI18n} from "vue-i18n"

import fr from './locales/fr.json'
import en from './locales/en.json'

const messages = {
    fr,
    en
}

const i18n = createI18n({
    legacy: false,
    locale: 'fr',
    messages
})

if (import.meta.env.PROD) {
    window.__VUE_DEVTOOLS_GLOBAL_HOOK__ = undefined;
}

createApp(App)
    .use(router)
    .use(i18n)
    .mount('#app')


AOS.init()
AOS.refresh()
