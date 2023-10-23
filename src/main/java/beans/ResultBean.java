package beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "results")
public class ResultBean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean result;
    private double x;
    private double y;
    private double r;
    private double execTime;
    private String execAt;

    public ResultBean(){}

    public Long getId() {
        return id;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
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

    @Override
    public String toString() {
        return "ResultBean{" +
                "id=" + id +
                ", result=" + result +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", execTime=" + execTime +
                ", execAt='" + execAt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultBean that = (ResultBean) o;

        if (result != that.result) return false;
        if (Double.compare(x, that.x) != 0) return false;
        if (Double.compare(y, that.y) != 0) return false;
        if (Double.compare(r, that.r) != 0) return false;
        if (Double.compare(execTime, that.execTime) != 0) return false;
        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(execAt, that.execAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, result, x, y, r, execTime, execAt);
    }
}
