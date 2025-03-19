package domain;

import java.time.LocalDateTime;

public abstract class Event {
    protected final EventId id;
    protected final Titre titre;
    protected final DateEvenement date;
    protected final HeureDebut heure;
    protected final DureeEvenement duree;

    public Event(EventId id, Titre titre, DateEvenement date, HeureDebut heure, DureeEvenement duree) {
        if (id == null || titre == null || date == null || heure == null || duree == null) {
            throw new IllegalArgumentException("Aucun champ de l'événement ne peut être null.");
        }
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.heure = heure;
        this.duree = duree;
    }

    public abstract String getDescription();

    protected LocalDateTime getDebutDateTime() {
        return LocalDateTime.of(date.getValeur(), heure.getValeur());
    }

    protected LocalDateTime getFinDateTime() {
        return getDebutDateTime().plusMinutes(duree.getValeur());
    }

    public abstract boolean chevauche(Event autre);

    public EventId getId() {
        return id;
    }

    public Titre getTitre() {
        return titre;
    }

    public DateEvenement getDate() {
        return date;
    }

    public HeureDebut getHeure() {
        return heure;
    }

    public DureeEvenement getDuree() {
        return duree;
    }
}
