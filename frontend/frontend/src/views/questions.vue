<template>
  <div class="faq-page">

    <section class="faq-hero">
      <h2>Questions Fréquemment Posées</h2>
      <h3>Envie de Poser une Question à Corthreads ?</h3>
      <img src="@/assets/questions.png" alt="illustation faq" class="faq-image" />
    </section>

    <section class="faq-liste">
      <div v-for="(item, index) in faqs" :key="index" class="faq-item">
        <button class="faq-question" :class="{ active: activeIndex === index }" @click="toggle(index)">
          {{item.question}}
          <span class="icon"><img :src="activeIndex === index ? moins : plus" alt="icon" /></span>
        </button>
          <div v-if="activeIndex === index" class="faq-reponse">
            {{ item.reponse}}
          </div>
      </div>
    </section>

    <section class="faq-footer">
      <p>D'autres questions ? n'hésitez pas à <a href="#">nous contacter</a></p>
    </section>
  </div>
</template>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Quicksand:wght@400;500;700&display=swap');

.faq-page {
  font-family: 'Quicksand', sans-serif;
  color: #103056;
  max-width: 90% ;
  margin: auto;
  padding: 85px 32px 32px;
}

.faq-hero {
  text-align: center;
  margin-bottom: 32px;
}

.faq-hero h2 {
  color: #FA6E89;
  font-weight: 700;
  margin-bottom: 8px;
  font-size: 24px ;
}

.faq-hero h3 {
  font-family: 'Quicksand', sans-serif;
  font-weight: 600;
  font-size: 32px ;
  margin-bottom: 4px ;
}

.faq-image {
  max-width: 500px ;
  margin-bottom : 32px ;
  margin-top: 0 ;
}

.faq-liste {
  margin-bottom: 48px ;
}

.faq-item {
  border: 1px solid #D0D0D0;
  border-radius: 8px;
  margin-bottom: 16px;
  overflow: hidden;
}

.faq-question {
  background-color: white ;
  font-family: 'Quicksand', sans-serif;
  color: #103056;
  font-weight: 600 ;
  width: 100% ;
  text-align: left;
  padding: 24px ;
  border: none ;
  display: flex ;
  justify-content: space-between;
  align-items: center;
  cursor: pointer;
  font-size: 18px ;
}

.faq-question.active {
  color: #FA6E89;
}

.faq-reponse {
  padding: 16px ;
  font-family: 'Quicksand', sans-serif;
  background-color: white ;
  color: #929090;
  font-size: 16px ;
  font-weight: 500 ;
}

.icon img {
  width: 15px ;
  height: 15px ;
}

.faq-footer {
  text-align: center;
  padding: 24px ;
}

.faq-footer a {
  text-decoration: none;
}

.faq-footer p a {
  color: #FA6E89;
  font-weight: 700;
  font-family: 'Quicksand', sans-serif;
}

@media screen and (max-width: 768px){
  .faq-page {
    padding: 85px 16px 16px ;
    max-width: 100% ;
  }

  .faq-hero h2 {
    font-size: 18px ;
  }

  .faq-hero h3 {
    font-size: 22px ;
  }

  .faq-image {
    max-width: 100% ;
    height: auto ;
  }

  .faq-question {
    paddding: 16px ;
    font-size: 16px ;
  }

  .faq-reponse {
    font-size: 15px ;
    padding: 12px 16px ;
  }

  .icon img {
    width: 14px ;
    height: 14px ;
  }

  .faq-footer {
    font-size: 14px ;
    padding: 16px ;
  }

  .faq-footer p {
    line-height: 1.4 ;
  }
}
</style>
<script setup>
import {ref} from 'vue' ;
import plus from "@/assets/symbole-epais-daddition.png" ;
import moins from "@/assets/signe-moins-dune-ligne-en-position-horizontale.png" ;

const faqs = [
  {
    question: "Qu’est-ce que Carthreads ?",
    reponse:
    "Carthreads est une plateforme solidaire en Algérie qui connecte donneurs et receveurs de sang de manière anonyme et sécurisée.",
  },
  {
    question: "Qui peut s’inscrire sur la plateforme Corthreads ?",
    reponse:
    "Tout citoyen résident en Algérie majeur peut s’inscrire pour devenir donneur ou receveur. Les professionnels de santé (médecins, spécialistes…) peuvent également s’inscrire pour encadrer le processus de don et valider les demandes.",
  },
  {
    question: "Mes informations personnelles sont-elles sécurisées ?",
    reponse:
    "Oui. Toutes les données collectées (nom, groupe sanguin, etc.) sont strictement confidentielles, cryptées et ne seront jamais partagées à des fins commerciales ou non médicales.",
  },
  {
    question: "Comment fonctionne le don d’organe sur la plateforme ?",
    reponse:
    "Le don d’organe se fait principalement après le décès (sauf pour le foie, possible du vivant). Si vous vous portez volontaire, un médecin vous contactera par mail pour organiser une visite médicale avant toute validation.",
  },
  {
    question:"Puis-je faire un don de sang directement depuis la plateforme ?",
    reponse:
    "Non, mais vous pouvez manifester votre volonté de donner. Vous devrez remplir un questionnaire de santé, puis un médecin validera votre admissibilité et vous ajoutera à la liste des donneurs disponibles.",
  },
  {
    question: "Comment publier un appel à don ou un témoignage ?",
    reponse:
    "Une fois inscrit et connecté, vous pouvez créer un poste soit pour un appel au don (avec les détails nécessaires : type de don, urgence, groupe sanguin...), soit pour un témoignage inspirant ou informatif.",
  },
  {
    question: "Pourquoi suis-je contacté par un médecin après avoir rempli un formulaire ?",
    reponse:
    "Pour garantir la fiabilité et la sécurité des dons, chaque inscription ou demande de don est évaluée médicalement. Un professionnel vous contactera pour un suivi, notamment pour organiser une visite ou valider votre statut.",
  },
]

const activeIndex = ref(null)

function toggle(index) {
  activeIndex.value = activeIndex.value === index ? null : index
}
</script>