package task8_one_table.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import task8_one_table.model.Person;
import task8_one_table.model.Student;
import util.MysqlSessionFactory;

public class StudentDaoImpl implements StudentDao {
    private final SessionFactory sessionFactory;

    public StudentDaoImpl() {
        this(MysqlSessionFactory.getInstance());
    }

    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Student student) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(student);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public Student findById(Integer id) {
        Student student = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            student = session.get(Student.class, id);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
        return student;
    }

    @Override
    public void update(Student student) {
        create(student);
    }

    @Override
    public void delete(Student student) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(student);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}
