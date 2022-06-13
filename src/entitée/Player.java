package entitée;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        hitbox = new Rectangle();
        hitbox.x = 8;
        hitbox.y = 16;
        hitbox.width = 16;
        hitbox.height = 20;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 9;
        worldY = gp.tileSize * 7;
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
            }
            else if (keyH.downPressed) {
                direction = "bas";
            }
            else if (keyH.leftPressed) {
                direction = "gauche";
            }
            if (keyH.rightPressed) {
                direction = "droite";
            }

            // Collision check
            colisionOn = false;
            gp.cCheck.checkSol(this);

            //Si la colision est fausse, on continue
            if (!colisionOn) {

                switch (direction) {
                    case "haut":
                        worldY -= speed;
                        break;
                    case "bas":
                        worldY += speed;
                        break;
                    case "gauche":
                        worldX -= speed;
                        break;
                    case "droite":
                        worldX += speed;
                        break;
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
            }
        }

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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

//    public void limite() {
//
//        //on ne peut pas aller en dehors de l'ecran
//
//        if (worldX > gp.screenWidth - gp.tileSize) {
//            worldX = gp.screenWidth - gp.tileSize;
//        } else if (worldX < 0) {
//            worldX = 0;
//        } else if (worldY < 0) {
//            worldY = 0;
//        } else if (worldY > gp.screenHeight - gp.tileSize) {
//            worldY = gp.screenHeight - gp.tileSize;
//        }
//    }

}