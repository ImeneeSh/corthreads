<template>
  <div class="etape-info-cit" v-if="formData.role === 'Medecin'">
    <div class="row">
      <select v-model="formData.genre" required>
        <option disabled value="">Genre</option>
        <option v-for="(genre, i) in genres" :key="i" :value="genre">{{ genre }} </option>
      </select>

      <input type="date" v-model="formData.dateNaissance" required />
    </div>

    <div class="row">
      <select v-model="formData.wilaya" required>
        <option disabled value="">Wilaya de fonction</option>
        <option v-for="(wilaya, i) in wilayas" :key="i" :value="wilaya">{{ wilaya }} </option>
      </select>

      <input type="text" v-model="formData.specialite" placeholder="Spécialité" required />
    </div>

    <div class="row">
      <input type="text" v-model="formData.etablissement" placeholder="Etablissement de fonction" required />
    </div>

    <p v-if="erreurInsc" class="message-erreur">{{ erreurInsc }}</p>

    <button v-if="!chargement" class="continuer-btn" type="submit" :disabled="!peutContinuer" >
      Terminer !
    </button>

    <div v-else class="chargement-container">
      <div class="spinner"></div>
    </div>

  </div>
</template>

<style scoped>
.etape-info-cit {
  display: flex ;
  flex-direction: column;
  gap: 12px ;
}

.row {
  display: flex;
  gap: 12px ;
  flex-wrap: wrap;
}

select, input[type="date"] {
  flex: 1 ;
  padding: 12px ;
  border-radius: 8px;
  border: 1px solid #D0D0D0;
  font-size: 13px;
  min-width: 160px;
}

select:focus,input[type="date"]:focus {
  outline: none;
  border: 2px solid #FA6E89;
}

input {
  width: 100%;
  padding: 12px ;
  border-radius: 8px;
  border: 1px solid #D0D0D0;
  margin-bottom: 12px;
  font-size: 14px;
}


input:focus {
  outline: none;
  border: 2px solid #FA6E89;
}


.continuer-btn {
  background-color: #FA6E89;
  color: white ;
  border: none;
  border-radius: 24px;
  padding: 12px ;
  width: 100% ;
  font-weight: bold;
  font-size: 16px ;
  cursor: pointer;
  display : flex;
  align-items: center;
  justify-content: center;
  gap: 8px ;
  margin-top: 12px;
}

.continuer-btn:disabled {
  background-color: #929090;
  cursor: not-allowed;
}

.continuer-btn:not(:disabled):hover {
  background-color: #103056;
}

.chargement-container {
  display: flex ;
  justify-content: center ;
  align-items : center ;
  height: 48px ;
}

.spinner {
  width: 30px;
  height: 30px;
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

.message-erreur {
  color: #FA6E89;
  font-weight: bold;
  font-size: 14px;
  margin-bottom: 12px;
  text-align: center;
}


@media screen and (max-width: 768px) {
  .row {
    flex-direction: column;
    gap: 8px ;
  }

  select, input[type="date"] {
    font-size: 14px ;
    min-width: 100% ;
  }

  .continuer-btn {
    font-size: 14px;
  }
}

</style>
<script setup>

import {computed, inject} from "vue";

const formData = inject("formData");
const chargement = inject("chargement");
const erreurInsc = inject ("erreurInsc") ;


const wilayas = [
  "Adrar", "Alger", "Annaba", "Aïn Defla", "Aïn Témouchent", "Batna", "Béchar", "Béjaïa", "Béni Abbès", "Biskra", "Blida", "Bordj Bou Arreridj", "Bordj Badji Mokhtar", "Bouira", "Boumerdès", "Chlef", "Constantine", "Djelfa", "Djanet", "Djijel", "El Bayadh", "El M'Ghair", "El Meniaa", "El Oued", "El Tarf", "Ghardaïa", "Guelma", "Illizi", "In Salah", "In Guezzam", "Khenchela", "Laghouat", "Mascara", "Médéa", "Mila", "Mostaganem", "M'Sila", "Naâma", "Oum El Bouaghi", "Oran", "Ouargla", "Ouled Djellal", "Relizane", "Saïda", "Sétif", "Sidi Bel Abbès", "Skikda", "Souk Ahras", "Tamanrasset", "Tébessa", "Tiaret", "Timimoun", "Tindouf", "Tissemsilt", "Tizi Ouzou", "Tipaza", "Tlemcen", "Touggourt"
]

const genres = [
  "Femme" , "Homme" , "Je préfère ne pas répondre"
]

const peutContinuer = computed(() =>
    formData.genre && formData.dateNaissance && formData.wilaya && formData.groupeSanguin && formData.rhesus
);
</script>
