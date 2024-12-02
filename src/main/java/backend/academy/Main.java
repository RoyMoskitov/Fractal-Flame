package backend.academy;

import backend.academy.Controller.FractalFlameController;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) throws InterruptedException {
        FractalFlameController controller = new FractalFlameController(System.out, System.in);
        controller.createImage();
    }
}
