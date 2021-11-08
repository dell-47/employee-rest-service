package by.it.employeerestservice.rest;

import by.it.employeerestservice.dto.EmployeeRequestDto;
import by.it.employeerestservice.dto.EmployeeResponseDto;
import by.it.employeerestservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeResponseDto> findAll(@RequestParam(name = "last_name") Optional<String> lastName) {
        if (lastName.isPresent()) {
            return employeeService.findByLastName(lastName.get());
        }
        return employeeService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNew(@RequestBody EmployeeRequestDto employeeRequestDto) {
        employeeService.addNew(employeeRequestDto);
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto getById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody EmployeeRequestDto employeeRequestDto) {
        employeeService.update(id, employeeRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        employeeService.deleteById(id);
    }
}
