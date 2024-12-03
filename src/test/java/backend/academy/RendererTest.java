package backend.academy;

import backend.academy.Model.FractalImage;
import backend.academy.Model.Rect;
import backend.academy.Transformations.HeartTransformation;
import backend.academy.Transformations.JuliaTransformation;
import backend.academy.Transformations.Transformation;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RendererTest {

    @Test
    void testRender() {
        FractalImage canvas = FractalImage.create(100, 100);
        Rect world = new Rect(0, 0, 10, 10);
        List<Transformation> variations = List.of(new HeartTransformation(), new JuliaTransformation());
        String seed = "test";

        Renderer.render(canvas, world, variations, 10, (short) 5, seed);

        int updatesCount = 0;
        for (int i = 0; i < canvas.width(); i++) {
            for (int j = 0; j < canvas.height(); j++) {
                updatesCount += canvas.pixel(i, j).hitCount();
            }
        }

        assertTrue(updatesCount > 25 * Renderer.SYMMETRY);
    }
}
