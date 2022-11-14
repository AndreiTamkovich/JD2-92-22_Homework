package my.dao;

import my.model.Person;
import my.util.MysqlSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
            System.out.println("Person saved");
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public Person get(long id) {
        Person person = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            person = session.get(Person.class, id);
            System.out.println("Person found: " + person.toString());
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
        return person;
    }

    @Override
    public void delete(Person person) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(person);
            System.out.println("Person deleted");
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public Person load(long id) {
        Person person = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            person = session.load(Person.class, id);
            System.out.println("Person found by load: " + person.toString());
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
        return person;
    }

    @Override
    public void flush(long id, Integer age, String name, String surname) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Person person = session.get(Person.class, id);
            person.setAge(age);
            person.setName(name);
            person.setSurname(surname);
            session.flush();
            System.out.println("Flush applied");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}