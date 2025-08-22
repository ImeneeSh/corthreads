<template>
  <div class="appel-container">
    <div class="filtres">
      <label>Filtrer par :</label>
      <div class="select-wrapper">
        <select>
          <option value="">Wilaya</option>
        </select>
        <img src="@/assets/fleche-vers-le-bas-pour-naviguer.png" class="select-icon" alt="icone déroulante" />
      </div>
      <div class="select-wrapper">
        <select>
          <option value="">Urgence</option>
        </select>
        <img src="@/assets/fleche-vers-le-bas-pour-naviguer.png" class="select-icon" alt="icone déroulante" />
      </div>
      <button class="btn-appliquer">Appliquer</button>
    </div>

    <div class="carte-appel">
      <div class="carte" v-for="(post, index) in postsAffiches" :key="index" data-aos="fade-up">
        <div class="carte-header">
          <img src="@/assets/utilisateur.png" alt="user icon" class="icon-user" />
          <div class="header-info">
            <h3>{{ post.utilisateur.prenom }} {{ post.utilisateur.nom }}</h3>
          </div>

          <p class="urgence" :class="urgenceClass(post.urgence)">
            Niveau d'urgence : <span>{{ post.urgence }}</span>
          </p>
        </div>

        <p class="message">{{ post.contenu }}</p>

        <p class="plus">Plus de détails :</p>
        <div class="infos">
          <p><strong>Type de don :</strong> {{ post.typeDon }}</p>
          <p><strong>Groupe sanguin :</strong> {{ post.groupSang }}</p>
          <p><strong>Rhesus :</strong> {{ post.rh }}</p>
          <p><strong>Wilaya :</strong> {{ post.wilaya }}</p>
          <p><strong>Date :</strong> {{ post.datePublication }}</p>
        </div>

        <button class="btn-don">
          <img src="@/assets/faire-un-don.png" class="icon-don" alt="don icon">
          Faire un don
        </button>
      </div>
    </div>

    <button class="voir-plus" @click="voirPlus" v-if="visiblePosts <= posts.length">
      Voir plus
    <img src="@/assets/fleche-droite.png" class="icon-fleche" alt="flèche" >
    </button>
  </div>
</template>

<style scoped>

.appel-container {
  background-color: #D4DEFF;
  min-height: 100vh ;
  padding: 120px 40px 40px;
}

.filtres {
  display : flex ;
  align-items: center ;
  gap: 15px ;
  margin-bottom: 30px ;
  justify-content: flex-end;
}

.filtres select, .btn-appliquer {
  padding : 8px 25px ;
  border-radius: 6px ;
  color: #103056;
  font-weight: 500;
  border: none ;
  background-color: white;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
  outline: none ;
}

.select-wrapper {
  position : relative ;
  display: inline-block;
}

.select-wrapper select {
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  background-color: white ;
  padding: 8px 40px 8px 25px ;
  border-radius: 6px;
  border: none ;
  color: #103056;
  font-weight: 500;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
  cursor: pointer ;
}

.select-icon {
  position: absolute;
  right: 16px ;
  top: 50% ;
  transform: translateY(-50%);
  width: 10px ;
  height: 10px ;
  pointer-events: none;
}

.btn-appliquer {
  background-color: #FA6E89;
  color: white ;
  font-weight: bold;
  cursor: pointer ;
}

.plus {
  font-weight: bold ;
}

.carte-appel {
  display : flex ;
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
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px ;
  margin-bottom: 10px ;
  flex-wrap: wrap ;
  width: 100% ;
}

.header-info {
  flex-grow: 1 ;
  display: flex ;
  flex-direction: column;
  justify-content: center;
}

.header-info h3 {
  margin : 0 ;
  color: #103056;
  font-size: 16px ;
  font-weight: bold;
  text-align: left ;
}

.icon-user {
  width: 30px ;
  height: 30px ;
}

.urgence {
  font-size: 14px ;
  font-weight: bold;
  color: #103056;
}

.urgence span {
  font-weight: bold;
}

.urgent span {
  color: #FF4242; ;
}

.normal span {
  color: #05DF72;
}

.message {
  margin:10px 0 ;
  font-weight: 500;
}

.infos {
  display: flex ;
  flex-wrap: wrap;
  gap: 25px ;
  margin-top: 10px ;
}

.infos p {
  margin: 4px 0 ;
  font-weight: bold;
  color: #FA6E89;
}

.infos strong {
  color: #103056;
  margin-right: 5px ;
}

.btn-don {
  margin-top: 20px;
  background-color: #103056;
  color: white;
  border: none ;
  padding: 10px 20px ;
  border-radius: 20px;
  cursor: pointer ;
  font-weight: bold;
  display: flex;
  align-items: center;
  margin-left: auto;
  gap: 8px;
  transition: background-color 0.3s ease;
}

.btn-don:hover {
  background-color: #FA6E89;
}

.icon-don {
  width: 16px ;
  height: 16px ;
}

.voir-plus {
  margin-top: 40px ;
  background-color: #FA6E89;
  color: white ;
  border: none ;
  padding : 10px 40px ;
  border-radius: 24px;
  font-weight: bold;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background-color 0.3s ease;
  margin-left: auto;
}

.voir-plus:hover {
  background-color: #103056;
}

.icon-fleche {
  height: 16px;
  width: 16px;
  vertical-align: middle;
  margin-left:6px ;
  transform: translateY(1px);
}

@media screen and (max-width: 768px) {
  .appel-container {
    padding: 100px 20px 40px ;
  }

  .filtres {
    flex-direction : column;
    align-items: flex-start;
    gap: 12px ;
  }

  .select-wrapper select{
    width: 100% ;
  }

  .select-icon {
    right: 16px ;
    width: 16px ;
    height: 12px
  }

  .select-wrapper, .btn-appliquer {
    width: 100% ;
  }

  .carte {
    padding: 16px ;
  }

  .carte-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 8px ;
  }

  .btn-don {
    width: fit-content;
    margin-left: auto;
    margin-right: 0;
  }

  .voir-plus {
    width: fit-content;
  }

  .message {
    font-size: 14px ;
  }

  .infos {
    gap: 15px ;
  }

  .infos p {
    font-size: 13px ;
  }

  .header-info h3 {
    font-size: 15px ;
  }

  .urgence {
    font-size: 13px ;
  }
}
</style>
<script setup lang="ts">

import { ref, onMounted, computed } from 'vue'
import axios from 'axios'

const posts = ref([])
const visiblePosts = ref(4)

const loading = ref(true)
const error = ref(null)

onMounted( async () => {
  try {
    const reponse = await axios.get('http://localhost:8080/api/posts/recherche?typePost=Appel_don')
    posts.value = reponse.data
  }catch (err) {
    console.error('Erreur lors de la récupération des données',err)
    error.value = "impossible de charger les appels à dons."
  }finally {
    loading.value = false
  }
})

const postsAffiches = computed(() => {
  return posts.value.slice(0, visiblePosts.value)
})

function voirPlus() {
  visiblePosts.value += 4
}

function urgenceClass(niveau : string) {
  return {
    urgent: niveau.toLowerCase() === 'urgent',
    normal: niveau.toLowerCase() === 'normal'
  }
}
</script>