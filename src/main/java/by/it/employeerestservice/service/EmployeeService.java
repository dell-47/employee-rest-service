package by.it.employeerestservice.service;

import by.it.employeerestservice.dao.DepartmentDao;
import by.it.employeerestservice.dao.EmployeeDao;
import by.it.employeerestservice.dto.EmployeeRequestDto;
import by.it.employeerestservice.dto.EmployeeResponseDto;
import by.it.employeerestservice.entity.Department;
import by.it.employeerestservice.entity.Employee;
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
        Employee employee = employeeDao.findById(id).get();
        return new EmployeeResponseDto(employee);
    }

    public EmployeeResponseDto addNew(EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRequestDto.getEmployeeFromDto();
        Long departmentId = employeeRequestDto.getDepartmentId();
        Department department = departmentDao.findById(departmentId).get();
        employee.setDepartment(department);
        Employee savedEmployee = employeeDao.save(employee);
        return new EmployeeResponseDto(savedEmployee);
    }

    public EmployeeResponseDto update(Long id, EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRequestDto.getEmployeeFromDto();
        Long departmentId = employeeRequestDto.getDepartmentId();
        Department department = departmentDao.findById(departmentId).get();
        employee.setDepartment(department);
        employee.setId(id);
        Employee updatedEmployee = employeeDao.save(employee);
        return new EmployeeResponseDto(updatedEmployee);
    }

    public List<EmployeeResponseDto> findAll() {
        List<Employee> employeeList = employeeDao.findAll();
        return employeeList.stream().map(EmployeeResponseDto::new).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        employeeDao.deleteById(id);
    }
}
