package by.it.employeerestservice.service;

import by.it.employeerestservice.dao.DepartmentDao;
import by.it.employeerestservice.dao.EmployeeDao;
import by.it.employeerestservice.dto.EmployeeRequestDto;
import by.it.employeerestservice.dto.EmployeeResponseDto;
import by.it.employeerestservice.entity.Department;
import by.it.employeerestservice.entity.Employee;
import by.it.employeerestservice.exception.EmployeeServiceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDao employeeDao;
    private final DepartmentDao departmentDao;

    public EmployeeResponseDto findById(Long id) {
        Employee employee = employeeDao.findById(id)
                .orElseThrow(() -> new EmployeeServiceNotFoundException("Employee not found [id = " + id + "]"));
        return new EmployeeResponseDto(employee);
    }

    public EmployeeResponseDto addNew(EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRequestDto.getEmployeeFromDto();
        Long departmentId = employeeRequestDto.getDepartmentId();
        Department department = departmentDao.findById(departmentId)
                .orElseThrow(() -> new EmployeeServiceNotFoundException("Department not found [id = " + departmentId + "]"));
        employee.setDepartment(department);
        Employee savedEmployee = employeeDao.save(employee);
        return new EmployeeResponseDto(savedEmployee);
    }

    public EmployeeResponseDto update(Long id, EmployeeRequestDto employeeRequestDto) {
        Long departmentId = employeeRequestDto.getDepartmentId();
        Employee employee = employeeRequestDto.getEmployeeFromDto();
        Department department = departmentDao.findById(departmentId)
                .orElseThrow(() -> new EmployeeServiceNotFoundException("Department not found [id = " + departmentId + "]"));
        employee.setDepartment(department);
        employee.setId(id);
        Employee updatedEmployee = employeeDao.save(employee);
        return new EmployeeResponseDto(updatedEmployee);
    }

    public List<EmployeeResponseDto> findAll() {
        return employeeDao.findAll()
                .stream()
                .map(EmployeeResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<EmployeeResponseDto> findByFilters(String firstName, String lastName) {
        return employeeDao.findByFirstNameContainsAndLastNameContains(firstName, lastName)
                .stream()
                .map(EmployeeResponseDto::new)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        employeeDao.deleteById(id);
    }
}