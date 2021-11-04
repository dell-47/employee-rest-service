package by.it.employeerestservice.entity;

import lombok.*;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Long id;

    private String firstName;

    private String lastName;

    private Long departmentId;

    private String jobTitle;

    private Gender gender;

    private Date dateOfBirth;
}
