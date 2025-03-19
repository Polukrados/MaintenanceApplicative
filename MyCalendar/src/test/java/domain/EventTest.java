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

    @Test
    void testDescriptionRendezVous() {
        EventId id = new EventId(UUID.randomUUID());
        Titre titre = new Titre("Consultation médicale");
        DateEvenement date = new DateEvenement(LocalDate.of(2025, 4, 15));
        HeureDebut heure = new HeureDebut(LocalTime.of(14, 0));
        DureeEvenement duree = new DureeEvenement(30);

        RendezVous rdv = new RendezVous(id, titre, date, heure, duree);
        assertEquals("Rendez-vous : Consultation médicale le 2025-04-15 à 14:00 pendant 30 minutes", rdv.getDescription());
    }

    @Test
    void testDescriptionReunion() {
        EventId id = new EventId(UUID.randomUUID());
        Titre titre = new Titre("Réunion d'équipe");
        DateEvenement date = new DateEvenement(LocalDate.of(2025, 4, 20));
        HeureDebut heure = new HeureDebut(LocalTime.of(10, 0));
        DureeEvenement duree = new DureeEvenement(90);
        Lieu lieu = new Lieu("Salle 101");
        Participants participants = new Participants(List.of("Alice", "Bob"));

        Reunion reunion = new Reunion(id, titre, date, heure, duree, lieu, participants);
        assertEquals("Réunion : Réunion d'équipe le 2025-04-20 à 10:00 pendant 90 minutes au Salle 101 avec Alice, Bob", reunion.getDescription());
    }

    @Test
    void testDescriptionEvenementPeriodique() {
        EventId id = new EventId(UUID.randomUUID());
        Titre titre = new Titre("Séance de sport");
        DateEvenement date = new DateEvenement(LocalDate.of(2025, 4, 25));
        HeureDebut heure = new HeureDebut(LocalTime.of(18, 0));
        DureeEvenement duree = new DureeEvenement(60);

        EvenementPeriodique event = new EvenementPeriodique(id, titre, date, heure, duree, 7);
        assertEquals("Événement périodique : Séance de sport tous les 7 jours à partir du 2025-04-25", event.getDescription());
    }

    @Test
    void testEvenementDansPeriode() {
        EventId id = new EventId(UUID.randomUUID());
        Titre titre = new Titre("Déjeuner d'affaire");
        DateEvenement date = new DateEvenement(LocalDate.of(2025, 4, 10));
        HeureDebut heure = new HeureDebut(LocalTime.of(12, 0));
        DureeEvenement duree = new DureeEvenement(90);

        RendezVous rdv = new RendezVous(id, titre, date, heure, duree);

        DateEvenement debutPeriode = new DateEvenement(LocalDate.of(2025, 4, 1));
        DateEvenement finPeriode = new DateEvenement(LocalDate.of(2025, 4, 15));

        assertTrue(rdv.estDansPeriode(debutPeriode, finPeriode));
    }

    @Test
    void testEvenementHorsPeriode() {
        EventId id = new EventId(UUID.randomUUID());
        Titre titre = new Titre("Voyage d'affaires");
        DateEvenement date = new DateEvenement(LocalDate.of(2025, 5, 10));
        HeureDebut heure = new HeureDebut(LocalTime.of(9, 0));
        DureeEvenement duree = new DureeEvenement(120);

        RendezVous rdv = new RendezVous(id, titre, date, heure, duree);

        DateEvenement debutPeriode = new DateEvenement(LocalDate.of(2025, 4, 1));
        DateEvenement finPeriode = new DateEvenement(LocalDate.of(2025, 4, 30));

        assertFalse(rdv.estDansPeriode(debutPeriode, finPeriode));
    }
}
