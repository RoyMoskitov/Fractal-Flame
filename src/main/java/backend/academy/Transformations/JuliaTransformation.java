package backend.academy.Transformations;

import backend.academy.Model.Point;
import java.util.concurrent.ThreadLocalRandom;

public final class JuliaTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        // Вычисление r и θ
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(point.y(), point.x());

        // Ω равен 0 или π
        double omega = ThreadLocalRandom.current().nextBoolean() ? 0 : Math.PI;

        // Вычисление новых координат
        double newX = Math.sqrt(r) * Math.cos(theta / 2 + omega);
        double newY = Math.sqrt(r) * Math.sin(theta / 2 + omega);

        return new Point(newX, newY);
    }
}
