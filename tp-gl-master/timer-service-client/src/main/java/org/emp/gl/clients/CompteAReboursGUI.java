package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;

import javax.swing.*;
import java.awt.*;

public class CompteAReboursGUI extends JPanel implements TimerChangeListener {

    private int compteur;
    private String name;
    private TimerService timerService;
    private JLabel labelNom;
    private JLabel labelCompteur;
    private boolean termine = false;

    public CompteAReboursGUI(String name, int valeurInitiale, TimerService timerService) {
        this.name = name;
        this.compteur = valeurInitiale;
        this.timerService = timerService;

        initialiserInterface();

        this.timerService.addTimerChangeListener(this);
    }

    private void initialiserInterface() {
        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(35, 35, 45));
        setBorder(BorderFactory.createLineBorder(new Color(60, 60, 70), 2));
        setPreferredSize(new Dimension(150, 80));

        labelNom = new JLabel(name);
        labelNom.setFont(new Font("Arial", Font.BOLD, 12));
        labelNom.setForeground(new Color(150, 150, 150));
        labelNom.setHorizontalAlignment(SwingConstants.CENTER);

        labelCompteur = new JLabel(String.valueOf(compteur));
        labelCompteur.setFont(new Font("Arial", Font.BOLD, 36));
        labelCompteur.setForeground(new Color(255, 200, 0));
        labelCompteur.setHorizontalAlignment(SwingConstants.CENTER);

        add(labelNom, BorderLayout.NORTH);
        add(labelCompteur, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if (prop.equals(TimerChangeListener.SECONDE_PROP)) {
            if (compteur > 0) {
                compteur--;
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        labelCompteur.setText(String.valueOf(compteur));

                        if (compteur <= 3 && compteur > 0) {
                            labelCompteur.setForeground(new Color(255, 100, 100));
                        }
                    }
                });
            } else if (compteur == 0 && !termine) {
                termine = true;
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        labelCompteur.setText("FIN");
                        labelCompteur.setForeground(new Color(100, 255, 100));
                        setBackground(new Color(30, 50, 30));
                    }
                });
                timerService.removeTimerChangeListener(this);
            }
        }
    }
}
