package task8_table_per_class.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import task8_table_per_class.model.EmployeeTablePerClass;
import util.MysqlSessionFactory;

public class EmployeeTablePerClassDaoImpl implements EmployeeTablePerClassDao {
    private final SessionFactory sessionFactory;

    public EmployeeTablePerClassDaoImpl() {
        this(MysqlSessionFactory.getInstance());
    }

    public EmployeeTablePerClassDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(EmployeeTablePerClass employeeTablePerClass) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(employeeTablePerClass);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public EmployeeTablePerClass findById(Integer id) {
        EmployeeTablePerClass employeeTablePerClass = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            employeeTablePerClass = session.get(EmployeeTablePerClass.class, id);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
        return employeeTablePerClass;
    }

    @Override
    public void update(EmployeeTablePerClass employeeTablePerClass) {
        create(employeeTablePerClass);
    }

    @Override
    public void delete(EmployeeTablePerClass employeeTablePerClass) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(employeeTablePerClass);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}