<template>
  <div class="appel-container">
    <div class="type-don">
      <label>Veuillez choisir un type de don :</label>
      <div class="options">
        <label><input type="radio" name="don" value="sanguin" v-model="selectedType" />Sanguin</label>
        <label><input type="radio" name="don" value="foie" v-model="selectedType" />Foie</label>
        <label><input type="radio" name="don" value="organe" v-model="selectedType" />Organe</label>
      </div>
    </div>

    <div v-if="loading" class="loader-container">
      <div class="loader"></div>
    </div>

    <div class="carte-appel">
      <div class="carte" v-for="(post, index) in postsAffiches" :key="index" data-aos="fade-up">
        <div class="carte-header">
          <img src="@/assets/utilisateur.png" alt="user icon" class="icon-user" />
          <div class="header-info">
            <h3>{{ post.utilisateur.prenom }} {{ post.utilisateur.nom }}</h3>
          </div>
        </div>

        <p class="plus">Plus de détails :</p>
        <div class="infos">
          <p><strong>Date de naissance :</strong> {{ afficherValeur(post.utilisateur.dateNaissance) }}</p>
          <p><strong>Genre :</strong> {{ afficherValeur(post.utilisateur.genre) }}</p>
          <p><strong>Groupe Sanguin :</strong> {{ afficherValeur(post.utilisateur.groupeSang) }}</p>
          <p><strong>Rhesus :</strong> {{ afficherValeur(post.utilisateur.rh) }}</p>
          <p><strong>Wilaya :</strong> {{ afficherValeur(post.utilisateur.wilaya) }}</p>
          <p class="champ-date-programmee">
            <label for="donneur-sang" class="label-date">Est donneur de sang :</label>
            <select v-model="post.nouvelEstDonneurSang" class="input-date" id="donneur-sang">
              <option :value="true">Oui</option>
              <option :value="false">Non</option>
            </select>
          </p>

          <p class="champ-date-programmee">
            <label for="dernier-don" class="label-date">Date du dernier don de sang :</label>
            <input type="date" v-model="post.nouvelleDateDernierDon" class="input-date" id="dernier-don"/>
          </p>

        </div>

        <div class="btn-group">

          <button class="btn-don btn-formulaire" @click="enregistrerInfosUtilisateur(post)">
            <img src="@/assets/une-reponse-rapide.png" class="icon-don" alt="don icon">
            Enregistrer
          </button>

          <div class="btn-email-wrapper">
            <button class="btn-don btn-contacter" @click.stop="gererEmail(index)">
              <img src="@/assets/lettre.png" class="icon-don" alt="don icon">
              Contacter
            </button>

            <div v-if="emailVisible === index" class="email-box" @click.stop>
              <p class="email-label">Adresse mail de l'utilisateur :</p>
              <div class="email-content">
                <input class="email-input" type="text" :value="post.utilisateur.idUser" readonly />
                <img src="@/assets/interface.png" class="copy-icon" alt="Copier" @click="copierEmail(post.utilisateur.idUser)" />
              </div>
            </div>
          </div>

          <div class="btn-email-wrapper">
            <button class="btn-don btn-supprimer" @click="afficherSupp(index)">
              <img src="@/assets/supprimer%20(1).png" class="icon-don" alt="don icon">
              Supprimer
            </button>

            <div v-if="indexSupp === index" class="boite-confirmation" @click.stop >
              <p class="question-confirmation">Êtes-vous sûr de vouloir supprimer cet appel à dons ?</p>
              <div class="boutons-confirmation">
                <button class="btn-don btn-supprimer" @click="confirmerSupp(index)">Confirmer</button>
                <button class="btn-don btn-annuler" @click="annulerSupp">Annuler</button>
              </div>
            </div>
          </div>

          <button class="btn-don valider">
            <img src="@/assets/valider.png" class="icon-don" alt="don icon">
            Valider
          </button>
        </div>
      </div>
    </div>

    <button class="voir-plus" @click="voirPlus" v-if="visiblePosts <= posts.length">
      Voir plus
      <img src="@/assets/fleche-droite.png" class="icon-fleche" alt="flèche" >
    </button>
  </div>
</template>

<style scoped>

.appel-container {
  background-color: #E8EEFF;
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

.btn-formulaire {
  background-color: #FA6E89;
  color: white ;
  cursor: pointer;
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

.plus {
  font-weight: bold ;
}

.loader-container {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 50px 0 ;
}

.loader {
  border: 6px solid #F3F3F3 ;
  border-top: 6px solid #FA6E89;
  border-radius: 50% ;
  width: 40px ;
  height: 40px ;
  animation: spin 2s linear infinite;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.carte-appel {
  display : flex ;
  flex-direction: column;
  gap: 24px ;
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

.carte-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px ;
  margin-bottom: 10px ;
  flex-wrap: wrap ;
  width: 100% ;
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

.icon-user {
  width: 30px ;
  height: 30px ;
}


.infos {
  display: flex ;
  flex-wrap: wrap;
  gap: 25px ;
  margin-top: 10px ;
}

.infos p {
  margin: 4px 0 ;
  font-weight: bold;
  color: #FA6E89;
}

.infos strong {
  color: #103056;
  margin-right: 5px ;
}

.btn-group {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  gap: 12px;
  margin-top: 30px;
  justify-content: flex-end;
}

.btn-don {
  color: white ;
  padding: 8px 18px ;
  border-radius: 20px;
  font-weight: bold;
  display: flex;
  align-items: center;
  gap: 8px;
  border: none ;
  font-size: 14px ;
  transition: all 0.3s ease;
  margin: 0 !important;
  cursor: pointer ;
}

.btn-email-wrapper {
  position: relative;
}

.btn-contacter {
  background-color: #103056;
  color: white;
  cursor: pointer;
}

.email-box {
  position: absolute ;
  top: auto ;
  bottom: 110% ;
  right: 0 ;
  background-color: white;
  border: 1px solid #D0D0D0;
  border-radius: 12px;
  padding: 16px ;
  box-shadow: 0 2px 6px rgba(0,0,0,0.05);
  max-width: 360px ;
  z-index: 100 ;
  animation: fadeIn 0.3s ease-in-out;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-5px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.email-label {
  font-weight: bold;
  margin-bottom: 8px ;
  color: #103056;
  font-size: 14px;
}

.email-content {
  display: flex ;
  align-items: center;
  gap: 8px ;
}

.email-input {
  flex: 1 ;
  padding: 10px 12px ;
  border-radius: 8px ;
  border: 1px solid #D0D0D0;
  font-size: 14px ;
  color: #333 ;
}

.email-input:focus {
  outline: none;
  border-color: #FA6E89;
}

.copy-icon {
  width: 20px ;
  height: 20px ;
  cursor: pointer ;
  transition : opacity 0.3s ease ;
}

.copy-icon:hover {
  opacity: 0.7;
}

.btn-supprimer {
  background-color: #FF4242;
  color: white;
  border: none ;
  cursor: pointer;
}

.boite-confirmation {
  position: absolute ;
  top: auto ;
  bottom: 110% ;
  right: 0 ;
  background-color: white;
  border: 1px solid #D0D0D0;
  border-radius: 12px;
  padding: 16px ;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
  max-width: 320px ;
  z-index: 100 ;
  animation: fadeIn 0.3s ease-in-out;
  text-align: center;
}

.question-confirmation {
  font-weight: bold;
  margin-bottom: 12px ;
  color: #103056;
  font-size: 14px;
}

.boutons-confirmation {
  display: flex ;
  justify-content: space-between;
  gap: 10px ;
}

.btn-annuler {
  background-color: white;
  color: #103056;
  border: 2px solid #ccc ;
  cursor: pointer ;
}

.icon-don {
  width: 16px ;
  height: 16px ;
}

.voir-plus {
  margin-top: 40px ;
  background-color: #FA6E89;
  color: white ;
  border: none ;
  padding : 10px 40px ;
  border-radius: 24px;
  font-weight: bold;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background-color 0.3s ease;
  margin-left: auto;
}

.valider {
  background-color: white;
  color: #05DF72;
  border: 2px solid #05DF72;
  cursor: default !important;
}

.voir-plus:hover {
  background-color: #103056;
}

.icon-fleche {
  height: 16px;
  width: 16px;
  vertical-align: middle;
  margin-left:6px ;
  transform: translateY(1px);
}


.champ-date-programmee {
  display: flex ;
  align-items: center;
  gap: 10px ;
  flex-wrap: wrap;
  margin-top: 0;
}

.label-date {
  font-weight: bold;
  color: #103056;
  display: flex;
  align-items: center;
  gap: 6px;
  font-size: 16px;
  margin-bottom: 10px;
}

.input-date {
  padding: 6px 12px;
  border: 2px solid #FA6E89;
  border-radius: 8px;
  font-size: 14px;
  font-family: 'Quicksand', sans-serif;
  color: #103056;
  background-color: white;
  transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

.input-date:focus {
  outline: none;
  border-color: #103056;
  box-shadow: 0 0 0 2px rgba(250, 110, 137, 0.2);
}

@media screen and (max-width: 768px) {
  .appel-container {
    padding: 100px 20px 40px ;
  }

  .carte {
    padding: 16px ;
  }

  .carte-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 8px ;
  }

  .btn-group {
    flex-direction: column;
    align-items: stretch;
    gap: 12px ;
    width: 100% ;
    margin-top: 26px ;
  }

  .btn-don {
    justify-content: center;
    width: 100%;
    padding: 10px 14px ;
    font-size: 14px ;
  }

  .icon-don {
    width: 16px ;
    height: 16px ;
  }

  .voir-plus {
    width: fit-content;
  }

  .infos {
    gap: 15px ;
  }

  .infos p {
    font-size: 13px ;
  }

  .header-info h3 {
    font-size: 15px ;
  }

  .email-box {
    position: static;
    width: 100% ;
    margin-top: 12px ;
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

  input[type="checkbox"],
  input[type="checkbox"]:checked {
    width: 24px;
    height: 24px;
    min-width: 24px;
    min-height: 24px;
  }

  .champ-date-programmee {
    flex-direction: column;
    align-items: flex-start;
  }

  .label-date {
    font-size: 13px;
  }

  .input-date {
    width: 100%;
  }
}
</style>
<script setup>
import { ref, watch, computed, onMounted } from "vue";
import { useRouter } from 'vue-router';
import axios from "axios";

const indexSupp = ref(null);

function afficherValeur(val) {
  if (val === null || val === undefined) {
    return '/';
  } else if (typeof val === 'boolean') {
    return val ? 'vrai' : 'faux';
  } else {
    return val;
  }
}

const router = useRouter();

const error = ref(null);

const selectedType = ref('sanguin');
const loading = ref(true);
const posts = ref([]);

watch(selectedType, (newValue) => {
  const currentRoute = router.currentRoute.value.path;
  let targetRoute = '';

  if (newValue === 'sanguin') {
    targetRoute = '/ListeDonneursValidesSang';
  } else if (newValue === 'foie') {
    targetRoute = '';
  } else if (newValue === 'organe') {
    targetRoute = '';
  }

  if (targetRoute !== currentRoute) {
    router.push(targetRoute);
  } else if (targetRoute && currentRoute === targetRoute) {
    router.replace({ path: '/temp' }).then(() => {
      router.replace(targetRoute);
    });
  }
});

const visiblePosts = ref(4);
const emailVisible = ref(null);


onMounted(async () => {
  try {
    const reponse = await axios.get('http://localhost:8080/api/listesang');
    posts.value = reponse.data.map(post => ({
      ...post,
      nouvelEstDonneurSang: post.utilisateur.DonneurSang === true || post.utilisateur.DonneurSang === "true",
      nouvelleDateDernierDon: post.utilisateur.dernierDonSang
          ? post.utilisateur.dernierDonSang.slice(0, 10)
          : '',
    }));

  } catch (err) {
    console.error('Erreur lors de la récupération des données', err);
    error.value = "impossible de charger les appels à dons.";
  } finally {
    loading.value = false;
  }
});

const postsAffiches = computed(() => {
  return posts.value.slice(0, visiblePosts.value);
});

function voirPlus() {
  visiblePosts.value += 4;
}

function gererEmail(index) {
  emailVisible.value = emailVisible.value === index ? null : index;
}

function copierEmail(email) {
  navigator.clipboard.writeText(email);
}

function afficherSupp(index){
  indexSupp.value = index;
}

function annulerSupp(){
  indexSupp.value = null ;
}

async function confirmerSupp(index) {
  const post = postsAffiches.value[index];

  try {
    await axios.delete(`http://localhost:8080/api/listesang/suppression/${post.idListeSang}`);
    alert("Suppression effectuer avec succès !");
    indexSupp.value = null;
  } catch (error) {
    alert("Erreur lors de la suppression");
    console.error(error);
  }
}

const enregistrerInfosUtilisateur = async (post) => {
  const email = post.utilisateur.idUser;
  const params = new URLSearchParams();

  if (post.nouvelEstDonneurSang !== post.utilisateur.DonneurSang) {
    params.append('donneurSang', post.nouvelEstDonneurSang);
  }

  if (post.nouvelleDateDernierDon !== (post.utilisateur.dernierDonSang?.slice(0, 10) || '')) {
    params.append('dernierDonSang', post.nouvelleDateDernierDon);
  }

  if ([...params].length === 0) {
    alert("Aucune modification détectée !");
    return;
  }

  try {
    await axios.put(`http://localhost:8080/api/utilisateurs/modifier/${email}?${params.toString()}`);
    alert("Informations mises à jour avec succès !");

    post.utilisateur.DonneurSang = post.nouvelEstDonneurSang;
    post.utilisateur.dernierDonSang = post.nouvelleDateDernierDon;

  } catch (error) {
    console.error("Erreur lors de la mise à jour :", error);
    alert("Une erreur est survenue lors de la mise à jour.");
  }
};

</script>