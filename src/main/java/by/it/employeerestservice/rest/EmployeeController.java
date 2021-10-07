package by.it.employeerestservice.rest;

import by.it.employeerestservice.dto.EmployeeRequestDto;
import by.it.employeerestservice.dto.EmployeeResponseDto;
import by.it.employeerestservice.entity.Employee;
import by.it.employeerestservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeResponseDto>> findAll() {
        List<EmployeeResponseDto> employeeList = employeeService.findAll();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeResponseDto> addNew(@RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto newEmployee = employeeService.addNew(employeeRequestDto);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponseDto> getById(@PathVariable Long id) {
        EmployeeResponseDto employeeResponseDto = employeeService.findById(id);
        return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponseDto> update(@PathVariable Long id, @RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto updatedEmployee = employeeService.update(id, employeeRequestDto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.CREATED);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Employee> delete(@PathVariable Long id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchElementExceptionHandle() {
        String message = String.format("%s %s", new Timestamp(new Date().getTime()), "Bad request");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
