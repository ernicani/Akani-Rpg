package main;

import objets.OBJ_clef;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setObjet() {

        gp.obj[0] = new OBJ_clef();
        gp.obj[0].mondeX = 23 * gp.tileSize;
        gp.obj[0].mondeY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_clef();
        gp.obj[1].mondeX = 23 * gp.tileSize;
        gp.obj[1].mondeY = 40 * gp.tileSize;

    }
}
