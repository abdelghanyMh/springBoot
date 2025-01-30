-- delete all data from the tables
DELETE
FROM Run;
-- Insert sample running data
INSERT INTO Run (id, title, started_on, completed_on, miles, location)
VALUES (1, 'Noon Run', '2024-02-20 06:05:00', '2024-02-20 10:27:00', 24, 'INDOOR'),
       (2, 'Afternoon Run', '2024-02-22 12:16:00', '2024-02-22 14:27:00', 6, 'OUTDOOR'),
       (3, 'Dawn Run', '2024-03-06 19:47:00', '2024-03-06 22:06:00', 19, 'INDOOR'),
       (4, 'Sunset Jog', '2024-03-31 08:32:00', '2024-03-31 12:57:00', 7, 'OUTDOOR'),
       (5, 'Sunset Jog', '2024-03-01 03:49:00', '2024-03-01 05:58:00', 11, 'INDOOR'),
       (6, 'Noon Run', '2024-03-02 21:02:00', '2024-03-03 00:36:00', 3, 'INDOOR'),
       (7, 'Sunset Jog', '2024-04-05 02:46:00', '2024-04-05 06:43:00', 8, 'INDOOR'),
       (8, 'Morning Run', '2024-03-29 06:37:00', '2024-03-29 09:19:00', 20, 'OUTDOOR'),
       (9, 'Sunset Jog', '2024-03-22 23:22:00', '2024-03-23 02:41:00', 4, 'INDOOR'),
       (10, 'Dawn Run', '2024-03-08 01:34:00', '2024-03-08 05:53:00', 23, 'OUTDOOR');