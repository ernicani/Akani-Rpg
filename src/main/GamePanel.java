package main;

import Sol.SolManageur;
import entitée.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Parametres de la fenetre
    final int tailleOriginaleTiles = 16; //16x16 pixels
    final int scale = 3;

    public final int tileSize = tailleOriginaleTiles * scale; //taille de chaque tile en pixels
    public final int maxScreenCol = 16; //nombre de colonnes sur l'ecran
    public final int maxScreenLig = 12; //nombre de lignes sur l'ecran
    public final int screenWidth = tileSize * maxScreenCol; //largeur de l'ecran en pixels
    public final int screenHeight = tileSize * maxScreenLig; //hauteur de l'ecran en pixels

    //Parametres du jeu
    int FPS = 60; //nombre de FPS

    SolManageur SolM = new SolManageur(this);
    KeyHandler keyH = new KeyHandler(); //gestionnaire des touches
    Thread gameThread; //thread du jeu
    Player player = new Player(this, keyH); //joueur

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void start() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void run() {

        //initialisation du jeu
        double timePerTick = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 1;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / timePerTick;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                System.out.println("player : " + player.y + "/" + player.x);
                timer = 0;
                drawCount = 0;
            }

        }
    }

    public void update() {

        player.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        //Graphiques du jeu
        Graphics2D g2 = (Graphics2D) g;

        SolM.draw(g2); //sol

        player.draw(g2); //joueur

        g2.dispose();

    }

}