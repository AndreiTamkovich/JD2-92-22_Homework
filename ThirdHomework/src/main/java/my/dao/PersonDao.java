package my.dao;

import my.model.Person;

public interface PersonDao {
    void create(Person person);
    Person get(long id);
    void delete(Person person);
    Person load(long id);
    void flush(long id, Integer age, String name, String surname);
}
