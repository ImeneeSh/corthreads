<template>
  <div class="temo-container">
    <div class="carte-form">
      <div class="header-form">
        <h3>Nouveau Témoignage</h3>
      </div>

      <div class="body-form">
        <div class="user-info">
          <img src="@/assets/utilisateur.png" alt="user icon" class="icon-user" />
          <span class="user-name">Ablehamid Kala</span>
        </div>
        <textarea class="textarea" placeholder="Dite quelque chose..."></textarea>
      </div>

      <div class="footer-form">
        <button class="btn-partager">Partager</button>
      </div>
    </div>
  </div>
</template>

<style scoped>

.temo-container {
  background-color: #FFE5EA ;
  min-height: 100vh ;
  padding: 100px 40px 40px ;
  display: flex ;
  justify-content: center ;
  align-items: center;
}

.carte-form {
  background-color: white ;
  border-radius: 12px ;
  box-shadow : 0 0 4px rgba(0,0,0,0.1);
  width: 100% ;
  max-width: 800px ;
  padding: 20px ;
  display: flex ;
  flex-direction: column;
}

.header-form {
  position : relative;
  text-align: center;
  margin-bottom: 20px ;
}

.header-form h3 {
  font-family: 'Quicksand', sans-serif ;
  color: #103056;
  margin: 0 ;
  font-size: 24px ;
}

.body-form {
  display: flex ;
  flex-direction: column ;
  gap: 12px ;
  margin-bottom: 20px ;
}

.user-info {
  display: flex ;
  align-items: center;
  gap: 10px ;
  color: #103056;
  font-weight: bold ;
}

.icon-user {
  width: 20px ;
  height: 20px ;
}

.user-name {
  font-size: 14px ;
}

.textarea {
  border: none ;
  background-color: #F7F7F7;
  border-radius: 8px ;
  padding: 12px ;
  min-height: 100px ;
  resize: none ;
  font-family: 'Quicksand', sans-serif;
  font-size: 14px ;
}

.textarea::placeholder {
  color: #a1a1a1 ;
}

.footer-form {
  display: flex ;
  justify-content: flex-end;
}

.btn-partager {
  background-color: #FA6E89;
  color: white ;
  border: none ;
  padding: 10px 24px ;
  border-radius: 10px ;
  font-weight: bold ;
  cursor: pointer ;
  font-family: 'Quicksand', sans-serif;
}

.btn-partager:hover {
  background-color: #103056;
}

@media screen and (max-width: 768px){
  .temo-container {
    padding: 120px 20px 0 20px ;
    align-items: flex-start ;
  }

  .carte-form {
    padding: 16px ;
  }

  .header-form h3 {
    font-size: 20px ;
  }

  .user-info {
    flex-direction: column ;
    align-items: flex-start;
    gap: 6px ;
  }

  .user-name {
    font-size: 13px ;
  }

  .textarea {
    font-size: 13px ;
    padding: 10px ;
  }

  .btn-partager {
    padding: 8px 20px ;
    font-size: 14px ;
  }
}
</style>

<script setup>

</script>