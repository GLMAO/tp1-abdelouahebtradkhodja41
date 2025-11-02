package org.emp.gl.timer.service.impl;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;
import static org.emp.gl.timer.service.TimerChangeListener.*;

import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Implementation du service de temps utilisant PropertyChangeSupport
 * pour gerer les observateurs de maniere thread-safe
 */
public class DummyTimerService implements TimerService {

    // Remplacement: ArrayList par PropertyChangeSupport
    private PropertyChangeSupport support;
    private Timer timer;

    // Variables pour stocker le temps ecoule
    private int dixiemeDeSeconde = 0;
    private int secondes = 0;
    private int minutes = 0;
    private int heures = 0;

    /**
     * Constructeur
     * Initialise le PropertyChangeSupport
     */
    public DummyTimerService() {
        // 'this' est la source des evenements
        support = new PropertyChangeSupport(this);
    }

    /**
     * Ajoute un observateur a la liste
     * Utilise PropertyChangeSupport qui gere la thread-safety
     * @param listener L'observateur a ajouter
     */
    @Override
    public void addTimerChangeListener(TimerChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Retire un observateur de la liste
     * Utilise PropertyChangeSupport qui gere la thread-safety
     * @param listener L'observateur a retirer
     */
    @Override
    public void removeTimerChangeListener(TimerChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Demarre le timer qui execute incrementer()
     * toutes les 100 millisecondes
     */
    @Override
    public void start() {
        timer = new Timer();
        // Execution periodique toutes les 100ms
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                incrementer();
            }
        }, 0, 100);
    }

    /**
     * Arrete le timer
     */
    @Override
    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * Incremente le temps et notifie les observateurs
     * Logique: 10 dixiemes = 1 seconde
     *          60 secondes = 1 minute
     *          60 minutes = 1 heure
     */
    private void incrementer() {
        int oldDixieme = dixiemeDeSeconde;
        int oldSeconde = secondes;
        int oldMinute = minutes;
        int oldHeure = heures;

        dixiemeDeSeconde++;

        if (dixiemeDeSeconde >= 10) {
            dixiemeDeSeconde = 0;
            secondes++;
            // Utilisation de firePropertyChange au lieu de notifyListeners
            support.firePropertyChange(SECONDE_PROP, oldSeconde, secondes);

            if (secondes >= 60) {
                secondes = 0;
                minutes++;
                support.firePropertyChange(MINUTE_PROP, oldMinute, minutes);

                if (minutes >= 60) {
                    minutes = 0;
                    heures++;
                    support.firePropertyChange(HEURE_PROP, oldHeure, heures);

                    if (heures >= 24) {
                        heures = 0;
                    }
                }
            }
        }

        support.firePropertyChange(DIXEME_DE_SECONDE_PROP, oldDixieme, dixiemeDeSeconde);
    }

    // SUPPRIMEE: La methode notifyListeners n'existe plus !
    // PropertyChangeSupport gere tout automatiquement

    // Getters pour les valeurs de temps
    @Override
    public int getDixiemeDeSeconde() {
        return dixiemeDeSeconde;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getHeures() {
        return heures;
    }
}
