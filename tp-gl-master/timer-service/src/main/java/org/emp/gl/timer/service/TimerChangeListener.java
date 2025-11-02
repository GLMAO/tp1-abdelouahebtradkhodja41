package org.emp.gl.timer.service;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/**
 * Interface pour les observateurs du service de temps
 * Etend PropertyChangeListener de java.beans pour beneficier
 * du mecanisme standard de Java
 */
public interface TimerChangeListener extends PropertyChangeListener {

    final static String DIXEME_DE_SECONDE_PROP = "dixieme";
    final static String SECONDE_PROP = "seconde";
    final static String MINUTE_PROP = "minute";
    final static String HEURE_PROP = "heure";

    /**
     * Methode custom appelee lors d'un changement de propriete de temps
     * @param prop La propriete qui a change
     * @param oldValue L'ancienne valeur
     * @param newValue La nouvelle valeur
     */
    void propertyChange(String prop, Object oldValue, Object newValue);

    /**
     * Implementation par defaut de PropertyChangeListener
     * Adapte l'appel standard Java vers notre methode custom
     * @param evt L'evenement de changement de propriete
     */
    @Override
    default void propertyChange(PropertyChangeEvent evt) {
        // Deleguer vers notre methode custom
        propertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
    }
}
