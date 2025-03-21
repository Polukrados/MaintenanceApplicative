package domain;

public class Reunion extends Event {
    private final Lieu lieu;
    private final Participants participants;

    public Reunion(EventId id, Titre titre, DateEvenement date, HeureDebut heure, DureeEvenement duree, Lieu lieu, Participants participants) {
        super(id, titre, date, heure, duree);
        if (lieu == null || participants == null) {
            throw new IllegalArgumentException("Lieu et Participants ne peuvent pas être null.");
        }
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    public String getDescription() {
        return "Réunion : " + titre + " le " + date + " à " + heure + " pendant " + duree + " au " + lieu + " avec " + participants;
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
