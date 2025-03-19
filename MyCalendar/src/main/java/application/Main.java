package application;

import domain.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        CalendarManager calendar = new CalendarManager();
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
                String utilisateur = demanderConnexion(scanner,userManager);
        if (utilisateur == null) {
            System.out.println("Fermeture de l'application.");
            return;
        }

        boolean continuer = true;


        while (continuer) {
            System.out.println("\n=== Menu Gestionnaire d'Événements ===");
            System.out.println("1 - Voir les événements");
            System.out.println("2 - Ajouter un rendez-vous");
            System.out.println("3 - Ajouter une réunion");
            System.out.println("4 - Ajouter un évènement périodique");
            System.out.println("5 - Supprimer un événement");
            System.out.println("6 - Quitter");
            System.out.print("Votre choix : ");

            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    afficherEvenements(calendar);
                    break;
                case "2":
                    ajouterRendezVous(scanner, calendar);
                    break;
                case "3":
                    ajouterReunion(scanner, calendar);
                    break;
                case "4":
                    ajouterEvenementPeriodique(scanner, calendar);
                    break;
                case "5":
                    supprimerEvenement(scanner, calendar);
                    break;
                case "6":
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    private static String demanderConnexion(Scanner scanner, UserManager userManager) {
        while (true) {
            System.out.println("\n=== Connexion ===");
            System.out.println("1 - Se connecter");
            System.out.println("2 - Créer un compte");
            System.out.println("3 - Quitter");
            System.out.print("Votre choix : ");

            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    System.out.print("Nom d'utilisateur : ");
                    String username = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String password = scanner.nextLine();

                    if (userManager.verifierConnexion(username, password)) {
                        System.out.println("✅ Connexion réussie !");
                        return username;
                    } else {
                        System.out.println("❌ Identifiants incorrects !");
                    }
                    break;

                case "2":
                    System.out.print("Nom d'utilisateur : ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String newPassword = scanner.nextLine();

                    if (userManager.inscrireUtilisateur(newUsername, newPassword)) {
                        System.out.println("✅ Compte créé avec succès !");
                        return newUsername;
                    } else {
                        System.out.println("❌ Nom d'utilisateur déjà pris !");
                    }
                    break;

                case "3":
                    return null; // Quitter l'application

                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }


    private static void afficherEvenements(CalendarManager calendar) {
        List<Event> evenements = calendar.getEvenements();
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement trouvé.");
        } else {
            System.out.println("Événements :");
            for (Event e : evenements) {
                System.out.println("- " + e.getDescription());
            }
        }
    }

    private static void ajouterRendezVous(Scanner scanner, CalendarManager calendar) {
        System.out.print("Titre de l'événement : ");
        Titre titre = new Titre(scanner.nextLine());

        System.out.print("Date (AAAA-MM-JJ) : ");
        DateEvenement date = new DateEvenement(LocalDate.parse(scanner.nextLine()));

        System.out.print("Heure début (HH:MM) : ");
        HeureDebut heure = new HeureDebut(LocalTime.parse(scanner.nextLine()));

        System.out.print("Durée (minutes) : ");
        DureeEvenement duree = new DureeEvenement(Integer.parseInt(scanner.nextLine()));

        RendezVous rdv = new RendezVous(new EventId(UUID.randomUUID()), titre, date, heure, duree);
        calendar.ajouterEvenement(rdv);

        System.out.println("Rendez-vous ajouté !");
    }

    private static void ajouterReunion(Scanner scanner, CalendarManager calendar) {
        System.out.print("Titre de l'événement : ");
        Titre titre = new Titre(scanner.nextLine());

        System.out.print("Date (AAAA-MM-JJ) : ");
        DateEvenement date = new DateEvenement(LocalDate.parse(scanner.nextLine()));

        System.out.print("Heure début (HH:MM) : ");
        HeureDebut heure = new HeureDebut(LocalTime.parse(scanner.nextLine()));

        System.out.print("Durée (minutes) : ");
        DureeEvenement duree = new DureeEvenement(Integer.parseInt(scanner.nextLine()));

        System.out.print("Lieu : ");
        Lieu lieu = new Lieu(scanner.nextLine());

        System.out.print("Participants (séparés par virgules) : ");
        Participants participants = new Participants(List.of(scanner.nextLine().split(",")));

        Reunion reunion = new Reunion(new EventId(UUID.randomUUID()), titre, date, heure, duree, lieu, participants);
        calendar.ajouterEvenement(reunion);

        System.out.println("Réunion ajoutée !");
    }

    private static void ajouterEvenementPeriodique(Scanner scanner, CalendarManager calendar) {
        System.out.print("Titre de l'événement : ");
        Titre titre = new Titre(scanner.nextLine());

        System.out.print("Date de début (AAAA-MM-JJ) : ");
        DateEvenement date = new DateEvenement(LocalDate.parse(scanner.nextLine()));

        System.out.print("Heure début (HH:MM) : ");
        HeureDebut heure = new HeureDebut(LocalTime.parse(scanner.nextLine()));

        System.out.print("Durée (minutes) : ");
        DureeEvenement duree = new DureeEvenement(Integer.parseInt(scanner.nextLine()));

        System.out.print("Fréquence en jours : ");
        int frequenceJours = Integer.parseInt(scanner.nextLine());

        EvenementPeriodique event = new EvenementPeriodique(new EventId(UUID.randomUUID()), titre, date, heure, duree, frequenceJours);
        calendar.ajouterEvenement(event);

        System.out.println("Événement périodique ajouté !");
    }

    private static void supprimerEvenement(Scanner scanner, CalendarManager calendar) {
        System.out.print("ID de l'événement à supprimer : ");
        EventId id = new EventId(UUID.fromString(scanner.nextLine()));

        calendar.supprimerEvenement(id);
        System.out.println("Événement supprimé (s'il existait).");
    }
}
