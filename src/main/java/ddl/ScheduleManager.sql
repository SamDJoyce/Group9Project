-- Create database
CREATE DATABASE ScheduleManager;

USE ScheduleManager;

-- Employee
CREATE TABLE employees (
	userId		int			NOT NULL UNIQUE AUTO_INCREMENT,
	name		varchar(50)	NOT NULL,
	email		varchar(50) NOT NULL,
	type		varchar(20)	NOT NULL,
	seniority	int			NULL,
	CONSTRAINT employees_PK PRIMARY KEY (userId)
);

-- Shifts Table
CREATE TABLE shifts (
	shiftId		int			NOT NULL UNIQUE AUTO_INCREMENT,
	start		timestamp	NOT NULL,
	end			timestamp	NOT NULL,
	employeeId	int			NULL,
	CONSTRAINT shifts_PK PRIMARY KEY (shiftId),
	CONSTRAINT employee_FK FOREIGN KEY (employeeId) REFERENCES employees (userId)
);

