package backend.academy.PostProcessing;

import backend.academy.Model.FractalImage;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicReference;

@FunctionalInterface
public
interface ImageProcessor {
    void process(FractalImage image);


    default void process(FractalImage image, int startIdx, int endIdx, AtomicReference<Double> globalMax,
        CyclicBarrier barrier) throws BrokenBarrierException, InterruptedException {
        process(image);
    }
}
