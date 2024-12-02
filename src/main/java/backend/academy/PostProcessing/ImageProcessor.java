package backend.academy.PostProcessing;

import backend.academy.Model.FractalImage;

@FunctionalInterface
public
interface ImageProcessor {
    void process(FractalImage image);
}
