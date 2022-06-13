package objets;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_clef extends SuperObjet {

    public OBJ_clef() {

        name = "clef 1";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entité/clef.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        colision = true;

    }
}
