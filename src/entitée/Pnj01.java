package entitée;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class Pnj01 extends Entity {

    public final int screenX;
    public final int screenY;

    public Pnj01(GamePanel gp) {
        super(gp);
        this.gp = gp;

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

        getImage();
        setDialogue();

    }


    public void setDefaultValues() {
        worldX = gp.tileSize * 10;
        worldY = gp.tileSize * 10;
        speed = 1;
        direction = "bas";
    }

    public void getImage() {

        bas1 = setup("/entité/PnjB01");
        bas2 = setup("/entité/PnjB02");
        haut1 = setup("/entité/PnjH01");
        haut2 = setup("/entité/PnjH02");
        gauche1 = setup("/entité/PnjG01");
        gauche2 = setup("/entité/PnjG02");
        droite1 = setup("/entité/PnjD01");
        droite2 = setup("/entité/PnjD02");

    }

    public void setDialogue() {

        dialogues[0] = "Salut, je suis un PNJ \nmais pas un simple PNJ.";
        dialogues[1] = "Je voulais savoir si tu sais comment j'ai créé\nL'univers de ce jeu rien qu'avec mes doigts.";
        dialogues[2] = "Je ne suis pas un PNJ comme les autres";
        dialogues[3] = "Car si tu ferme les yeux, je disparais.";
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 48) {

            Random r = new Random();
            int i = r.nextInt(100) + 1;

            if (i <= 25) {
                direction = "bas";
            } else if (i <= 50) {
                direction = "haut";
            } else if (i <= 75) {
                direction = "gauche";
            } else {
                direction = "droite";
            }

            actionLockCounter = 0;
        }


    }

    public void speek() {

        super.speek();

    }


}
