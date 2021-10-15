package by.it.employeerestservice.dao;

import by.it.employeerestservice.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee findById(Long id);

    List<Employee> findAll();

    int save(Employee employee);

    int update(Employee employee);

    int deleteById(Long id);
}
