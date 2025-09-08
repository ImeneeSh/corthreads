<template>
  <div class="parametres-container">
    <div class="carte">
      <div class="formulaire">
        <div class="gauche">
          <div class="image"><img src="@/assets/utilisateur.png" alt="profil" class="img" /></div>

          <div class="form-group">
            <label>Nom</label>
            <input type="text" v-model="utilisateur.nom" />
          </div>

          <div class="form-group">
            <label>Prénom</label>
            <input type="text" v-model="utilisateur.prenom" />
          </div>

          <div class="form-group">
            <label>Date de naissance</label>
            <input type="date" v-model="utilisateur.dateNaissance" />
          </div>

          <div class="form-group">
            <label>Groupe sanguin</label>
            <select v-model="utilisateur.groupSang">
              <option v-for="(sang, i) in sangs" :key="i" :value="sang">{{ sang }}</option>
            </select>
          </div>
        </div>

        <div class="droite">
          <div class="form-group">
            <label>Wilaya</label>
            <select v-model="utilisateur.wilaya">
              <option v-for="(wilaya, i) in wilayas" :key="i" :value="wilaya">{{ wilaya }}</option>
            </select>
          </div>

          <div class="form-group">
            <label>Rôle</label>
            <input type="text" v-model="utilisateur.role" disabled />
          </div>

          <div v-if="utilisateur.role === 'Medecin'">
            <div class="form-group">
              <label>Spécialité</label>
              <input type="text" v-model="utilisateur.specialite" />
            </div>
            <div class="form-group">
              <label>Établissement</label>
              <input type="text" v-model="utilisateur.etablissement" />
            </div>
          </div>

          <div class="form-group">
            <label>Mot de passe</label>
            <input type="password" v-model="utilisateur.mdp" />
          </div>

          <div class="btn"><button class="enregistrer" @click="confirmation">Enregistrer</button></div>
        </div>
      </div>
    </div>

    <!-- section confirmation -->

    <div class="modal" v-if="confirmationVisible">
      <div class="modal-contenu">
        <h3 class="titre">Remarque !</h3>

        <p v-if="!messageErreur">
          Une fois les modifications enregistrées, vous ne pourrez les modifier de nouveau qu’après un délai de 30 jours.
          Veuillez vous assurer que les informations saisies sont correctes avant de valider.
        </p>

        <p v-else style="color: #FF4242; margin-top: 12px; font-weight: bold">
          {{ messageErreur }}
        </p>

        <div class="boutons-modal">
          <button @click="annuler">Annuler</button>
          <button class="valider" @click="valider">Valider</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

* {
  font-family: 'Quicksand', sans-serif;
}
.parametres-container {
  background-color: #E8EEFF;
  min-height: 100vh ;
  padding: 120px 40px 40px;
}

.carte {
  background-color: white ;
  border-radius: 12px ;
  box-shadow: 0 4px 10px rgba(0,0,0,0.05);
  transition: 0.3s ease ;
  position: relative ;
  z-index: 1 ;
  overflow: visible ;
  padding: 20px ;
}

.formulaire {
  display: flex ;
  gap: 40px ;
  flex-wrap: wrap ;
}

.gauche, .droite {
  flex: 1;
  min-width: 280px;
}

.image {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.img {
  width: 100px ;
  height: 100px ;
  margin-bottom: 30px ;
  margin-top: 30px ;
}

.form-group {
  margin-bottom: 16px;
}

label {
  display: block;
  margin-bottom: 6px ;
  font-weight:bold ;
  color: #103056;
}

input, select {
  width: 100% ;
  padding: 10px ;
  border: 1px solid #D0D0D0;
  border-radius: 8px;
}

.enregistrer {
  background-color: #103056;
  color: white ;
  font-weight: bold;
  padding: 12px 24px ;
  border: none ;
  border-radius: 8px ;
  cursor: pointer ;
  margin-top: 50px ;
  font-size: 16px;
  transition: background-color 0.3s ease ;
}

.enregistrer:hover {
  background-color: #FA6E89;
}

.btn {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.modal {
  position: fixed ;
  top: 0;
  left: 0 ;
  width: 100vw ;
  height: 100vh ;
  background-color: rgba(0,0,0,0.5);
  display: flex ;
  align-items: center;
  justify-content: center;
  z-index: 100 ;
}

.modal-contenu {
  background: white;
  padding: 32px ;
  border-radius: 12px ;
  max-width: 500px;
  width: 90% ;
  text-align: center;
}

.modal-contenu .titre {
  color: #FA6E89;
  margin-bottom: 12px;
}

.boutons-modal {
  display: flex ;
  justify-content: space-around;
  margin-top: 20px ;
}

.boutons-modal button {
  padding: 10px 20px;
  border: none;
  border-radius: 8px;
  font-weight: bold;
  cursor: pointer;
}

.boutons-modal .valider {
  background-color: #103056;
  color: white;
}

.boutons-modal button:not(.valider) {
  background-color: #e0e0e0;
}

@media screen and(max-width : 768px) {
  .parametres-container {
    padding: 100px 20px 40px ;
  }
}
</style>
<script setup>

import {ref, onMounted } from 'vue';
import axios from 'axios' ;
import { useRouter } from 'vue-router' ;

const utilisateur = ref({});
const confirmationVisible = ref(false);
const router= useRouter();

const sangs = ["A","B","AB","O"];

const wilayas = [
  "Adrar", "Alger", "Annaba", "Aïn Defla", "Aïn Témouchent", "Batna", "Béchar", "Béjaïa", "Béni Abbès", "Biskra", "Blida", "Bordj Bou Arreridj", "Bordj Badji Mokhtar", "Bouira", "Boumerdès", "Chlef", "Constantine", "Djelfa", "Djanet", "Djijel", "El Bayadh", "El M'Ghair", "El Meniaa", "El Oued", "El Tarf", "Ghardaïa", "Guelma", "Illizi", "In Salah", "In Guezzam", "Khenchela", "Laghouat", "Mascara", "Médéa", "Mila", "Mostaganem", "M'Sila", "Naâma", "Oum El Bouaghi", "Oran", "Ouargla", "Ouled Djellal", "Relizane", "Saïda", "Sétif", "Sidi Bel Abbès", "Skikda", "Souk Ahras", "Tamanrasset", "Tébessa", "Tiaret", "Timimoun", "Tindouf", "Tissemsilt", "Tizi Ouzou", "Tipaza", "Tlemcen", "Touggourt"
];

const messageErreur = ref('');

const idUser = sessionStorage.getItem("idUser");

onMounted(async () => {
  try {
    const res = await axios.get(`http://localhost:8080/api/utilisateurs/${idUser}`);
    utilisateur.value = res.data;
  } catch(error){
    console.log("Erreur lors du chargement des infos de l'utilisateur", error);
  }
});

function confirmation(){

  if (!utilisateur.value.dateDerniereModification) {
    confirmationVisible.value = true;
    messageErreur.value = '';
    return;
  }

  const derniereModif = new Date(utilisateur.value.dateDerniereModification);
  const aujourdHui = new Date();
  const diffTemps = aujourdHui - derniereModif;
  const diffJours = diffTemps / (1000 * 60 * 60 * 24);

  if (diffJours < 30) {
    messageErreur.value = `Modification refusée : vous devez attendre ${Math.ceil(30 - diffJours)} jour(s) avant de pouvoir modifier à nouveau vos informations.`;
    confirmationVisible.value = true;
  } else {
    messageErreur.value = '';
    confirmationVisible.value = true;
  }
}

function annuler() {
  confirmationVisible.value = false ;
  router.push('/');
}

async function valider() {

  if (messageErreur.value) {
    confirmationVisible.value = false;
    router.push('/');
    return;
  }

  confirmationVisible.value = false ;

  const modifs = {};
  for (const [cle, valeur] of Object.entries(utilisateur.value)) {
    if(valeur !== null && valeur !== undefined) {
      modifs[cle] = valeur ;
    }
  }

  const aujourdhuis = new Date().toISOString().split('T')[0];
  modifs.dateDerniereModification = aujourdhuis;

  try {
    const params = new URLSearchParams(modifs).toString();
    await axios.put(`http://localhost:8080/api/utilisateurs/modifier/${idUser}?${params}`);
    alert("Modifications enregistrées !");
    await router.push('/');
  }catch (error){
    console.error("Erreur lors de la maj", error);
    alert("Erreur lors de la sauvegarde des données");
  }
}

</script>