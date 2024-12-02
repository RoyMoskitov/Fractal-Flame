package backend.academy.Model;

public record FractalImage(Pixel[] data, int width, int height) {

    public static FractalImage create(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }
        Pixel[] data = new Pixel[width * height];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                data[i * width + j] = new Pixel();
            }
        }
        return new FractalImage(data, width, height);
    }

    public boolean contains(int x, int y) {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }

    public Pixel pixel(int x, int y) {
        if (!contains(x, y)) {
            throw new IllegalArgumentException("Image doesn't contain this pixel");
        }
        return data[y * width + x];
    }
}
