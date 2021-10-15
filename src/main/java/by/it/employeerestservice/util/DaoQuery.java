package by.it.employeerestservice.util;

public final class DaoQuery {

    private DaoQuery() {
    }

    public static final String FIND_BY_ID_QUERY = "SELECT * FROM employee WHERE employee_id = ?";
    public static final String FIND_ALL_QUERY = "SELECT * FROM employee";
    public static final String SAVE_QUERY = "INSERT INTO employee (first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_QUERY = "UPDATE employee SET first_name = ?, last_name = ?, department_id = ?, job_title = ?, gender = ?, date_of_birth = ? WHERE employee_id = ?";
    public static final String DELETE_QUERY = "DELETE FROM employee WHERE employee_id = ?";
}
