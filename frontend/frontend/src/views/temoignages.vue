<template>
  <div class="temo-container">
    <div class="titre">
      <h2>Sauvés grâce à vos dons, ils témoignent !</h2>
    </div>

    <div v-if="loading" class="loader-container">
      <div class="loader"></div>
    </div>

    <div class="carte-temo">
      <div class="carte" v-for="(post, index) in postsAffiches" :key="index" data-aos="fade-up">
        <div class="carte-header">
          <img src="@/assets/utilisateur.png" alt="user icon" class="icon-user" />
          <div class="header-info">
            <h3>{{ post.utilisateur.prenom }} {{ post.utilisateur.nom }}</h3>
          </div>
        </div>

        <p class="message">{{ post.contenu }}</p>

        <div class="info">
          <p><strong>Date :</strong>{{ post.datePublication }}</p>
        </div>
      </div>
    </div>

    <button class="voir-plus" @click="voirPlus" v-if="visiblePosts <= posts.length">
      Voir Plus
      <img src="@/assets/fleche-droite.png" class="icon-fleche" alt="flèche" >
    </button>
  </div>
</template>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Quicksand:wght@400;500;700&display=swap');

.temo-container {
  background-color: #FFE5EA ;
  min-height: 100vh ;
  padding: 120px 40px 40px ;
}

.titre {
  font-weight: bold;
  margin-bottom: 20px ;
  color: #103056;
  text-align: center;
}

.titre h2 {
  font-size: 30px ;
  font-family: 'Quicksand', sans-serif;
  margin-bottom: 40px ;
}

.loader-container {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 50px 0 ;
}

.loader {
  border: 6px solid #F3F3F3;
  border-top: 6px solid #FA6E89;
  border-radius: 50%;
  width: 40px ;
  height: 40px ;
  animation: spin 2s linear infinite ;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.carte-temo {
  display: flex ;
  flex-direction: column;
  gap: 24px ;
}

.carte {
  background-color: white ;
  border-radius: 12px ;
  padding: 20px ;
  box-shadow: 0 4px 10px rgba(0,0,0,0.05);
  transition: 0.3s ease ;
}

.carte-header {
  display: flex ;
  align-items: center ;
  justify-content: space-between;
  gap: 12px ;
  margin-bottom : 10px ;
  flex-wrap: wrap ;
  width: 100% ;
}

.header-info {
  flex-grow: 1 ;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.header-info h3 {
  margin: 0 ;
  color: #103056;
  font-size: 16px ;
  font-weight: bold;
  text-align: left ;
  font-family: 'Quicksand', sans-serif;
}

.icon-user {
  width: 30px ;
  height: 30px ;
}

.message {
  margin: 10px 0 ;
  font-weight: 500 ;
}

.info {
  display: flex ;
  flex-wrap: wrap ;
  gap: 25px ;
  margin-top: 10px ;
}

.info p {
  margin: 4px 0 ;
  font-weight: bold ;
  color: #FA6E89;
}

.info strong {
  color: #103056;
  margin-right: 5px ;
}

.voir-plus {
  margin-top: 40px ;
  background-color: #FA6E89;
  color: white ;
  border: none ;
  padding: 10px 40px ;
  border-radius: 24px ;
  font-weight: bold;
  cursor: pointer ;
  display: flex ;
  align-items: center;
  justify-content: center;
  transition: background-color 0.3s ease;
  margin-left: auto ;
}

.voir-plus:hover {
  background-color: #103056;
}

.icon-fleche {
  height: 16px ;
  width: 16px ;
  vertical-align: middle;
  margin-left: 6px;
  transform: translateY(1px);
}

@media screen and (max-width: 768px){
  .temo-container {
    padding: 100px 20px 40px ;
  }

  .carte {
    padding: 16px ;
  }

  .carte-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 8px ;
  }

  .voir-plus {
    width: fit-content;
  }

  .message {
    font-size: 14px ;
  }

  .info {
    gap: 15px ;
  }

  .info p {
    font-size: 13px ;
  }

  .header-info h3 {
    font-size: 15px ;
  }
}

</style>

<script setup lang="ts">

import {ref, onMounted, computed } from "vue"
import axios from 'axios'

const posts = ref([])
const visiblePosts = ref (4)

const loading = ref(true)
const error = ref(null)

onMounted (async () => {
  try {
    const reponse = await axios.get('http://localhost:8080/api/posts/recherche?typePost=Temoignage')
    posts.value = reponse.data
  }catch (err) {
    console.error('Erreur lors de la récupération des données', err)
    error.value = "impossible de charger les appels à dons"
  }finally {
    loading.value = false
  }
})

const postsAffiches = computed (() => {
  return posts.value.slice(0, visiblePosts.value)
})

function voirPlus() {
  visiblePosts.value += 4
}

</script>