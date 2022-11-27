package task8_table_per_class.dao;

import task8_table_per_class.model.EmployeeTablePerClass;

public interface EmployeeTablePerClassDao {
    void create(EmployeeTablePerClass employeeTablePerClass);

    EmployeeTablePerClass findById(Integer id);

    void update(EmployeeTablePerClass employeeTablePerClass);

    void delete(EmployeeTablePerClass employeeTablePerClass);
}
