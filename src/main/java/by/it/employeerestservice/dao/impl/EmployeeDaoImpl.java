package by.it.employeerestservice.dao.impl;

import by.it.employeerestservice.dao.EmployeeDao;
import by.it.employeerestservice.entity.Employee;
import by.it.employeerestservice.util.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static by.it.employeerestservice.util.DaoQuery.*;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Employee findById(Long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, new EmployeeMapper(), id);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY, new EmployeeMapper());
    }

    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update(
                SAVE_QUERY,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender(),
                employee.getDateOfBirth());
    }

    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update(
                UPDATE_QUERY,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(), employee.getGender(),
                employee.getDateOfBirth(), employee.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(DELETE_QUERY, id);
    }
}
