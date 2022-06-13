package main;

import objets.OBJ_clef;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GUI {

    GamePanel gp;
    Font arial_40;
    BufferedImage img_clef;
    public boolean messageOn = false;
    public String message;
    int messageConteur = 0;

    public GUI(GamePanel gp) {

        this.gp = gp;

        arial_40 = new Font("Arial", Font.BOLD, 20);

        OBJ_clef clef = new OBJ_clef();
        img_clef = clef.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2) {

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawString("x " + gp.player.pClef ,75,55);
        g2.drawImage(img_clef, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);



        if (messageOn) {

            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

            messageConteur++;

            if (messageConteur > 100) {
                messageOn = false;
                messageConteur = 0;
            }
        }
    }


}
