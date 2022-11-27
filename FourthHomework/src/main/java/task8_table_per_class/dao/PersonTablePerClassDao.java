package task8_table_per_class.dao;

import task8_table_per_class.model.PersonTablePerClass;

public interface PersonTablePerClassDao {
    void create(PersonTablePerClass personTablePerClass);

    PersonTablePerClass findById(Integer id);

    void update(PersonTablePerClass personTablePerClass);

    void delete(PersonTablePerClass personTablePerClass);
}
