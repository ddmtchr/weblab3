package database;

import beans.ResultBean;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Testcontainers
public class ResultDAOImplTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0")
            .withUsername("postgres")
            .withPassword("postgres")
            .withDatabaseName("postgres");

    @BeforeAll
    public static void startContainer() {
        postgres.start();
    }

    @AfterAll
    public static void stopContainer() {
        postgres.stop();
    }

    @Test
    public void getAllTest() throws SQLException {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            var criteriaQuery = session.getCriteriaBuilder().createQuery(ResultBean.class);
            criteriaQuery.select(criteriaQuery.from(ResultBean.class));
            List<ResultBean> resultList = session.createQuery(criteriaQuery).getResultList();
            Assertions.assertIterableEquals(resultList, DAOFactory.getInstance().getResultDAO().getAll());
        }
    }

    @Test
    public void addResultTest() throws SQLException {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            ResultBean testResult = new ResultBean();
            testResult.setResult(true);
            testResult.setX(1111);
            testResult.setY(0);
            testResult.setR(0);
            testResult.setExecAt("FORDELETE");
            testResult.setExecTime(42);
            DAOFactory.getInstance().getResultDAO().addResult(testResult);

            ResultBean addedResult = session.get(ResultBean.class, testResult.getId());
            Assertions.assertEquals(addedResult, testResult);
        }
    }

    @Test
    public void deleteTest() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery("DELETE FROM results WHERE exec_at='FORDELETE'", ResultBean.class).executeUpdate();
            session.getTransaction().commit();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ResultBean> criteriaQuery = cb.createQuery(ResultBean.class);
            Root<ResultBean> root = criteriaQuery.from(ResultBean.class);
            criteriaQuery.where(cb.equal(root.get("execAt"), "FORDELETE"));
            List<ResultBean> resultList = session.createQuery(criteriaQuery).getResultList();
            Assertions.assertEquals(0, resultList.size());
        }
    }

    @Test
    public void updateRTest() throws SQLException {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ResultBean> criteriaQuery = cb.createQuery(ResultBean.class);
            Root<ResultBean> root = criteriaQuery.from(ResultBean.class);
            criteriaQuery.where(cb.lessThan(root.get("x"), 10));
            List<ResultBean> resultList = session.createQuery(criteriaQuery).getResultList();
            if (!resultList.isEmpty()) {
                double originalR = resultList.get(0).getR();
                DAOFactory.getInstance().getResultDAO().updateR(42);
                session.clear();

                double newR = session.createQuery(criteriaQuery).getResultList().get(0).getR();
                DAOFactory.getInstance().getResultDAO().updateR(originalR);
                Assertions.assertEquals(42, newR);
            } else {
                Assertions.assertEquals(0, 0);
            }
        }
    }
}
