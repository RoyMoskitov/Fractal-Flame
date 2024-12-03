package backend.academy.Transformations;

import backend.academy.Model.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeartTransformationTest {

    @Test
    void testApplyPositive() {
        HeartTransformation transformation = new HeartTransformation();
        Point point = new Point(1, 0);

        Point result = transformation.apply(point);

        assertEquals(0.0, result.y(), 1e-6);
        assertTrue(result.x() > 0);
    }

    @Test
    void testApplyNegative() {
        HeartTransformation transformation = new HeartTransformation();
        Point point = new Point(-1, 0);

        Point result = transformation.apply(point);

        assertEquals(0.0, result.y(), 1e-6);
        assertTrue(result.x() < 0);
    }
}
