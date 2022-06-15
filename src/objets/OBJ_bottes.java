package objets;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_bottes extends SuperObjet {

    GamePanel gp;

    public OBJ_bottes(GamePanel gp) {
        name = "Bottes 1";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entité/bottes.png")));
            uTools.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
