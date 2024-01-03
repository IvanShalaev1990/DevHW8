INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES
('Rick Sanchez', '1945-01-01', 'Senior', 10000),
('Morty Smith', '2002-07-01', 'Trainee', 950),
('Summer Smith', '2000-03-03', 'Middle', 3500),
('Beth Smith', '1970-11-17', 'Senior', 7500),
('Jerry Smith', '1968-05-05', 'Junior', 300),
('Birdperson', '1978-08-25', 'Middle', 5000),
('Squanchy', '1980-12-10', 'Trainee', 1100),
('Mr. Meeseeks', '2010-02-20', 'Junior', 800),
('Unity', '1985-04-15', 'Middle', 4000),
('Evil Morty', '2002-07-01', 'Senior', 10000);

INSERT INTO client (NAME) VALUES
('Philip J. Fry'),
('Turanga Leela'),
('Bender Bending Rodriguez'),
('Professor Hubert J. Farnsworth'),
('Amy Wong');

INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE) VALUES
(1, '2023-01-01', '2023-02-28'),
(2, '2023-03-15', '2023-05-30'),
(3, '2023-06-01', '2023-08-15'),
(4, '2023-09-01', '2023-11-30'),
(5, '2024-01-01', '2024-03-31'),
(3, '2024-04-15', '2024-06-30'),
(1, '2024-07-01', '2024-09-30'),
(2, '2024-10-15', '2025-01-15'),
(5, '2025-02-01', '2025-04-30'),
(1, '2025-05-15', '2025-08-15');

INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(2,5),
(2,6),
(3,7),
(3,8),
(4,9),
(5,10),
(5,1),
(5,2),
(6,3),
(6,4),
(7,5),
(8,6),
(8,7),
(9,8),
(10,8);