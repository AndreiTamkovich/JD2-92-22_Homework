package task8_join.dao;

import task8_join.model.StudentJoin;

public interface StudentJoinDao {
    void create(StudentJoin studentJoin);

    StudentJoin findById(Integer id);

    void update(StudentJoin studentJoin);

    void delete(StudentJoin studentJoin);
}
