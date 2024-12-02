package backend.academy.Transformations;

import backend.academy.Model.Point;
import java.security.SecureRandom;

public final class LinearTransformation implements Transformation {
    SecureRandom random;
    int colorIdx;
    double[][] coefficients = {
        {0.5, 0.5, -0.5, 0.5, 0.5, -0.5},
        {-0.5, 0.5, 0.5, 0.5, -0.5, -0.5},
        {0.5, -0.5, 0.5, 0.5, -0.5, -0.5},
        {0.5, -0.5, -0.5, -0.5, 0.5, 0.5}
    };

    int[][] colors = {
        //{223, 140, 0}, {0, 83, 223}, {140, 0, 223}, {74, 13, 36}
        //{51, 13, 74}, {74, 13, 36}, {33, 87, 0}, {0, 87, 55}
        //{0, 87, 77}, {233, 0, 195}, {100, 3, 39}, {3, 39, 100}
        {255, 87, 51},   // Насыщенный оранжевый
        {72, 61, 139},   // Темный синий (индиго)
        {34, 139, 34},   // Зелёный (лесной)
        {173, 216, 230}, // Нежно-голубой
//        {255, 20, 147},  // Яркий розовый
//        {0, 255, 255},   // Голубой (циан)
//        {255, 165, 0},   // Оранжевый (светлый)
//        {75, 0, 130},    // Индиго
//        {210, 105, 30},  // Каштановый
//        {240, 128, 128}  // Лососевый

    };


    public LinearTransformation(String seed) {
        random = new SecureRandom(seed.getBytes());
    }

    @Override
    public Point apply(Point point) {
        colorIdx = random.nextInt(coefficients.length);
        double[] coef = coefficients[colorIdx];
        double x = coef[0] * point.x() + coef[1] * point.y() + coef[4];
        double y = coef[2] * point.x() + coef[3] * point.y() + coef[5];
        return new Point(x, y);
    }

    public int[] getColor() {
        return colors[colorIdx];
    }
}
