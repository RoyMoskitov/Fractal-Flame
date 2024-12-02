package backend.academy.Controller;

import backend.academy.ImageSaving.ImageFormat;
import backend.academy.ImageSaving.ImageUtils;
import backend.academy.Model.FractalImage;
import backend.academy.Model.Rect;
import backend.academy.PostProcessing.GammaLogCorrection;
import backend.academy.PostProcessing.ImageProcessor;
import backend.academy.Renderer;
import backend.academy.Transformations.Transformation;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FractalFlameController {

    public static final Double LEFT_WORLD_WIDTH_BORDER = -1.77;
    public static final Double LEFT_WORLD_HEIGHT_BORDER = -1.0;
    public static final Double WORLD_WIDTH = 3.54;
    public static final Double WORLD_HEIGHT = 2.0;
    public static final Integer MAX_WIDTH = 1920;
    public static final Integer MAX_HEIGHT = 1080;
    public static final Integer MAX_SAMPLES = 10_000_000;
    public static final Short ITERATIONS_PER_SAMPLE = 50;
    public static final String pictureName = "FractalFlame";
    public static final ImageFormat IMAGE_FORMAT = ImageFormat.PNG;
    public static final List<ImageProcessor> IMAGE_PROCESSOR_LIST = List.of(new GammaLogCorrection());
    InputHandler inputHandler;

    public FractalFlameController(PrintStream output, InputStream input) {
        inputHandler = new InputHandler(output, input);
    }

    public void createImage() {
        int imageWidth = inputHandler.chooseNumber(1, MAX_WIDTH, "width (in pixels)");
        int imageHeight = inputHandler.chooseNumber(1, MAX_HEIGHT, "height (in pixels)");
        FractalImage image = FractalImage.create(imageWidth, imageHeight);

        int samples = inputHandler.chooseNumber(1, MAX_SAMPLES,
            "number of iterations to generate a fractal");

        List<Transformation> transformationList = inputHandler.chooseTransformations();
        Rect world = new Rect(LEFT_WORLD_WIDTH_BORDER, LEFT_WORLD_HEIGHT_BORDER, WORLD_WIDTH, WORLD_HEIGHT);

        inputHandler.printText("Image is generating...\n");

        Renderer.render(image, world, transformationList,
            samples, ITERATIONS_PER_SAMPLE, String.valueOf(System.nanoTime()));

        for (var imageProcessor : IMAGE_PROCESSOR_LIST) {
            imageProcessor.process(image);
        }

        Path path = Paths.get(pictureName + "." + IMAGE_FORMAT.toString().toLowerCase());
        ImageUtils.save(image, path, IMAGE_FORMAT);

        inputHandler.printText("Image generated successfully\n");
    }


}
