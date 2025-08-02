package org.example.corthreads.repositories;

import org.example.corthreads.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository ;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface PostRepository extends JpaRepository<Post,String> {

    @Query("SELECT p FROM Post p WHERE " +
            "(:nom IS NULL OR p.utilisateur.nom = :nom) AND " +
            "(:prenom IS NULL OR p.utilisateur.prenom = :prenom) AND " +
            "(:wilaya IS NULL OR p.utilisateur.wilaya = :wilaya)")
    List<Post> findByUtilisateurAttributes(
            @Param("nom") String nom,
            @Param("prenom") String prenom,
            @Param("wilaya") String wilaya
    );

    @Query("SELECT p FROM Post p WHERE " +
            "(:typePost IS NULL OR p.typePost = :typePost) AND " +
            "(:typeDon IS NULL OR p.typeDon = :typeDon) AND " +
            "(:groupSang IS NULL OR p.groupSang = :groupSang) AND " +
            "(:urgence IS NULL OR p.urgence = :urgence) AND " +
            "(:wilaya IS NULL OR p.wilaya = :wilaya) AND " +
            "(:datePublication IS NULL OR p.datePublication = :datePublication)")
    List<Post> findByCriteria(
            @Param("typePost") TypePost typePost,
            @Param("typeDon") TypeDon typeDon,
            @Param("groupSang") GroupeSang groupSang,
            @Param("urgence") Urgence urgence,
            @Param("wilaya") String wilaya,
            @Param("datePublication") LocalDate datePublication);
}
