package beans;

import java.io.Serializable;

public class InputBean implements Serializable {
    private int x;
    private Double y;
    private Double r;

    public InputBean() {
        r = 1.0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }
}
