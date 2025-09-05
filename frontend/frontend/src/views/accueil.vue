<template>
  <div class="accueil-container">

    <!-- popup de bienvenu -->

    <PopupSucces v-if="showPopup" :titre="popupTitre" :message="popupMessage" :illustration="celebrationImg" @fermer="closePopup" />

    <!-- section hero -->
    <section class="hero">
      <img src="@/assets/bannerr.jpg" alt="Ruban du don" class="hero-bg" />
      <div class="hero-content">
        <h1 class="hero-titre">"Les liens du coeur"</h1>
        <p class="hero-subtitle">
          Témoignez , partager et préservez <br /> les liens du don
        </p>
        <button class="cta-button premier">
          Découvrir plus
        <img src="@/assets/fleche-droite.png" alt="flèche" class="fleche-icon" />
        </button>
      </div>
    </section>

    <!-- section témoigange -->
  <section class="section-temoignage" data-aos="fade-up">
    <h2>Découvrez des témoignages touchants</h2>
    <div class="temoignage-content">
      <img src="@/assets/papotage.png" alt="illustration témoignage" />
      <blockquote>
        <em>
          “ Je suis Anne-Sophie, maman de Lucas, insuffisant rénal... Lucas, de par sa pathologie, a pu bénéficier de plusieurs transfusions sanguines et c'est grâce à ça qu'il continue de se battre au quotidien. Aujourd'hui, il fête son 6e anniversaire de greffe. “
        </em>
        <cite>— Anne-Sophie, mère de Lucas 8 ans</cite>
      </blockquote>
    </div>
    <button class="cta-button deuxieme">
      <router-link to="/temoignage" class="link">Découvrir plus</router-link>
      <img src="@/assets/fleche-droite.png" alt="flèche" class="fleche-icon" />
    </button>
  </section>

    <!-- section appel à dons -->
    <section class="section-dons" data-aos="fade-down">
      <div class="dons-header">
        <h2>Faites des appels à <br/> dons et sauvez des <br/> vies !</h2>
        <img src="@/assets/talking.png" alt="illustration appel à dons" />
      </div>
      <button class="cta-button troisieme">
        <router-link to="/appelDon" class="link">Découvrir plus</router-link>
        <img src="@/assets/fleche-droite.png" alt="flèche" class="fleche-icon" />
      </button>
    </section>

  </div>

</template>

<style scoped>

.accueil-container {
  display: flex;
  flex-direction: column;
  width: 100%;
  overflow-x: hidden;
  padding-top: 85px ;
}

/* section hero*/
.hero {
  position: relative;
  width: 100%;
  height: 480px;
  overflow: hidden;
  display: flex;
  align-items: center ;
  justify-content: center;
}

.hero-bg {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.hero-content {
  position: absolute;
  text-align: left;
  color: #103056;
  padding-right: 500px ;
  margin-bottom: 80px ;
}

.hero-titre {
  font-family: 'Playfair Display', serif;
  font-size: 75px;
  margin-bottom: 16px;
}

.hero-subtitle {
  font-size: 29px;
  margin-bottom: 24px;
  font-weight: 500;
}

.cta-button {
  display: inline-flex;
  align-items: center;
  gap: 10px ;
  padding: 10px 40px;
  background-color: #FA6E89;
  border: none;
  border-radius: 24px;
  color: white;
  font-weight: bold;
  cursor: pointer;
  font-size: 14px;
  transition: background-color 0.3s ease;
}

.link {
  color: inherit;
  text-decoration: none;
  font: inherit;
}

.cta-button.premier {
  margin-top: 30px ;
}

.cta-button.deuxieme {
  margin-left: 200px ;
  margin-bottom: 60px ;
}

.cta-button.troisieme {
  margin-left: 95px ;
  margin-bottom: 60px ;
}

.cta-button:hover {
  background-color: #103056;
}

.fleche-icon {
  height: 16px;
  width: 16px;
  vertical-align: middle;
  margin-left:6px ;
  transform: translateY(1px);
}

/*section témoignage*/

.section-temoignage {
  padding: 20px 20px;
  text-align: center;
  border-bottom: 1px solid #D0D0D0;
}

.section-temoignage h2 {
  color: #103056;
  font-weight: bold;
  font-size: 48px;
  margin-bottom: 32px;
}

.temoignage-content {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 50px ;
  max-width: 1000px;
  margin : 5px auto 24px auto;
  text-align: left ;
}

.temoignage-content img {
  max-width: 400px;
}

blockquote {
  font-style: italic;
  color: black;
  font-size: 18px;
  line-height: 1.5;
}

cite {
  display: block;
  margin-top: 10px;
  font-style: normal;
  font-weight: 600;
  color: #103056;
}

/*section dons*/

.section-dons {
  padding: 20px 20px;
  text-align: left;
  border-bottom: 1px solid #D0D0D0;
}

.dons-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 40px ;
  padding: 0 40px;
}

.dons-header h2{
  color: #103056;
  font-weight: bold;
  font-size: 48px;
  margin-bottom: 32px;
  margin-left: 60px ;
}

.dons-header img{
  max-width: 600px;
  margin-bottom: 24px;
}

router-link {
  text-decoration: none;

}

/* section popup */


@media (max-width: 768px){

  .accueil-container {
    padding-top: 70px;
  }

  /* section hero */

  .hero {
    height: 320px  ;
    justify-content: center;
    align-items: center;
  }

  .hero-content {
    position: absolute;
    text-align: center;
    padding: 50px 20px 0;
  }

  .hero-titre {
    font-size: 32px ;
    margin-bottom: 12px;
  }

  .hero-subtitle {
    font-size: 18px ;
    margin-bottom: 20px;
  }

  .cta-button.premier {
    margin-top: 10px;
  }

  .hero-bg {
    height: 100% ;
    object-fit: cover;
  }

  /* section témoignage*/

  .section-temoignage h2{
    font-size: 24px ;
  }

  .temoignage-content {
    flex-direction: column;
    align-items: center;
    text-align: center;
    padding: 0 16px;
  }

  .temoignage-content img {
    max-width: 100%;
  }

  blockquote {
    font-size: 16px;
  }

  .cta-button.deuxieme {
    margin: 20px auto 40px auto;
    display: block;
  }

  /* section dons */

  .dons-header {
    flex-direction: column;
    padding: 0 ;
    text-align: center;
  }

  .dons-header h2 {
    font-size: 24px;
    margin: 20px 0;
  }

  .dons-header img {
    max-width: 80%;
    height: auto;
    margin-bottom: 20px ;
  }

  .cta-button.troisieme {
    margin: 0 auto 40px auto;
    display: block;
  }

  /* les boutons */
  .cta-button {
    padding: 10px 24px;
    font-size: 14px;
  }

  .fleche-icon {
    height: 14px;
    width: 14px;
  }
}
</style>
<script setup>
import AOS from 'aos'
import 'aos/dist/aos.css'

import { ref , onMounted } from 'vue'

import PopupSucces from '@/components/popupSucces.vue'
import celebrationImg from '@/assets/celebration.png'

const nom = ref('');
const prenom = ref('');
const showPopup = ref(false);
const popupTitre = ref('');
const popupMessage = ref('');

const popupTitreStorage = sessionStorage.getItem('popupTitre');
const popupMessageStorage = sessionStorage.getItem('popupMessage');

if (popupTitreStorage && popupMessageStorage) {
  popupTitre.value = popupTitreStorage;
  popupMessage.value = popupMessageStorage;
  showPopup.value = true;

  sessionStorage.removeItem('popupTitre');
  sessionStorage.removeItem('popupMessage');
}


onMounted(() => {
  AOS.init({
    duration: 800,
    once: false,
    offset: 120,
  })

  const nomStorage = sessionStorage.getItem('nom');
  const prenomStorage = sessionStorage.getItem('prenom');
  const inscriptionReussie = sessionStorage.getItem('inscriptionReussie');

  if (inscriptionReussie) {
    popupTitre.value ="Votre inscription a été effectuée avec succès !"
    popupMessage.value = "Bienvenu chez la famille Corthreads" ;
    showPopup.value = true ;
    sessionStorage.removeItem('inscriptionReussie');
  }
  else if (nomStorage && prenomStorage) {
    nom.value = nomStorage ;
    prenom.value = prenomStorage ;
    popupTitre.value ="Votre connexion a été effectuée avec succès !"
    popupMessage.value =  `Bienvenue ${prenom.value} ${nom.value} !`;
    showPopup.value = true;
    sessionStorage.removeItem('nom');
    sessionStorage.removeItem('prenom');
  }
})

function closePopup(){
  showPopup.value = false;
}
</script>

