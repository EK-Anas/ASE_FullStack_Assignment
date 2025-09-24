create table users(
  id bigint AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT null unique,
  phone VARCHAR(255) NOT null unique,
  role ENUM("STUDENT", "TEACHER") NOT NULL
)


create table students(
  student_id bigint,
  course VARCHAR(255) NOT NULL,
  FOREIGN KEY (student_id) REFERENCES users(id)
);

create table teachers(
  employee_id bigint,
  department VARCHAR(255) NOT NULL,
  FOREIGN KEY (employee_id) REFERENCES users(id)
);