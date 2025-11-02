package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;

/**
 * Classe representant un compte a rebours observateur
 * du service de temps
 * Se desinscrit automatiquement quand le compteur arrive a 0
 */
public class CompteARebours implements TimerChangeListener {

    private int compteur;
    private String name;
    private TimerService timerService;

    /**
     * Constructeur du compte a rebours
     * @param name Le nom du compte a rebours
     * @param valeurInitiale La valeur de depart du compteur
     */
    public CompteARebours(String name, int valeurInitiale) {
        this.name = name;
        this.compteur = valeurInitiale;
        System.out.println("CompteARebours " + name + " initialise a " + valeurInitiale);
    }

    /**
     * Injection de dependance du TimerService
     * Le compte a rebours s'inscrit automatiquement comme observateur
     * @param timerService Le service de temps
     */
    public void setTimerService(TimerService timerService) {
        this.timerService = timerService;
        // Auto-inscription en tant qu'observateur
        this.timerService.addTimerChangeListener(this);
    }

    /**
     * Methode callback appelee par le sujet (TimerService)
     * lors d'un changement de temps
     * @param prop La propriete qui a change
     * @param oldValue L'ancienne valeur
     * @param newValue La nouvelle valeur
     */
    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        // Reagir uniquement aux changements de seconde
        if (prop.equals(TimerChangeListener.SECONDE_PROP)) {
            if (compteur > 0) {
                // Afficher le compteur actuel
                System.out.println(name + " : " + compteur);
                compteur--;
            } else if (compteur == 0) {
                // Compte a rebours termine
                System.out.println(name + " : TERMINE !");
                // Se desinscrire automatiquement
                timerService.removeTimerChangeListener(this);
                compteur = -1; // Pour eviter de reafficher
            }
            // Si compteur < 0, ne rien faire (deja desinscrit)
        }
    }
}
