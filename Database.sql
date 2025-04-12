CREATE DATABASE company;

USE company;

CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(50),
    email VARCHAR(100)
);

INSERT INTO employees VALUES
(1, 'Alice', 'HR', 'alice@example.com'),
(2, 'Bob', 'IT', 'bob@example.com'),
(3, 'Charlie', 'Finance', 'charlie@example.com');
