package by.it.employeerestservice.dao;

import by.it.employeerestservice.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    Optional<Employee> findById(Long id);

    List<Employee> findAll();

    int save(Employee employee);

    int update(Employee employee);

    int deleteById(Long id);
}
