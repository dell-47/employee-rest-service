package by.it.employeerestservice.service;

import by.it.employeerestservice.dao.impl.EmployeeDaoImpl;
import by.it.employeerestservice.dto.EmployeeRequestDto;
import by.it.employeerestservice.dto.EmployeeResponseDto;
import by.it.employeerestservice.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeDaoImpl employeeDaoImpl;

    public EmployeeResponseDto findById(Long id) {
        Employee employee = employeeDaoImpl.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        return new EmployeeResponseDto(employee);
    }

    public List<EmployeeResponseDto> findAll() {
        List<Employee> employeeList = employeeDaoImpl.findAll();
        return employeeList.stream().map(EmployeeResponseDto::new).collect(Collectors.toList());
    }

   public void addNew(EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRequestDto.getEmployeeFromDto();
        employeeDaoImpl.save(employee);
    }

     public void update(Long id, EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRequestDto.getEmployeeFromDto();
        employee.setId(id);
        employeeDaoImpl.update(employee);
    }

    public void deleteById(Long id) {
        employeeDaoImpl.deleteById(id);
    }
}
