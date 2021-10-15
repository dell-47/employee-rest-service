package by.it.employeerestservice.service;

import by.it.employeerestservice.dao.impl.EmployeeDaoImpl;
import by.it.employeerestservice.dto.EmployeeResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static by.it.employeerestservice.service.EmployeeServiceTestDataProvider.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeDaoImpl mockedEmployeeDao;


    @Test
    public void whenFindByValidId_thenEmployeeShouldBeFound() {
        when(mockedEmployeeDao.findById(EXISTING_ID)).thenReturn(EXPECTED_EMPLOYEE);
        EmployeeResponseDto found = employeeService.findById(EXISTING_ID);
        assertEquals(EXPECTED_EMPLOYEE_DTO, found);
    }

    @Test
    public void whenFindByInvalidId_thenExceptionShouldBeThrown() {
        when(mockedEmployeeDao.findById(NOT_EXISTING_ID)).thenThrow(EmptyResultDataAccessException.class);
        assertThrows(EmptyResultDataAccessException.class, () -> employeeService.findById(NOT_EXISTING_ID));
    }

    @Test
    public void whenFindAll_thenListOfEmployeeShouldBeReturned() {
        when(mockedEmployeeDao.findAll()).thenReturn(EXPECTED_EMPLOYEE_LIST);
        List<EmployeeResponseDto> found = employeeService.findAll();
        assertEquals(EXPECTED_EMPLOYEE_LIST_DTO, found);
    }
}
