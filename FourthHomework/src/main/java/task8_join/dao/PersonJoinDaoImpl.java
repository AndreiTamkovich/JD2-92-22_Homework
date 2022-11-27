package task8_join.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import task8_join.model.PersonJoin;
import util.MysqlSessionFactory;

public class PersonJoinDaoImpl implements PersonJoinDao {
    private final SessionFactory sessionFactory;

    public PersonJoinDaoImpl() {
        this(MysqlSessionFactory.getInstance());
    }

    public PersonJoinDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(PersonJoin personJoin) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(personJoin);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }


    @Override
    public PersonJoin findById(Integer id) {
        PersonJoin personJoin = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            personJoin = session.get(PersonJoin.class, id);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
        return personJoin;
    }

    @Override
    public void update(PersonJoin personJoin) {
        create(personJoin);
    }

    @Override
    public void delete(PersonJoin personJoin) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(personJoin);
            session.getTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}
