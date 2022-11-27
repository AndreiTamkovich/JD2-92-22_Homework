package task8_table_per_class.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import task8_table_per_class.model.StudentTablePerClass;
import util.MysqlSessionFactory;

public class StudentTablePerClassDaoImpl implements StudentTablePerClassDao {
    private final SessionFactory sessionFactory;

    public StudentTablePerClassDaoImpl() {
        this(MysqlSessionFactory.getInstance());
    }

    public StudentTablePerClassDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(StudentTablePerClass studentTablePerClass) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(studentTablePerClass);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public StudentTablePerClass findById(Integer id) {
        StudentTablePerClass studentTablePerClass = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            studentTablePerClass = session.get(StudentTablePerClass.class, id);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
        return studentTablePerClass;
    }

    @Override
    public void update(StudentTablePerClass studentTablePerClass) {
        create(studentTablePerClass);
    }

    @Override
    public void delete(StudentTablePerClass studentTablePerClass) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(studentTablePerClass);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}
