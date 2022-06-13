package Sol;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class SolManageur {

    GamePanel gp;
    Sol[] sol;
    int[][] mapSolNum;

    public SolManageur(GamePanel gp) {

        this.gp = gp;

        sol = new Sol[10];
        mapSolNum = new int[gp.maxScreenCol][gp.maxScreenLig];

        getSolImage();
        chargerMap();
    }

    public void getSolImage() {
        try {

            sol[0] = new Sol();
            sol[0].sol = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sol/herbe.png")));
            sol[0].colisions = false;

            sol[1] = new Sol();
            sol[1].sol = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sol/mur.png")));
            sol[1].colisions = true;

            sol[2] = new Sol();
            sol[2].sol = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sol/eau.png")));
            sol[2].colisions = true;


        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void chargerMap() {

        try {
            InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));

            int col = 0;
            int lig = 0;

            while (col < gp.maxScreenCol && lig < gp.maxScreenLig) {
                String line = br.readLine();
                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapSolNum[col][lig] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
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

        int col = 0;
        int lig = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && lig < gp.maxScreenLig) {

            int mNum = mapSolNum[col][lig];

            g2.drawImage(sol[mNum].sol, x, y, gp.tileSize, gp.tileSize, null);
            col ++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                lig++;
                x = 0;
                y += gp.tileSize;
            }

        }
    }
}