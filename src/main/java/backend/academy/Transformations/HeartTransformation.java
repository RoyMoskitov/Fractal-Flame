package backend.academy.Transformations;

import backend.academy.Model.Point;

public final class HeartTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        // Вычисляем радиус r и угол theta
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(point.y(), point.x());

        // Вычисляем новые координаты согласно формуле
        double newX = r * Math.sin(theta * r);
        double newY = -r * Math.cos(theta * r);

        return new Point(newX, newY);
    }
}
