package task8_join.dao;

import task8_join.model.EmployeeJoin;

public interface EmployeeJoinDao {
    void create(EmployeeJoin employeeJoin);

    EmployeeJoin findById(Integer id);

    void update(EmployeeJoin employeeJoin);

    void delete(EmployeeJoin employeeJoin);
}
