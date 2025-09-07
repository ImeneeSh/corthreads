package org.example.corthreads.services;

import org.example.corthreads.models.GroupeSang;
import org.example.corthreads.models.TypePost;
import org.example.corthreads.models.TypeDon;
import org.example.corthreads.models.Urgence;
import org.example.corthreads.models.Utilisateur;
import org.example.corthreads.models.Post;
import org.springframework.stereotype.Service;
import org.example.corthreads.repositories.PostRepository ;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UtilisateurService utilisateurService;

    public PostService(PostRepository postRepository,UtilisateurService utilisateurService) {
        this.postRepository = postRepository;
        this.utilisateurService = utilisateurService;
    }

    public Post ajouterPost(Post post) {
        Utilisateur utilisateur = utilisateurService.trouverParEmail(post.getIdUser());

        if(utilisateur == null){
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID" + post.getIdUser());
        }

        post.setUtilisateur(utilisateur);

        if(post.getTypePost() == null || post.getWilaya() == null){
            throw new IllegalArgumentException("Le type du poste et la wilaya sont obligatoires !!");
        }

        if(post.getDatePublication()==null){
            post.setDatePublication(LocalDate.now());
        }

        return postRepository.save(post);
    }

    public List<Post> rechercherPost(Utilisateur utilisateurCritere,TypePost typePost ,TypeDon typeDon ,GroupeSang groupSang,Urgence urgence,String wilaya, LocalDate datePublication){
        if(utilisateurCritere != null){
            return postRepository.findByUtilisateurAttributes(utilisateurCritere.getNom(), utilisateurCritere.getPrenom(), utilisateurCritere.getWilaya());
        }
        return postRepository.findByCriteria(
                typePost,
                typeDon,
                groupSang,
                urgence,
                wilaya,
                datePublication);
    }

    public void supprimerPostId(String idPost) {
        if(!postRepository.existsById(idPost)){
            throw new IllegalArgumentException("Le post n'existe pas");
        }
        postRepository.deleteById(idPost);
    }
}
