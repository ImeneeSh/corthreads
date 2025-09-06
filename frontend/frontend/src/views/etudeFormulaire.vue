<template>
  <div class="formulaire-container">

    <div class="carte">

      <div class="carte-header">
        <img src="@/assets/utilisateur.png" alt="user icon" class="icon-user" />
        <div class="header-info">
          <h3>{{ prenom }} {{ nom }}</h3>
        </div>
      </div>


      <div class="section">
        <h3>Santé générale</h3>
        <div v-for="(question, index) in sante" :key="'sante-' + index" class="question">
          <label>{{ question.contenu }}</label>
          <div class="reponses">
            <label><input type="radio" :name="'sante-' + question.idQstGen" :value="true" v-model="reponses['sante-' + question.idQstGen]" :disabled="reponses['sante-' + question.idQstGen] !== undefined" /> Oui</label>
            <label><input type="radio" :name="'sante-' + question.idQstGen" :value="false" v-model="reponses['sante-' + question.idQstGen]" :disabled="reponses['sante-' + question.idQstGen] !== undefined"/> Non</label>
          </div>
        </div>
      </div>

      <div class="section">
        <h3>Comportements à risque</h3>
        <div v-for="(question, index) in risque" :key="'risque-' + index" class="question">
          <label>{{ question.contenu }}</label>
          <div class="reponses">
            <label><input type="radio" :name="'risque-' + question.idQstRisk" :value="true" v-model="reponses['risque-' + question.idQstRisk]" :disabled="reponses['risque-' + question.idQstRisk] !== undefined" /> Oui</label>
            <label><input type="radio" :name="'risque-' + question.idQstRisk" :value="false" v-model="reponses['risque-' + question.idQstRisk]" :disabled="reponses['risque-' + question.idQstRisk] !== undefined" /> Non</label>
          </div>
        </div>
      </div>

      <div class="section">
        <h3>Antécédents médicaux</h3>
        <div v-for="(question, index) in antecedents" :key="'ant-' + index" class="question">
          <label>{{ question.contenu }}</label>
          <div class="reponses">
            <label><input type="radio" :name="'ant-' + question.idQstAntc" :value="true" v-model="reponses['ant-' + question.idQstAntc]" :disabled="reponses['ant-' + question.idQstAntc] !== undefined"/>Oui</label>
            <label><input type="radio" :name="'ant-' + question.idQstAntc" :value="false" v-model="reponses['ant-' + question.idQstAntc]" :disabled="reponses['ant-' + question.idQstAntc] !== undefined" />Non</label>
          </div>
        </div>
      </div>

      <div class="btns">
        <button class="btn-submit btn-valider"  @click="">
          <img src="@/assets/verifier.png" alt="valider" class="icon" />
          Valider
        </button>

        <button class="btn-submit btn-rejeter"  @click="">
          <img src="@/assets/effacer.png" alt="rejeter" class="icon" />
          Rejeter
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>

* {
  font-family: 'Quicksand', sans-serif;
}

.formulaire-container {
  background-color: #FFE5EA;
  min-height: 100vh ;
  padding: 120px 40px 40px;
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

.carte {
  background-color: white ;
  border-radius: 12px ;
  padding: 20px ;
  box-shadow: 0 4px 10px rgba(0,0,0,0.05);
  transition: 0.3s ease ;
}

h2 {
  text-align: left;
  font-size: 25px ;
  margin-bottom: 50px ;
  color: #103056;
}

.section{
  margin-bottom: 30px ;
  background-color: #EBE3FF ;
  padding: 25px ;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0,0,0,0.05);
}

.section h3 {
  color: #FA6E89;
  margin-bottom: 30px ;
  text-align: center;
  font-size: 22px ;
  font-family: 'Poppins', sans-serif;
}

.question {
  margin-bottom: 15px ;
  font-weight: 500;
}

.reponses {
  display: flex;
  gap: 20px ;
  margin-top: 5px ;
}

.confirmation {
  margin-bottom: 20px ;
  padding: 20px ;
  color: #FA6E89;
  font-weight: bold;
}

.btn-submit {
  color: white ;
  padding: 12px 30px ;
  border: none ;
  border-radius: 10px ;
  font-weight: bold ;
  font-size: 16px ;
  cursor: pointer ;
  transition: background-color 0.3s ease;
  display: block ;
}

.btn-valider {
  background-color: #05DF72;
}

.btn-rejeter {
  background-color: #FF4242;
}

.btns {
  display: flex;
  gap: 5px;
  justify-content: flex-end;
  margin-top: 30px;
}

.icon {
  width: 16px ;
  height: 16px ;
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

.carte-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px ;
  margin-bottom: 10px ;
  flex-wrap: wrap ;
  width: 100% ;
}

.icon-user {
  width: 30px ;
  height: 30px ;
}

@media screen and (max-width: 768px) {
  .formulaire-container {
    padding: 100px 20px 30px ;
  }

  .btns {
    flex-direction: column;
    gap: 4px;
  }

  .carte {
    padding: 15px ;
  }

  h2 {
    font-size: 20px ;
    margin-bottom: 30px ;
  }

  .section {
    padding: 15px ;
  }

  .section h3 {
    font-size: 18px;
    margin-bottom: 20px ;
  }

  .question {
    font-size: 14px ;
  }

  .reponses {
    flex-direction: column;
    gap: 10px ;
  }

  .btn-submit {
    width: 100% ;
    margin: 20px 0 0 auto ;
    font-size: 16px ;
    padding: 12px ;
  }

  input[type="checkbox"],
  input[type="checkbox"]:checked {
    width: 24px;
    height: 24px;
    min-width: 24px;
    min-height: 24px;
  }

}
</style>
<script setup>

import {onMounted, ref, watch} from 'vue' ;
import { useRouter } from 'vue-router' ;
import { useRoute } from 'vue-router' ;
import illustrationRequired from "@/assets/required.png";

import axios from 'axios' ;


const idUser = ref('') ;
const router = useRouter() ;
const route = useRoute() ;
idUser.value = route.params.idUser;
const nom = ref('');
const prenom = ref('');

const selectedType = ref('sanguin');

const reponses = ref({}) ;


const afficherPopup = ref(false)
const popupTitre = ref('')
const popupMessage = ref('')
const rediriger = ref(false)

const estConnecte = ref (false)
const illustrationPopup = ref(illustrationRequired)

watch(selectedType, (newValue) => {

  const currentRoute = router.currentRoute.value.path ;
  let targetRoute = '' ;

  if (newValue === 'sanguin') {
    targetRoute = '/faireDonSang';
  }

  else if (newValue === 'organe') {
    targetRoute = '/faireDonOrgane';
  }

  else if (newValue === 'foie') {
    targetRoute = '/faireDonFoi';
  }

  if (targetRoute && currentRoute !== targetRoute ){
    router.push(targetRoute);
  } else if (targetRoute && currentRoute === targetRoute ){
    router.replace({ path: '/temp' }).then(() => {
      router.replace(targetRoute);
    });
  }
});

const sante = ref([]) ;

const risque = ref([]) ;

const antecedents = ref([]) ;

onMounted(async () => {

  nom.value = route.params.nom ;
  prenom.value = route.params.prenom ;

  if(!idUser) {
    afficherPopup.value = true
    popupTitre.value = 'Connexion requise'
    popupMessage.value = 'Vous devez être connecté pour faire un don'
    illustrationPopup.value = illustrationRequired
    rediriger.value = true
    return
  }

  estConnecte.value = true ;


  try {
    const [resGen,resRisk,resAntc , resUser] = await Promise.all([
      axios.get('http://localhost:8080/api/qstgen'),
      axios.get('http://localhost:8080/api/qstrisk'),
      axios.get('http://localhost:8080/api/qstantc'),
      axios.get(`http://localhost:8080/api/reponses/${idUser.value}`)
    ]);

    sante.value = resGen.data ;
    risque.value = resRisk.data ;
    antecedents.value = resAntc.data ;


    resUser.data.forEach(rep => {
      console.log("rep brut : ", rep);

      let key = '';
      if (rep.idQstGen != null) {
        key = 'sante-' + rep.idQstGen;
      } else if (rep.idQstRisk != null) {
        key = 'risque-' + rep.idQstRisk;
      } else if (rep.idQstAntc != null) {
        key = 'ant-' + rep.idQstAntc;
      }

      if (key) {
        reponses.value[key] = rep.valeur === true || rep.valeur === "true";
      } else {
        console.warn("Aucune clé détectée pour rep :", rep);
      }
    });

  } catch (error) {
    console.log('Erreur lors de la récupération des questions :' , error);
  }

});

</script>