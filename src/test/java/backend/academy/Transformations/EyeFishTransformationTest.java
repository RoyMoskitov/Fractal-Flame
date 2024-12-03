package backend.academy.Transformations;

import backend.academy.Model.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EyeFishTransformationTest {

    @Test
    void testApply() {
        EyeFishTransformation transformation = new EyeFishTransformation();
        Point input = new Point(1.0, 1.0);

        Point result = transformation.apply(input);

        double r = Math.sqrt(1.0 + 1.0);
        double coef = 2 / (1 + r);

        assertEquals(coef, result.x(), 1e-6);
        assertEquals(coef, result.y(), 1e-6);
    }
}
