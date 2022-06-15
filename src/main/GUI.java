package main;

import objets.OBJ_clef;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GUI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_20_plain;
    Font arial_20_bold;
    Font arial_40;
    Font arial_100;
    public boolean messageOn = false;
    public String message;
    public String currentDialogue;
    int commandNum = 0;


    public GUI(GamePanel gp) {

        this.gp = gp;

        arial_20_plain = new Font("Arial", Font.PLAIN, 20);
        arial_20_bold = new Font("Arial", Font.BOLD, 20);
        arial_40 = new Font("Arial", Font.BOLD, 40);
        arial_100 = new Font("Arial", Font.BOLD, 100);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        //Title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        //Play state
        if (gp.gameState == gp.playState) {

        }
        //Pause state
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        if (gp.gameState == gp.diablogueState) {
            drawDialogueScreen();
        }
    }

    public void drawTitleScreen () {

        g2.setColor(new Color(70,120,80));
        g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());

        // Nom du jeu
        g2.setFont(arial_100);
        String text = "AkaniRpg";
        int x = getXforCenter(text);
        int y = gp.tileSize * 3;

        g2.setColor(Color.black);
        g2.drawString(text, x+7, y+7);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //Image
        x = gp.screenWidth/2 - (gp.tileSize * 2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.bas1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        //Menu
        g2.setFont(arial_40);
        text = "Nouvelle Partie";
        x = getXforCenter(text);
        y += gp.tileSize*3.5;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-gp.tileSize/2, y);
        }

        text = "Charger Partie";
        x = getXforCenter(text);
        y += gp.tileSize;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-gp.tileSize/2, y);
        }

        text = "Quitter";
        x = getXforCenter(text);
        y += gp.tileSize;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x-gp.tileSize/2, y);
        }


    }

    public void drawPauseScreen() {

        g2.setFont(arial_100);
        String text1 = "PAUSE";
        int x = getXforCenter(text1);
        int y = gp.screenHeight / 2 ;

        g2.drawString(text1, x,y);
        g2.setFont(arial_20_bold);
        String text2 = "Appuyez sur Echap pour continuer";
        x = getXforCenter(text2);
        g2.drawString(text2, x, y+200);
    }

    public void drawDialogueScreen() {

        //FENETRE
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 2;
        int width = gp.screenWidth - gp.tileSize * 4;
        int height = gp.tileSize * 8;
        drawSubWindow(x, y, width, height);

        g2.setFont(arial_20_bold);
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += gp.tileSize;
        }


        g2.setFont(arial_20_plain);
        g2.drawString("Appuyez sur Entrer pour continuer", x+gp.tileSize*4, y+gp.tileSize*4);

    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c=new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255,200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

    }

    public int getXforCenter(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }
}
