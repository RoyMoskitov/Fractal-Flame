package backend.academy.PostProcessing;

import backend.academy.Model.FractalImage;

public class GammaLogCorrection implements ImageProcessor {

    private final static double GAMMA = 2.2;

    @Override
    public void process(FractalImage image) {
        double max = Double.MIN_VALUE;
        double[][] normals = new double[image.height()][image.width()];
        for (int i = 0; i < image.height(); ++i) {
            for (int j = 0; j < image.width(); ++j) {
                if (image.pixel(j, i).hitCount() != 0) {
                    normals[i][j] = Math.log1p(image.pixel(j, i).hitCount());
                    if (normals[i][j] > max) {
                        max = normals[i][j];
                    }
                }
            }
        }

        for (int i = 0; i < image.height(); ++i) {
            for (int j = 0; j < image.width(); ++j) {
                normals[i][j] /= max;
                image.pixel(j, i).r(
                    (int) (image.pixel(j, i).r() * Math.pow(normals[i][j], (1.0 / GAMMA)))
                );
                image.pixel(j, i).g(
                    (int) (image.pixel(j, i).g() * Math.pow(normals[i][j], (1.0 / GAMMA)))
                );
                image.pixel(j, i).b(
                    (int) (image.pixel(j, i).b() * Math.pow(normals[i][j], (1.0 / GAMMA)))
                );
            }
        }

    }
}
