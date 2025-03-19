package domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testInstanciationSousClasses() {
        EventId id = new EventId(UUID.randomUUID());
        Titre titre = new Titre("RDV chez le dentiste");
        DateEvenement date = new DateEvenement(LocalDate.of(2025, 3, 20));
        HeureDebut heure = new HeureDebut(LocalTime.of(10, 0));
        DureeEvenement duree = new DureeEvenement(30);

        RendezVous rdv = new RendezVous(id, titre, date, heure, duree);
        assertNotNull(rdv);

        Lieu lieu = new Lieu("Salle A");
        Participants participants = new Participants(List.of("Alice", "Bob"));
        Reunion reunion = new Reunion(id, titre, date, heure, duree, lieu, participants);
        assertNotNull(reunion);

        EvenementPeriodique eventPeriodique = new EvenementPeriodique(id, titre, date, heure, duree, 7);
        assertNotNull(eventPeriodique);
    }

    @Test
    void testEventAbstrait() {
        assertThrows(InstantiationException .class, () -> {
            Event.class.getDeclaredConstructor(EventId.class, Titre.class, DateEvenement.class, HeureDebut.class, DureeEvenement.class)
                    .newInstance(new EventId(UUID.randomUUID()), new Titre("Test"), new DateEvenement(LocalDate.now()), new HeureDebut(LocalTime.now()), new DureeEvenement(60));
        });
    }

    @Test
    void testConflitEntreRendezVous() {
        EventId id1 = new EventId(UUID.randomUUID());
        EventId id2 = new EventId(UUID.randomUUID());
        Titre titre = new Titre("Rendez-vous important");
        DateEvenement date = new DateEvenement(LocalDate.of(2025, 3, 25));
        HeureDebut heure1 = new HeureDebut(LocalTime.of(10, 0));
        HeureDebut heure2 = new HeureDebut(LocalTime.of(10, 30));
        DureeEvenement duree = new DureeEvenement(60);

        RendezVous rdv1 = new RendezVous(id1, titre, date, heure1, duree);
        RendezVous rdv2 = new RendezVous(id2, titre, date, heure2, duree);

        assertTrue(rdv1.chevauche(rdv2));
        assertTrue(rdv2.chevauche(rdv1));
    }

    @Test
    void testAucunConflit() {
        EventId id1 = new EventId(UUID.randomUUID());
        EventId id2 = new EventId(UUID.randomUUID());
        Titre titre = new Titre("Rendez-vous matin");
        DateEvenement date = new DateEvenement(LocalDate.of(2025, 3, 25));
        HeureDebut heure1 = new HeureDebut(LocalTime.of(8, 0));
        HeureDebut heure2 = new HeureDebut(LocalTime.of(10, 0));
        DureeEvenement duree = new DureeEvenement(60);

        RendezVous rdv1 = new RendezVous(id1, titre, date, heure1, duree);
        RendezVous rdv2 = new RendezVous(id2, titre, date, heure2, duree);

        assertFalse(rdv1.chevauche(rdv2));
        assertFalse(rdv2.chevauche(rdv1));
    }
}
