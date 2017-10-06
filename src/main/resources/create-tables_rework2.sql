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
  userRole VARCHAR(64)              NOT NULL,
  user_id  INT UNIQUE               NOT NULL,
  PRIMARY KEY (auth_id),
  FOREIGN KEY fk_auth_user (user_id) REFERENCES user (user_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS course (
  course_id     INT      NOT NULL AUTO_INCREMENT,
  title         VARCHAR(64),
  create_date   DATETIME NOT NULL,
  course_status BOOLEAN,
  user_id       INT,
  CONSTRAINT fk_user_course FOREIGN KEY (user_id) REFERENCES user (user_id),
  PRIMARY KEY (course_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS student (
  student_id INT NOT NULL AUTO_INCREMENT,
  course_id  INT,
  CONSTRAINT fk_course_student FOREIGN KEY (course_id) REFERENCES course (course_id),
  user_id    INT,
  CONSTRAINT fk_user_student FOREIGN KEY (user_id) REFERENCES user (user_id),
  mark       INT,
  feedback   VARCHAR(512),
  PRIMARY KEY (student_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;


