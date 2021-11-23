package by.it.employeerestservice.rest;

import by.it.employeerestservice.dto.EmployeeRequestDto;
import by.it.employeerestservice.dto.EmployeeResponseDto;
import by.it.employeerestservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
@Tag(name = "Employees")
@Slf4j
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @Operation(summary = "Get all employees or employee by last name",
            description = "If param last_name presented - get employee by last name. Else - get all employees.")
    public List<EmployeeResponseDto> findByFilters(@RequestParam(name = "first_name", defaultValue = "")
                                             @Parameter(description = "Employee first name. Not required.")
                                                     String firstName,
                                             @RequestParam(name = "last_name", defaultValue = "")
                                             @Parameter(description = "Employee last name. Not required.")
                                                     String lastName) {
        log.info("IN: findByFilters - [first_name = {}, last_name = {}]", firstName, lastName);
        List<EmployeeResponseDto> employeeList = employeeService.findByFilters(firstName, lastName);
        log.info("OUT: findByFilters - [{}]", employeeList);
        return employeeList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new employee")
    public void addNew(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        log.info("IN: addNew - [{}]", employeeRequestDto);
        employeeService.addNew(employeeRequestDto);
        log.info("OUT: addNew - [No params]");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by id")
    public EmployeeResponseDto getById(@PathVariable @Min(1) Long id) {
        log.info("IN: getById - [id = {}]", id);
        EmployeeResponseDto employee = employeeService.findById(id);
        log.info("OUT: getById - [{}]", employee);
        return employee;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit employee")
    public void update(@PathVariable @Min(1) Long id, @RequestBody EmployeeRequestDto employeeRequestDto) {
        log.info("IN: update - [id = {}, {}]", id, employeeRequestDto);
        employeeService.update(id, employeeRequestDto);
        log.info("OUT: update - [No params]");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete employee")
    public void delete(@PathVariable @Min(1) Long id) {
        log.info("IN: delete - [id = {}]", id);
        employeeService.deleteById(id);
        log.info("OUT: delete - [No params]");
    }
}
