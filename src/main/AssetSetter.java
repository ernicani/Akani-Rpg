package main;

import objets.OBJ_clef;
import objets.OBJ_porte;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setObjet() {

        gp.obj[0] = new OBJ_clef();
        gp.obj[0].mondeX = 10 * gp.tileSize;
        gp.obj[0].mondeY = 8 * gp.tileSize;

        gp.obj[1] = new OBJ_porte();
        gp.obj[1].mondeX = 15 * gp.tileSize;
        gp.obj[1].mondeY = 8 * gp.tileSize;

    }
}
