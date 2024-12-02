package backend.academy.Transformations;

import backend.academy.Model.Point;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class EyeFishTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double coef = 2 / (1 + Math.sqrt(point.x() * point.x() + point.y() * point.y()));
        double x = coef * point.x();
        double y = coef * point.y();
        return new Point(x, y);
    }
}
