package backend.academy.Transformations;

import backend.academy.Model.Point;
import java.util.concurrent.ThreadLocalRandom;

public final class BlurTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double psi1 = ThreadLocalRandom.current().nextDouble() % 1;
        double psi2 = ThreadLocalRandom.current().nextDouble() % 1;
        double x = psi1 * Math.cos(2 * Math.PI * psi2);
        double y = psi1 * Math.sin(2 * Math.PI * psi2);
        return new Point(x, y);
    }
}
