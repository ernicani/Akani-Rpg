package entitée;

import main.GamePanel;
import main.UtilityTools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public BufferedImage bas1, bas2, haut1, haut2, gauche1, gauche2, droite1, droite2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle hitbox;
    public Rectangle hitboxNPC01;
    public int defauthitboxX, defauthitboxY;
    public boolean colisionOn = false;
    public int actionLockCounter = 0;
    String[] dialogues = new String[20];
    int dialoguesIndex = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {}

    public void speek() {


        if (dialogues[dialoguesIndex] == null) {
            dialoguesIndex = 0;
        }
        gp.gui.currentDialogue = dialogues[dialoguesIndex];
        dialoguesIndex++;

        switch (gp.player.direction) {
            case "haut":
                direction = "bas";
                break;
            case "bas":
                direction = "haut";
                break;
            case "gauche":
                direction = "droite";
                break;
            case "droite":
                direction = "gauche";
                break;
        }
    }

    public void update() {
/*
        setAction();

        colisionOn = false;
        gp.cCheck.checkSol(this);
        gp.cCheck.checkObjet(this,false);
        gp.cCheck.checkPlayer(this);


        //Si colision est false, on continue
        if (!colisionOn) {
            switch (direction) {
                case "haut": worldY -= speed;break;
                case "bas": worldY += speed;break;
                case "gauche": worldX -= speed;break;
                case "droite": worldX += speed;break;
            }
        }

        spriteCounter++;
        if (spriteCounter == 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }*/
    }



    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {

            switch (direction) {
                case "haut":
                    if (spriteNum == 1) {
                        image = haut1;
                    } else if (spriteNum == 2) {
                        image = haut2;
                    }
                    break;
                case "bas":
                    if (spriteNum == 1) {
                        image = bas1;
                    }
                    if (spriteNum == 2) {
                        image = bas2;
                    }
                    break;
                case "gauche":
                    if (spriteNum == 1) {
                        image = gauche1;
                    } else if (spriteNum == 2) {
                        image = gauche2;
                    }
                    break;
                case "droite":
                    if (spriteNum == 1) {
                        image = droite1;
                    } else if (spriteNum == 2) {
                        image = droite2;
                    }
                    break;
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public BufferedImage setup(String imagePath) {
        UtilityTools uTools = new UtilityTools();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream( imagePath + ".png")));
            image = uTools.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {

            e.printStackTrace();
        }
        return image;
    }

}