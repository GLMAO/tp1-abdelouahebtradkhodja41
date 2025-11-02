package org.emp.gl.timer.service;

/**
 * Interface du service de temps
 * Etend TimeChangeProvider pour heriter des methodes de gestion des listeners
 */
public interface TimerService extends TimeChangeProvider {

    /**
     * @return Le nombre de minutes ecoulees
     */
    int getMinutes();

    /**
     * @return Le nombre d'heures ecoulees
     */
    int getHeures();

    /**
     * @return Le nombre de secondes ecoulees
     */
    int getSecondes();

    /**
     * @return Le nombre de dixiemes de seconde ecoules
     */
    int getDixiemeDeSeconde();
}
