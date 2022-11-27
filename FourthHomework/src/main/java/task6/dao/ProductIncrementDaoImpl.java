package task6.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import task6.model.ProductIncrement;
import util.MysqlSessionFactory;

public class ProductIncrementDaoImpl implements ProductIncrementDao {
    private final SessionFactory sessionFactory;

    public ProductIncrementDaoImpl() {
        this(MysqlSessionFactory.getInstance());
    }

    public ProductIncrementDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(ProductIncrement productIncrement) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(productIncrement);
            session.flush();
            System.out.println("Persistent ID (Increment generator): "
                    + session.getIdentifier(productIncrement));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}
