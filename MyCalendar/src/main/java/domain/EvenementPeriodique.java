package domain;

import java.time.LocalDate;

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

    @Override
    public boolean chevauche(Event autre) {
        return !(this.getFinDateTime().isBefore(autre.getDebutDateTime()) ||
                this.getDebutDateTime().isAfter(autre.getFinDateTime()));
    }

    @Override
    public boolean estDansPeriode(DateEvenement debut, DateEvenement fin) {
        LocalDate occurrence = date.getValeur();
        while (!occurrence.isAfter(fin.getValeur())) {
            if (!occurrence.isBefore(debut.getValeur())) {
                return true;
            }
            occurrence = occurrence.plusDays(frequenceJours);
        }
        return false;
    }
}
