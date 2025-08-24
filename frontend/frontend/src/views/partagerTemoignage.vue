<template>
  <div class="temo-container">
    <div class="carte-form">
      <div class="header-form">
        <h3>Nouveau Témoignage</h3>
      </div>

      <div class="body-form">
        <div class="user-info">
          <img src="@/assets/utilisateur.png" alt="user icon" class="icon-user" />
          <span class="user-name">{{ prenom }} {{nom}}</span>
        </div>
        <textarea class="textarea" placeholder="Dite quelque chose..." v-model="contenu"></textarea>
      </div>

      <div class="footer-form">
        <button class="btn-partager" @click="envoyerTemoignage">Partager</button>
      </div>
    </div>

    <Popup v-if="afficherPopup" :titre="popupTitre" :message="popupMessage" :illustration="illustrationPopup" @fermer="fermerPopup"/>
  </div>
</template>

<style scoped>

.temo-container {
  background-color: #FFE5EA ;
  min-height: 100vh ;
  padding: 100px 40px 40px ;
  display: flex ;
  justify-content: center ;
  align-items: center;
}

.carte-form {
  background-color: white ;
  border-radius: 12px ;
  box-shadow : 0 0 4px rgba(0,0,0,0.1);
  width: 100% ;
  max-width: 800px ;
  padding: 20px ;
  display: flex ;
  flex-direction: column;
}

.header-form {
  position : relative;
  text-align: center;
  margin-bottom: 20px ;
}

.header-form h3 {
  font-family: 'Quicksand', sans-serif ;
  color: #103056;
  margin: 0 ;
  font-size: 24px ;
}

.body-form {
  display: flex ;
  flex-direction: column ;
  gap: 12px ;
  margin-bottom: 20px ;
}

.user-info {
  display: flex ;
  align-items: center;
  gap: 10px ;
  color: #103056;
  font-weight: bold ;
}

.icon-user {
  width: 20px ;
  height: 20px ;
}

.user-name {
  font-size: 14px ;
}

.textarea {
  border: none ;
  background-color: #F7F7F7;
  border-radius: 8px ;
  padding: 12px ;
  min-height: 100px ;
  resize: none ;
  font-family: 'Quicksand', sans-serif;
  font-size: 14px ;
}

.textarea::placeholder {
  color: #a1a1a1 ;
}

.footer-form {
  display: flex ;
  justify-content: flex-end;
}

.btn-partager {
  background-color: #FA6E89;
  color: white ;
  border: none ;
  padding: 10px 24px ;
  border-radius: 10px ;
  font-weight: bold ;
  cursor: pointer ;
  font-family: 'Quicksand', sans-serif;
}

.btn-partager:hover {
  background-color: #103056;
}

@media screen and (max-width: 768px){
  .temo-container {
    padding: 120px 20px 0 20px ;
    align-items: flex-start ;
  }

  .carte-form {
    padding: 16px ;
  }

  .header-form h3 {
    font-size: 20px ;
  }

  .user-info {
    flex-direction: column ;
    align-items: flex-start;
    gap: 6px ;
  }

  .user-name {
    font-size: 13px ;
  }

  .textarea {
    font-size: 13px ;
    padding: 10px ;
  }

  .btn-partager {
    padding: 8px 20px ;
    font-size: 14px ;
  }
}
</style>

<script setup>

import {ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import Popup from '@/components/popupSucces.vue'
import illustrationConnexion from '@/assets/celebration.png'
import illustrationRequired from '@/assets/required.png'

const illustrationPopup = ref(illustrationRequired)

const router = useRouter()

const afficherPopup = ref(false)
const popupTitre = ref('')
const popupMessage = ref('')
const rediriger = ref(false)

const estConnecte = ref (false)
const contenu = ref('')
const nom = ref('')
const prenom = ref('')

onMounted(() => {
  const idUser = sessionStorage.getItem('idUser')
  if(!idUser) {
    afficherPopup.value = true
    popupTitre.value= 'Connexion requise'
    popupMessage.value = 'Vous devez être connecté pour publier un témoignage'
    illustrationPopup.value = illustrationRequired
    rediriger.value = true
  } else {
    estConnecte.value = true
    nom.value = sessionStorage.getItem('nom') || ''
    prenom.value = sessionStorage.getItem('prenom') || ''
  }
})

const envoyerTemoignage = async () => {
  const idUser = sessionStorage.getItem('idUser')

  if (!idUser || !contenu.value.trim()){
    return
  }

  const body = {
    idUser: idUser,
    typePost: 'Temoignage',
    typeDon: 'Sang',
    groupSang: 'A',
    rh: 'positif',
    urgence: 'Normal',
    contenu: contenu.value,
    wilaya: 'Tlemcen'
  }

  try {
    await axios.post('http://localhost:8080/api/posts/ajout' , body)

    popupTitre.value = 'Témoignage partagé avec succès'
    popupMessage.value = 'Merci pour votre contribution.'
    illustrationPopup.value = illustrationConnexion
    afficherPopup.value = true
    rediriger.value = true
    contenu.value = ''
  } catch (error) {
    console.error(error)
    alert("Erreur lors de l'envoi du témoignage.")
  }
}

const fermerPopup = () => {
  afficherPopup.value = false

  if(rediriger.value) {
    router.push('/')
  }
}
</script>