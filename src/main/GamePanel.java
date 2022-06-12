package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Parametres de la fenetre
    final int tailleOriginaleTiles = 16; //16x16 pixels
    final int scale = 3;

    final int tileSize = tailleOriginaleTiles * scale; //taille de chaque tile en pixels
    final int maxScreenCol = 16; //nombre de colonnes sur l'ecran
    final int maxScreenLig = 12; //nombre de lignes sur l'ecran
    final int screenWidth = tileSize * maxScreenCol; //largeur de l'ecran en pixels
    final int screenHeight = tileSize * maxScreenLig; //hauteur de l'ecran en pixels

    //Parametres du jeu
    int FPS = 60; //nombre de FPS
    KeyHandler keyH = new KeyHandler(); //gestionnaire des touches
    Thread gameThread; //thread du jeu


    //parametres de depart du joueur
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 5;
    int playerJumpH = 10;


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
                System.out.println("player : " + playerY + "/" + playerX);
                timer = 0;
                drawCount = 0;
            }

        }
    }

    public void update() {

        if (keyH.upPressed) {
            playerY -= playerSpeed;
        }
        if (keyH.leftPressed) {
            playerX -= playerSpeed;
        }
        if (keyH.rightPressed) {
            playerX += playerSpeed;
        }
        if (keyH.spacePressed) {
            jump();
        }

        limite();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        //Graphiques du jeu
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, tileSize,tileSize );

        g2.dispose();

    }

    public void chute() {

        if (playerY < screenHeight-tileSize) {
            playerY += playerSpeed/1.5;             //pour eviter que le joueur ne sorte de l'ecran et chute
        }
    }

    public void limite() {

        //on ne peut pas aller en dehors de l'ecran

        if (playerX > screenWidth-tileSize) {
            playerX = screenWidth-tileSize;
        } else if (playerX < 0) {
            playerX = 0;
        } else if (playerY < 0) {
            playerY = 0;
        }
    }

    public void jump() {
        playerY -= playerJumpH;
    }

}
