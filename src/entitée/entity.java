package entitée;

import java.awt.*;
import java.awt.image.BufferedImage;

public class entity {

    public int worldX, worldY;
    public int speed;
    public BufferedImage bas1, bas2, haut1, haut2, gauche1, gauche2, droite1, droite2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle hitbox;
    public boolean colisionOn = false;

}