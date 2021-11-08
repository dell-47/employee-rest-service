package by.it.employeerestservice.exception;

import java.util.Date;

public class ServiceExceptionMessage {
    private final Date timestamp;
    private final int status;
    private final String message;
    private final String path;

    public ServiceExceptionMessage(int status, Date timestamp, String message, String path) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
