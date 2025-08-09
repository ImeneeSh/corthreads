<template>
  <div class="choix-role">
      <p class="sous-titre">Veuillez séléctionner votre type d'utilisateur</p>

      <div class="roles">
        <div class="role-card" :class="{ actif: formData.role === 'Citoyen'}" @click="formData.role = 'Citoyen'">
          <img :src="iconeCitoyen" alt="Citoyen"/>
          <span>Citoyen</span>
        </div>

        <div class="role-card" :class="{ actif: formData.role === 'Medecin'}" @click="formData.role = 'Medecin'">
          <img :src="iconeMedecin" alt="Medecin"/>
          <span>Médecin</span>
        </div>
      </div>

      <button class="continuer-btn" type="submit" :disabled="!formData.role">Continuer
        <img src="@/assets/fleche-droite.png" alt="flèche" class="fleche-icon" /></button>
  </div>
</template>

<style scoped>

.choix-role {
 display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: white;
  width: 100%;
}

.sous-titre {
  font-size: 14px;
  color: #929090;
  margin-bottom: 24px ;
  text-align: center;
}

.roles {
  display: flex;
  justify-content: space-between;
  gap: 16px ;
  width: 100%;
  margin-bottom: 24px ;
}

.role-card {
  flex: 1 ;
  border: 1px solid #D0D0D0;
  border-radius: 8px;
  padding: 16px 8px ;
  cursor: pointer ;
  transition: 0.3s ;
  display: flex ;
  flex-direction: column;
  align-items: center;
  color: #103056;
  background-color: white;
  text-align: center;
}

.role-card img {
  height: 40px ;
  margin-bottom: 8px;
}

.role-card span {
  font-weight: bold;
  font-size: 14px;
}

.role-card.actif {
  border-color: #FA6E89;
  color: #FA6E89;
  border-width: 2px;
}

.continuer-btn {
  background-color: #FA6E89;
  color: white ;
  border: none ;
  border-radius: 24px ;
  padding: 12px ;
  width: 100% ;
  font-weight: bold;
  font-size: 16px ;
  cursor: pointer;
  transition: 0.3s ;
  display: flex ;
  align-items: center;
  justify-content: center;
  margin-top: 16px ;
}

.continuer-btn:disabled {
  background-color: #929090;
  cursor: not-allowed;
}

.continuer-btn:not(:disabled):hover {
  background-color: #103056;
}

.fleche-icon {
  height: 18px;
  width: 18px;
  margin-left: 8px ;
}

@media screen and (max-width: 768px) {
  .choix-role {
    width: 100% ;
    padding: 24px ;
    box-sizing: border-box;
  }

  .roles {
    flex-direction: column ;
    gap: 16px ;
  }

  .role-card {
    width: 100%
  }

  .continuer-btn {
    width: 100% ;
    font-size: 14px ;
  }

  .sous-titre {
    font-size: 13px;
  }

  .role-card span {
    font-size: 13px;
  }
}

</style>
<script setup>
import { inject , computed } from "vue" ;

const formData = inject("formData");

const iconeCitoyen = computed(() =>
  formData.role === "Citoyen"
  ? new URL('@/assets/homme-daffaire (1).png' , import.meta.url).href
      : new URL('@/assets/homme-daffaire.png' ,import.meta.url).href
);

const iconeMedecin = computed(() =>
  formData.role === "Medecin"
    ? new URL('@/assets/medecin (1).png' , import.meta.url).href
      : new URL('@/assets/medecin.png' ,import.meta.url).href
);
</script>