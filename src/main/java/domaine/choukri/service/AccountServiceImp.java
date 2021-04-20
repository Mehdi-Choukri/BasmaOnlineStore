package domaine.choukri.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domaine.choukri.dao.RoleRepository;
import domaine.choukri.dao.UtilisateurRepository;
import domaine.choukri.entites.Role;
import domaine.choukri.entites.Utilisateur;

@Service
@Transactional
public class AccountServiceImp implements AccountService{
	
	@Autowired
	private UtilisateurRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//ce constructeur fait la même chose que l'annotation autowired
	//il fait l'injection des dependances dans l'application

	public AccountServiceImp(UtilisateurRepository userRepository, RoleRepository roleRepository , BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public Utilisateur saveUser(String userName, String password, String confirmedPassword) {

		Utilisateur user = userRepository.findByuserName(userName);
		if(user != null ) throw new RuntimeException("User  exists already");
		if(!password.equals(confirmedPassword)) throw new RuntimeException("Your password doesn't match the password confirmation");

		Utilisateur userTosave = new Utilisateur();
		
		userTosave.setuserName(userName);
		userTosave.setPassword(bCryptPasswordEncoder.encode(password));
		userRepository.save(userTosave);
		addRoleToUser(userName,"USER");
		
		return userTosave;
		
	}

	@Override
	public Role save(Role role) {
	 
		return roleRepository.save(role);
	}

	@Override
	public Utilisateur loadUserByemail(String userName) {
	 
		return userRepository.findByuserName(userName);
	}

	@Override
	public void addRoleToUser(String userName, String roleDescription) {
	 Utilisateur user = userRepository.findByuserName(userName);
	 
	 Role role = roleRepository.findByroleName(roleDescription);
	 
	 user.getRoles().add(role);
	 
	}

	@Override
	public Utilisateur loadUserByUsername(String userName) {
		 
		return userRepository.findByuserName(userName); 
	}

	@Override
	public void deleteUser(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utilisateur updateUser(String userName, String password, String confirmedPassword) {
		Utilisateur user = loadUserByUsername( userName);
		if(user != null )
		{
			if(!password.equals(confirmedPassword)) { throw new RuntimeException("Your password doesn't match the password confirmation");}
			else {
				Utilisateur userTosave = userRepository.findByuserName(userName);
				
				userTosave.setuserName(userName);
				userTosave.setPassword(bCryptPasswordEncoder.encode(password));
				userRepository.save(userTosave);
				
			}
			 
		}
		else
		{
			throw new RuntimeException("User  do not exist  ");
		}
		
		
		
		return null;
	}

}
