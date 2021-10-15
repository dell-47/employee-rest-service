package by.it.employeerestservice.rest;

import by.it.employeerestservice.dto.EmployeeRequestDto;
import by.it.employeerestservice.dto.EmployeeResponseDto;
import by.it.employeerestservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> findAll() {
        List<EmployeeResponseDto> employeeList = employeeService.findAll();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNew(@RequestBody EmployeeRequestDto employeeRequestDto) {
        employeeService.addNew(employeeRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getById(@PathVariable Long id) {
        EmployeeResponseDto employeeResponseDto = employeeService.findById(id);
        return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EmployeeRequestDto employeeRequestDto) {
        employeeService.update(id, employeeRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> emptyResultHandle() {
        String message = String.format("%s %s", new Timestamp(new Date().getTime()), "Bad request");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
