<template>
  <div class="connexion">
    <div class="illustration">
      <img src="@/assets/logo-removebg-preview.png" alt="logo" class="logo"/>
      <img src="@/assets/connexion.png" alt="illustration" class="personne" />
    </div>

    <div class="formulaire">
      <div class="box">
        <h2>Bienvenue sur Corthreads</h2>

        <form @submit.prevent>
          <input type="email" placeholder="Email" required/>
          <div class="mdp-wrapper">
            <input :type="motDePasseVisible ? 'text' : 'password'" placeholder="Mot de passe" required />
            <img :src="motDePasseVisible ? voir : cacher " alt="voir" class="oeil" @click="motDePasseVisible = !motDePasseVisible" />
          </div>

          <a href="#" class="oubli">Mot de passe oublié ?</a>

          <button class="connexion-btn">Connexion</button>

          <div class="separateur">
            <span>ou</span>
          </div>

          <button type="button" class="google-btn" @click="connexionGgl">
            <img src="@/assets/chercher.png" alt="Google" />
            Continuer avec Google
          </button>

          <p class="inscription">Vous n'avez pas de compte ? <a href="#">S'inscrire</a></p>

        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>

.connexion {
  display : flex ;
  height: 100vh;
  overflow: hidden;
}

.illustration {
  width: 50% ;
  background-color: #C1CEFD;
  display: flex ;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 40px ;
}

.logo {
  height: 130px ;
  margin-top : 30px ;
}

.personne {
  max-width: 100%;
  height: auto;
}

.formulaire {
  width: 50% ;
  display: flex ;
  align-items: center;
  justify-content: center;
  padding: 40px ;
  background-color: white;
}

.box {
  background-color: white;
  border: 1px solid #D0D0D0;
  padding: 32px ;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0,0,0,.1);
  width: 100% ;
  max-width: 400px;
}

h2 {
  text-align: center;
  color: #103056;
  margin-bottom: 24px ;
}

input {
  width: 100% ;
  padding: 12px ;
  border-radius: 8px;
  border: 1px solid #D0D0D0;
  margin-bottom: 12px;
  font-size: 14px;
}

.mdp-wrapper {
  position: relative;
}

.mdp-wrapper input {
  padding-right: 40px;
}

.oeil {
  position: absolute;
  right: 12px ;
  top: 12px ;
  height: 20px ;
  width: 20px ;
  cursor: pointer;
}

.oubli {
  font-size: 13px;
  color: #103056;
  text-decoration: none;
  display: inline-block;
  margin-bottom: 20px;
  font-weight: 600;
}

.connexion-btn {
  background-color: #FA6E89;
  color: white;
  border: none ;
  border-radius: 24px;
  padding: 12px ;
  width: 100% ;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
  margin-bottom: 16px;
}

.connexion-btn:hover {
  background-color: #103056;
}

.separateur {
  text-align: center;
  margin-bottom: 16px;
  color: #D0D0D0;
}

.google-btn {
  background-color: #E9E7E9;
  color: #103056;
  border: none ;
  padding: 12px ;
  border-radius: 24px;
  width: 100% ;
  font-weight: bold;
  display: flex ;
  align-items: center;
  justify-content: center;
  gap: 8px ;
  cursor: pointer ;
}

.google-btn img {
  width: 20px ;
  height: 20px ;
}

.inscription {
  text-align: center;
  font-size: 13px;
  margin-top: 16px;
  color: #103056;
}

.inscription a {
  color: #FA6E89;
  text-decoration: none ;
  font-weight: 600;
}

input:focus {
  outline: none ;
  border: 2px solid #FA6E89;
}

@media screen and (max-width: 768px) {
  .connexion {
    flex-direction : column;
    height: auto ;
  }

  .illustration {
    display: none;
  }

  .formulaire {
    width : 100%;
    padding: 20px ;
  }

  .box {
    max-width : 90%;
    margin: 0 auto;
    padding: 24px ;
  }

  h2 {
    font-size: 20px;
  }

  input, .connexion-btn, .google-btn {
    font-size: 14px ;
  }

  .inscription {
    font-size: 12px;
  }
}
</style>
<script setup>

import { onMounted , onBeforeMount, ref } from "vue" ;
import voir from "@/assets/oeil (1).png";
import cacher from "@/assets/cacher (1).png" ;

import { signInWithPopup , auth ,provider } from "@/firebase.js";

const motDePasseVisible = ref(false);

onMounted(() => {
  document.body.classList.add('no-scroll');
});

onBeforeMount(() => {
  document.body.classList.remove('no-scroll');
})

function connexionGgl() {
  signInWithPopup(auth , provider)
      .then((result) => {
        const user = result.user;
        console.log("Connecté avec Google :", user);
        // ce qui se passe après
      })
      .catch((error) => {
        console.error("Erreur Google : " ,error);
      });
}
</script>