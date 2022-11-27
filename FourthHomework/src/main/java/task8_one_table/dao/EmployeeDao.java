package task8_one_table.dao;

import task8_one_table.model.Employee;

public interface EmployeeDao {
    void create(Employee employee);

    Employee findById(Integer id);

    void update(Employee employee);

    void delete(Employee employee);
}
