DROP TABLE IF EXISTS faculties;

CREATE TABLE faculties (
    id UUID NOT NULL,
    name VARCHAR(50) NOT NULL,
    teacher VARCHAR(50) NOT NULL,
    actual_visitors INTEGER NOT NULL,
    max_visitors INTEGER NOT NULL,
    price_per_day NUMERIC(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO faculties(id, name, teacher, actual_visitors, max_visitors, price_per_day)
VALUES
    ('773dcbc0-d2fa-45b4-acf8-485b682adedd', 'Geography', 'Ivanov Pert Sidorovich', 7, 20, 6.72),
    ('8d8cfc84-e77c-4722-b4d6-8e9fdc17c721', 'Mathematics', 'Kobrina Daria Nikolaevna', 19, 25, 10.15)