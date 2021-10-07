package by.it.employeerestservice.service;

import by.it.employeerestservice.dao.DepartmentDao;
import by.it.employeerestservice.dao.EmployeeDao;
import by.it.employeerestservice.dto.EmployeeResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static by.it.employeerestservice.service.EmployeeServiceTestDataProvider.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeDao mockedEmployeeDao;

    @MockBean
    private DepartmentDao mockedDepartmentDao;

    @Test
    public void whenFindByValidId_thenEmployeeShouldBeFound() {
        when(mockedEmployeeDao.findById(EXISTING_ID)).thenReturn(Optional.ofNullable(EXPECTED_EMPLOYEE));
        EmployeeResponseDto found = employeeService.findById(EXISTING_ID);
        assertEquals(EXPECTED_EMPLOYEE_DTO, found);
    }

    @Test
    public void whenFindByInvalidId_thenExceptionShouldBeThrown() {
        when(mockedEmployeeDao.findById(NOT_EXISTING_ID)).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class, () -> employeeService.findById(NOT_EXISTING_ID));
    }

    @Test
    public void whenFindAll_thenListOfEmployeeShouldBeReturned() {
        when(mockedEmployeeDao.findAll()).thenReturn(EXPECTED_EMPLOYEE_LIST);
        List<EmployeeResponseDto> found = employeeService.findAll();
        assertEquals(EXPECTED_EMPLOYEE_LIST_DTO, found);
    }

    @Test
    public void whenAddNew_thenNewEmployeeShouldBeSaved() {
        when(mockedEmployeeDao.save(DEFAULT_EMPLOYEE)).thenReturn(EXPECTED_EMPLOYEE);
        when(mockedDepartmentDao.findById(DEFAULT_DEPARTMENT_ID)).thenReturn(Optional.ofNullable(EXPECTED_DEPARTMENT));
        EmployeeResponseDto found = employeeService.addNew(DEFAULT_EMPLOYEE_DTO);
        assertEquals(EXPECTED_EMPLOYEE_DTO, found);
    }
}
