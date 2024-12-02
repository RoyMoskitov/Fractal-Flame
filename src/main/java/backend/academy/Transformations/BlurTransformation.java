package backend.academy.Transformations;

import backend.academy.Model.Point;
import java.security.SecureRandom;


public final class BlurTransformation implements Transformation {
    private final SecureRandom random;

    public BlurTransformation (String seed) {
        random = new SecureRandom(seed.getBytes());
    }

    @Override
    public Point apply(Point point) {
        double psi1 = random.nextDouble() % 1;
        double psi2 = random.nextDouble() % 1;
        double x = psi1 * Math.cos(2 * Math.PI * psi2);
        double y = psi1 * Math.sin(2 * Math.PI * psi2);
        return new Point(x, y);
    }
}
