package by.it.employeerestservice.rest;

import by.it.employeerestservice.dto.EmployeeRequestDto;
import by.it.employeerestservice.dto.EmployeeResponseDto;
import by.it.employeerestservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
@Tag(name = "Employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @Operation(summary = "Get all employees or employee by last name",
               description = "If param last_name presented - get employee by last name. Else - get all employees.")
    public List<EmployeeResponseDto> findAll(@RequestParam(name = "last_name")
                                             @Parameter(description = "Employee last name. Not required.")
                                             Optional<String> lastName) {
        if (lastName.isPresent()) {
            return employeeService.findByLastName(lastName.get());
        }
        return employeeService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new employee")
    public void addNew(@RequestBody EmployeeRequestDto employeeRequestDto) {
        employeeService.addNew(employeeRequestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by id")
    public EmployeeResponseDto getById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit employee")
    public void update(@PathVariable Long id, @RequestBody EmployeeRequestDto employeeRequestDto) {
        employeeService.update(id, employeeRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete employee")
    public void delete(@PathVariable Long id) {
        employeeService.deleteById(id);
    }
}
