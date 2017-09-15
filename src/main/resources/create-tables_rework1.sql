CREATE SCHEMA `faculty`
  DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS user (
  user_id   INT         NOT NULL AUTO_INCREMENT,
  username  VARCHAR(64) NOT NULL,
  birthDate DATETIME    NOT NULL,
  PRIMARY KEY (user_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS auth (
  auth_id  INT                      NOT NULL AUTO_INCREMENT,
  email    VARCHAR(64) UNIQUE       NOT NULL,
  password VARCHAR(64)              NOT NULL,
  user_id  INT UNIQUE               NOT NULL,
  PRIMARY KEY (auth_id),
  FOREIGN KEY fk_auth_user (user_id) REFERENCES user (user_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;
``
CREATE TABLE IF NOT EXISTS role (
  role_id INT                NOT NULL AUTO_INCREMENT,
  role    VARCHAR(64) UNIQUE NOT NULL,
  PRIMARY KEY (role_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS auth_role (
  role_id INT NOT NULL,
  auth_id INT NOT NULL,
  FOREIGN KEY fk_role_auth (role_id) REFERENCES role (role_id),
  FOREIGN KEY fk_auth_role (auth_id) REFERENCES auth (auth_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS course (
  course_id INT      NOT NULL AUTO_INCREMENT,
  title     VARCHAR(64),
  date      DATETIME NOT NULL,
  user_id   INT,
  CONSTRAINT fk_user_course FOREIGN KEY (user_id) REFERENCES user (user_id),
  PRIMARY KEY (course_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS students (
  student_id INT NOT NULL AUTO_INCREMENT,
  course_id  INT,
  CONSTRAINT fk_course_student FOREIGN KEY (course_id) REFERENCES course (course_id),
  user_id    INT,
  CONSTRAINT fk_user_student FOREIGN KEY (user_id) REFERENCES user (user_id),
  mark       INT,
  feedback   VARCHAR(256),
  PRIMARY KEY (student_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;


INSERT INTO user (username, birthDate) VALUES ('admin', STR_TO_DATE('17/07/2013', '%d/%m/%Y'));
INSERT INTO auth (email, password, user_id) VALUES ('admin', 'admin', 1);
INSERT INTO role (role) VALUES ('ADMIN');
INSERT INTO role (role) VALUES ('STUDENT');
INSERT INTO role (role) VALUES ('TEACHER');
INSERT INTO auth_role (role_id, auth_id) VALUES (1, 1);
