package domain;

public class RendezVous extends Event {
    public RendezVous(EventId id, Titre titre, DateEvenement date, HeureDebut heure, DureeEvenement duree) {
        super(id, titre, date, heure, duree);
    }

    @Override
    public String getDescription() {
        return "Rendez-vous : " + titre + " le " + date + " à " + heure + " pendant " + duree;
    }

    @Override
    public boolean chevauche(Event autre) {
        return !(this.getFinDateTime().isBefore(autre.getDebutDateTime()) ||
                this.getDebutDateTime().isAfter(autre.getFinDateTime()));
    }

    @Override
    public boolean estDansPeriode(DateEvenement debut, DateEvenement fin) {
        return !date.getValeur().isBefore(debut.getValeur()) && !date.getValeur().isAfter(fin.getValeur());
    }
}
