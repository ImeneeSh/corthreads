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
import {ref, reactive, provide , computed, onMounted } from "vue";
import {useRouter} from 'vue-router' ;
import axios from 'axios' ;
import EtapeRole from './etapeRole.vue' ;
import EtapeMail from './etapeMail.vue' ;
import EtapeNom from './etapeNomPrenom.vue' ;
import EtapeInfoCit from './etapeInfosCitoyen.vue'
import EtapeInfoMed from './etapeInfosMed.vue'

const etapeActuelle = ref(0);

const router = useRouter();
const chargement = ref(false) ;
const erreurInsc = ref('') ;

const viaGoogle = ref(false) ;

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

function construirePayload() {
  if (formData.role === "Medecin") {
    return {
      idUser: formData.idUser,
      nom: formData.nom,
      prenom: formData.prenom,
      wilaya: formData.wilaya,
      dateNaissance: formData.dateNaissance,
      genre: formData.genre,
      role: formData.role,
      mdp: formData.mdp,
      specialite: formData.specialite,
      etablissement: formData.etablissement
    };
  } else if (formData.role === "Citoyen") {
    return {
      idUser: formData.idUser,
      nom: formData.nom,
      prenom: formData.prenom,
      wilaya: formData.wilaya,
      dateNaissance: formData.dateNaissance,
      genre: formData.genre,
      role: formData.role,
      mdp: formData.mdp,
      groupeSang: formData.groupeSang,
      rh: formData.rh
    };
  }
  return {};
}


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

const etapesCitoyenGoogle = [
    EtapeRole ,
    EtapeInfoCit,
];

const etapesMedecinGoogle = [
  EtapeRole ,
  EtapeInfoCit,
];

provide("chargement" , chargement) ;

async function inscription(){
  chargement.value = true ;
  erreurInsc.value = '' ;
  try {
    console.log("Payload envoyé :", JSON.stringify(construirePayload(), null, 2));
    const reponse = await axios.post("http://localhost:8080/api/utilisateurs/inscription" , construirePayload());
    console.log("Insciption réussie :" , reponse.data);
    sessionStorage.setItem('inscriptionReussie' , 'true');
    sessionStorage.setItem('idUser', formData.idUser);
    sessionStorage.setItem('nom', formData.nom);
    sessionStorage.setItem('prenom', formData.prenom);
    sessionStorage.setItem('role', formData.role);
    await router.push('/');
  }catch(error) {
    console.error("Erreur lors de l'inscription :", error.response?.data || error.message);
    erreurInsc.value = error.response?.data?.message || "L'inscription a échoué. Veuillez réessayer." ;
  }finally {
    chargement.value = false ;
  }
}

async function suivant() {
  if (etapeActuelle.value < etapes.value.length -1) {
    etapeActuelle.value ++;
  } else {
    await inscription();
  }
}

const etapes = computed(() => {
  if(viaGoogle.value){
    if(formData.role === "Medecin") return etapesMedecinGoogle;
    if(formData.role === "Citoyen") return etapesCitoyenGoogle;
    return [EtapeRole];
  }else {
    if(formData.role === "Medecin") return etapesMedecin;
    if(formData.role === "Citoyen") return etapesCitoyen;
    return [EtapeRole];
  }
}) ;

onMounted(() => {
  const fromGgl = sessionStorage.getItem('googleInscription');

  if(fromGgl){
    viaGoogle.value = true ;

    formData.idUser = sessionStorage.getItem('idUser') || '';
    formData.nom = sessionStorage.getItem('nom') || '';
    formData.prenom = sessionStorage.getItem('prenom') || '';
    sessionStorage.removeItem('googleInscription');
  }
})


</script>