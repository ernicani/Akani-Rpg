package main;

import entitée.Pnj01;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setObjet() {

    }

    public void setNPC() {

        gp.pnj[0] = new Pnj01(gp);
        gp.pnj[0].worldX = gp.tileSize * 15;
        gp.pnj[0].worldY = gp.tileSize * 8;
    }


}
