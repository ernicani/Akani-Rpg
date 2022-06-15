package Sol;

import main.GamePanel;
import main.UtilityTools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Random;

public class SolManageur {

    GamePanel gp;
    public Sol[] sol;
    public int[][] mapSolNum;

    public SolManageur(GamePanel gp) {

        this.gp = gp;

        sol = new Sol[200];
        mapSolNum = new int[gp.maxMondeCol][gp.maxMondeLig];

        getSolImage();
        chargerMap("/maps/monde01.txt");
    }

    public void getSolImage() {

            setup(0,"eau",true);
            setup(1,"herbe",false);
            setup(2,"herbe1",false);
            setup(3,"herbe2",false);
            setup(4,"spawn_milieux",false);
            setup(5,"mur",true);
            setup(6,"arbre",true);
            setup(7,"sable",false);

    }

    public void setup(int index ,String imagePath,boolean colisions) {

        UtilityTools ut = new UtilityTools();

        try {
            sol[index] = new Sol();
            sol[index].sol = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sol/"+ imagePath + ".png")));
            sol[index].sol = ut.scaleImage(sol[index].sol,gp.tileSize,gp.tileSize);
            sol[index].colisions = colisions;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chargerMap(String mapName) {

        try {
            InputStream is = getClass().getResourceAsStream(mapName);
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));

            int col = 0;
            int lig = 0;

            while (col < gp.maxMondeCol && lig < gp.maxMondeLig) {
                String line = br.readLine();
                while (col < gp.maxMondeCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapSolNum[col][lig] = num;
                    col++;
                }
                if (col == gp.maxMondeCol) {
                    col = 0;
                    lig++;
                }
            }
            br.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldLig = 0;

        while (worldCol < gp.maxMondeCol && worldLig < gp.maxMondeLig) {

            int mNum = mapSolNum[worldCol][worldLig];

            int mondeX = worldCol * gp.tileSize;
            int mondeY = worldLig * gp.tileSize;
            int screenX = mondeX - gp.player.worldX + gp.player.screenX;
            int screenY = mondeY - gp.player.worldY + gp.player.screenY;

            if (mondeX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                mondeX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                mondeY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                mondeY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {

                g2.drawImage(sol[mNum].sol, screenX, screenY, null);
            }

            worldCol ++;

            if (worldCol == gp.maxMondeCol) {
                worldCol = 0;
                worldLig++;
            }

        }
    }
}