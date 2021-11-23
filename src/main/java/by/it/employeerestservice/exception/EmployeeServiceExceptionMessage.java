package by.it.employeerestservice.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class EmployeeServiceExceptionMessage {
    private final Date timestamp;
    private final int status;
    private final String message;
    private final String path;
}
