package database;

import beans.ResultBean;

import java.sql.SQLException;
import java.util.List;

public interface ResultDAO {
    boolean addResult(ResultBean resultBean) throws SQLException;

    boolean updateR(double r) throws SQLException;

    boolean clearResults() throws SQLException;

    List<ResultBean> getAll() throws SQLException;
}
