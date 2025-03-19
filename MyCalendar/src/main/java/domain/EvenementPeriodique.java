package domain;

public class EvenementPeriodique extends Event {
    private final int frequenceJours;

    public EvenementPeriodique(EventId id, Titre titre, DateEvenement date, HeureDebut heure, DureeEvenement duree, int frequenceJours) {
        super(id, titre, date, heure, duree);
        if (frequenceJours <= 0) {
            throw new IllegalArgumentException("La fréquence doit être un entier positif.");
        }
        this.frequenceJours = frequenceJours;
    }

    @Override
    public String getDescription() {
        return "Événement périodique : " + titre + " tous les " + frequenceJours + " jours à partir du " + date;
    }
}
