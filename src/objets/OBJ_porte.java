package objets;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_porte extends SuperObjet {

    GamePanel gp;

    public OBJ_porte( GamePanel gp) {


        name = "Porte";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entité/porte.png")));
            uTools.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        colision = true;

    }
}
