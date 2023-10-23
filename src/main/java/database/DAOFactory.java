package database;

public class DAOFactory {
    private static ResultDAO resultDAO = null;
    private static DAOFactory instance = null;

    public static synchronized DAOFactory getInstance(){
        if (instance == null){
            instance = new DAOFactory();
        }
        return instance;
    }

    public ResultDAO getResultDAO(){
        if (resultDAO == null){
            resultDAO = new ResultDAOImpl();
        }
        return resultDAO;
    }
}
