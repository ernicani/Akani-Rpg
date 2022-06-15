package objets;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_clef extends SuperObjet {

    GamePanel gp;

    public OBJ_clef(GamePanel gp) {


        name = "clef 1";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entité/clef.png")));
            uTools.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        colision = true;

    }
}
