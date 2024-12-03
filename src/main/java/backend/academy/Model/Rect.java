package backend.academy.Model;

/**
 * Rectangular area which is used to create spots on it, ;ater is transformed into image using renderer class
 */
public record Rect(double x, double y, double width, double height) {
    public boolean contains(Point p) {
        return (p.x() >= x && p.x() < (x + width) && p.y() >= y && p.y() < (y + height()));
    }
}
