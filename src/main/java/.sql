SELECT *
FROM employee;

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
