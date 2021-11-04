package by.it.employeerestservice.service;

import by.it.employeerestservice.dto.EmployeeResponseDto;
import by.it.employeerestservice.entity.Employee;
import by.it.employeerestservice.entity.Gender;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

public class EmployeeServiceTestDataProvider {

    public static final Long EXISTING_ID = 3L;
    public static final Long NOT_EXISTING_ID = 22L;
    private static final String DEFAULT_FIRST_NAME = "FirstName";
    private static final String DEFAULT_LAST_NAME = "LastName";
    private static final String DEFAULT_JOB_TITLE = "Developer";
    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Date DEFAULT_DATE_OF_BIRTH = new Date(946728000000L);
    public static final Long DEFAULT_DEPARTMENT_ID = 1L;

    public static final Employee EXPECTED_EMPLOYEE = generateExpectedEmployee();
    public static final EmployeeResponseDto EXPECTED_EMPLOYEE_DTO = generateExpectedEmployeeDto();
    public static final List<Employee> EXPECTED_EMPLOYEE_LIST = generateExpectedEmployeeList();
    public static final List<EmployeeResponseDto> EXPECTED_EMPLOYEE_LIST_DTO = generateExpectedEmployeeListDto();

    private static Employee generateExpectedEmployee() {
        return Employee
                .builder()
                .id(EXISTING_ID)
                .firstName(DEFAULT_FIRST_NAME)
                .lastName(DEFAULT_LAST_NAME)
                .departmentId(DEFAULT_DEPARTMENT_ID)
                .jobTitle(DEFAULT_JOB_TITLE)
                .gender(DEFAULT_GENDER)
                .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
                .build();
    }

    private static EmployeeResponseDto generateExpectedEmployeeDto() {
        return EmployeeResponseDto
                .builder()
                .id(EXISTING_ID)
                .firstName(DEFAULT_FIRST_NAME)
                .lastName(DEFAULT_LAST_NAME)
                .department(DEFAULT_DEPARTMENT_ID)
                .jobTitle(DEFAULT_JOB_TITLE)
                .gender(DEFAULT_GENDER)
                .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
                .build();
    }

    private static List<Employee> generateExpectedEmployeeList() {
        return Collections.singletonList(EXPECTED_EMPLOYEE);
    }

    private static List<EmployeeResponseDto> generateExpectedEmployeeListDto() {
        return Collections.singletonList(EXPECTED_EMPLOYEE_DTO);
    }
}
