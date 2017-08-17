CREATE TABLE IF NOT EXISTS user_role (
  user_role_id INT NOT NULL AUTO_INCREMENT,
  userType VARCHAR(64),
  PRIMARY KEY (user_role_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

INSERT INTO user_role (userType) VALUES ('admin');
INSERT INTO user_role (userType) VALUES ('student');
INSERT INTO user_role (userType) VALUES ('teacher');

CREATE TABLE IF NOT EXISTS user (
  user_id    INT NOT NULL AUTO_INCREMENT,
  username   VARCHAR(64),
  email      VARCHAR(64),
  password   VARCHAR(64),
  user_role_id INT,
  CONSTRAINT fk_user_role_user FOREIGN KEY (user_role_id) REFERENCES user_role (user_role_id),
  PRIMARY KEY (user_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS course (
  course_id  INT      NOT NULL AUTO_INCREMENT,
  title      VARCHAR(64),
  date       DATETIME NOT NULL,
  teacher_id INT,
  CONSTRAINT fk_teacher_course FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id),
  PRIMARY KEY (course_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS course_student (
  course_student_id INT NOT NULL AUTO_INCREMENT,
  course_id         INT,
  CONSTRAINT fk_course_course_student FOREIGN KEY (course_id) REFERENCES course (course_id),
  student_id        INT,
  CONSTRAINT fk_user_course_student FOREIGN KEY (student_id) REFERENCES student (student_id),
  mark              INT,
  feedback          VARCHAR(256),
  PRIMARY KEY (course_student_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

INSERT INTO user (username, email, password, userRole) VALUES ('admin', 'admin@mail.ru', '123', 'ADMIN');