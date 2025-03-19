package application;

import domain.*;
import domain.Event;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

public class CalendarUI {
    private final CalendarManager calendarManager;
    private final JTextArea eventsDisplay;
    private final UserManager userManager;

    public CalendarUI() {
        calendarManager = new CalendarManager();

        userManager = new UserManager();
        String utilisateur = demanderConnexion();

        if (utilisateur == null) {
            System.exit(0); // Quitte l'application si l'utilisateur annule la connexion
        }

        JFrame frame = new JFrame("Gestionnaire d'Événements - Connecté : " + utilisateur);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        eventsDisplay = new JTextArea();
        eventsDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(eventsDisplay);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3));

        JButton addEventButton = new JButton("Ajouter RDV");
        addEventButton.addActionListener(e -> ajouterRendezVous());

        JButton addMeetingButton = new JButton("Ajouter Réunion");
        addMeetingButton.addActionListener(e -> ajouterReunion());

        JButton addPeriodicButton = new JButton("Ajouter Événement Périodique");
        addPeriodicButton.addActionListener(e -> ajouterEvenementPeriodique());

        JButton deleteEventButton = new JButton("Supprimer Événement");
        deleteEventButton.addActionListener(e -> supprimerEvenement());

        JButton refreshButton = new JButton("Actualiser");
        refreshButton.addActionListener(e -> afficherEvenements());

        panel.add(addEventButton);
        panel.add(addMeetingButton);
        panel.add(addPeriodicButton);
        panel.add(deleteEventButton);
        panel.add(refreshButton);

        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);

        afficherEvenements();
    }

    private String demanderConnexion() {
        while (true) {
            String[] options = {"Se connecter", "Créer un compte", "Quitter"};
            int choix = JOptionPane.showOptionDialog(null, "Bienvenue !",
                    "Connexion", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            if (choix == 2) return null; // Quitter l'application

            String username = JOptionPane.showInputDialog("Nom d'utilisateur :");
            String password = JOptionPane.showInputDialog("Mot de passe :");

            if (choix == 0) { // Connexion
                if (userManager.verifierConnexion(username, password)) {
                    return username;
                } else {
                    JOptionPane.showMessageDialog(null, "Identifiants incorrects !");
                }
            } else if (choix == 1) { // Création de compte
                if (userManager.inscrireUtilisateur(username, password)) {
                    JOptionPane.showMessageDialog(null, "Compte créé avec succès !");
                } else {
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur déjà pris !");
                }
            }
        }
    }


    private void ajouterEvenementPeriodique() {
        JTextField titreField = new JTextField();
        JTextField dateField = new JTextField("AAAA-MM-JJ");
        JTextField heureField = new JTextField("HH:MM");
        JTextField dureeField = new JTextField("Durée en minutes");
        JTextField frequenceField = new JTextField("Fréquence en jours");

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Titre:"));
        panel.add(titreField);
        panel.add(new JLabel("Date de début:"));
        panel.add(dateField);
        panel.add(new JLabel("Heure de début:"));
        panel.add(heureField);
        panel.add(new JLabel("Durée:"));
        panel.add(dureeField);
        panel.add(new JLabel("Fréquence (jours):"));
        panel.add(frequenceField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Ajouter un Événement Périodique",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                EventId id = new EventId(UUID.randomUUID());
                Titre titre = new Titre(titreField.getText());
                DateEvenement date = new DateEvenement(LocalDate.parse(dateField.getText()));
                HeureDebut heure = new HeureDebut(LocalTime.parse(heureField.getText()));
                DureeEvenement duree = new DureeEvenement(Integer.parseInt(dureeField.getText()));
                int frequenceJours = Integer.parseInt(frequenceField.getText());

                EvenementPeriodique event = new EvenementPeriodique(id, titre, date, heure, duree, frequenceJours);
                calendarManager.ajouterEvenement(event);
                afficherEvenements();
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Format de date/heure invalide (Attendu: AAAA-MM-JJ et HH:MM) !");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Les champs Durée et Fréquence doivent être des nombres !");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erreur de saisie. Vérifiez vos données !");
            }
        }
    }


    private void afficherEvenements() {
        List<Event> evenements = calendarManager.getEvenements();
        eventsDisplay.setText("");
        if (evenements.isEmpty()) {
            eventsDisplay.append("Aucun événement disponible.\n");
        } else {
            for (Event e : evenements) {
                eventsDisplay.append(e.getId().valeur() + " - " + e.getDescription() + "\n");
            }
        }
    }

    private void ajouterRendezVous() {
        JTextField titreField = new JTextField();
        JTextField dateField = new JTextField("AAAA-MM-JJ");
        JTextField heureField = new JTextField("HH:MM");
        JTextField dureeField = new JTextField("Durée en minutes");

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Titre:"));
        panel.add(titreField);
        panel.add(new JLabel("Date:"));
        panel.add(dateField);
        panel.add(new JLabel("Heure:"));
        panel.add(heureField);
        panel.add(new JLabel("Durée:"));
        panel.add(dureeField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Ajouter un Rendez-vous",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                EventId id = new EventId(UUID.randomUUID());
                Titre titre = new Titre(titreField.getText());
                DateEvenement date = new DateEvenement(LocalDate.parse(dateField.getText()));
                HeureDebut heure = new HeureDebut(LocalTime.parse(heureField.getText()));
                DureeEvenement duree = new DureeEvenement(Integer.parseInt(dureeField.getText()));

                RendezVous rdv = new RendezVous(id, titre, date, heure, duree);
                calendarManager.ajouterEvenement(rdv);
                afficherEvenements();
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Format de date/heure invalide (Attendu: AAAA-MM-JJ et HH:MM) !");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La durée doit être un nombre !");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erreur de saisie. Vérifiez vos données !");
            }
        }
    }

    private void ajouterReunion() {
        JTextField titreField = new JTextField();
        JTextField dateField = new JTextField("AAAA-MM-JJ");
        JTextField heureField = new JTextField("HH:MM");
        JTextField dureeField = new JTextField("Durée en minutes");
        JTextField lieuField = new JTextField();
        JTextField participantsField = new JTextField("Alice,Bob");

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Titre:"));
        panel.add(titreField);
        panel.add(new JLabel("Date:"));
        panel.add(dateField);
        panel.add(new JLabel("Heure:"));
        panel.add(heureField);
        panel.add(new JLabel("Durée:"));
        panel.add(dureeField);
        panel.add(new JLabel("Lieu:"));
        panel.add(lieuField);
        panel.add(new JLabel("Participants (séparés par virgule):"));
        panel.add(participantsField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Ajouter une Réunion",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                EventId id = new EventId(UUID.randomUUID());
                Titre titre = new Titre(titreField.getText());
                DateEvenement date = new DateEvenement(LocalDate.parse(dateField.getText()));
                HeureDebut heure = new HeureDebut(LocalTime.parse(heureField.getText()));
                DureeEvenement duree = new DureeEvenement(Integer.parseInt(dureeField.getText()));
                Lieu lieu = new Lieu(lieuField.getText());
                Participants participants = new Participants(List.of(participantsField.getText().split(",")));

                Reunion reunion = new Reunion(id, titre, date, heure, duree, lieu, participants);
                calendarManager.ajouterEvenement(reunion);
                afficherEvenements();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erreur de saisie !");
            }
        }
    }

    private void supprimerEvenement() {
        String idString = JOptionPane.showInputDialog("Entrez l'ID de l'événement à supprimer:");
        if (idString != null) {
            try {
                EventId id = new EventId(UUID.fromString(idString));
                calendarManager.supprimerEvenement(id);
                afficherEvenements();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ID invalide !");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalendarUI::new);
    }
}
