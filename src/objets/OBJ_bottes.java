package objets;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_bottes extends SuperObjet {

    public OBJ_bottes() {
        name = "Bottes 1";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entité/bottes.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
