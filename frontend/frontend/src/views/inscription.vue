<template>
  <div class="inscription">
    <div class="illustration">
      <img src="@/assets/logo-removebg-preview.png" alt="logo" class="logo" />
      <img src="@/assets/connexion-removebg-preview.png" alt="illustration" class="personne" />
    </div>

    <div class="formulaire">
      <div class="box">
        <h2>Inscrivez-vous maintenant sur Corthreads</h2>

        <form @submit.prevent="suivant">
          <component :is="etapes[etapeActuelle]" />
        </form>

      </div>
    </div>
  </div>
</template>

<style scoped>
.inscription {
  display : flex ;
  height: 100vh;
  overflow: hidden;
}

.illustration {
  width: 50% ;
  background : linear-gradient(135deg, #C1CEFD 0%, #AAB8F5 100%);
  display: flex ;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  padding: 40px ;
  position: relative;
}

.logo {
  height: 150px ;
  margin-top : 30px ;
}

.personne {
  width: 100%;
  max-width: 400px ;
  height: auto ;
  object-fit: contain ;
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

@media screen and (max-width: 768px){
  .inscription {
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
}

</style>
<script setup>
import {ref, reactive, provide } from "vue";
import EtapeRole from './etapeRole.vue' ;
import EtapeMail from './etapeMail.vue' ;

const etapeActuelle = ref(0);

const formData = reactive({
  role: "",
  email: "",
  motDePasse: "",
  nom: "",
  prenom: "",
  genre: "",
  dateNaissance: "",
  wilaya: "",
  groupeSanguin: "",
  rhesus: "",
});

provide("formData", formData);

const etapes = [
  EtapeRole,
  EtapeMail,
];

function suivant() {
  if (etapeActuelle.value < etapes.length -1) {
    etapeActuelle.value ++;
  } else {
    console.log("Données finales :", formData);
    // appel d'api d'inscription ici
  }
}

</script>