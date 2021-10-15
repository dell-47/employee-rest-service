package by.it.employeerestservice.dto;

import by.it.employeerestservice.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long department;
    private String jobTitle;
    private String gender;
    private Date dateOfBirth;

    public EmployeeResponseDto(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.department = employee.getDepartmentId();
        this.jobTitle = employee.getJobTitle();
        this.gender = employee.getGender();
        this.dateOfBirth = employee.getDateOfBirth();
    }
}
