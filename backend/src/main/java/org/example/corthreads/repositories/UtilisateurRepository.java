package org.example.corthreads.repositories;

import org.example.corthreads.models.Genre;
import org.example.corthreads.models.GroupeSang;
import org.example.corthreads.models.Role ;
import org.example.corthreads.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository ;

import java.util.List;

@Repository //cela indique que c'est un composant spring qui gère la bdd

public interface UtilisateurRepository extends JpaRepository<Utilisateur,String> {

    /* pas besoin d'implémentation car Spring Data JPA s'en charge ,
    les méthodes de base ( save , findById et deleteById sont fournies automatiquement
     */

    List <Utilisateur> findByNomAndPrenom(String nom ,String prenom);

    List<Utilisateur> findByNom(String nom);

    List<Utilisateur> findByPrenom(String prenom);

    List<Utilisateur> findByGroupeSang(GroupeSang groupeSang);

    List<Utilisateur> findByWilaya(String wilaya);

    List<Utilisateur> findByRole(Role role);

    List<Utilisateur> findByGenre(Genre genre);

}
