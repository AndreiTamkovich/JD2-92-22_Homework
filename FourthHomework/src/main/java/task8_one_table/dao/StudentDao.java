package task8_one_table.dao;

import task8_one_table.model.Student;

public interface StudentDao {
    void create(Student student);

    Student findById(Integer id);

    void update(Student student);

    void delete(Student student);
}
