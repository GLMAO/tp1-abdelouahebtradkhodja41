package org.emp.gl.timer.service;

/**
 * Interface definissant le contrat pour un fournisseur
 * de notifications de changement de temps
 */
public interface TimeChangeProvider {

    /**
     * Permet a un observateur de s'inscrire
     * @param listener L'observateur a ajouter
     */
    void addTimerChangeListener(TimerChangeListener listener);

    /**
     * Permet a un observateur de se desinscrire
     * @param listener L'observateur a retirer
     */
    void removeTimerChangeListener(TimerChangeListener listener);

    /**
     * Demarre le service de notification
     */
    void start();

    /**
     * Arrete le service de notification
     */
    void stop();
}
