package application;

import domain.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JsonManagerTest {

    @Test
    void testSauvegardeEtChargement() {
        EventId id = new EventId(UUID.randomUUID());
        Titre titre = new Titre("Réunion test");
        DateEvenement date = new DateEvenement(LocalDate.of(2025, 5, 10));
        HeureDebut heure = new HeureDebut(LocalTime.of(14, 0));
        DureeEvenement duree = new DureeEvenement(90);

        RendezVous rdv = new RendezVous(id, titre, date, heure, duree);
        JsonManager.sauvegarderEvenements(List.of(rdv));

        List<Event> evenementsCharges = JsonManager.chargerEvenements();
        assertFalse(evenementsCharges.isEmpty());
        assertEquals("Réunion test", evenementsCharges.getFirst().getTitre().valeur());
    }
}
