INSERT INTO user (username, birthDate) VALUES ('admin', STR_TO_DATE('17/07/2013', '%d/%m/%Y'));
INSERT INTO auth (email, password, userRole, user_id) VALUES ('admin', 'admin', 'ADMIN', 1);

INSERT INTO user (username, birthDate) VALUES ('teacher', STR_TO_DATE('5/01/1988', '%d/%m/%Y'));
INSERT INTO auth (email, password, userRole, user_id) VALUES ('teacher', 'teacher', 'TEACHER', 2);

INSERT INTO user (username, birthDate) VALUES ('oleg', STR_TO_DATE('13/11/2000', '%d/%m/%Y'));
INSERT INTO auth (email, password, userRole, user_id) VALUES ('student', 'student', 'STUDENT', 3);

INSERT INTO user (username, birthDate) VALUES ('petya', STR_TO_DATE('06/09/2001', '%d/%m/%Y'));
INSERT INTO auth (email, password, userRole, user_id) VALUES ('petya@mail.ru', 'student', 'STUDENT', 4);

INSERT INTO user (username, birthDate) VALUES ('mira', STR_TO_DATE('03/07/1984', '%d/%m/%Y'));
INSERT INTO auth (email, password, userRole, user_id) VALUES ('mira@mail.ru', 'teacher', 'TEACHER', 5);

INSERT INTO user (username, birthDate) VALUES ('volodya', STR_TO_DATE('21/06/1989', '%d/%m/%Y'));
INSERT INTO auth (email, password, userRole, user_id) VALUES ('volodya@mail.ru', 'student', 'STUDENT', 6);

INSERT INTO user (username, birthDate) VALUES ('katya', STR_TO_DATE('11/02/1995', '%d/%m/%Y'));
INSERT INTO auth (email, password, userRole, user_id) VALUES ('katya@mail.ru', 'teacher', 'TEACHER', 7);

INSERT INTO user (username, birthDate) VALUES ('gek', STR_TO_DATE('15/04/1998', '%d/%m/%Y'));
INSERT INTO auth (email, password, userRole, user_id) VALUES ('gek@mail.ru', 'student', 'STUDENT', 8);