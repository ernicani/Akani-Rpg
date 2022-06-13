package Sol;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
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
        try {
            sol[0] = new Sol();
            sol[0].sol =ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sol/eau.png")));
            sol[0].colisions = true;

            sol[1] = new Sol();
            sol[1].sol = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sol/herbe.png")));
            sol[1].colisions = false;

            sol[2] = new Sol();
            sol[2].sol = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sol/herbe1.png")));
            sol[2].colisions = false;

            sol[3] = new Sol();
            sol[3].sol = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sol/herbe2.png")));
            sol[3].colisions = false;

            sol[4] = new Sol();
            sol[4].sol = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sol/spawn_milieux.png")));
            sol[4].colisions = false;

            sol[5] = new Sol();
            sol[5].sol = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sol/mur.png")));
            sol[5].colisions = true;

            sol[6] = new Sol();
            sol[6].sol = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sol/arbre.png")));
            sol[6].colisions = true;

            sol[7] = new Sol();
            sol[7].sol = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sol/sable.png")));
            sol[7].colisions = false;


        } catch (IOException e) {

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

                g2.drawImage(sol[mNum].sol, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol ++;

            if (worldCol == gp.maxMondeCol) {
                worldCol = 0;
                worldLig++;
            }

        }
    }
}