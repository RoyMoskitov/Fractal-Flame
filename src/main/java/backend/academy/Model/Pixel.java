package backend.academy.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dot on the FractalImage class
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pixel {
    int r;
    int g;
    int b;
    int hitCount;
}
