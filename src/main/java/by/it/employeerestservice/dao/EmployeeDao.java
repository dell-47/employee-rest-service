package by.it.employeerestservice.dao;

import by.it.employeerestservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeDao extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstNameContainsAndLastNameContains(String firstName, String lastName);
}
