package backend.academy.Transformations;

import backend.academy.Model.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CrossTransformationTest {

    @Test
    void testApply() {
        CrossTransformation transformation = new CrossTransformation();
        Point input = new Point(2.0, 1.0);

        Point result = transformation.apply(input);

        double denominator = Math.pow((input.x() * input.x() - input.y() * input.y()), 2);
        double coef = Math.sqrt(1 / denominator);
        double expectedX = coef * input.x();
        double expectedY = coef * input.y();

        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }
}
