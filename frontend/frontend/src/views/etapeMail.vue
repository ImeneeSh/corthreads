<template>
  <div class="etape-mail">
    <input type="email" v-model="formData.idUser" placeholder="Email" required />

    <div class="mdp-wrapper">
      <input :type="motDePasseVisible ? 'text' : 'password'" v-model="formData.mdp" placeholder="Mot De Passe" required />
      <img :src="motDePasseVisible ? voir : cacher" alt="oeil" class="oeil" @click="motDePasseVisible = !motDePasseVisible" />
    </div>

    <div class="mdp-wrapper">
      <input :type="motDePasseVisible ? 'text' : 'password'" v-model="confirmation" placeholder="Confirmer le motDe Passe" required />
    </div>

    <p v-if="motDePasseNonIdentique" class="erreur">Les mots de passe ne correpondent pas</p>

    <button class="continuer-btn" type="submit" :disabled="!peutContinuer">
      Continuer <img src="@/assets/fleche-droite.png" alt="flèche" class="fleche-icon" />
    </button>
  </div>
</template>

<style scoped>
.etape-mail {
  display: flex ;
  flex-direction: column;
  gap : 12px;
}

input {
  width: 100% ;
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

.mdp-wrapper {
  position : relative ;
}

.oeil {
  position : absolute;
  right: 12px ;
  top: 12px ;
  height: 20px ;
  width: 20px ;
  cursor : pointer;
}

.erreur {
  color: #FA6E89;
  font-size: 13px ;
  margin-top: -4px ;
  margin-bottom: 4px;
  text-align: center;
  font-weight: bold;
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
    width: 100% ;
    font-size: 14px ;
  }

  input {
    font-size: 14px ;
  }
}
</style>
<script setup>
import {inject , ref, computed } from "vue" ;
import voir from "@/assets/oeil (1).png" ;
import cacher from "@/assets/cacher (1).png" ;

const formData = inject("formData");
const confirmation = ref("");
const motDePasseVisible = ref(false);

const motDePasseNonIdentique = computed(() =>
  formData.mdp && confirmation.value && formData.mdp !== confirmation.value
);

const peutContinuer = computed(() =>
  formData.idUser && formData.mdp && confirmation.value && formData.mdp === confirmation.value
);
</script>

