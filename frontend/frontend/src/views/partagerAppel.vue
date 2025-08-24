<template>
  <div class="appel-container">
    <div class="carte-form">
      <div class="header-form">
        <h3>Nouvel appel à dons</h3>
      </div>

      <div class="body-form">
        <div class="user-info">
          <img src="@/assets/utilisateur.png" alt="user icon" class="icon-user" />
          <span class="user-name">{{ prenom }} {{ nom }}</span>
        </div>
        <textarea class="textarea" placeholder="Dite quelque chose..." v-model="contenu"></textarea>
      </div>

      <div class="info">
        <p class="plus">Plus de détails :</p>
        <div class="infos">
          <strong>Niveau d'urgence :</strong>
          <div class="select-wrapper">
            <select v-model="selectedUrgence">
              <option value="">Urgence</option>
              <option v-for="(urgence, i) in niveauxUrgence" :key="i" :value="urgence">{{ urgence }}</option>
            </select>

            <img src="@/assets/fleche-vers-le-bas-pour-naviguer.png" class="select-icon" alt="icone déroulante" />
          </div>

          <strong>Type de don :</strong>
          <div class="select-wrapper">
            <select v-model="selectedTypeDon">
              <option value="">Type de don</option>
              <option v-for="(typedon, i) in typedons" :key="i" :value="typedon">{{ typedon }}</option>
            </select>

            <img src="@/assets/fleche-vers-le-bas-pour-naviguer.png" class="select-icon" alt="icone déroulante" />
          </div>

          <strong>Groupe sanguin :</strong>
          <div class="select-wrapper">
            <select v-model="selectedGroupSang">
              <option value="">Groupe sanguin</option>
              <option v-for="(typesang, i) in typesangs" :key="i" :value="typesang">{{ typesang }}</option>
            </select>

            <img src="@/assets/fleche-vers-le-bas-pour-naviguer.png" class="select-icon" alt="icone déroulante" />
          </div>

          <strong>Rhesus :</strong>
          <div class="select-wrapper">
            <select v-model="selectedRh">
              <option value="">Rhesus</option>
              <option v-for="(rh, i) in rhs" :key="i" :value="rh">{{ rh }}</option>
            </select>

            <img src="@/assets/fleche-vers-le-bas-pour-naviguer.png" class="select-icon" alt="icone déroulante" />
          </div>

          <strong>Wilaya :</strong>
          <div class="select-wrapper">
            <select v-model="selectedWilaya">
              <option value="">Wilaya</option>
              <option v-for="(wilaya, i) in wilayas" :key="i" :value="wilaya">{{ wilaya }}</option>
            </select>

            <img src="@/assets/fleche-vers-le-bas-pour-naviguer.png" class="select-icon" alt="icone déroulante" />
          </div>
        </div>
      </div>

      <div class="footer-form">
        <button class="btn-partager" @click="envoyerAppel">Partager</button>
      </div>
    </div>

    <Popup v-if="afficherPopup" :titre="popupTitre" :message="popupMessage" :illustration="illustrationPopup" @fermer="fermerPopup" />
  </div>
</template>

<style scoped>

.appel-container {
  background-color: #E8EEFF;
  min-height: 100vh ;
  padding: 100px 40px 40px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.carte-form {
  background-color: white;
  border-radius: 12px ;
  box-shadow: 0 0 4px rgba(0,0,0,0.1);
  width: 100%;
  max-width: 900px;
  padding: 20px ;
  display: flex ;
  flex-direction: column;
}

.header-form {
  position: relative;
  text-align: center;
  margin-bottom: 20px ;
}

.header-form h3 {
  font-family: 'Quicksand', sans-serif;
  color: #103056;
  margin : 0 ;
  font-size: 24px ;
}

.body-form {
  display: flex;
  flex-direction: column;
  gap: 12px ;
  margin-bottom: 20px;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 10px ;
  color: #103056;
  font-weight: bold;
}

.icon-user {
  width: 20px ;
  height: 20px ;
}

.user-name {
  font-size: 14px;
}

.textarea {
  border: none ;
  background-color: #F7F7F7;
  border-radius: 8px ;
  padding: 12px ;
  min-height: 100px ;
  resize: none ;
  font-family: 'Quicksand', sans-serif;
  font-size: 14px;
}

.textarea::placeholder {
  color: #a1a1a1;
}

.footer-form {
  display: flex ;
  justify-content: flex-end;
}

.plus {
  font-weight: bold ;
}

.infos {
  display: flex ;
  flex-wrap: wrap;
  gap: 25px ;
  margin-top: 10px ;
}

.infos strong {
  color: #103056;
  margin-right: 5px;
  font-size: 14px ;
}

.select-wrapper {
  position: relative;
  display: inline-block;
}

.select-wrapper select {
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  background-color: white;
  padding: 8px 40px 8px 25px ;
  border-radius: 6px;
  border-color: #D0D0D0;
  border-width: 1px;
  color: #FA6E89;
  font-weight: 600;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1) ;
  cursor: pointer ;
}

.select-icon {
  position: absolute;
  right: 16px ;
  top: 50% ;
  transform: translateY(-50%);
  width: 10px ;
  height: 10px ;
  pointer-events: none;
}

.btn-partager {
  background-color: #FA6E89;
  color: white ;
  border: none ;
  padding: 10px 24px ;
  border-radius: 10px;
  font-weight: bold;
  cursor: pointer;
  font-family: 'Quicksand', sans-serif;
}

.btn-partager:hover {
  background-color: #103056;
}

@media screen and (max-width: 768px){
  .appel-container {
    padding: 120px 20px 30px 20px ;
    align-items: flex-start;
  }

  .carte-form {
    padding: 16px ;
  }

  .header-form h3{
    font-size: 20px ;
  }

  .user-info {
    flex-direction: column;
    align-items: flex-start;
    gap: 6px ;
  }

  .user-name {
    font-size: 13px;
  }

  .textarea {
    font-size: 13px;
    padding: 10px ;
  }

  .btn-partager {
    margin-top: 25px ;
    padding: 8px 20px ;
    font-size: 14px;
  }
}
</style>
<script setup>

import {ref, onMounted} from 'vue'
import { useRouter} from 'vue-router'
import axios from 'axios'
import Popup from '@/components/popupSucces.vue'
import illustrationConnexion from '@/assets/celebration.png'
import illustrationRequired from '@/assets/required.png'

const illustrationPopup = ref(illustrationRequired)

const router = useRouter()

const afficherPopup = ref(false)
const popupTitre = ref('')
const popupMessage = ref('')
const rediriger = ref(false)

const estConnecte = ref (false)
const contenu = ref('')
const nom= ref('')
const prenom = ref('')

const selectedUrgence= ref('')
const selectedTypeDon= ref('')
const selectedGroupSang= ref('')
const selectedRh= ref('')
const selectedWilaya= ref('')

const typedons = ["Sang", "Organe", "Foie"]

const wilayas = [
  "Adrar", "Alger", "Annaba", "Aïn Defla", "Aïn Témouchent", "Batna", "Béchar", "Béjaïa", "Béni Abbès", "Biskra", "Blida", "Bordj Bou Arreridj", "Bordj Badji Mokhtar", "Bouira", "Boumerdès", "Chlef", "Constantine", "Djelfa", "Djanet", "Djijel", "El Bayadh", "El M'Ghair", "El Meniaa", "El Oued", "El Tarf", "Ghardaïa", "Guelma", "Illizi", "In Salah", "In Guezzam", "Khenchela", "Laghouat", "Mascara", "Médéa", "Mila", "Mostaganem", "M'Sila", "Naâma", "Oum El Bouaghi", "Oran", "Ouargla", "Ouled Djellal", "Relizane", "Saïda", "Sétif", "Sidi Bel Abbès", "Skikda", "Souk Ahras", "Tamanrasset", "Tébessa", "Tiaret", "Timimoun", "Tindouf", "Tissemsilt", "Tizi Ouzou", "Tipaza", "Tlemcen", "Touggourt"
]

const niveauxUrgence = ["Normal", "Urgent"]

const typesangs = ["A", "B", "AB", "O"]

const rhs = ["positif","negatif"]

onMounted(() => {
  const idUser = sessionStorage.getItem('idUser')
  if(!idUser) {
    afficherPopup.value = true
    popupTitre.value = 'Connexion requise'
    popupMessage.value = 'Vous devez être connecté pour publier un appel à dons'
    illustrationPopup.value = illustrationRequired
    rediriger.value = true
  } else {
    estConnecte.value = true
    nom.value = sessionStorage.getItem('nom') || ''
    prenom.value = sessionStorage.getItem('prenom') || ''
  }
})

const envoyerAppel = async () => {
  const idUser = sessionStorage.getItem('idUser')

  if(!idUser || !contenu.value.trim()) {
    return
  }

  const body = {
    idUser : idUser,
    typePost: 'Appel_don',
    typeDon: selectedTypeDon.value,
    groupSang: selectedGroupSang.value,
    rh: selectedRh.value,
    urgence: selectedUrgence.value,
    contenu: contenu.value,
    wilaya: selectedWilaya.value
  }

  try {
    await axios.post('http://localhost:8080/api/posts/ajout' , body)

    popupTitre.value = 'Appel partagé avec succès'
    popupMessage.value = 'Merci pour votre confiance.'
    illustrationPopup.value = illustrationConnexion
    afficherPopup.value = true
    rediriger.value = true
    contenu.value = ''
  } catch (error) {
    console.error(error)
    alert("Erreur lors de l'envoi de l'appel à dons.")
  }
}

const fermerPopup = () => {
  afficherPopup.value = false

  if(rediriger.value) {
    router.push('/')
  }
}
</script>

