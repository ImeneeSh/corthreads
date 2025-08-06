<template>
  <div class="popup-overlay">
    <div class="popup-content" data-aos="zoom-in">
      <img :src="fermerIcon" alt="fermer" class="close-icon" @click="$emit('fermer')" />
      <h2> {{ titre }}</h2>
      <p class="bienvenue">{{ message }}</p>
      <img :src="illustration" alt="célébration" class="popup-illustration" />
    </div>
  </div>
</template>

<style scoped>
.popup-overlay {
  position : fixed ;
  top: 0 ;
  left : 0 ;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
  z-index: 999 ;
  display: flex;
  align-items: center;
  justify-content: center;
}

.popup-content {
  background-color: white ;
  padding : 32px ;
  border-radius: 16px ;
  width: 90% ;
  max-width: 600px ;
  text-align: center;
  position: relative;
  box-shadow: 0 8px 20px rgba(0,0,0,0.2);
}

.close-icon {
  position: absolute;
  top: 16px ;
  right: 16px ;
  width: 24px;
  height: 24px;
  cursor: pointer;
}

.popup-content h2 {
  color: #FA6E89;
  font-size: 20px ;
  margin-bottom: 8px;
}

.bienvenue {
  font-size: 18px ;
  font-weight: bold;
  color: #103056;
  margin-bottom: 16px;
}

.popup-illustration {
  width: 100%;
  max-width: 280px;
  margin-top: 10px ;
}
</style>
<script setup>
import { defineProps } from 'vue'
import fermerIcon from "@/assets/marque-x.png"

defineProps({
  titre: {type : String, default: 'succès !'},
  message: { type: String, default: 'Opération effectuée avec succès !'},
  illustration: {type : String , required: true },
})
</script>
