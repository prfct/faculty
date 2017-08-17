INSERT INTO user (username, email, password, userRole) VALUES ('teacher1', 'teacher1@mail.ru', '123', 'ADMIN');
INSERT INTO user (username, email, password, userRole) VALUES ('teacher2', 'teacher2@mail.ru', '123', 'ADMIN');
INSERT INTO user (username, email, password, userRole) VALUES ('teacher3', 'teacher3@mail.ru', '123', 'ADMIN');
INSERT INTO teacher (user_id) VALUES (2);
INSERT INTO course (title, date, teacher_id) VALUES ('Math', '2017-08-09 16:18:11', 1);