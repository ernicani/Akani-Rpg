package objets;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_porte extends SuperObjet {

    public OBJ_porte() {

        name = "Porte";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entité/porte.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        colision = true;

    }
}
