package application;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static final String FILE_PATH = "users.txt";
    private final Map<String, String> users;

    public UserManager() {
        users = new HashMap<>();
        chargerUtilisateurs();
    }

    public boolean inscrireUtilisateur(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Utilisateur déjà existant
        }
        users.put(username, password);
        sauvegarderUtilisateurs();
        return true;
    }

    public boolean verifierConnexion(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    private void sauvegarderUtilisateurs() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des utilisateurs : " + e.getMessage());
        }
    }

    private void chargerUtilisateurs() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parts = ligne.split(":");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des utilisateurs : " + e.getMessage());
        }
    }
}
