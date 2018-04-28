drop table employee;
drop table position;

CREATE TABLE position ( id int PRIMARY KEY AUTO_INCREMENT, name varchar(255) );

CREATE TABLE employee
(
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  surname varchar(255) NOT NULL,
  hire_date date NOT NULL,
  salary double,
  position_id int,
  CONSTRAINT position_fk_id FOREIGN KEY (position_id) REFERENCES position (id)
);