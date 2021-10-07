package by.it.employeerestservice.dao;

import by.it.employeerestservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
}
