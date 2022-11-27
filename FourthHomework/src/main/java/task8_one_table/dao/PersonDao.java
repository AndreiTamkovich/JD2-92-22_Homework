package task8_one_table.dao;

import task8_one_table.model.Person;

public interface PersonDao {
    void create(Person person);

    Person findById(Integer id);

    void update(Person person);

    void delete(Person person);

}
