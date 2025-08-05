<template>
  <div class="contacter-page">
    <img src="@/assets/proposArriere.png" alt="fond vague rose" class="background" />

    <section class="contacter-container">
      <h4>Contactez Maintenant</h4>
      <h2>Avez-vous des questions ? Ecrivez un message</h2>

      <form class="contacter-form">
        <textarea placeholder="Ecrire un message" rows="6"></textarea>
        <button type="submit">Envoyer un message</button>
      </form>
    </section>
  </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Quicksand:wght@400;500;700&display=swap');

.contacter-page {
  position: relative;
  padding: 100px 16px ;
  font-family: 'Quicksand', sans-serif;
  overflow: hidden;
  background-color: white;
}

.background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 0;
  opacity: 1;
}

.contacter-container {
  position: relative;
  z-index: 1;
  max-width: 700px;
  margin: auto;
  text-align: left;
}

.contacter-container h4 {
  color: #FA6E89;
  font-weight: 700;
  font-size: 16px;
  margin-bottom: 8px;
}

.contacter-container h2 {
  color: #103056;
  font-weight: 700;
  font-size: 24px;
  font-family: 'Quicksand', sans-serif;
  margin-bottom: 32px;
  line-height: 1.4;
}

.contacter-form textarea {
  width: 100%;
  padding: 16px ;
  border: 1px solid #103056;
  border-radius: 6px;
  font-family: 'Quicksand', sans-serif;
  font-size: 16px;
  resize: none;
  margin-bottom: 24px;
}

.contacter-form button {
  background-color: #FA6E89;
  color: white;
  border: none;
  padding: 12px 24px;
  border-radius: 6px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: background 0.3s ease ;
}

.contacter-form button:hover {
  background-color: #103056;
}

@media screen and (max-width: 768px){
  .contacter-container h2{
    font-size: 20px;
  }

  .contacter-form textarea {
    font-size: 14px;
  }

  .contacter-form button {
    font-size: 14px ;
    padding: 10px 20px ;
  }
}
</style>
<script setup>

</script>