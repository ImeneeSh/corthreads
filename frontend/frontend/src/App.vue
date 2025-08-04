<template>
  <div id="app">
    <Header/>
    <router-view/>
    <Footer/>
  </div>
</template>
<script setup>
import Header from "@/components/header.vue";
import Footer from "@/components/footer.vue"
</script>