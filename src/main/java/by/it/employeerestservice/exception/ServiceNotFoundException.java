package by.it.employeerestservice.exception;

public class ServiceNotFoundException extends RuntimeException{

    public ServiceNotFoundException(String message) {
        super(message);
    }
}
