import { createRouter, createWebHistory } from 'vue-router'
import Accueil from '@/views/accueil.vue'
import Connexion from '@/views/connexion.vue'
import Questions from '@/views/questions.vue'
import Apropos from '@/views/a_propos.vue'
import Contacter from '@/views/contacter.vue'
import Inscription from '@/views/inscription.vue'
import AppelDon from '@/views/appelDon.vue'
import Temoignage from '@/views/temoignages.vue'
import partagerTemoignage from '@/views/partagerTemoignage.vue'
import partagerAppel from '@/views/partagerAppel.vue'
import faireDonSang from '@/views/faireUnDonSang.vue'
import faireDonOrgane from '@/views/faireUnDonOrgane.vue'
import faireDonFoie from '@/views/faireUnDonFoie.vue'

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
    },
    {
        path: '/questions',
        name: 'Questions',
        component: Questions
    },
    {
        path: '/a_propos',
        name: 'Apropos',
        component: Apropos
    },
    {
        path: '/contacter',
        name: 'Contacter',
        component: Contacter
    },
    {
        path: '/inscription',
        name: 'Inscription',
        component: Inscription
    },
    {
      path: '/appelDon',
      name: 'AppelDon',
      component: AppelDon
    },
    {
        path: '/temoignage',
        name: 'Temoignage',
        component: Temoignage
    },
    {
        path: '/partagerTemoignage',
        name: 'PartagerTemoignage',
        component: partagerTemoignage
    },
    {
        path: '/partagerAppel',
        name: 'PartagerAppel',
        component: partagerAppel
    },
    {
        path: '/faireDonSang',
        name: 'FaireDonSang',
        component: faireDonSang
    },
    {
        path: '/faireDonOrgane',
        name: 'FaireDonOrgane',
        component: faireDonOrgane
    },
    {
        path: '/faireDonFoi',
        name: 'FaireDonFoi',
        component: faireDonFoie
    }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,

    scrollBehavior(to, from, savedPosition){
        return { top: 0}
    }
})

export default router