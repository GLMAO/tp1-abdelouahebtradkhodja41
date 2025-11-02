package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;

/**
 * Classe representant une horloge observatrice
 * du service de temps
 */
public class Horloge implements TimerChangeListener {

    private String name;
    private TimerService timerService;

    /**
     * Constructeur de l'horloge
     * @param name Le nom de l'horloge
     */
    public Horloge(String name) {
        this.name = name;
        System.out.println("Horloge " + name + " initialized!");
    }

    /**
     * Injection de dependance du TimerService
     * L'horloge s'inscrit automatiquement comme observateur
     * @param timerService Le service de temps
     */
    public void setTimerService(TimerService timerService) {
        this.timerService = timerService;
        // Auto-inscription en tant qu'observateur
        this.timerService.addTimerChangeListener(this);
    }

    /**
     * Affiche l'heure actuelle
     */
    public void afficherHeure() {
        if (timerService != null)
            System.out.println(name + " affiche " +
                    timerService.getHeures() + ":" +
                    timerService.getMinutes() + ":" +
                    timerService.getSecondes());
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
            afficherHeure();
        }
    }
}
