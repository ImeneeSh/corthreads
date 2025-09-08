<template>
  <header class="header">

    <div class="section-gauche">
      <img src="@/assets/logo-removebg-preview.png" alt="Logo" class="logo"/>
      <div class="menu-icon" @click="gererMenu">
        <img src="@/assets/burger-bar.png" alt="Menu" />
      </div>
    </div>

    <nav class="nav-links">
      <router-link to="/" class="nav-item">{{ t('accueil') }}</router-link>
      <router-link to="/appelDon">{{ t('appelsADons') }}</router-link>
      <router-link to="/temoignage">{{ t('temoignages') }}</router-link>
    </nav>

    <div class="section-droite">

      <div class="lang-select">
        <button class="icon-language" @click.stop="gerermenulang">
          <img src="@/assets/global.png" alt="Langues" />
        </button>

        <div v-if="langVisible" class="lang-dropdown">
          <div class="langue-option" :class="{ active: selectedLang === 'fr'}" @click.stop="changerLangue('fr')">
            <img src="@/assets/france.png" alt="Français" />
            <span>{{ t('francais') }}</span>
          </div>

          <div class="langue-option" :class="{ active: selectedLang === 'en'}" @click.stop="changerLangue('en')">
            <img src="@/assets/royaume-uni.png" alt="Anglais" />
            <span>{{ t('anglais') }}</span>
          </div>

        </div>
      </div>

      <div class="section-user" v-if="estConnecter">
        <button class="user-icon-btn" @click="gererMenuUtil">
          <img src="@/assets/utilisateur.png" alt="utilisateur" />
        </button>

        <div v-if="menuUtil" class="user-dropdown">
          <div class="user-option" @click="router.push('/')"> <!-- après push dans la page des paramètres -->
            <img src="@/assets/parametres-cog.png" alt="Paramètres" />
            <span class="parametres-text">{{ t('parametres') }}</span>
          </div>

          <div class="user-option" @click="deconnexion">
            <img src="@/assets/deconnexion.png" alt="Déconnexion" />
            <span class="deconnexion-text">{{ t('deconnexion') }}</span>
          </div>
        </div>
      </div>
      <router-link v-if="!estConnecter" to="/connexion" class="seConnecter-button">{{ t('seConnecter') }}</router-link>
    </div>
  </header>

  <div v-if="menuActive" class="overlay" @click="fermerMenu"></div>

  <!--Menu Vertical -->
  <div v-if="menuActive" class="menu-ver">
    <img src="@/assets/logo-removebg-preview.png" alt="Logo" class="logo"/>

    <div class="menu-button mobile-only">
      <router-link v-if="!estConnecter" to="/connexion" class="seConnecter-button menu-version">Se Connecter</router-link>
    </div>
    <ul class="menu-links" v-if="!estConnecter || role === 'Citoyen'">
      <li><router-link to="/" class="menu-link" :class="{ active: route.path === '/' }">Accueil</router-link></li>
      <li class="mobile-only"><router-link to="/temoignage" class="menu-link" :class="{ active: route.path === '/temoignages' }">Témoignages</router-link></li>
      <li class="mobile-only"><router-link to="/appelDon" class="menu-link" :class="{ active: route.path === '/appels-a-dons' }">Appels à dons</router-link></li>
      <li><router-link to="/faireDonSang" class="menu-link" :class="{ active: route.path === '/faire-un-don' }">Faire un don</router-link></li>
      <li><router-link to="/partagerAppel" class="menu-link" :class="{ active: route.path === '/appel-a-dons' }">Faire un appel à dons</router-link></li>
      <li><router-link to="/partagerTemoignage" class="menu-link" :class="{ active: route.path === '/partager-temoignage' }">Partager un témoignage</router-link></li>
    </ul>


    <ul class="menu-links" v-if="role === 'Medecin'">
      <li><router-link to="/" class="menu-link" :class="{ active: route.path === '/' }">Accueil</router-link></li>
      <li><router-link to="/listeCandidatsDons" class="menu-link" :class="{ active: route.path === '/candidats-au-don' }">Liste des candidats au don</router-link></li>
      <li><router-link to="/listeDonneursPotentielSang" class="menu-link" :class="{ active: route.path === '/donneurs-potentiels' }">Listes des donneurs potentiels</router-link></li>
      <li><router-link to="/ListeDonneursValidesSang" class="menu-link" :class="{ active: route.path === '/donneurs-valides' }">Listes des donneurs validés</router-link></li>
    </ul>


  </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

* {
  font-family: 'Poppins', sans-serif;
}

.header {
  position : fixed ;
  top: 0;
  left: 0;
  width: 100% ;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 32px;
  background-color: white;
  border-bottom: 1px solid #D0D0D0;
  z-index: 10;
}

.section-gauche {
  display: flex;
  align-items: center;
  gap: 16px;
}

.logo {
  height: 70px;
}

.menu-icon {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.menu-icon img {
  height: 24px;
  width: 24px;
  cursor:pointer;
}

.nav-links {
  display: flex;
  gap: 80px;
}

.nav-links a {
  text-decoration: none;
  color: #103056;
  font-weight: 700;
  display: flex;
  align-items: center;
  gap: 16px;
}

.icon-language {
  background: none;
  border: none;
  cursor: pointer;
  margin-right: 20px ;
}

.section-droite {
  display: flex ;
  align-items: center;
  gap: 12px ;
}

.icon-language img {
  height: 22px;
  width: 22px;
  vertical-align: middle;
}

.seConnecter-button {
  padding : 8px 16px ;
  border: 2px solid #103056;
  border-radius: 24px;
  background-color: transparent;
  color: #103056;
  font-weight: 700;
  font-size: 16px;
  cursor: pointer;
  transition: all 0.3s ease;
  text-decoration: none;
}

.seConnecter-button:hover {
  background-color: #103056;
  color: white;
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  backdrop-filter: blur(1px);
  background-color: rgba(0, 0, 0, 0.2);
  z-index: 20;
}

  /* Menu verticale */
  .menu-ver {
    position: fixed;
    top : 0 ;
    left : 0 ;
    width: 260px ;
    height: 100vh;
    background-color: white;
    z-index : 30;
    padding : 24px 20px ;
    display: flex ;
    flex-direction: column;
    gap: 32px ;
    box-shadow: 2px 0 8px rgba(0,0,0,0.1);
  }

  .menu-ver .logo {
    height: 70px;
    object-fit: contain;
  }

  .menu-button {
    display: flex ;
    justify-content: center;
    align-items: center;
    margin-top: 10px;
    margin-bottom: 10px;
  }

  .menu-links {
    list-style: none;
    padding: 0;
    margin: 0;
    display: flex;
    flex-direction: column;
    gap: 16px;
  }

  .menu-link{
    text-decoration: none;
    font-weight: 600;
    color: #103056;
    font-size: 16px;
    padding: 8px 12px;
    border-radius: 8px;
    display: block;
    transition: all 0.2s ease;
  }

  .menu-link:hover {
    background-color: #FA6E89;
    color: white ;
  }

  .menu-links .active {
    background-color: #FA6E89;
    color : white;
    font-weight: bold;
    border-radius: 8px;
  }

  .mobile-only {
    display : none ;
  }

  .lang-select {
    position: relative;
    display: inline-block;
  }

  .langue-option {
    display: flex;
    width: 100% ;
    align-items: center;
    justify-content: center;
    gap: 10px ;
    padding: 12px 16px;
    cursor: pointer;
    transition: background-color 0.2s ease;
    border-radius: 8px ;
  }

  .langue-option:hover {
    background-color: #FA6E89;
    color: white;
    border-radius: 12px ;
  }

  .langue-option:hover span{
  color: white;
  }

  .langue-option img {
    width: 20px;
    height: 20px;
  }

  .langue-option span {
    color : #103056;
  }

  .langue-option.active {
    background-color: #FA6E89;
    border-radius: 12px ;
  }

  .langue-option.active span {
    color: white;
  }

  .lang-dropdown {
    display: flex;
    flex-direction: column;
    gap: 10px ;
    align-items: stretch;
    justify-content: center;
    padding: 12px ;
    border-radius: 12px ;
    background-color: white;
    box-shadow: 2px 0 10px rgba(0,0,0,0.1);
    position: absolute;
    top : 40px ;
    right :0 ;
    width: 190px;
    z-index: 50;
  }

  .section-user {
    position: relative;
  }

  .user-icon-btn {
    background : none;
    border: none ;
    cursor: pointer;
  }

  .user-icon-btn img {
    width: 30px ;
    height: 30px ;
  }

  .user-dropdown {
    display: flex ;
    flex-direction: column;
    gap: 10px ;
    align-items: center;
    padding: 12px ;
    border-radius: 12px ;
    background-color: white;
    box-shadow: 2px 0 10px rgba(0,0,0,0.1);
    position: absolute;
    top: 40px ;
    right :0 ;
    width: 190px;
    z-index: 50;
  }

  .user-option {
    display: flex;
    width: 100% ;
    align-items: center;
    justify-content: center;
    gap: 10px ;
    padding: 12px 16px;
    cursor: pointer;
    transition: background-color 0.2s ease;
    border-radius: 8px ;
  }

  .user-option:hover {
    background-color: #FA6E89;
    color: white;
    border-radius: 12px ;
  }

  .user-option:hover span {
    color: white;
  }

  .user-option img {
    width: 20px ;
    height: 20px ;
  }

  .parametres-text {
    color: #103056;
  }

  .deconnexion-text {
    color: #FF4242 ;
  }

  @media (max-width: 768px){

    .mobile-only {
      display: block !important;
    }
    .nav-links ,
    .seConnecter-button {
      display: none;
    }

    .menu-ver .menu-links {
      display: flex ;
      flex-direction: column;
      gap: 16px;
    }

    .seConnecter-button.menu-version {
      width: 100%;
      margin-top: 16px;
      display: block !important;
    }

    .user-icon-btn img {
      width: 25px ;
      height: 25px ;
    }
  }
</style>
<script setup>

import {onBeforeMount, onMounted, ref} from 'vue' ;
import { useRouter , useRoute } from 'vue-router'
import {useI18n} from "vue-i18n";

const {t, locale} = useI18n();

const menuActive = ref(false);
const langVisible = ref(false);
const selectedLang = ref('fr');

const estConnecter= ref(false);
const role = ref('');
const menuUtil = ref(false);
const router = useRouter()

const route = useRoute() //pour détécter la route actuel

function gererMenu(){
  menuActive.value = !menuActive.value ;
}

function fermerMenu() {
  menuActive.value = false ;
}


function gerermenulang(){
  langVisible.value = ! langVisible.value ;
}

function gererClick(e) {
  const langMenu = document.querySelector('.lang-select');
  const userMenu = document.querySelector('.section-user');

  if(langMenu && !langMenu.contains(e.target)) {
    langVisible.value = false;
  }

  if(userMenu && !userMenu.contains(e.target)) {
    menuUtil.value = false ;
  }
}

onMounted(() => {
  const lang = localStorage.getItem('langue');

  if(lang){
    locale.value = lang
    selectedLang.value = lang
  }
  window.addEventListener('click',gererClick);
  const storedRole = sessionStorage.getItem('role')

  if(storedRole){
    estConnecter.value= true
    role.value = storedRole
  }
});

onBeforeMount(() => {
  window.removeEventListener('click',gererClick);
});

function changerLangue(langue) {
  selectedLang.value = langue;
  locale.value = langue
  langVisible.value = false;
  localStorage.setItem('langue', langue)
}

function deconnexion(){
  sessionStorage.clear()
  estConnecter.value = false ;
  router.push('/')
}

function gererMenuUtil(){
  menuUtil.value= !menuUtil.value ;
}
</script>