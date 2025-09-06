<template>
  <div class="questionnaire-container">
    <div class="type-don">
      <label>Veuillez choisir un type de don :</label>
      <div class="options">
        <label><input type="radio" name="don" value="sanguin" v-model="selectedType" />Sanguin</label>
        <label><input type="radio" name="don" value="organe" v-model="selectedType"/>Organe</label>
        <label><input type="radio" name="don" value="foie" v-model="selectedType" />Foie</label>
      </div>
    </div>

    <div class="carte">

      <h2>Veuillez répondre au questionnaire suivant pour s'assurer de votre admisibilité</h2>

      <div class="section">
        <h3>Santé générale</h3>
        <div v-for="(question, index) in sante" :key="'sante-' + index" class="question">
          <label>{{ question.contenu }}</label>
          <div class="reponses">
            <label><input type="radio" :name="'sante-' + question.idQstGen" :value="true" v-model="reponses['sante-' + question.idQstGen]" /> Oui</label>
            <label><input type="radio" :name="'sante-' + question.idQstGen" :value="false" v-model="reponses['sante-' + question.idQstGen]" /> Non</label>
          </div>
        </div>
     </div>

      <div class="section">
        <h3>Comportements à risque</h3>
        <div v-for="(question, index) in risque" :key="'risque-' + index" class="question">
          <label>{{ question.contenu }}</label>
          <div class="reponses">
            <label><input type="radio" :name="'risque-' + question.idQstRisk" :value="true" v-model="reponses['risque-' + question.idQstRisk]" /> Oui</label>
            <label><input type="radio" :name="'risque-' + question.idQstRisk" :value="false" v-model="reponses['risque-' + question.idQstRisk]" /> Non</label>
          </div>
        </div>
      </div>

      <div class="section">
        <h3>Antécédents médicaux</h3>
        <div v-for="(question, index) in antecedents" :key="'ant-' + index" class="question">
          <label>{{ question.contenu }}</label>
          <div class="reponses">
            <label><input type="radio" :name="'ant-' + question.idQstAntc" :value="true" v-model="reponses['ant-' + question.idQstAntc]" />Oui</label>
            <label><input type="radio" :name="'ant-' + question.idQstAntc" :value="false" v-model="reponses['ant-' + question.idQstAntc]" />Non</label>
          </div>
        </div>
      </div>

      <div class="confirmation">
        <label>
          <input type="checkbox" v-model="accepte"/>
          Je déclare avoir rempli ce formulaire en toute honnêteté et certifie que toutes les réponses sont vraies.
        </label>
     </div>

      <button class="btn-submit" :disabled="!accepte" @click="soumettreRep">M'inscrire</button>
     </div>

    <Popup v-if="afficherPopup" :titre="popupTitre" :message="popupMessage" :illustration="illustrationPopup" @fermer="fermerPopup" />
  </div>
</template>

<style scoped>

* {
  font-family: 'Quicksand', sans-serif;
}

.questionnaire-container {
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

.carte {
  background-color: white ;
  border-radius: 12px ;
  padding: 20px ;
  box-shadow: 0 4px 10px rgba(0,0,0,0.05);
  transition: 0.3s ease ;
}

h2 {
  text-align: center;
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
  background-color: #103056;
  color: white ;
  padding: 12px 30px ;
  border: none ;
  border-radius: 10px ;
  font-weight: bold ;
  font-size: 16px ;
  cursor: pointer ;
  transition: background-color 0.3s ease;
  display: block ;
  margin-left: auto ;
}

.btn-submit:hover {
  background-color: #FA6E89;
}

.btn-submit:disabled {
  background-color: #929090;
  cursor: not-allowed;
  opacity: 0.6 ;
}

@media screen and (max-width: 768px) {
  .questionnaire-container {
    padding: 100px 20px 30px ;
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

  .confirmation {
    font-size: 14px ;
    padding: 15px ;
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
import Popup from "@/components/popupSucces.vue";
import illustrationRequired from "@/assets/required.png";

import axios from 'axios' ;


const router = useRouter() ;
const selectedType = ref('sanguin');

const accepte = ref(false) ;


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
  const idUser = sessionStorage.getItem('idUser')
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
    const [resGen,resRisk,resAntc] = await Promise.all([
        axios.get('http://localhost:8080/api/qstgen'),
      axios.get('http://localhost:8080/api/qstrisk'),
      axios.get('http://localhost:8080/api/qstantc'),
    ]);

    sante.value = resGen.data ;
    risque.value = resRisk.data ;
    antecedents.value = resAntc.data ;
  } catch (error) {
    console.log('Erreur lors de la récupération des questions :' , error);
  }
});

const reponses = ref({}) ;

const fermerPopup = () => {
  afficherPopup.value = false

  if(rediriger.value) {
    router.push('/')
  }
}

const soumettreRep = async () => {

  const idUser = sessionStorage.getItem('idUser');

  try {
  const dejaInscrit = await axios.get(`http://localhost:8080/api/reponses/${idUser}`) ;
  if(dejaInscrit.data.length > 0) {
    afficherPopup.value = true;
    popupTitre.value = "Déja inscrit";
    popupMessage.value = "Vous avez déjà rempli ce formulaire. Merci pour votre contribution" ;
    rediriger.value = true ;
    return ;

  }

  const promesses = [] ;

  sante.value.forEach(q => {
    const valeur = reponses.value['sante-' + q.idQstGen];
    if(valeur !== undefined) {
      promesses.push(
          axios.post('http://localhost:8080/api/reponses/ajout' ,{
            idUser,
            idQstGen: q.idQstGen,
            valeur,
          })
      );
    }
  });

  risque.value.forEach(q => {
    const valeur = reponses.value['risque-' + q.idQstRisk];
    if (valeur !== undefined) {
      promesses.push(
          axios.post('http://localhost:8080/api/reponses/ajout', {
            idUser,
            idQstRisk: q.idQstRisk,
            valeur,
          })
      );
    }
  });

  antecedents.value.forEach(q => {
    const valeur = reponses.value['ant-' + q.idQstAntc];
    if (valeur !== undefined) {
      promesses.push(
          axios.post('http://localhost:8080/api/reponses/ajout', {
            idUser,
            idQstAntc: q.idQstAntc,
            valeur,
          })

      );
    }
  });

  await Promise.all(promesses) ;

  await axios.post("http://localhost:8080/api/incrsang/ajout", {
    idUser: idUser
  });

  sessionStorage.setItem("popupTitre","Vos réponses ont été envoyées avec succès");
  sessionStorage.setItem("popupMessage", "Merci pour votre contribution !");
  await router.push('/');

  }catch (error) {
    console.error("Erreur lors de la soumission :" , error);
    afficherPopup.value = true;
    popupTitre.value = "Erreur" ;
    popupMessage.value = "Une erreur lors de la soumission . Veuillez réessayer." ;
  }
};
</script>