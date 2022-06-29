

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


public class MyFilters {
    private BufferedImage imageForEditing;
    public static final int SEPIA_DEPTH = 20;
    public static final int SEPIA_INTENSITY = 30;
    public static final int COLOR_SHIFT_RIGHT = 1;
    public static final int COLOR_SHIFT_LEFT_FILTER = 2;
    public static final int SEPIA_FILTER = 3;
    public static final int BLACK_AND_WHITE_FILTER = 4;
    public static final int NEGATIVE_FILTER = 5;
    public static final int MIRROR_FILTER = 6;

    public void setFilterLoop(int type, BufferedImage originalImage) {
        for (int y = 0; y < originalImage.getHeight(); y++) {
            for (int x = 0; x < originalImage.getWidth(); x++) {
                getRGBbyType(type, originalImage, x, y);
            }
        }
    }

    public void setImageForEditing(URL url) {
        try {
            this.imageForEditing = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getRGBbyType(int type, BufferedImage image, int x, int y) {
        int currentRGB = image.getRGB(x, y);
        Color currentColor = new Color(currentRGB);
        Color newColor = null;
        switch (type) {
            case COLOR_SHIFT_RIGHT -> {
                newColor = new Color(currentColor.getGreen(), currentColor.getBlue(),
                        currentColor.getRed());
            }
            case COLOR_SHIFT_LEFT_FILTER -> {
                newColor = new Color(currentColor.getBlue(), currentColor.getRed(),
                        currentColor.getGreen());
            }
            case SEPIA_FILTER -> {
                int average = (currentColor.getRed() + currentColor.getGreen() + currentColor.getBlue()) / 3;
                int newRed = average + (SEPIA_DEPTH * 2);
                int newGreen = average + SEPIA_DEPTH;
                int newBlue = average - SEPIA_INTENSITY;
                if (newRed > 255) newRed = 255;
                if (newGreen > 255) newGreen = 255;
                if (newBlue < 0) newBlue = 0;
                newColor = new Color(newRed, newGreen, newBlue);
            }
            case BLACK_AND_WHITE_FILTER -> {
                int grey = (int) (0.299 * currentColor.getRed() + 0.587 * currentColor.getGreen()
                        + 0.114 * currentColor.getBlue());
                newColor = new Color(grey, grey, grey);
            }
            case NEGATIVE_FILTER -> {
                newColor = new Color(255 - currentColor.getRed(),
                        255 - currentColor.getGreen(), 255 - currentColor.getBlue());
            }
            case MIRROR_FILTER -> this.imageForEditing.setRGB((image.getWidth() - 1) - x, y,
                    image.getRGB(x, y));
        }
        if (newColor != null)
            this.imageForEditing.setRGB(x, y, newColor.getRGB());
    }

    public BufferedImage getImageForEditing() {
        return this.imageForEditing;
    }

}
