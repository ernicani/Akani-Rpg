package entitée;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "bas";
    }

    public void getPlayerImage() {
        try {
            bas1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_bas_1.png")));
            bas2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_bas_2.png")));
            haut1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_haut_1.png")));
            haut2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_haut_2.png")));
            gauche1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_gauche_1.png")));
            gauche2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_gauche_2.png")));
            droite1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_droite_1.png")));
            droite2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/player_droite_2.png")));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "haut";
                y -= speed;
            }
            if (keyH.downPressed) {
                direction = "bas";
                y += speed;
            }
            if (keyH.leftPressed) {
                direction = "gauche";
                x -= speed;
            }
            if (keyH.rightPressed) {
                direction = "droite";
                x += speed;
            }
            spriteCounter++;
            if (spriteCounter == 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        limite();
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    public void limite() {

        //on ne peut pas aller en dehors de l'ecran

        if (x > gp.screenWidth - gp.tileSize) {
            x = gp.screenWidth - gp.tileSize;
        } else if (x < 0) {
            x = 0;
        } else if (y < 0) {
            y = 0;
        } else if (y > gp.screenHeight - gp.tileSize) {
            y = gp.screenHeight - gp.tileSize;
        }
    }

}