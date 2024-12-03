package backend.academy.Controller;

import backend.academy.Transformations.BlurTransformation;
import backend.academy.Transformations.CrossTransformation;
import backend.academy.Transformations.EyeFishTransformation;
import backend.academy.Transformations.HeartTransformation;
import backend.academy.Transformations.JuliaTransformation;
import backend.academy.Transformations.Transformation;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static org.apache.commons.validator.GenericValidator.isInt;

@SuppressWarnings("MultipleStringLiterals")
public class InputHandler {

    //this field must be changed if new transformations are added
    public static final List<String> AVAILABLE_TRANSFORMATIONS_LIST
        = List.of("Blur", "Cross", "Eye fish", "Heart", "Julia");
    private final PrintStream output;
    private final Scanner scannerInput;

    public InputHandler(PrintStream output, InputStream input) {
        this.output = output;
        this.scannerInput = new Scanner(input, StandardCharsets.UTF_8);
    }

    public List<Transformation> chooseTransformations() {
        int count = chooseNumber(1, AVAILABLE_TRANSFORMATIONS_LIST.size(), "number of transformations");
        List<Integer> transformationIndices = chooseTransformationIndices(count);
        List<Transformation> transformations = new ArrayList<>();
        for (Integer idx : transformationIndices) {
            transformations.add(mapToTransformation(idx));
        }
        return transformations;
    }

    @SuppressWarnings("MagicNumber")
    private Transformation mapToTransformation(Integer idx) {
        return switch (idx) {
            case 1 -> new BlurTransformation();
            case 2 -> new CrossTransformation();
            case 3 -> new EyeFishTransformation();
            case 4 -> new HeartTransformation();
            case 5 -> new JuliaTransformation();
            default -> throw new IllegalArgumentException("Cannot map this value to transformation type): " + idx);
        };
    }

    private List<Integer> chooseTransformationIndices(int transformationNumber) {
        List<Integer> transformationIndices;
        String choice;
        do {
            output.println("Choose " + transformationNumber + " transformations "
                + "(each number must be separated by space) :");
            for (int i = 0; i < AVAILABLE_TRANSFORMATIONS_LIST.size(); i++) {
                output.println((i + 1) + ". " + AVAILABLE_TRANSFORMATIONS_LIST.get(i));
            }
            choice = scannerInput.nextLine();

            String[] parts = choice.split("\\s+"); //делим по одному или большему количеству пробелов/табов
            if (parts.length != transformationNumber) {
                output.println("You must enter exactly " + transformationNumber + " numbers. Try again.");
                continue;
            }

            transformationIndices = Arrays.stream(parts)
                .map(Integer::parseInt).toList();

            if (transformationIndices.stream().anyMatch(index -> index < 1
                || index > AVAILABLE_TRANSFORMATIONS_LIST.size())) {
                output.println("Some indices are out of range. Choose numbers between 1 and "
                    + AVAILABLE_TRANSFORMATIONS_LIST.size() + ".");
                continue;
            }

            break;

        } while (true);

        return transformationIndices;
    }

    public int chooseNumber(int min, int max, String type) {
        String choice;
        do {
            output.println("Choose " + type);
            output.println(type + " should be between " + min + " and " + max);
            choice = scannerInput.nextLine();
        } while (!isInt(choice) || Integer.parseInt(choice) < min
            || Integer.parseInt(choice) > max);
        return Integer.parseInt(choice);
    }

    public void printText(String str) {
        output.println(str);
    }
}
