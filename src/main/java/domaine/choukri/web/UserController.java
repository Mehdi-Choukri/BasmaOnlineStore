package domaine.choukri.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import domaine.choukri.entites.Utilisateur;
import domaine.choukri.service.AccountService;
 

@RestController
public class UserController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/register")
	public Utilisateur register(@RequestBody UserForm userform) {
		return accountService.saveUser(userform.getUserName(), userform.getPassword(), userform.getConfirmPassword());
	}

}

 
class UserForm {
	private String userName;
	private String password;
	private String confirmPassword;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}