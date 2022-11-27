package task8_table_per_class.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import task8_table_per_class.model.PersonTablePerClass;
import util.MysqlSessionFactory;

public class PersonTablePerClassDaoImpl implements PersonTablePerClassDao {
    private final SessionFactory sessionFactory;

    public PersonTablePerClassDaoImpl() {
        this(MysqlSessionFactory.getInstance());
    }

    public PersonTablePerClassDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(PersonTablePerClass personTablePerClass) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(personTablePerClass);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public PersonTablePerClass findById(Integer id) {
        PersonTablePerClass personTablePerClass = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            personTablePerClass = session.get(PersonTablePerClass.class, id);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
        return personTablePerClass;
    }

    @Override
    public void update(PersonTablePerClass personTablePerClass) {
        create(personTablePerClass);
    }

    @Override
    public void delete(PersonTablePerClass personTablePerClass) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(personTablePerClass);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}
