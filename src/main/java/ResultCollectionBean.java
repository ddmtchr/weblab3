import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ResultCollectionBean implements Serializable {
    private List<ResultBean> resultList;

    public ResultCollectionBean() {
        resultList = new ArrayList<>();
    }

    public List<ResultBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultBean> resultList) {
        this.resultList = resultList;
    }

    public void addNewResult(double x, double y, double r) {
        ResultBean newResult = new ResultBean();
        long startTime = System.nanoTime();
        newResult.setResult(AreaCheckBean.checkHit(x, y, r) ? "Hit" : "Miss");
        newResult.setX(x);
        newResult.setY(y);
        newResult.setR(r);
        newResult.setExecAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        newResult.setExecTime(((System.nanoTime() - startTime) / 1000) / 1000.0);
        resultList.add(newResult);

        //add to databasa


    }

    public void clearCollection() {
        resultList.clear();
        // clear databasa
    }
}
