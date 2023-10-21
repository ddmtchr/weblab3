import java.io.Serializable;

public class ResultBean implements Serializable {
//    private long id; // todo
    private String result;
    private double x;
    private double y;
    private double r;
    private double execTime;
    private String execAt;

    public ResultBean(){}

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getExecTime() {
        return execTime;
    }

    public void setExecTime(double execTime) {
        this.execTime = execTime;
    }

    public String getExecAt() {
        return execAt;
    }

    public void setExecAt(String execAt) {
        this.execAt = execAt;
    }

}
