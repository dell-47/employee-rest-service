package by.it.employeerestservice.dao;

import by.it.employeerestservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDao extends JpaRepository<Department, Long> {
}
