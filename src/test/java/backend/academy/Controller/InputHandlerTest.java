package backend.academy.Controller;

import backend.academy.Transformations.BlurTransformation;
import backend.academy.Transformations.CrossTransformation;
import backend.academy.Transformations.JuliaTransformation;
import backend.academy.Transformations.Transformation;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InputHandlerTest {
    private PrintStream printStream;
    private InputHandler inputHandler;

    @BeforeEach
    void setUp() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
    }

    @Test
    void testChooseNumberValidInput() {
        String input = "3\n";
        inputHandler = new InputHandler(printStream, new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        int number = inputHandler.chooseNumber(1, 5, "test");

        assertEquals(3, number);
    }

    @Test
    void testChooseNumberInvalidAndValidInput() {
        String input = "10\n3\n";
        inputHandler = new InputHandler(printStream, new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        int number = inputHandler.chooseNumber(1, 5, "test");

        assertEquals(3, number);
    }

    @Test
    void testChooseTransformations() {
        String input = "3\n1 2 5\n";
        inputHandler = new InputHandler(printStream, new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        List<Transformation> transformations = inputHandler.chooseTransformations();

        assertEquals(3, transformations.size());
        assertEquals(BlurTransformation.class, transformations.get(0).getClass());
        assertEquals(CrossTransformation.class, transformations.get(1).getClass());
        assertEquals(JuliaTransformation.class, transformations.get(2).getClass());
    }
}
