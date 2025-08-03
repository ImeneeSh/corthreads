import { createRouter, createWebHistory } from 'vue-router'
import Accueil from '@/views/accueil.vue'

const routes = [
    {
        path: '/',
        name: 'Accueil',
        component: Accueil
    },
    //les autres ici
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

export default router