package backend.academy.PostProcessing;

import backend.academy.Model.FractalImage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GammaLogCorrectionTest {

    @Test
    void testProcess() {
        FractalImage image = FractalImage.create(1, 2);

        image.pixel(0, 0).r(100);
        image.pixel(0, 0).g(100);
        image.pixel(0, 0).b(0);
        image.pixel(0, 0).hitCount(5);

        image.pixel(0, 1).r(0);
        image.pixel(0, 1).g(0);
        image.pixel(0, 1).b(255);
        image.pixel(0, 1).hitCount(10);

        GammaLogCorrection processor = new GammaLogCorrection();
        processor.process(image);

        double expected0_0 = 100 * Math.pow(Math.log1p(5) / Math.log1p(10), (1.0 / 2.2));
        double expectedB0_1 = 255 * Math.pow(1, (1.0 / 2.2));

        assertEquals((int) expected0_0, image.pixel(0, 0).r());
        assertEquals((int) expected0_0, image.pixel(0, 0).g());
        assertEquals(0, image.pixel(0, 0).b());

        assertEquals(0, image.pixel(0, 1).r());
        assertEquals(0, image.pixel(0, 1).g());
        assertEquals((int) expectedB0_1, image.pixel(0, 1).b());
    }
}


