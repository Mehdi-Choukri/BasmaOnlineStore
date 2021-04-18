package domaine.choukri.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import domaine.choukri.entites.Role;


@RepositoryRestResource
public interface RoleRepository  extends JpaRepository<Role, Long>{

	
	public Role findByroleName(String desc);
}
