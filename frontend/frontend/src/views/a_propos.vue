<template>
  <div class="propos-page">

    <section class="propos-container">
      <h2>A propos de Corthreads !</h2>

      <div class="illustration">
        <img src="@/assets/proposPhoto.png" alt="illustation à propos" />
      </div>

      <div class="propos-texte">
        <p>
          Corthreads est une plateforme solidaire née d’un engagement profond :
          faciliter les liens entre donneurs et receveurs de produits biologiques (sang et organes)
          en Algérie, de façon sécurisée, anonyme et encadrée par des professionnels de santé.
        </p>
        <p>
          L’objectif est simple : sauver des vies en connectant les bonnes volontés à ceux qui en ont besoin,
          grâce à un système transparent de témoignages, d’appels de dons filtrables, et de validation médicale.
        </p>
        <p>
          Ce projet a été créé par une jeune étudiante algérienne en informatique,
          portée par une volonté de mettre la technologie au service de l’humain et de la solidarité nationale.
          <br />
          Avec Corthreads, chaque geste compte, chaque histoire relie.
        </p>
      </div>
    </section>
  </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Quicksand:wght@400;500;700&display=swap');

.propos-page {
  font-family: 'Quicksand', sans-serif;
  position: relative;
  padding: 100px 16px 40px ;
  background-color: white ;
  color: #103056;
  overflow: hidden;
  max-width: 900px;
  margin: auto ;
}

.propos-container {
  position: relative;
  z-index: 1;
  text-align: center;
}

.propos-container h2 {
  color: #FA6E89;
  font-weight: 700;
  margin-bottom: 32px;
  font-size: 24px ;
}

.illustration img {
  width: 100%;
  max-width: 550px ;
  margin: auto ;
}

.propos-texte {
  text-align: left;
  margin-top: 32px;
  padding: 0 16px ;
}

.propos-texte p {
  font-size: 16px ;
  line-height: 1.8;
  margin-bottom: 24px ;
}

@media screen and (max-width: 768px){
  .propos-page {
    padding: 100px 16px 20px;
  }

  .propos-container h2 {
    font-size: 20px;
    margin-bottom: 24px;
  }

  .illustration img {
    max-width: 320px ;
  }

  .propos-texte {
    padding: 0;
    text-align: center;
  }

  .propos-texte p {
    font-size: 14px ;
    margin-bottom: 20px;
    line-height: 1.6;
  }
}
</style>
<script setup>

</script>