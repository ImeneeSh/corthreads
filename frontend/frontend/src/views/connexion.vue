<template>
  <div class="connexion">
    <div class="illustration">
      <img src="@/assets/Design%20sans%20titre%20(1).png" alt="illustration" class="illustration-image" />
    </div>

    <div class="formulaire">
      <div class="box">
        <h2>Bienvenue sur Corthreads</h2>

        <form @submit.prevent="connexion">
          <input type="email" v-model="email" placeholder="Email" required/>
          <div class="mdp-wrapper">
            <input :type="motDePasseVisible ? 'text' : 'password'" v-model="motDePasse" placeholder="Mot de passe" required />
            <img :src="motDePasseVisible ? voir : cacher " alt="voir" class="oeil" @click="motDePasseVisible = !motDePasseVisible" />
          </div>

          <a href="#" class="oubli">Mot de passe oublié ?</a>

          <p v-if="errConnexion" class="message-erreur">{{ errConnexion }}</p>

          <button class="connexion-btn" type="submit" v-if="!chargement">Connexion</button>

          <div v-else class="chargement-container">
            <div class="spinner"></div>
          </div>

          <div class="separateur">
            <span>ou</span>
          </div>

          <button type="button" class="google-btn" @click="connexionGgl">
            <img src="@/assets/chercher.png" alt="Google" />
            Continuer avec Google
          </button>

          <p class="inscription">Vous n'avez pas de compte ? <router-link to="/inscription">S'inscrire</router-link></p>

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
  height: 100vh ;
  overflow: hidden;
  display: flex ;
  align-items: center;
  justify-content: center;
  padding: 0 ;
}

.illustration-image {
  width: 100% ;
  height: 100% ;
  object-fit: cover;
  object-position: center;
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

.message-erreur {
  color: #FA6E89;
  font-weight: bold;
  font-size: 14px;
  margin-bottom: 12px;
  text-align: center;
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

.chargement-container {
  display: flex ;
  justify-content: center ;
  align-items: center;
  height: 48px ;
}

.spinner {
  width: 30px ;
  height: 30px ;
  border: 3px solid #FA6E89;
  border-top: 3px solid transparent;
  border-radius: 50%;
  animation: spin 0.8s linear infinite;
}

@keyframes spin {
  to {
    transform: rotate(360deg);
  }
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
import axios from "axios" ;

import voir from "@/assets/oeil (1).png";
import cacher from "@/assets/cacher (1).png" ;

import { useRouter } from 'vue-router' ;

import { signInWithPopup , auth ,provider } from "@/firebase.js";

const motDePasseVisible = ref(false);

const email= ref('');
const motDePasse= ref ('');

const router = useRouter();

const chargement= ref(false);
const errConnexion= ref('');

onMounted(() => {
  document.body.classList.add('no-scroll');
});

onBeforeMount(() => {
  document.body.classList.remove('no-scroll');
})

async function connexionGgl() {
  try {
    const resultat = await signInWithPopup(auth, provider) ;
    const user = resultat.user ;
    console.log ("Connecté avec Google :", user);

    const idUser = user.email ;

    const reponse = await axios.get(`http://localhost:8080/api/utilisateurs/${idUser}`);

    if(reponse.data) {
      sessionStorage.setItem('idUser', idUser);
      sessionStorage.setItem('nom', user.displayName.split(" ")[1] || '');
      sessionStorage.setItem('prenom', user.displayName.split(" ")[0] || '');
      sessionStorage.setItem('role', reponse.data.role);

      await router.push('/');
    } else {
      sessionStorage.setItem('googleInscription' , 'true');
      sessionStorage.setItem('idUser', idUser);
      sessionStorage.setItem('nom', user.displayName.split(" ")[1] || '');
      sessionStorage.setItem('prenom', user.displayName.split(" ")[0] || '');

      await router.push('/inscription');
    }
  } catch (error) {
    console.log("Erreur Google : ",error);
  }
}

async function connexion(){
  chargement.value = true ;
  try {
    const reponse = await axios.post("http://localhost:8080/api/auth/connexion", {
      idUser: email.value ,
      mdp: motDePasse.value,
    });

    const { idUser ,nom, prenom, role } = reponse.data ;

    sessionStorage.setItem('idUser' , idUser);
    sessionStorage.setItem('nom', nom) ;
    sessionStorage.setItem('prenom', prenom);
    sessionStorage.setItem('role' , role);

    errConnexion.value= '';
    await router.push('/');

  } catch(error){
    console.error("Erreur de connexion , vérifier vos identifiants !:", error);

    if(error.response && error.response.status === 401 ){
      errConnexion.value = "Identifiants incorrects. Veuillez réessayer.";
    } else {
      errConnexion.value = "Une erreur est survenue. Veuillez réessayer plus tard.";
    }
  }finally {
    chargement.value = false ;
  }
}
</script>