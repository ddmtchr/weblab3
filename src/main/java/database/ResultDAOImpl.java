package database;

import beans.AreaChecker;
import beans.ResultBean;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class ResultDAOImpl implements ResultDAO {
    @Override
    public boolean addResult(ResultBean resultBean) throws SQLException {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(resultBean);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Failed to insert value " + e);
            return false;
        }
    }

    @Override
    public boolean updateR(double r) throws SQLException {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<ResultBean> currentCollection = getAll();
            for (ResultBean result: currentCollection) {
                result.setR(r);
                result.setResult(AreaChecker.checkHit(result.getX(), result.getY(), r));
                session.update(result);
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Uh oh");
            return false;
        }
    }

    @Override
    public boolean clearResults() throws SQLException {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery("DELETE FROM results", ResultBean.class).executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Failed to clear the table " + e);
            return false;
        }
    }

    @Override
    public List<ResultBean> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            var criteriaQuery = session.getCriteriaBuilder().createQuery(ResultBean.class);
            criteriaQuery.select(criteriaQuery.from(ResultBean.class));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            System.out.println("Failed to get all elements " + e);
            return null;
        }
    }
}
