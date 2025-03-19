package domain;

public class RendezVous extends Event {
    public RendezVous(EventId id, Titre titre, DateEvenement date, HeureDebut heure, DureeEvenement duree) {
        super(id, titre, date, heure, duree);
    }

    @Override
    public String getDescription() {
        return "Rendez-vous : " + titre + " le " + date + " à " + heure + " pendant " + duree;
    }
}
