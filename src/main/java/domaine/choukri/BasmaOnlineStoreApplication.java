package domaine.choukri;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import domaine.choukri.entites.Role;
import domaine.choukri.service.AccountService;

@SpringBootApplication
public class BasmaOnlineStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasmaOnlineStoreApplication.class, args);
	}
	 
	@Bean
	CommandLineRunner start(AccountService accountService)
	{ 
		return args->{
			accountService.save(new Role("USER"));
			accountService.save(new Role("ADMIN"));
			Stream.of("utilisateur1.0","utilisateur2.0","utilisateur3.0","admin").forEach(un->{
				accountService.saveUser(un, "ABCD", "ABCD");
			});
			accountService.addRoleToUser("admin", "ADMIN");
		};
	}
	@Bean
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
	 

}
