package by.it.employeerestservice.service;

import by.it.employeerestservice.dao.DepartmentDao;
import by.it.employeerestservice.dao.EmployeeDao;
import by.it.employeerestservice.dto.EmployeeRequestDto;
import by.it.employeerestservice.entity.Department;
import by.it.employeerestservice.entity.Employee;
import by.it.employeerestservice.exception.EmployeeServiceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.activemq.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

@Service
@RequiredArgsConstructor
public class EmployeeJmsService {

    private final JmsTemplate jmsTemplate;
    private final EmployeeDao employeeDao;
    private final DepartmentDao departmentDao;

    public void send(EmployeeRequestDto employeeRequestDto) {
        jmsTemplate.convertAndSend("test_queue", employeeRequestDto);
    }

    @JmsListener(destination = "test_queue")
    public void receive(Message message) throws JMSException {
        EmployeeRequestDto employeeRequestDto = (EmployeeRequestDto) ((ObjectMessage) message).getObject();
        Employee employee = employeeRequestDto.getEmployeeFromDto();
        Long departmentId = employeeRequestDto.getDepartmentId();
        Department department = departmentDao.findById(departmentId)
                .orElseThrow(() -> new EmployeeServiceNotFoundException("Department not found [id = " + departmentId + "]"));
        employee.setDepartment(department);
        employeeDao.save(employee);
    }
}
