package backend.academy.Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FractalFlameControllerTest {

    @Test
    void testCreateImage() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        PrintStream originalOut = System.out;
        System.setOut(printStream);

        InputStream mockIn = new ByteArrayInputStream("1920\n1080\n1000\n1\n1\n1\n".getBytes());
        FractalFlameController controller = new FractalFlameController(new PrintStream(System.out), mockIn);

        controller.createImage();

        Path savedImagePath = Paths.get(FractalFlameController.PICTURE_NAME + "."
            + FractalFlameController.IMAGE_FORMAT.toString().toLowerCase());

        assertTrue(Files.exists(savedImagePath));
        String output = outputStream.toString();
        assertTrue(output.contains("Image generated successfully"), "Standard output should contain success message");

        Files.delete(savedImagePath);
        System.setOut(originalOut);
    }

}
