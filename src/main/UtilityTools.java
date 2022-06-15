package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTools {

    public BufferedImage scaleImage(BufferedImage original, int newW, int newH) {

        BufferedImage scaledImage = new BufferedImage(newW, newH, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, newW, newH, null);
        g2.dispose();

        return scaledImage;
    }

}
