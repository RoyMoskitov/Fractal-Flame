package backend.academy.PostProcessing;

import backend.academy.Model.FractalImage;

/**
 * Implementation of imageProcessor that process image after it was created by renderer,
 * makes the picture more aesthetically attractive,
 * making places where there are not many colored pixels even darker
 */
@SuppressWarnings("MultipleStringLiterals")
public class GammaLogCorrection implements ImageProcessor {

    private final static double GAMMA = 2.2;

    @Override
    public void process(FractalImage image) {
        double max = Double.MIN_VALUE;
        double[][] normals = new double[image.height()][image.width()];
        for (int i = 0; i < image.height(); ++i) {
            max = ConcurrentGammaLogCorrection.getLocalMax(image, max, normals, i);
        }

        for (int i = 0; i < image.height(); ++i) {
            ConcurrentGammaLogCorrection.gammaCorrection(image, normals, max, i, GAMMA);
        }

    }
}
