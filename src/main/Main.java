package main;

import main.fenetre.Frame;

import javax.swing.*;

public class Main {

    public static void main1(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Frame("");
            }
        });

    }


    public static void main(String[] args) {

        JFrame fenetre = new JFrame();
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        fenetre.setTitle("2d game");


        GamePanel gamePanel = new GamePanel();
        fenetre.add(gamePanel);

        fenetre.pack();
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);


        gamePanel.setupGame();
        gamePanel.start();
    }
}