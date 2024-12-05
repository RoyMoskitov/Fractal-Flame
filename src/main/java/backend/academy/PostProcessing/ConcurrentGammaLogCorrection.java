package backend.academy.PostProcessing;

import backend.academy.Model.FractalImage;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Concurrent analogue of GammaLogCorrection, works absolutely the same way
 */
@SuppressWarnings("MultipleStringLiterals")
public class ConcurrentGammaLogCorrection implements ImageProcessor {
    private final static double GAMMA = 2.2;

    @Override
    public void process(FractalImage image) {
        GammaLogCorrection gammaLogCorrection = new GammaLogCorrection();
        gammaLogCorrection.process(image);
    }

    @Override
    public void process(FractalImage image, int startIdx, int endIdx,
        AtomicReference<Double> globalMax, CyclicBarrier barrier) throws BrokenBarrierException, InterruptedException {
        double localMax = Double.MIN_VALUE;
        double[][] normals = new double[image.height()][image.width()];
        for (int i = startIdx; i < endIdx; ++i) {
            localMax = getLocalMax(image, localMax, normals, i);
        }

        globalMax.accumulateAndGet(localMax, Double::max);
        barrier.await();

        double max = globalMax.get();
        for (int i = startIdx; i < endIdx; ++i) {
            gammaCorrection(image, normals, max, i, GAMMA);
        }

    }

    static void gammaCorrection(FractalImage image, double[][] normals, double max, int i, double gamma) {
        for (int j = 0; j < image.width(); ++j) {
            normals[i][j] /= max;
            image.pixel(j, i).r(
                (int) (image.pixel(j, i).r() * Math.pow(normals[i][j], (1.0 / gamma)))
            );
            image.pixel(j, i).g(
                (int) (image.pixel(j, i).g() * Math.pow(normals[i][j], (1.0 / gamma)))
            );
            image.pixel(j, i).b(
                (int) (image.pixel(j, i).b() * Math.pow(normals[i][j], (1.0 / gamma)))
            );
        }
    }

    @SuppressWarnings("ParameterAssignment")
    static double getLocalMax(FractalImage image, double localMax, double[][] normals, int i) {
        for (int j = 0; j < image.width(); ++j) {
            if (image.pixel(j, i).hitCount() != 0) {
                normals[i][j] = Math.log1p(image.pixel(j, i).hitCount());
                if (normals[i][j] > localMax) {
                    localMax = normals[i][j];
                }
            }
        }
        return localMax;
    }
}
