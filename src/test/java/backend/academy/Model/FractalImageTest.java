package backend.academy.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FractalImageTest {

    @Test
    void testCreateWithInvalidDimensions() {
        assertThrows(IllegalArgumentException.class,
            () -> FractalImage.create(-1, 1));

        assertThrows(IllegalArgumentException.class,
            () -> FractalImage.create(1, -1));
    }

    @Test
    void testCreateWithValidDimensions() {
        int width = 5;
        int height = 3;

        FractalImage fractalImage = FractalImage.create(width, height);

        assertNotNull(fractalImage);
        assertEquals(width, fractalImage.width());
        assertEquals(height, fractalImage.height());

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                assertNotNull(fractalImage.pixel(x, y));
            }
        }
    }

    @Test
    void testContainsMethod() {
        int width = 4;
        int height = 4;
        FractalImage fractalImage = FractalImage.create(width, height);

        assertTrue(fractalImage.contains(0, 0));
        assertTrue(fractalImage.contains(3, 3));

        assertFalse(fractalImage.contains(-1, 0));
        assertFalse(fractalImage.contains(4, 0));
    }
}
