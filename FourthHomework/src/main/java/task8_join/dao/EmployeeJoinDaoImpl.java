package task8_join.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import task8_join.model.EmployeeJoin;
import util.MysqlSessionFactory;

public class EmployeeJoinDaoImpl implements EmployeeJoinDao {
    private final SessionFactory sessionFactory;

    public EmployeeJoinDaoImpl() {
        this(MysqlSessionFactory.getInstance());
    }

    public EmployeeJoinDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(EmployeeJoin employeeJoin) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(employeeJoin);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public EmployeeJoin findById(Integer id) {
        EmployeeJoin employeeJoin = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            employeeJoin = session.get(EmployeeJoin.class, id);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
        return employeeJoin;
    }

    @Override
    public void update(EmployeeJoin employeeJoin) {
        create(employeeJoin);
    }

    @Override
    public void delete(EmployeeJoin employeeJoin) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(employeeJoin);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}
