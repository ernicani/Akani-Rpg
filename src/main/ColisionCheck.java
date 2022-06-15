package main;

import entitée.Entity;
import entitée.Player;
import java.awt.Rectangle;

public class ColisionCheck {

    GamePanel gp;

    public ColisionCheck(GamePanel gp) {

        this.gp = gp;
    }

    public void checkSol(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.hitbox.x;
        int entityRightWorldX = entity.worldX + entity.hitbox.x + entity.hitbox.width;
        int entityTopWorldY = entity.worldY + entity.hitbox.y;
        int entityBottomWorldY = entity.worldY + entity.hitbox.y + entity.hitbox.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopLig = entityTopWorldY / gp.tileSize;
        int entityBottomLig = entityBottomWorldY / gp.tileSize;

        int solNum1, solNum2;

        switch (entity.direction) {
            case "haut":

                entityTopLig = (entityTopWorldY - entity.speed) / gp.tileSize;
                solNum1 = gp.SolM.mapSolNum[entityLeftCol][entityTopLig];
                solNum2 = gp.SolM.mapSolNum[entityRightCol][entityTopLig];

                if (gp.SolM.sol[solNum1].colisions || gp.SolM.sol[solNum2].colisions) {

                    entity.colisionOn = true;
                }
                break;
            case "bas":

                entityBottomLig = (entityBottomWorldY + entity.speed) / gp.tileSize;
                solNum1 = gp.SolM.mapSolNum[entityLeftCol][entityBottomLig];
                solNum2 = gp.SolM.mapSolNum[entityRightCol][entityBottomLig];

                if (gp.SolM.sol[solNum1].colisions || gp.SolM.sol[solNum2].colisions) {

                    entity.colisionOn = true;
                }
                break;
            case "gauche":

                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                solNum1 = gp.SolM.mapSolNum[entityLeftCol][entityTopLig];
                solNum2 = gp.SolM.mapSolNum[entityLeftCol][entityBottomLig];

                if (gp.SolM.sol[solNum1].colisions || gp.SolM.sol[solNum2].colisions) {

                    entity.colisionOn = true;
                }
                break;
            case "droite":

                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                solNum1 = gp.SolM.mapSolNum[entityRightCol][entityTopLig];
                solNum2 = gp.SolM.mapSolNum[entityRightCol][entityBottomLig];

                if (gp.SolM.sol[solNum1].colisions || gp.SolM.sol[solNum2].colisions) {

                    entity.colisionOn = true;
                }
                break;
        }


    }

    public int checkObjet(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {

            if (gp.obj[i] != null) {

                entity.hitbox.x = entity.worldX + entity.hitbox.x;
                entity.hitbox.y = entity.worldY + entity.hitbox.y;

                gp.obj[i].hitbox.x = gp.obj[i].mondeX + gp.obj[i].hitbox.x;
                gp.obj[i].hitbox.y = gp.obj[i].mondeY + gp.obj[i].hitbox.y;

                switch (entity.direction) {
                    case "haut":
                        entity.hitbox.y -= entity.speed;
                        if (entity.hitbox.intersects(gp.obj[i].hitbox)) {
                            if (gp.obj[i].colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                            break;
                        }


                    case "bas":
                        entity.hitbox.y += entity.speed;
                        if (entity.hitbox.intersects(gp.obj[i].hitbox)) {
                            if (gp.obj[i].colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                            break;
                        }

                    case "gauche":
                        entity.hitbox.x -= entity.speed;
                        if (entity.hitbox.intersects(gp.obj[i].hitbox)) {
                            if (gp.obj[i].colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                            break;
                        }

                    case "droite":
                        entity.hitbox.x += entity.speed;
                        if (entity.hitbox.intersects(gp.obj[i].hitbox)) {
                            if (gp.obj[i].colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                            break;
                        }

                }
                entity.hitbox.x = entity.defauthitboxX;
                entity.hitbox.y = entity.defauthitboxY;
                gp.obj[i].hitbox.x = gp.obj[i].defauthitboxX;
                gp.obj[i].hitbox.y = gp.obj[i].defauthitboxY;
            }


        }

        return index;

    }

    public int checkEntity(Entity entity, Entity[] target ) {


        int index = 999;

        for (int i = 0; i < target.length; i++) {

            if (target[i] != null) {

                entity.hitbox.x = entity.worldX + entity.hitbox.x;
                entity.hitbox.y = entity.worldY + entity.hitbox.y;

                target[i].hitbox.x = target[i].worldX + target[i].hitbox.x;
                target[i].hitbox.y = target[i].worldY + target[i].hitbox.y;

                switch (entity.direction) {
                    case "haut":
                        entity.hitbox.y -= entity.speed;
                        if (entity.hitbox.intersects(target[i].hitbox)) {
                            entity.colisionOn = true;
                            index = i;
                        }
                        break;


                    case "bas":
                        entity.hitbox.y += entity.speed;
                        if (entity.hitbox.intersects(target[i].hitbox)) {
                            entity.colisionOn = true;
                            index = i;
                        }
                        break;

                    case "gauche":
                        entity.hitbox.x -= entity.speed;
                        if (entity.hitbox.intersects(target[i].hitbox)) {
                            entity.colisionOn = true;
                            index = i;
                        }
                        break;

                    case "droite":
                        entity.hitbox.x += entity.speed;
                        if (entity.hitbox.intersects(target[i].hitbox)) {
                            entity.colisionOn = true;
                            index = i;
                        }
                        break;

                }
                entity.hitbox.x = entity.defauthitboxX;
                entity.hitbox.y = entity.defauthitboxY;
                target[i].hitbox.x = target[i].defauthitboxX;
                target[i].hitbox.y = target[i].defauthitboxY;
                break;
            }


        }

        return index;
    }

    public void checkPlayer(Entity entity) {

        entity.hitbox.x = entity.worldX + entity.hitbox.x;
        entity.hitbox.y = entity.worldY + entity.hitbox.y;

        gp.player.hitbox.x = gp.player.worldX + gp.player.hitbox.x;
        gp.player.hitbox.y = gp.player.worldY + gp.player.hitbox.y;

        switch (entity.direction) {
            case "haut":
                entity.hitbox.y -= entity.speed;
                if (entity.hitbox.intersects(gp.player.hitbox)) {
                    entity.colisionOn = true;
                }


            case "bas":
                entity.hitbox.y += entity.speed;
                if (entity.hitbox.intersects(gp.player.hitbox)) {
                    entity.colisionOn = true;
                }

            case "gauche":
                entity.hitbox.x -= entity.speed;
                if (entity.hitbox.intersects(gp.player.hitbox)) {
                    entity.colisionOn = true;
                }

            case "droite":
                entity.hitbox.x += entity.speed;
                if (entity.hitbox.intersects(gp.player.hitbox)) {
                    entity.colisionOn = true;
                }

        }
        entity.hitbox.x = entity.defauthitboxX;
        entity.hitbox.y = entity.defauthitboxY;
        gp.player.hitbox.x = gp.player.defauthitboxX;
        gp.player.hitbox.y = gp.player.defauthitboxY;
    }


}
