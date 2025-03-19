package application;

import domain.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JsonManager {
    private static final String FILE_PATH = "evenements.json";

    public static void sauvegarderEvenements(List<Event> evenements) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Event event : evenements) {
                writer.write(eventToJson(event));
                writer.newLine();
            }
            System.out.println("Événements sauvegardés !");
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des événements : " + e.getMessage());
        }
    }

    public static List<Event> chargerEvenements() {
        List<Event> evenements = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                evenements.add(jsonToEvent(ligne));
            }
        } catch (IOException e) {
            System.out.println("Aucun fichier trouvé, création d'une nouvelle liste.");
        }
        return evenements;
    }

    private static String eventToJson(Event event) {
        return event.getId().valeur() + ";" +
                event.getTitre().valeur() + ";" +
                event.getDate().getValeur() + ";" +
                event.getHeure().getValeur() + ";" +
                event.getDuree().getValeur();
    }

    private static Event jsonToEvent(String json) {
        String[] parts = json.split(";");
        EventId id = new EventId(UUID.fromString(parts[0]));
        Titre titre = new Titre(parts[1]);
        DateEvenement date = new DateEvenement(java.time.LocalDate.parse(parts[2]));
        HeureDebut heure = new HeureDebut(java.time.LocalTime.parse(parts[3]));
        DureeEvenement duree = new DureeEvenement(Integer.parseInt(parts[4]));

        return new RendezVous(id, titre, date, heure, duree);
    }
}
