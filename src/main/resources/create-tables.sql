CREATE TABLE IF NOT EXISTS user (
  user_id  INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(64),
  email    VARCHAR(64),
  password VARCHAR(64),
  userRole VARCHAR(64),
  PRIMARY KEY (user_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS teacher (
  teacher_id INT NOT NULL AUTO_INCREMENT,
  user_id    INT UNIQUE,
  CONSTRAINT fk_user_teacher FOREIGN KEY (user_id) REFERENCES user (user_id),
  PRIMARY KEY (teacher_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS student (
  student_id INT NOT NULL AUTO_INCREMENT,
  user_id    INT UNIQUE,
  CONSTRAINT fk_user_student FOREIGN KEY (user_id) REFERENCES user (user_id),
  PRIMARY KEY (student_id)
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
  CONSTRAINT fk_course_course_participant FOREIGN KEY (course_id) REFERENCES course (course_id),
  student_id        INT,
  CONSTRAINT fk_user_course_participant FOREIGN KEY (student_id) REFERENCES student (student_id),
  mark              INT,
  feedback          VARCHAR(256),
  PRIMARY KEY (course_student_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

INSERT INTO user (username, email, password, userRole) VALUES ('admin', 'admin@mail.ru', '123', 'ADMIN');