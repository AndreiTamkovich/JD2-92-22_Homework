package task8_table_per_class.dao;

import task8_table_per_class.model.StudentTablePerClass;

public interface StudentTablePerClassDao {
    void create(StudentTablePerClass studentTablePerClass);

    StudentTablePerClass findById(Integer id);

    void update(StudentTablePerClass studentTablePerClass);

    void delete(StudentTablePerClass studentTablePerClass);
}
