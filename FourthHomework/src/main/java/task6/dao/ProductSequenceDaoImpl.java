package task6.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import task6.model.ProductSequence;
import util.MysqlSessionFactory;

public class ProductSequenceDaoImpl implements ProductSequenceDao {
    private final SessionFactory sessionFactory;

    public ProductSequenceDaoImpl() {
        this(MysqlSessionFactory.getInstance());
    }

    public ProductSequenceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(ProductSequence productSequence) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(productSequence);
            session.flush();
            System.out.println("Persistent ID (Sequence generator): "
                    + session.getIdentifier(productSequence));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}
