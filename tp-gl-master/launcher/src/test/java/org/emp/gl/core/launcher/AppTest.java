package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.HorlogeGUI;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.impl.DummyTimerService;

import javax.swing.SwingUtilities;
import java.util.Random;

public class App {

    private static final String LIGNE = "=".repeat(60);
    private static final String SEPARATEUR = "-".repeat(60);

    public static void main(String[] args) {
        afficherBanniere();
        testPartieF_GUI();
    }

    private static void afficherBanniere() {
        System.out.println("\n" + LIGNE);
        System.out.println("  TP GENIE LOGICIEL - DESIGN PATTERN OBSERVER");
        System.out.println("  Etudiant : TRADKHODJA Abdelouaheb");
        System.out.println("  Specialite : IASD - Annee 2025/2026");
        System.out.println("  Professeur : CDT MAZARI");
        System.out.println(LIGNE + "\n");
    }

    private static void testPartieF_GUI() {
        afficherTitre("PARTIE F : INTERFACE GRAPHIQUE SWING");

        TimerService timerService = new DummyTimerService();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HorlogeGUI(timerService);
            }
        });

        afficherInfo("Interface graphique lancee avec succes");
        afficherInfo("Horloge digitale avec date en temps reel");
        afficherInfo("Fenetre Swing affichee");

        System.out.println();

        timerService.start();
    }

    private static void testPartieF_Complet() {
        afficherTitre("DEMONSTRATION COMPLETE - PATTERN OBSERVER");

        TimerService timerService = new DummyTimerService();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HorlogeGUI(timerService);
            }
        });

        afficherSousSection("HORLOGES CONSOLE");
        Horloge horlogeAlger = new Horloge("Alger");
        Horloge horlogeParis = new Horloge("Paris");
        horlogeAlger.setTimerService(timerService);
        horlogeParis.setTimerService(timerService);

        afficherSousSection("COMPTES A REBOURS");
        Random random = new Random();
        for (int i = 1; i <= 3; i++) {
            int valeur = 5 + random.nextInt(6);
            CompteARebours compte = new CompteARebours("Compte" + i, valeur);
            compte.setTimerService(timerService);
        }

        System.out.println("\n" + SEPARATEUR);
        afficherInfo("Interface graphique active");
        afficherInfo("2 horloges console actives");
        afficherInfo("3 comptes a rebours actifs");
        System.out.println(SEPARATEUR + "\n");

        timerService.start();
    }

    private static void testPartieC() {
        afficherTitre("PARTIE C : PATTERN OBSERVER - HORLOGES");

        TimerService timerService = new DummyTimerService();

        Horloge horloge1 = new Horloge("Num 1");
        Horloge horloge2 = new Horloge("Num 2");
        Horloge horloge3 = new Horloge("Num 3");

        horloge1.setTimerService(timerService);
        horloge2.setTimerService(timerService);
        horloge3.setTimerService(timerService);

        afficherInfo("3 horloges synchronisees - Demarrage...");
        System.out.println();

        timerService.start();
    }

    private static void testPartieDSimple() {
        afficherTitre("PARTIE D : COMPTE A REBOURS SIMPLE");

        TimerService timerService = new DummyTimerService();

        CompteARebours compte = new CompteARebours("Test", 5);
        compte.setTimerService(timerService);

        afficherInfo("CompteARebours avec valeur initiale : 5");
        System.out.println();

        timerService.start();
    }

    private static void testPartieDComplet() {
        afficherTitre("PARTIE D : 10 COMPTES A REBOURS ALEATOIRES");

        TimerService timerService = new DummyTimerService();
        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            int valeur = 10 + random.nextInt(11);
            CompteARebours compte = new CompteARebours("Compte" + i, valeur);
            compte.setTimerService(timerService);
        }

        afficherInfo("10 CompteARebours crees avec valeurs 10-20");
        System.out.println();

        timerService.start();
    }

    private static void testPartieEComplet() {
        afficherTitre("PARTIE E : PROPERTYCHANGESUPPORT");

        TimerService timerService = new DummyTimerService();
        Random random = new Random();

        afficherSousSection("HORLOGES");
        Horloge horloge1 = new Horloge("Horloge Paris");
        Horloge horloge2 = new Horloge("Horloge Alger");
        horloge1.setTimerService(timerService);
        horloge2.setTimerService(timerService);

        afficherSousSection("COMPTES A REBOURS");
        for (int i = 1; i <= 5; i++) {
            int valeur = 5 + random.nextInt(6);
            CompteARebours compte = new CompteARebours("Compte" + i, valeur);
            compte.setTimerService(timerService);
        }

        System.out.println("\n" + SEPARATEUR);
        afficherInfo("Thread-safety garanti par PropertyChangeSupport");
        afficherInfo("Desincription dynamique des CompteARebours");
        afficherInfo("Horloges continuent apres fin des comptes");
        System.out.println(SEPARATEUR + "\n");

        timerService.start();
    }

    private static void afficherTitre(String titre) {
        System.out.println("\n" + LIGNE);
        System.out.println("  " + titre);
        System.out.println(LIGNE + "\n");
    }

    private static void afficherSousSection(String section) {
        System.out.println("\n[" + section + "]");
    }

    private static void afficherInfo(String info) {
        System.out.println("  ✓ " + info);
    }
}
