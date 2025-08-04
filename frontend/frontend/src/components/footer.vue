<template>
  <footer class="footer">
    <div class="top-footer">

      <div class="section-logo">
        <img src="@/assets/logo-removebg-preview.png" alt="logo" class="logo" />
      </div>

      <div class="section-milieu">
        <h3>Un besoin ? une question ?</h3>
        <div class="links">
          <a href="#">Questions Fréquement Posées</a>
          <span>|</span>
          <a href="#">A propos</a>
          <span>|</span>
          <a href="#">Nous contacter</a>
        </div>
      </div>

      <div class="section-reseaux">
        <h3>Suivez-nous :</h3>
        <div class="reseaux-icons">
          <img src="@/assets/facebook%20(1).png" alt="Facebook" />
          <img src="@/assets/instagram%20(1).png" alt="Instagram" />
        </div>
      </div>
    </div>

    <div class="bas-footer">
      <p>Réalisé avec ♡ par Nour El Imene Sahi</p>
      <p>© 2025 Corthreads - Tous droits réservés</p>
    </div>
  </footer>
</template>

<style scoped>

.footer {
  background-color: white;
  color: #103056;
  display: flex;
  flex-direction: column;
  align-items: center ;
  border-top: 1px solid #D0D0D0 ;
}

.top-footer {
  width: 100% ;
  max-width: 1200px ;
  padding: 32px ;
  display: flex ;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
}

.section-logo {
  display: flex ;
  align-items: center;
  gap: 10px ;
}

.logo {
  height: 80px ;
}

.section-milieu{
  text-align: left;
}

.section-milieu h3 {
  font-weight: 600;
  margin-bottom: 8px ;
}

.links {
  display: flex ;
  gap: 10px ;
  flex-wrap: wrap;
  justify-content: center;
}

.links a {
  color : #FA6E89;
  text-decoration: none;
  font-weight: 600;
}

.links span {
  color : #FA6E89;
  text-decoration: none;
  font-weight: 600;
}

.section-reseaux {
  text-align: center;
}

.section-reseaux h3 {
  margin-bottom : 8px ;
}

.reseaux-icons {
  display: flex ;
  justify-content: center;
  gap: 10px ;
}

.reseaux-icons img {
  width: 30px ;
  height: 30px;
  cursor: pointer;
}

.bas-footer {
  width: 100% ;
  background-color: #FA6E89;
  color: white ;
  gap : 6px ;
  text-align: center;
  padding: 16px 0 ;
}

.bas-footer p:first-child {
  margin-bottom: 4px;
  font-size: 14px;
  transform: translateY(-4px);
}

.bas-footer p:last-child {
  font-size: 13px;
  transform: translateY(4px);
}

@media (max-width: 768px) {
  .top-footer {
    flex-direction: column;
    align-items: center;
    gap: 24px ;
    padding: 24px 16px ;
    text-align: center;
  }

  .section-logo {
    justify-content: center;
  }

  .logo {
    height: 70px
  }

  .section-milieu h3,
  .section-reseaux h3 {
    font-size: 16px ;
  }

  .links {
    flex-direction: column;
    gap: 6px;
  }

  .links span {
    display: none;
  }

  .reseaux-icons {
    gap: 16px ;
  }

  .bas-footer {
    padding: 20px 10px ;
    font-size: 14px ;
  }
}

</style>
<script setup>
</script>
