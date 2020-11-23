INSERT INTO users (id, name, password,email,phone,active) VALUES (1, 'Shaimaa','123456', 'shaimaa@orange.com','010',true);
INSERT INTO users (id, name, password,email,phone,active) VALUES (2, 'Soso','654321', 'Soso@orange.com','010',true);

INSERT INTO tables (id, number_of_persons) VALUES (1, 5);
INSERT INTO tables (id, number_of_persons) VALUES (2, 2);

INSERT INTO reservations (id, date_time) VALUES (1, '2020-11-22 09:13:55');
INSERT INTO tables_reservation (table_id, reservation_id) VALUES (1, 1);
INSERT INTO users_reservation (user_id, reservation_id) VALUES (1, 1);