package by.it.employeerestservice.dto;

import by.it.employeerestservice.entity.Employee;
import by.it.employeerestservice.entity.Gender;
import by.it.employeerestservice.validation.AgeValid;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
@Schema(description = "Employee DTO to request")
public class EmployeeRequestDto {
    private String firstName;
    private String lastName;
    private Long departmentId;
    private String jobTitle;
    private Gender gender;

    @AgeValid
    private Date dateOfBirth;

    public Employee getEmployeeFromDto() {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setJobTitle(jobTitle);
        employee.setGender(gender);
        employee.setDateOfBirth(dateOfBirth);
        return employee;
    }
}