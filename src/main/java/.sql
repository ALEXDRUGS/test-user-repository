SELECT *
FROM employee;

SELECT *
FROM city;

SELECT first_name AS имя,
       last_name  AS фамилия
FROM employee;

SELECT first_name, last_name, gender, age
FROM employee
WHERE age < 30
   OR age > 50;

SELECT first_name, last_name, gender, age
FROM employee
WHERE age > 30
  AND age < 50;

SELECT *
FROM employee
ORDER BY last_name DESC;

SELECT *
FROM employee
WHERE length(first_name) > 4;

UPDATE employee
SET first_name = 'Kurt'
WHERE id = 7;

UPDATE employee
SET first_name = 'Monica'
WHERE id = 2;

SELECT first_name AS Имя,
       sum(age)   AS Суммарный_возраст
FROM employee
GROUP BY Имя
HAVING count(first_name) > 1;

SELECT first_name AS Имя,
       min(age)   AS Минимальный_возраст
FROM employee
GROUP BY first_name;

SELECT first_name AS Имя,
       max(age)   AS Максимальный_возраст
FROM employee
GROUP BY Имя
HAVING count(first_name) > 1
ORDER BY Максимальный_возраст;

CREATE TABLE city
(
    city_id   BIGSERIAL NOT NULL PRIMARY KEY,
    city_name VARCHAR(50)
);

ALTER TABLE employee
    ADD city_id INT;

ALTER TABLE employee
    ADD FOREIGN KEY (city_id)
        REFERENCES city (city_id);

INSERT INTO city(city_name)
VALUES ('Berlin');

INSERT INTO city(city_name)
VALUES ('Augsburg');

INSERT INTO city(city_name)
VALUES ('Bremen');

INSERT INTO city(city_name)
VALUES ('Hamburg');

INSERT INTO city(city_name)
VALUES ('Leipzig');

UPDATE employee
SET city_id = 1
WHERE id = 1;

UPDATE employee
SET city_id = 2
WHERE id = 2;

UPDATE employee
SET city_id = 3
WHERE id = 5;

UPDATE employee
SET city_id = 4
WHERE id = 7;

UPDATE employee
SET city_id = 5
WHERE id = 12;

SELECT first_name, last_name, city_name
FROM employee e
         JOIN city c
              ON e.city_id = c.city_id;

INSERT INTO employee(first_name, last_name, gender, age, city_id)
VALUES ('Kurt', 'Russell', 'male', 71, null);

SELECT city_name, first_name, last_name
FROM city c
         JOIN employee e
              ON c.city_id = e.city_id;