package backend.academy.ImageSaving;

import backend.academy.Model.FractalImage;
import backend.academy.Model.Pixel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;
import javax.imageio.ImageIO;

/**
 * Utility class used to save pictures after rendering
 */
public final class ImageUtils {
    private ImageUtils() {
    }

    @SuppressWarnings("MagicNumber")
    public static void save(FractalImage image, Path filename, ImageFormat format) {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.width(); i++) {
            for (int j = 0; j < image.height(); ++j) {
                Pixel cur = image.pixel(i, j);
                int color = (cur.r() << 16) | (cur.g() << 8) | cur.b();
                bufferedImage.setRGB(i, j, color);
            }
        }

        try {
            ImageIO.write(bufferedImage, format.name().toLowerCase(Locale.ENGLISH), filename.toFile());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
