package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HorlogeGUI extends JFrame implements TimerChangeListener {

    private JLabel labelHeure;
    private JLabel labelDate;
    private JLabel labelJour;
    private JPanel panelComptes;
    private TimerService timerService;

    public HorlogeGUI(TimerService timerService) {
        this.timerService = timerService;

        initialiserInterface();

        this.timerService.addTimerChangeListener(this);

        mettreAJourAffichage();

        setVisible(true);
    }

    private void initialiserInterface() {
        setTitle("Pattern Observer - Demonstration Complete");
        setSize(750, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(25, 25, 35));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(25, 25, 35));
        JLabel titre = new JLabel("PATTERN OBSERVER - DEMONSTRATION COMPLETE");
        titre.setFont(new Font("Arial", Font.BOLD, 14));
        titre.setForeground(new Color(100, 150, 255));
        headerPanel.add(titre);

        JPanel horlogePanel = new JPanel();
        horlogePanel.setLayout(new BoxLayout(horlogePanel, BoxLayout.Y_AXIS));
        horlogePanel.setBackground(new Color(25, 25, 35));
        horlogePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 255), 2),
                "HORLOGE DIGITALE",
                0, 0,
                new Font("Arial", Font.BOLD, 12),
                new Color(100, 150, 255)
        ));
        horlogePanel.setPreferredSize(new Dimension(700, 200));

        labelJour = new JLabel("DIMANCHE");
        labelJour.setFont(new Font("Arial", Font.BOLD, 14));
        labelJour.setForeground(new Color(150, 150, 150));
        labelJour.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelHeure = new JLabel("00:00:00");
        labelHeure.setFont(new Font("Arial", Font.BOLD, 60));
        labelHeure.setForeground(new Color(0, 255, 200));
        labelHeure.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelDate = new JLabel("01 Janvier 2025");
        labelDate.setFont(new Font("Arial", Font.PLAIN, 16));
        labelDate.setForeground(new Color(180, 180, 180));
        labelDate.setAlignmentX(Component.CENTER_ALIGNMENT);

        horlogePanel.add(Box.createVerticalStrut(10));
        horlogePanel.add(labelJour);
        horlogePanel.add(Box.createVerticalStrut(10));
        horlogePanel.add(labelHeure);
        horlogePanel.add(Box.createVerticalStrut(10));
        horlogePanel.add(labelDate);
        horlogePanel.add(Box.createVerticalStrut(10));

        JPanel comptesContainer = new JPanel();
        comptesContainer.setLayout(new BorderLayout());
        comptesContainer.setBackground(new Color(25, 25, 35));
        comptesContainer.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(255, 200, 0), 2),
                "COMPTES A REBOURS",
                0, 0,
                new Font("Arial", Font.BOLD, 12),
                new Color(255, 200, 0)
        ));
        comptesContainer.setPreferredSize(new Dimension(700, 250));

        panelComptes = new JPanel();
        panelComptes.setLayout(new GridLayout(2, 3, 10, 10));
        panelComptes.setBackground(new Color(25, 25, 35));
        panelComptes.setBorder(new EmptyBorder(10, 10, 10, 10));

        comptesContainer.add(panelComptes, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(25, 25, 35));
        centerPanel.add(horlogePanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(comptesContainer);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(25, 25, 35));
        JLabel credit = new JLabel("TP Genie Logiciel - TRADKHODJA Abdelouaheb - IASD 2025/26 - Prof: CDT MAZARI");
        credit.setFont(new Font("Arial", Font.ITALIC, 11));
        credit.setForeground(new Color(100, 100, 100));
        footerPanel.add(credit);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public void ajouterCompteARebours(CompteAReboursGUI compte) {
        panelComptes.add(compte);
        panelComptes.revalidate();
        panelComptes.repaint();
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if (prop.equals(TimerChangeListener.SECONDE_PROP) ||
                prop.equals(TimerChangeListener.MINUTE_PROP) ||
                prop.equals(TimerChangeListener.HEURE_PROP)) {

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    mettreAJourAffichage();
                }
            });
        }
    }

    private void mettreAJourAffichage() {
        String heures = String.format("%02d", timerService.getHeures());
        String minutes = String.format("%02d", timerService.getMinutes());
        String secondes = String.format("%02d", timerService.getSecondes());

        labelHeure.setText(heures + ":" + minutes + ":" + secondes);

        SimpleDateFormat sdfJour = new SimpleDateFormat("EEEE", new Locale("fr", "FR"));
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd MMMM yyyy", new Locale("fr", "FR"));
        Date maintenant = new Date();

        labelJour.setText(sdfJour.format(maintenant).toUpperCase());
        labelDate.setText(sdfDate.format(maintenant));
    }
}
