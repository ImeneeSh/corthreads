import { createRouter, createWebHistory } from 'vue-router'
import Accueil from '@/views/accueil.vue'
import Connexion from '@/views/connexion.vue'

const routes = [
    {
        path: '/',
        name: 'Accueil',
        component: Accueil
    },
    {
        path: '/connexion',
        name: 'Connexion',
        component: Connexion
    }
    //les autres ici
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

export default router