<template>
  <div class="etape-nom">
    <input type="email" v-model="formData.nom" placeholder="Nom" required />
    <input type="email" v-model="formData.prenom" placeholder="Prénom" required />

    <button class="continuer-btn" type="submit" :disabled="!peutContinuer">
      Continuer <img src="@/assets/fleche-droite.png" alt="flèche" class="fleche-icon" />
    </button>
  </div>
</template>

<style scoped>
.etape-nom {
  display: flex ;
  flex-direction: column;
  gap: 12px ;
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

.fleche-icon {
  height: 18px ;
  width: 18px ;
}

@media screen and (max-width: 768px) {

  .continuer-btn {
    width: 100%;
    font-size: 14px;
  }

  input {
    font-size: 14px;
  }
}
</style>
<script setup>

import {computed, inject, ref} from "vue";

const formData = inject("formData");
const confirmation = ref("");

const peutContinuer = computed(() =>
    formData.email && formData.motDePasse && confirmation.value && formData.motDePasse === confirmation.value
);
</script>
