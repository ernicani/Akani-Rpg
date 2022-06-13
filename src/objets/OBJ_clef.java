package objets;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_clef extends SuperObjet {

    public OBJ_clef() {

        name = "clef";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objets/clef.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
