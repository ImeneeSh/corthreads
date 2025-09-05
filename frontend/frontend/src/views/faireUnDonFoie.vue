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
      <h2>Important à savoir</h2>

      <p>En choisissant de faire un don de foie ,un médecin affilié à notre plateforme vous contactera prochainement par e-mail
        afin de programmer une visite médicale. Cette consultation permettra d’évaluer votre état de santé.
        Si le médecin valide votre aptitude au don, votre profil sera ajouté à une liste dédiée aux donneurs
        de foie et vous serez officiellement inscrit comme volontaire..</p>

      <div class="confirmation">
        <label>
          <input type="checkbox" v-model="accepte" />
          Je déclare avoir lu attentivement toutes les informations fournies et certifie les avoir comprises en toute honnêteté.
        </label>
      </div>

      <button class="btn-submit" :disabled="!accepte" @click="soumettreInscription">M'inscrire</button>
    </div>

    <Popup v-if="afficherPopup" :titre="popupTitre" :message="popupMessage" :illustration="illustrationPopup" @fermer="fermerPopup"/>
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

p {
  font-weight: 500 ;
  text-align: left;
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


import { ref, watch } from 'vue' ;
import { useRouter } from 'vue-router' ;

import Popup from "@/components/popupSucces.vue";
import axios from 'axios';

const router = useRouter() ;
const selectedType = ref('foie');

const accepte = ref(false) ;

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

const soumettreInscription = async () => {
  const idUser = sessionStorage.getItem('idUser');

  try {
    await axios.post('http://localhost:8080/api/incrfoie/ajout', {
      idUser: idUser,
      dateVisiteProgrammer: "2025-09-20" ,
    });

    sessionStorage.setItem("popupTitre", "Inscription réussie !");
    sessionStorage.setItem("popupMessage", "Merci pour votre générosité. Votre inscription a été prise en compte.");
    await router.push('/');
  } catch (error) {
    console.error("Erreur lors de l'inscription :", error);
    afficherPopup.value = true;
    popupTitre.value = "Erreur";
    popupMessage.value = "Une erreur est survenue lors de l'inscription. Veuillez réessayer.";
  }
};

const fermerPopup = () => {
  afficherPopup.value = false;
  if (rediriger.value) {
    router.push('/');
  }
};

</script>

