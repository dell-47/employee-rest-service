DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

CREATE TABLE department
(
    department_id   SERIAL PRIMARY KEY,
    department_title VARCHAR(250) NOT NULL
);

CREATE TABLE employee
(
    employee_id   SERIAL PRIMARY KEY,
    first_name    VARCHAR(250) NOT NULL,
    last_name     VARCHAR(250) NOT NULL,
    department_id INT       NOT NULL,
    job_title     VARCHAR(250) NOT NULL,
    gender        VARCHAR(8) NOT NULL,
    date_of_birth DATE NOT NULL,
    foreign key (department_id) references department (department_id)
);

INSERT INTO department (department_title)
VALUES ('Development'),
       ('Marketing');

INSERT INTO employee (first_name, last_name, department_id, job_title, gender, date_of_birth)
VALUES ('Ivan', 'Ivanov', 1, 'Senior developer', 'MALE', '2001-11-12');