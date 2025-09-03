<template>
  <div class="inscription">
    <div class="illustration">
      <img src="@/assets/Design%20sans%20titre%20(1).png" alt="illustration" class="illustration-image" />
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
import {ref, reactive, provide , computed } from "vue";
import EtapeRole from './etapeRole.vue' ;
import EtapeMail from './etapeMail.vue' ;
import EtapeNom from './etapeNomPrenom.vue' ;
import EtapeInfoCit from './etapeInfosCitoyen.vue'
import EtapeInfoMed from './etapeInfosMed.vue'

const etapeActuelle = ref(0);

const formData = reactive({
  role: "",
  idUser: "",
  mdp: "",
  nom: "",
  prenom: "",
  genre: "",
  dateNaissance: "",
  wilaya: "",
  groupeSang: "",
  rh: "",
  specialite:"",
  etablissement: ""
});

provide("formData", formData);

const etapesCitoyen = [
  EtapeRole,
  EtapeMail,
  EtapeNom,
  EtapeInfoCit,
];

const etapesMedecin = [
  EtapeRole,
  EtapeMail,
  EtapeNom,
  EtapeInfoMed,
];

function suivant() {
  if (etapeActuelle.value < etapes.value.length -1) {
    etapeActuelle.value ++;
  } else {
    console.log("Données finales :", formData);
    // appel d'api d'inscription ici
  }
}

const etapes = computed(() => {
  if(formData.role === "Medecin") return etapesMedecin;
  if(formData.role === "Citoyen") return etapesCitoyen;
  return [EtapeRole];
})

</script>