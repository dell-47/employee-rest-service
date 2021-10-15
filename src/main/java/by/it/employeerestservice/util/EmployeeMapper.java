package by.it.employeerestservice.util;

import by.it.employeerestservice.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Employee.builder()
                .id(rs.getLong("employee_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .departmentId(rs.getLong("department_id"))
                .jobTitle(rs.getString("job_title"))
                .gender(rs.getString("gender"))
                .dateOfBirth(rs.getDate("date_of_birth"))
                .build();
    }
}
