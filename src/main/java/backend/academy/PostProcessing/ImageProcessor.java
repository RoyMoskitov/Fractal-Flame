package backend.academy.PostProcessing;

import backend.academy.Model.FractalImage;
import java.util.concurrent.ExecutorService;

@FunctionalInterface
public
interface ImageProcessor {
    void process(FractalImage image);
}
