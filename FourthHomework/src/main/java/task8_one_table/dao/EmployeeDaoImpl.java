package task8_one_table.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import task8_one_table.model.Employee;
import util.MysqlSessionFactory;

public class EmployeeDaoImpl implements EmployeeDao {
    private final SessionFactory sessionFactory;

    public EmployeeDaoImpl() {
        this(MysqlSessionFactory.getInstance());
    }

    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Employee employee) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(employee);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public Employee findById(Integer id) {
        Employee employee = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            employee = session.get(Employee.class, id);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
        return employee;
    }

    @Override
    public void update(Employee employee) {
        create(employee);
    }

    @Override
    public void delete(Employee employee) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(employee);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}
