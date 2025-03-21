package application;

import domain.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CalendarManagerTest {

    @Test
    void testAjoutEvenement() {
        CalendarManager calendar = new CalendarManager();
        EventId id = new EventId(UUID.randomUUID());
        Titre titre = new Titre("Réunion projet");
        DateEvenement date = new DateEvenement(LocalDate.of(2025, 4, 10));
        HeureDebut heure = new HeureDebut(LocalTime.of(9, 0));
        DureeEvenement duree = new DureeEvenement(60);
        Lieu lieu = new Lieu("Salle B");
        Participants participants = new Participants(List.of("Alice", "Bob"));

        Reunion reunion = new Reunion(id, titre, date, heure, duree, lieu, participants);
        calendar.ajouterEvenement(reunion);

        assertTrue(calendar.getEvenements().contains(reunion));
    }

    @Test
    void testSuppressionEvenement() {
        CalendarManager calendar = new CalendarManager();
        EventId id1 = new EventId(UUID.randomUUID());
        EventId id2 = new EventId(UUID.randomUUID());
        Titre titre = new Titre("Réunion");
        DateEvenement date = new DateEvenement(LocalDate.of(2025, 4, 10));
        HeureDebut heure = new HeureDebut(LocalTime.of(9, 0));
        DureeEvenement duree = new DureeEvenement(60);
        Lieu lieu = new Lieu("Salle B");
        Participants participants = new Participants(List.of("Alice", "Bob"));

        Reunion reunion = new Reunion(id1, titre, date, heure, duree, lieu, participants);
        calendar.ajouterEvenement(reunion);
        assertTrue(calendar.getEvenements().contains(reunion));

        // Suppression de l'événement
        calendar.supprimerEvenement(id1);
        assertFalse(calendar.getEvenements().contains(reunion));

        // Tentative de suppression d'un événement non existant (ne doit pas planter)
        assertDoesNotThrow(() -> calendar.supprimerEvenement(id2));
    }
}
