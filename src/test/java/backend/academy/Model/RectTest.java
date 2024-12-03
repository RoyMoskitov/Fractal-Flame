package backend.academy.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RectTest {

    @Test
    void testContains() {
        Rect rect = new Rect(0.0, 0.0, 10.0, 5.0);

        assertTrue(rect.contains(new Point(1.0, 1.0)));
        assertTrue(rect.contains(new Point(0.0, 0.0)));

        assertFalse(rect.contains(new Point(10.0, 0.0)));
        assertFalse(rect.contains(new Point(0.0, 5.0)));
    }
}
