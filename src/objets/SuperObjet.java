package objets;

import main.GamePanel;
import main.UtilityTools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObjet {

    public BufferedImage image;
    public String name;
    public boolean colision = false;
    public int mondeX, mondeY;
    public Rectangle hitbox = new Rectangle(0,0,48,48);
    public int defauthitboxX = 0;
    public int defauthitboxY = 0;
    UtilityTools uTools = new UtilityTools();


    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = mondeX - gp.player.worldX + gp.player.screenX;
        int screenY = mondeY - gp.player.worldY + gp.player.screenY;

        if (mondeX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                mondeX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                mondeY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                mondeY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
