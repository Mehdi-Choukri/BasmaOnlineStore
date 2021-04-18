package domaine.choukri.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import domaine.choukri.entites.Utilisateur;

@RepositoryRestResource
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long>{

	// public Utilisateur findByemail(String email);
	 public Utilisateur findByuserName(String username);
	
}
