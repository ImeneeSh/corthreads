<template>
  <div class="donneurs-container">
    <div class="type-don">
      <label>Veuillez choisir un type de don :</label>
      <div class="options">
        <label><input type="radio" name="don" value="sanguin" v-model="selectedType" />Sanguin</label>
        <label><input type="radio" name="don" value="foie" v-model="selectedType" />Foie</label>
      </div>
    </div>

    <div v-if="loading" class="loader-container">
      <div class="loader"></div>
    </div>

    <div class="carte-appel">
      <div class="carte" v-for="(post, index) in postsAffiches" :key="index" data-aos="fade-up">
        <div class="carte-header">
          <img src="@/assets/utilisateur.png" alt="user icon" class="icon-user" />
          <div class="header-info">
            <h3>{{ post.utilisateur.prenom }} {{ post.utilisateur.nom }}</h3>
          </div>
        </div>

        <p class="plus">Plus de détails :</p>
        <div class="infos">
          <p><strong>Date de naissance :</strong> {{ afficherValeur(post.utilisateur.dateNaissance) }}</p>
          <p><strong>Genre :</strong> {{ afficherValeur(post.utilisateur.genre) }}</p>
          <p><strong>Groupe Sanguin :</strong> {{ afficherValeur(post.utilisateur.groupeSang) }}</p>
          <p><strong>Rhesus :</strong> {{ afficherValeur(post.utilisateur.rh) }}</p>
          <p><strong>Wilaya :</strong> {{ afficherValeur(post.utilisateur.wilaya) }}</p>
          <p><strong>Est donneur de sang :</strong> {{ afficherValeur(post.utilisateur.donneurSang) }}</p>
          <p><strong>Date du dernier don de sang :</strong> {{ afficherValeur(post.utilisateur.dernierDonSang) }}</p>
        </div>

        <div class="btn-group">

          <button class="btn-don btn-formulaire">
            <img src="@/assets/une-reponse-rapide.png" class="icon-don" alt="don icon">
            Etude du formulaire
          </button>

          <div class="btn-email-wrapper">
            <button class="btn-don btn-contacter" @click.stop="gererEmail(index)">
              <img src="@/assets/lettre.png" class="icon-don" alt="don icon">
              Contacter
            </button>

            <div v-if="emailVisible === index" class="email-box" @click.stop>
              <p class="email-label">Adresse mail de l'utilisateur :</p>
              <div class="email-content">
                <input class="email-input" type="text" :value="post.utilisateur.idUser" readonly />
                <img src="@/assets/interface.png" class="copy-icon" alt="Copier" @click="copierEmail(post.utilisateur.idUser)" />
              </div>
            </div>
          </div>

          <div class="btn-email-wrapper">
            <button class="btn-don btn-supprimer" @click="afficherSupp(index)">
              <img src="@/assets/supprimer%20(1).png" class="icon-don" alt="don icon">
              Supprimer
            </button>

            <div v-if="indexSupp === index" class="boite-confirmation" @click.stop >
              <p class="question-confirmation">Êtes-vous sûr de vouloir supprimer cet utilisateur ?</p>
              <div class="boutons-confirmation">
                <button class="btn-don btn-supprimer" @click="confirmerSupp(index)">Confirmer</button>
                <button class="btn-don btn-annuler" @click="annulerSupp">Annuler</button>
              </div>
            </div>
          </div>

          <button class="btn-don btn-attente">
            <img src="@/assets/horloge-murale.png" class="icon-don" alt="don icon">
            En attente
          </button>
        </div>
      </div>
    </div>

    <button class="voir-plus" @click="voirPlus" v-if="visiblePosts <= posts.length">
      Voir plus
      <img src="@/assets/fleche-droite.png" class="icon-fleche" alt="flèche" >
    </button>

  </div>
</template>

<style scoped>

.donneurs-container {
  background-color: #FFE5EA;
  min-height: 100vh ;
  padding: 120px 40px 40px;
}

.type-don {
  display: flex ;
  align-items: center ;
  justify-content: center;
  gap: 15px ;
  margin-bottom: 30px ;
  font-weight: 600 ;
  flex-wrap: wrap ;
}

.type-don > label {
  margin-bottom: 0 ;
}

.type-don .options {
  display: flex ;
  align-items: center;
  gap: 20px ;
  margin: 0 ;
}

input[type="radio"]:checked,
input[type="checkbox"]:checked {
  appearance: none;
  -webkit-appearance: none ;
  width: 20px ;
  height: 20px ;
  border: 2px solid #FA6E89;
  background-color: white ;
  display: inline-block;
  cursor: pointer;
  position : relative ;
  transition: all 0.2s ease ;
}

input[type="radio"] {
  border-radius: 50%;
}

input[type="checkbox"] {
  border-radius: 4px ;
}

input[type="radio"]:checked::before {
  content: "";
  width: 10px;
  height: 10px;
  background-color: #FA6E89;
  border-radius: 50%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%) scale(0);
  transition: transform 0.2s ease;
}

input[type="checkbox"]::before {
  content: "";
  position: absolute;
  top: 43%;
  left: 50%;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 2px 2px 0;
  transform: translate(-50%, -50%) rotate(45deg) scale(0);
  transition: transform 0.2s ease;
}

input[type="radio"]:checked::before,
input[type="checkbox"]:checked::before {
  transform: translate(-50%, -50%) rotate(45deg) scale(1);
}

input[type="checkbox"]:checked {
  background-color: #FA6E89;
  border-color: #FA6E89;
}

label {
  display: flex;
  align-items: center;
  gap: 8px ;
}

.options {
  color: #103056;
  font-weight: 600;
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


.loader-container {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 50px 0 ;
}

.loader {
  border: 6px solid #F3F3F3 ;
  border-top: 6px solid #FA6E89;
  border-radius: 50% ;
  width: 40px ;
  height: 40px ;
  animation: spin 2s linear infinite;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.carte-appel {
  display : flex ;
  flex-direction: column;
  gap: 24px ;
}

.carte {
  background-color: white ;
  border-radius: 12px ;
  box-shadow: 0 4px 10px rgba(0,0,0,0.05);
  transition: 0.3s ease ;
  position: relative ;
  z-index: 1 ;
  overflow: visible ;
  padding: 20px ;
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
  font-family: 'Quicksand', sans-serif;
}

.icon-user {
  width: 30px ;
  height: 30px ;
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

.btn-group {
  display: flex ;
  justify-content: flex-end;
  gap: 16px ;
  margin-top: 26px ;
}

.btn-don {
  padding: 8px 18px ;
  border-radius: 20px;
  font-weight: bold;
  display: flex;
  align-items: center;
  gap: 8px;
  border: none ;
  font-size: 14px ;
  transition: all 0.3s ease;
  margin-left: 0;
  margin-top: 0;
}

.btn-email-wrapper {
  position: relative;
}

.btn-contacter {
  background-color: #103056;
  color: white;
  cursor: pointer;
}

.btn-formulaire {
  background-color: #FA6E89;
  color: white ;
  cursor: pointer;
}

.email-box {
  position: absolute ;
  top: auto ;
  bottom: 110% ;
  right: 0 ;
  background-color: white;
  border: 1px solid #D0D0D0;
  border-radius: 12px;
  padding: 16px ;
  box-shadow: 0 2px 6px rgba(0,0,0,0.05);
  max-width: 360px ;
  z-index: 100 ;
  animation: fadeIn 0.3s ease-in-out;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-5px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.email-label {
  font-weight: bold;
  margin-bottom: 8px ;
  color: #103056;
  font-size: 14px;
}

.email-content {
  display: flex ;
  align-items: center;
  gap: 8px ;
}

.email-input {
  flex: 1 ;
  padding: 10px 12px ;
  border-radius: 8px ;
  border: 1px solid #D0D0D0;
  font-size: 14px ;
  color: #333 ;
}

.email-input:focus {
  outline: none;
  border-color: #FA6E89;
}

.copy-icon {
  width: 20px ;
  height: 20px ;
  cursor: pointer ;
  transition : opacity 0.3s ease ;
}

.copy-icon:hover {
  opacity: 0.7;
}

.btn-supprimer {
  background-color: #FF4242;
  color: white;
  border: none ;
  cursor: pointer;
}

.boite-confirmation {
  position: absolute ;
  top: auto ;
  bottom: 110% ;
  right: 0 ;
  background-color: white;
  border: 1px solid #D0D0D0;
  border-radius: 12px;
  padding: 16px ;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
  max-width: 320px ;
  z-index: 100 ;
  animation: fadeIn 0.3s ease-in-out;
  text-align: center;
}

.question-confirmation {
  font-weight: bold;
  margin-bottom: 12px ;
  color: #103056;
  font-size: 14px;
}

.boutons-confirmation {
  display: flex ;
  justify-content: space-between;
  gap: 10px ;
}

.btn-annuler {
  background-color: white;
  color: #103056;
  border: 2px solid #ccc ;
  cursor: pointer ;
}

.btn-attente {
  background-color: white;
  color: #FF9900;
  border: 2px solid #FF9900;
}


.icon-don {
  width: 16px ;
  height: 16px ;
}

@media screen and (max-width: 768px) {
  .donneurs-container {
    padding: 100px 20px 30px;
  }

  .type-don {
    flex-direction: column;
    align-items: center;
    gap: 10px;
    text-align: center;
  }

  .type-don .options {
    flex-direction: row;
    justify-content: center;
    gap: 15px;
    flex-wrap: wrap;
  }

  input[type="checkbox"],
  input[type="checkbox"]:checked {
    width: 24px;
    height: 24px;
    min-width: 24px;
    min-height: 24px;
  }

  .voir-plus {
    width: fit-content;
  }

  .infos p {
    font-size: 13px ;
  }

  .header-info h3 {
    font-size: 15px ;
  }

  .carte {
    padding: 16px ;
  }

  .carte-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 8px ;
  }

  .btn-group {
    flex-direction: column;
    align-items: stretch;
    gap: 12px ;
    width: 100% ;
    margin-top: 26px ;
  }

  .btn-don {
    justify-content: center;
    width: 100%;
    padding: 10px 14px ;
    font-size: 14px ;
  }

  .icon-don {
    width: 16px ;
    height: 16px ;
  }
}
</style>
<script setup lang="ts">

import {ref, watch, computed, onMounted} from "vue";
import { useRouter } from 'vue-router';
import axios from "axios";

function afficherValeur(val: any): string {
  if (val === null || val === undefined) {
    return '/';
  } else if (typeof val === 'boolean') {
    return val ? 'vrai' : 'faux' ;
  } else {
    return val ;
  }
}

const router = useRouter();

const error = ref(null) ;

const selectedType = ref('sanguin');
const loading = ref(true) ;
const posts = ref([]);


watch(selectedType, (newValue) => {

  const currentRoute = router.currentRoute.value.path ;
  let targetRoute = '' ;

  if (newValue === 'sanguin') {
    targetRoute = '/listeDonneursPotentielSang';
  }

  else if (newValue === 'foie') {
    targetRoute = ''; //chemin
  }

  if (targetRoute && currentRoute !== targetRoute ){
    router.push(targetRoute);
  } else if (targetRoute && currentRoute === targetRoute ){
    router.replace({ path: '/temp' }).then(() => {
      router.replace(targetRoute);
    });
  }
});

const visiblePosts = ref(4)

const emailVisible = ref(null)

onMounted( async () => {
  try {
    const reponse = await axios.get('http://localhost:8080/api/incrsang')
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

const indexSupp = ref<number | null>(null)

function voirPlus() {
  visiblePosts.value += 4
}

function gererEmail(index: number) {
  emailVisible.value = emailVisible.value === index ? null : index ;
}

function copierEmail(email: string) {
  navigator.clipboard.writeText(email);
}

function afficherSupp(index: number){
  indexSupp.value = index;
}

function annulerSupp(){
  indexSupp.value = null ;
}

function confirmerSupp(index: number) {
  indexSupp.value = null ;
  // logique de suppression
}
</script>

