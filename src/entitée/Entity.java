package entitée;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity {

    public int worldX, worldY;
    public int speed;
    public BufferedImage bas1, bas2, haut1, haut2, gauche1, gauche2, droite1, droite2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle hitbox;
    public int defauthitboxX, defauthitboxY;

    public boolean colisionOn = false;

}