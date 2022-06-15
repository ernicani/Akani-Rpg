package entitée;

import main.GamePanel;
import main.KeyHandler;
import objets.SuperObjet;

import java.awt.*;

import java.awt.image.BufferedImage;


public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int pClef = 0;
    public int porteConteur = 0;
    public int index;
    public boolean porteOuverte = false;
    public SuperObjet tmp;
    boolean mooving = false;
    int pixelCounter = 0;


    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;


        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        hitbox = new Rectangle();
        hitbox.x = 1;
        hitbox.y = 1;
        defauthitboxX = hitbox.x;
        defauthitboxY = hitbox.y;
        hitbox.width = 46;
        hitbox.height = 46;

        setDefaultValues();
        getPlayerImage();

    }
    public void dispTmpObjet(int i) {

        index = i;
        tmp = gp.obj[i];
        porteOuverte = true;
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 9;
        worldY = gp.tileSize * 8;
        speed = 3;
        direction = "bas";
    }

    public void getPlayerImage() {

        bas1 = setup("/entité/PB1");
        bas2 = setup("/entité/PB2");
        haut1 = setup("/entité/PH1");
        haut2 = setup("/entité/PH2");
        gauche1 = setup("/entité/PG1");
        gauche2 = setup("/entité/PG2");
        droite1 = setup("/entité/PD1");
        droite2 = setup("/entité/PD2");

    }

    public void update() {

        if (!mooving) {
            if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

                if (keyH.upPressed) {
                    direction = "haut";
                } else if (keyH.downPressed) {
                    direction = "bas";
                } else if (keyH.leftPressed) {
                    direction = "gauche";
                }
                if (keyH.rightPressed) {
                    direction = "droite";
                }
                mooving = true;

                //check les collisions
                colisionOn = false;
                gp.cCheck.checkSol(this);

                //check les pnj
                int npcIndex = gp.cCheck.checkEntity(this, gp.pnj);
                interactNPC(npcIndex);

                //check les objets
                int objetIndex = gp.cCheck.checkObjet(this, true);
                pickUpOjets(objetIndex);
            }
        }
        if (mooving) {

            //Si colision est false, on continue
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

            pixelCounter+=3;
            if (pixelCounter == gp.tileSize) {
                pixelCounter = 0;
                mooving = false;
            }

        }


    }

    public void pickUpOjets(int i) {

        if (i != 999) {

            String objet = gp.obj[i].name;
            switch (objet) {

                case "clef 1":
                    pClef++;
                    dispObjet(i);
                    gp.gui.showMessage("Vous avez trouvé une clef !");
                    break;

                case "Porte":
                    if (pClef != 0) {
                        dispTmpObjet(i);
                    }
                    else {
                        gp.gui.showMessage("Vous n'avez pas de clef !");
                    }
                    break;
                case "Bottes 1":
                    gp.gui.showMessage("Vous avez trouvé des bottes !");
                    dispObjet(i);
                    break;
            }
        }
    }


    private void interactNPC(int i) {

        if (i != 999) {
            gp.gameState = 3;
            gp.pnj[i].speek();
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

        if (porteOuverte) {

            gp.obj[index] = null;
            porteConteur++;
            if (porteConteur == 60) {
                gp.obj[index] = tmp;
                porteOuverte = false;
                porteConteur = 0;
            }
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public void dispObjet(int i) {

        gp.obj[i] = null;
    }






}