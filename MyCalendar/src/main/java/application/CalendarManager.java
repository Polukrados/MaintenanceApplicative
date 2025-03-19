package application;

import domain.Event;
import domain.EventId;

import java.util.ArrayList;
import java.util.List;

public class CalendarManager {
    private final List<Event> evenements;

    public CalendarManager() {
        this.evenements = new ArrayList<>(JsonManager.chargerEvenements());
    }

    public void ajouterEvenement(Event event) {
        evenements.add(event);
        JsonManager.sauvegarderEvenements(evenements);
    }

    public void supprimerEvenement(EventId id) {
        evenements.removeIf(event -> event.getId().equals(id));
        JsonManager.sauvegarderEvenements(evenements);
    }

    public List<Event> getEvenements() {
        return new ArrayList<>(evenements);
    }
}
