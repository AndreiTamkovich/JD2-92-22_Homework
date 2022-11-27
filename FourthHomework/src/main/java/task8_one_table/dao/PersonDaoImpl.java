package task8_one_table.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import task8_one_table.model.Person;
import util.MysqlSessionFactory;

public class PersonDaoImpl implements PersonDao {
    private final SessionFactory sessionFactory;

    public PersonDaoImpl() {
        this(MysqlSessionFactory.getInstance());
    }

    public PersonDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Person person) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(person);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public Person findById(Integer id) {
        Person person = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            person = session.get(Person.class, id);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
        return person;
    }

    @Override
    public void update(Person person) {
        create(person);
    }

    @Override
    public void delete(Person person) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(person);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}
