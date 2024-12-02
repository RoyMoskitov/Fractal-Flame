package backend.academy.Transformations;

import backend.academy.Model.Point;

public final class CrossTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double coef = Math.sqrt(1/((point.x()* point.x() - point.y() * point.y())
            * (point.x()* point.x() - point.y() * point.y())));
        double x = coef * point.x();
        double y = coef * point.y();
        return new Point(x, y);
    }
}
