package task8_join.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import task8_join.model.StudentJoin;
import util.MysqlSessionFactory;

public class StudentJoinDaoImpl implements StudentJoinDao {
    private final SessionFactory sessionFactory;

    public StudentJoinDaoImpl() {
        this(MysqlSessionFactory.getInstance());
    }

    public StudentJoinDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(StudentJoin studentJoin) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(studentJoin);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public StudentJoin findById(Integer id) {
        StudentJoin studentJoin = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            studentJoin = session.get(StudentJoin.class, id);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
        return studentJoin;
    }

    @Override
    public void update(StudentJoin studentJoin) {
        create(studentJoin);
    }

    @Override
    public void delete(StudentJoin studentJoin) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(studentJoin);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}
