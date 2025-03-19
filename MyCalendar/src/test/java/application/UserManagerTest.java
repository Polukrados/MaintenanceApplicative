package application;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {

    @Test
    void testInscriptionEtConnexion() {
        UserManager userManager = new UserManager();

        String username = "testUser";
        String password = "testPass";

        // Inscription
        assertTrue(userManager.inscrireUtilisateur(username, password));

        // Connexion valide
        assertTrue(userManager.verifierConnexion(username, password));

        // Connexion invalide
        assertFalse(userManager.verifierConnexion(username, "mauvaisMotDePasse"));

        // Inscription avec un nom déjà utilisé
        assertFalse(userManager.inscrireUtilisateur(username, password));
    }
}
