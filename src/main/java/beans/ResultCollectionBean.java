package beans;

import database.DAOFactory;

import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ResultCollectionBean implements Serializable {
    private List<ResultBean> resultList;

    public ResultCollectionBean() {
        try {
            resultList = DAOFactory.getInstance().getResultDAO().getAll();
        } catch (Exception e) {
            System.out.println("Uh oh");
        }
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
        boolean isHit = AreaChecker.checkHit(x, y, r);
        newResult.setResult(isHit);
        newResult.setX(x);
        newResult.setY(y);
        newResult.setR(r);
        newResult.setExecAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        newResult.setExecTime(((System.nanoTime() - startTime) / 1000) / 1000.0);

        try {
            DAOFactory.getInstance().getResultDAO().addResult(newResult);
            resultList.add(newResult);
        } catch (Exception e) {
            System.out.println("Uh oh");
        }
        FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add(
                "drawPoint(" + x + ", " + y + ", " + r + ", " + isHit + ")"); // todo
    }

    public void updateR(double r) {
        try {
            DAOFactory.getInstance().getResultDAO().updateR(r);
            resultList = DAOFactory.getInstance().getResultDAO().getAll();
        } catch (Exception e) {
            System.out.println("Uh oh");
        }
    }

    public void clearCollection() {
        try {
            DAOFactory.getInstance().getResultDAO().clearResults();
            resultList.clear();
        } catch (Exception e) {
            System.out.println("Uh oh");
        }
    }
}
