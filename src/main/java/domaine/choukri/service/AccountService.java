package domaine.choukri.service;

import domaine.choukri.entites.Role;
import domaine.choukri.entites.Utilisateur;

public interface AccountService {
	
	public Utilisateur saveUser(String email,String password,String confirmedPassword);
	public Role save(Role role);
	public Utilisateur loadUserByemail(String email);
	public Utilisateur loadUserByUsername(String email);
	public void addRoleToUser(String email,String roleDescription);

}
