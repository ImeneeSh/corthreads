<template>
  <div id="app">
    <Header v-if="!pageConnexion && !pageInscription"/>
    <router-view/>
    <Footer v-if="!pageConnexion && !pageInscription"/>
  </div>
</template>
<script setup>
import Header from "@/components/header.vue";
import Footer from "@/components/footer.vue"
import {useRoute} from 'vue-router';
import { computed } from 'vue';

const route=useRoute();

const pageConnexion = computed(() => route.path === '/connexion');
const pageInscription = computed(() => route.path === '/inscription');
</script>