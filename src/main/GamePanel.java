package main;


import entitée.Entity;
import entitée.Player;
import Sol.SolManageur;
import objets.SuperObjet;

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

    //Parametres du monde
    public final int maxMondeCol = 42; //nombre de colonnes dans le monde
    public final int maxMondeLig = 17; //nombre de lignes dans le monde


    //Parametres du jeu
    public int FPS = 60; //nombre de FPS

    SolManageur SolM = new SolManageur(this);
    public KeyHandler keyH = new KeyHandler(this); //gestionnaire des touches
    Thread gameThread; //thread du jeu
    public ColisionCheck cCheck = new ColisionCheck(this); //gestionnaire de collision
    public AssetSetter aSetter = new AssetSetter(this); //gestionnaire d'assets
    public GUI gui = new GUI(this); //gestionnaire de l'interface

    //entité et objets
    public Player player = new Player(this, keyH); //joueur
    public SuperObjet obj[] = new SuperObjet[10]; //tableau de objets
    public Entity[] pnj = new Entity[10]; //tableau de pnj

    // Game State
    public int gameState; //etat du jeu
    public final int titleScreen = 0; //etat de jeu en menu
    public final int playState = 1; //etat de jeu en jeu
    public final int pauseState = 2; //etat de jeu en pause
    public final int diablogueState = 3; //etat de jeu en diablogue


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObjet();
        aSetter.setNPC();
        //playMusic();
        //stopMusic();
        gameState = 1;
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
                try {
                    update();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                //System.out.println("FPS: " + drawCount);
                //System.out.println("player : " + player.worldY + "/" + player.worldX);
                timer = 0;
                drawCount = 0;
            }

        }
    }

    public void update() throws InterruptedException {

        if (gameState == playState) {
            //joueur
            player.update();
            //pnj
            for (int i = 0; i < pnj.length; i++) {
                if (pnj[i] != null) {
                    pnj[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            //paused
        }
        if (gameState == diablogueState) {
            //diablogue
        }


    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        //Graphiques du jeu
        Graphics2D g2 = (Graphics2D) g;

        //SOL
        SolM.draw(g2);

        //OBJETS
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }

        }

        // PNJ
        for (int i = 0; i < pnj.length; i++) {
            if (pnj[i] != null) {
                pnj[i].draw(g2);
            }

            //JOUEUR
            player.draw(g2);

            //GUI
            gui.draw(g2);

            g2.dispose();

        }

    }
}