package application;

import domain.Event;
import domain.EventId;

import java.util.ArrayList;
import java.util.List;

public class CalendarManager {
    private final List<Event> evenements;

    public CalendarManager() {
        this.evenements = new ArrayList<>();
    }

    public void ajouterEvenement(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("L'événement ne peut pas être null.");
        }
        evenements.add(event);
    }

    public void supprimerEvenement(EventId id) {
        evenements.removeIf(event -> event.getId().equals(id));
    }

    public List<Event> getEvenements() {
        return new ArrayList<>(evenements); // Retourne une copie pour préserver l'encapsulation.
    }
}
