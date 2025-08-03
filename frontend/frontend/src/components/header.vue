<template>
  <header class="header">

    <div class="section-gauche">
      <img src="@/assets/logo-removebg-preview.png" alt="Logo" class="logo"/>
      <div class="menu-icon" @click="gererMenu">
        <img src="@/assets/burger-bar.png" alt="Menu" />
      </div>
    </div>

    <nav class="nav-links">
      <router-link to="/" class="nav-item">Accueil</router-link>
      <a href="#">Témoignages</a>
      <a href="#">Appels à dons</a>
    </nav>

    <div class="section-droite">

      <div class="lang-select">
        <button class="icon-language" @click.stop="gerermenulang">
          <img src="@/assets/global.png" alt="Langues" />
        </button>

        <div v-if="langVisible" class="lang-dropdown">
          <div class="langue-option" :class="{ active: selectedLang === 'fr'}" @click.stop="changerLangue('fr')">
            <img src="@/assets/france.png" alt="Français" />
            <span>Français</span>
          </div>

          <div class="langue-option" :class="{ active: selectedLang === 'en'}" @click.stop="changerLangue('en')">
            <img src="@/assets/royaume-uni.png" alt="Anglais" />
            <span>Anglais</span>
          </div>

        </div>
      </div>

      <button class="seConnecter-button">Se Connecter</button>
    </div>
  </header>

  <div v-if="menuActive" class="overlay" @click="fermerMenu"></div>

  <!--Menu Vertical -->
  <div v-if="menuActive" class="menu-ver">
    <img src="@/assets/logo-removebg-preview.png" alt="Logo" class="logo"/>

    <div class="menu-button mobile-only">
      <button class="seConnecter-button menu-version">Se Connecter</button>
    </div>
    <ul class="menu-links">
      <li><a href="#" :class="{ active : pageActive === 'Accueil'}" @click="changerPage('Accueil')">Accueil</a></li>
      <li class="mobile-only"><a href="#" :class="{ active : pageActive === 'Témoignages'}" @click="changerPage('Témoignages')">Témoignages</a></li>
      <li class="mobile-only"><a href="#" :class="{ active : pageActive === 'Appels à dons'}" @click="changerPage('Appels à dons')">Appels à dons</a></li>

      <li><a href="#" :class="{ active : pageActive === 'Faire un don'}" @click="changerPage('Faire un don')">Faire un don</a></li>
      <li><a href="#" :class="{ active : pageActive === 'Partager un témoignage'}" @click="changerPage('Partager un témoignage')">Partager un témoignage</a></li>
      <li><a href="#" :class="{ active : pageActive === 'Faire un appel à dons'}" @click="changerPage('Faire un appel à dons')">Faire un appel à dons</a></li>
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

.icon-language img {
  height: 22px;
  width: 22px;
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

  .menu-links a {
    text-decoration: none;
    font-weight: 600;
    color: #103056;
    font-size: 16px;
    padding: 8px 12px;
    border-radius: 8px;
    display: block;
    transition: all 0.2s ease;
  }

  .menu-links a:hover {
    background-color: #FA6E89;
    color: white ;
  }

  .menu-links .active {
    background-color: #FA6E89;
    color : white;
    font-weight: bold;
  }

  .mobile-only {
    display : none ;
  }

  .lang-select {
    position: relative;
    display: inline-block;
  }

  .lang-dropdown {
    position: absolute;
    top : 40px ;
    right :0 ;
    background-color: white;
    border-radius: 12px;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    padding: 8px 0;
    z-index : 50;
    width: 190px ;
    height : 90px ;
  }

  .langue-option {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 10px ;
    padding: 8px 12px;
    cursor: pointer;
    transition: background-color 0.2s ease;
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
    align-items: center;
    justify-content: center;
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
  }
</style>
<script setup>

import {onBeforeMount, onMounted, ref} from 'vue' ;

const menuActive = ref(false);
const pageActive = ref('Accueil');
const langVisible = ref(false);
const selectedLang = ref('fr');

function gererMenu(){
  menuActive.value = !menuActive.value ;
}

function fermerMenu() {
  menuActive.value = false ;
}

function changerPage(newPage) {
  pageActive.value = newPage ;
  fermerMenu();
}

function gerermenulang(){
  langVisible.value = ! langVisible.value ;
}

function gererClick(e) {
  const langMenu = document.querySelector('.lang-select');

  if(langMenu && !langMenu.contains(e.target)) {
    langVisible.value = false;
  }
}

onMounted(() => {
  window.addEventListener('click',gererClick);
});

onBeforeMount(() => {
  window.removeEventListener('click',gererClick);
});

function changerLangue(langue) {
  selectedLang.value = langue;
  langVisible.value = false;
}
</script>