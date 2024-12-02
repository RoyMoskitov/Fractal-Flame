package backend.academy;

import backend.academy.Controller.FractalFlameController;
import backend.academy.ImageSaving.ImageFormat;
import backend.academy.ImageSaving.ImageUtils;
import backend.academy.Model.FractalImage;
import backend.academy.Model.Rect;
import backend.academy.PostProcessing.GammaLogCorrection;
import backend.academy.Transformations.BlurTransformation;
import backend.academy.Transformations.CrossTransformation;
import backend.academy.Transformations.EyeFishTransformation;
import backend.academy.Transformations.HeartTransformation;
import backend.academy.Transformations.JuliaTransformation;
import backend.academy.Transformations.LinearTransformation;
import backend.academy.Transformations.Transformation;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
//        Transformation blur = new BlurTransformation("fdjsjnf");
//        Transformation cross = new CrossTransformation();
//        Transformation eye = new EyeFishTransformation();
//        Transformation linear = new LinearTransformation("ffnuiwfnu");
//        Transformation heart = new HeartTransformation();
//        Transformation julia = new JuliaTransformation("uffn");
//        List<Transformation> transformationList = new ArrayList<>();
//
//        //transformationList.add(julia);
//        //transformationList.add(heart);
//        //transformationList.add(cross);
//        //transformationList.add(linear);
//        //transformationList.add(eye);
//        //transformationList.add(blur);
//        FractalImage image = FractalImage.create(1920, 1080);
//        Rect world = new Rect(-1.77, -1.0, 3.54, 2.0);
//        Renderer.render(image, world ,transformationList, 1_000_00, (short) 50, "rjnfwemd");
//        GammaLogCorrection cor = new GammaLogCorrection();
//        cor.process(image);
//        ImageFormat format = ImageFormat.PNG;
//        Path path = Paths.get("C:\\Users\\Dima\\IdeaProjects\\backend_academy_2024_project_4-java-RoyMoskitov\\pic.png");
//        ImageUtils.save(image, path, format);
        FractalFlameController controller = new FractalFlameController(System.out, System.in);
        controller.createImage();
    }
}
