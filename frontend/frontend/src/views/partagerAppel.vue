<template>
  <div class="appel-container">
    <div class="carte-form">
      <div class="header-form">
        <h3>Nouvel appel à dons</h3>
      </div>

      <div class="body-form">
        <div class="user-info">
          <img src="@/assets/utilisateur.png" alt="user icon" class="icon-user" />
          <span class="user-name">John Doe</span>
        </div>
        <textarea class="textarea" placeholder="Dite quelque chose..." ></textarea>
      </div>

      <div class="info">
        <p class="plus">Plus de détails :</p>
        <div class="infos">
          <strong>Niveau d'urgence :</strong>
          <div class="select-wrapper">
            <select>
              <option value="">Urgent</option>
            </select>

            <img src="@/assets/fleche-vers-le-bas-pour-naviguer.png" class="select-icon" alt="icone déroulante" />
          </div>

          <strong>Type de don :</strong>
          <div class="select-wrapper">
            <select>
              <option value="">Sang</option>
            </select>

            <img src="@/assets/fleche-vers-le-bas-pour-naviguer.png" class="select-icon" alt="icone déroulante" />
          </div>

          <strong>Groupe sanguin :</strong>
          <div class="select-wrapper">
            <select>
              <option value="">A</option>
            </select>

            <img src="@/assets/fleche-vers-le-bas-pour-naviguer.png" class="select-icon" alt="icone déroulante" />
          </div>

          <strong>Rhesus :</strong>
          <div class="select-wrapper">
            <select>
              <option value="">positif</option>
            </select>

            <img src="@/assets/fleche-vers-le-bas-pour-naviguer.png" class="select-icon" alt="icone déroulante" />
          </div>

          <strong>Wilaya :</strong>
          <div class="select-wrapper">
            <select>
              <option value="">Adrar</option>
            </select>

            <img src="@/assets/fleche-vers-le-bas-pour-naviguer.png" class="select-icon" alt="icone déroulante" />
          </div>
        </div>
      </div>

      <div class="footer-form">
        <button class="btn-partager">Partager</button>
      </div>
    </div>
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
    padding: 120px 20px 0 20px ;
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
    padding: 8px 20px ;
    font-size: 14px;
  }
}
</style>
<script setup>

</script>

