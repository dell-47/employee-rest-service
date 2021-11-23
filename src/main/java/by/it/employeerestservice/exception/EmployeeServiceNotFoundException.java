package by.it.employeerestservice.exception;

public class EmployeeServiceNotFoundException extends RuntimeException{

    public EmployeeServiceNotFoundException(String message) {
        super(message);
    }
}
