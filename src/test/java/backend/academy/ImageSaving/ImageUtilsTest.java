package backend.academy.ImageSaving;

import backend.academy.Model.FractalImage;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImageUtilsTest {

    @Test
    void testSave(@TempDir Path tempDir) throws IOException {
        int width = 2;
        int height = 2;
        FractalImage image = FractalImage.create(width, height);

        Path outputFile = tempDir.resolve("test.png");
        ImageFormat format = ImageFormat.PNG;

        ImageUtils.save(image, outputFile, format);

        assertTrue(outputFile.toFile().exists());

        BufferedImage loadedImage = ImageIO.read(outputFile.toFile());
        assertEquals(width, loadedImage.getWidth());
        assertEquals(height, loadedImage.getHeight());
    }
}
