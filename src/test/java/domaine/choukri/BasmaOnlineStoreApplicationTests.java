package domaine.choukri;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import domaine.choukri.dao.UtilisateurRepository;
import domaine.choukri.entites.Utilisateur;

@SpringBootTest
class BasmaOnlineStoreApplicationTests {

	@Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    void saveUserTest() {
        utilisateurRepository.save(new Utilisateur("choukri@gmail.com", "ABCD", false, new ArrayList<>()));
        assertThat(utilisateurRepository.findByuserName("choukri@gmail.com")).isNotNull();
    }
    
    @Test
    void updateUserTest() {
        Utilisateur user = utilisateurRepository.findByuserName("choukri@gmail.com");
        if(user!=null) {
            user.setActive(true);
            utilisateurRepository.save(user);
            Utilisateur user1 = utilisateurRepository.findByuserName("choukri@gmail.com");
            assertThat(true).isEqualTo(user1.getActive());
        }
    }
   

    @Test
    void deleteUserTest() {
        Utilisateur user = utilisateurRepository.findByuserName("choukri@gmail.com");
        if(user!=null) {
            utilisateurRepository.delete(user);
            assertThat(utilisateurRepository.findByuserName("choukri@gmail.com")).isNull();
        }
    }

    @Test
    void getAllUserTest() {
        List<Utilisateur> users = utilisateurRepository.findAll();
        assertThat(0).isNotEqualTo(users.size());
    }
 
}
