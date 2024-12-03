package backend.academy;

import backend.academy.Model.FractalImage;
import backend.academy.Model.Point;
import backend.academy.Model.Rect;
import backend.academy.Transformations.LinearTransformation;
import backend.academy.Transformations.Transformation;
import java.security.SecureRandom;
import java.util.List;

/**
 * Generates a fractal flame image by applying a series of transformations
 * to randomly generated points within a rectangular area (world), which is later mapped on your image
 */
public class Renderer {

    /**
     *  Defines the number of symmetrical transformations to apply
     */
    public static final int SYMMETRY = 1;
    /**
     * Sets the initial iteration value for rendering
     */
    public static final int STARTING_ITERATION = -20;

    private Renderer() {
    }

    public static void render(
        FractalImage canvas, Rect world, List<Transformation> variations,
        int samples, short iterPerSample, String seed
    ) {
        SecureRandom random = new SecureRandom(seed.getBytes());
        LinearTransformation linearTransformation = new LinearTransformation();
        for (int num = STARTING_ITERATION; num < samples; ++num) {
            Point pw = randomPoint(world, random);
            if (num < 0) {
                continue;
            }
            for (short step = STARTING_ITERATION; step < iterPerSample; ++step) {

                Transformation nonLinearTransformation = randomTransformation(variations, random);
                int[] color = linearTransformation.getColor();
                pw = linearTransformation.apply(pw);
                pw = nonLinearTransformation.apply(pw);

                double theta2 = 0.0;
                for (int s = 0; s < SYMMETRY; theta2 += Math.PI * 2 / SYMMETRY, ++s) {
                    var pwr = rotate(pw, theta2);
                    if (!world.contains(pwr)) {
                        continue;
                    }
                    addColor(world, pwr, canvas, color);
                }
            }
        }
    }

    private static void addColor(Rect world, Point pwr, FractalImage canvas, int[] baseColor) {
        int x = (int) ((pwr.x() - world.x()) / world.width() * canvas.width());
        int y = (int) ((pwr.y() - world.y()) / world.height() * canvas.height());
        if (canvas.contains(x, y)) {
            synchronized (canvas.pixel(x, y)) {
                canvas.pixel(x, y).r(baseColor[0]);
                canvas.pixel(x, y).g(baseColor[1]);
                canvas.pixel(x, y).b(baseColor[2]);
                //canvas.pixel(x, y).hitCount().getAndIncrement();
                canvas.pixel(x, y).hitCount(canvas.pixel(x, y).hitCount() + 1);
            }
        }
    }

    private static Point rotate(Point pw, double theta) {
        double x = pw.x() * Math.cos(theta) - pw.y() * Math.sin(theta);
        double y = pw.x() * Math.sin(theta) + pw.y() * Math.cos(theta);
        return new Point(x, y);
    }

    private static Transformation randomTransformation(List<Transformation> variations, SecureRandom random) {
        int idx = random.nextInt(variations.size());
        return variations.get(idx);
    }

    private static Point randomPoint(Rect world, SecureRandom random) {
        double x = world.x() + random.nextDouble() * world.width();
        double y = world.y() + random.nextDouble() * world.height();
        return new Point(x, y);
    }
}
