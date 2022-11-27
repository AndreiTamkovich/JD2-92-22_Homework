package task8_join.dao;

import task8_join.model.PersonJoin;

public interface PersonJoinDao {
    void create(PersonJoin personJoin);

    PersonJoin findById(Integer id);

    void update(PersonJoin personJoin);

    void delete(PersonJoin personJoin);
}
