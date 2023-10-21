import java.io.Serializable;

public class AreaCheckBean implements Serializable {
    public static boolean checkHit(double x, double y, double r) {
        if (x < 0) {
            if (y > 0) {
                return (x >= -r / 2 && y <= r);
            } else {
                return (x >= -r / 2 && y >= -2 * x);
            }
        } else {
            return (y <= 0 && (x * x + y * y) <= r * r / 4);
        }
    }
}
