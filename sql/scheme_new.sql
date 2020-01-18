SET NAMES utf8;

DROP DATABASE IF EXISTS exam_system;
CREATE DATABASE exam_system CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

USE exam_system;

-- -----------------------------------------------------
-- Table `exam_system`.`difficulties`
-- -----------------------------------------------------
CREATE TABLE exam_system.difficulties(
id INTEGER NOT NULL PRIMARY KEY,
name VARCHAR(45) NOT NULL UNIQUE);

-- -----------------------------------------------------
-- Table `exam_system`.`language`
-- -----------------------------------------------------
CREATE TABLE exam_system.language (
  id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(45) NOT NULL UNIQUE);

-- -----------------------------------------------------
-- Table `exam_system`.`subjects`
-- -----------------------------------------------------
CREATE TABLE subjects (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  lang_id INTEGER NULL,
  FOREIGN KEY (lang_id) REFERENCES exam_system.language (id) ON DELETE RESTRICT ON UPDATE CASCADE,
  UNIQUE (name));



-- -----------------------------------------------------
-- Table `exam_system`.`tests`
-- -----------------------------------------------------
CREATE TABLE exam_system.tests (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  subject_id INTEGER NOT NULL,
  difficulty_id INTEGER NOT NULL,
  time INTEGER NOT NULL,
   FOREIGN KEY (difficulty_id) REFERENCES exam_system.difficulties (id) ON DELETE RESTRICT ON UPDATE CASCADE,
   FOREIGN KEY (subject_id) REFERENCES exam_system.subjects (id) ON DELETE CASCADE ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `exam_system`.`questions`
-- -----------------------------------------------------
CREATE TABLE exam_system.questions (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  content VARCHAR(2000) NOT NULL,
  test_id INTEGER NOT NULL,
  FOREIGN KEY (test_id) REFERENCES exam_system.tests (id) ON DELETE CASCADE ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `exam_system`.`answers`
-- -----------------------------------------------------
CREATE TABLE exam_system.answers (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  content VARCHAR(150) NOT NULL,
  correct TINYINT NULL DEFAULT '0',
  questions_id INTEGER NOT NULL,
  FOREIGN KEY (questions_id) REFERENCES exam_system.questions (id) ON DELETE CASCADE ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `exam_system`.`roles`
-- -----------------------------------------------------
CREATE TABLE exam_system.roles (
  id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(10) NOT NULL,
  UNIQUE (name));
 
-- -----------------------------------------------------
-- Table `exam_system`.`users`
-- -----------------------------------------------------
CREATE TABLE exam_system.users (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(20) NOT NULL,
  password VARCHAR(20) NOT NULL,
  first_name VARCHAR(20) NOT NULL,
  last_name VARCHAR(20) NOT NULL,
  email VARCHAR(30) NOT NULL,
  role_id INTEGER NOT NULL,
  FOREIGN KEY (role_id) REFERENCES exam_system.roles (id) ON DELETE RESTRICT ON UPDATE CASCADE,
  UNIQUE (login));

-- -----------------------------------------------------
-- Table `exam_system`.`users_tests`
-- -----------------------------------------------------
CREATE TABLE exam_system.users_tests (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  users_id INTEGER NOT NULL,
  tests_id INTEGER NOT NULL,
  test_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  test_result INTEGER NOT NULL,
  FOREIGN KEY (users_id) REFERENCES exam_system.users (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (tests_id) REFERENCES exam_system.tests (id) ON DELETE CASCADE ON UPDATE CASCADE);
  
-- -----------------------------------------------------
-- Create Tests view
-- -----------------------------------------------------
CREATE VIEW `tests_view` (id, name, subject_id, difficulty_id, time, questions_count) AS
SELECT tests.id, tests.name, tests.subject_id, tests.difficulty_id, tests.time, COUNT(questions.test_id)
FROM tests
LEFT JOIN questions ON tests.id= questions.test_id
GROUP BY tests.name;

-- -----------------------------------------------------
-- Create Users_Tests view
-- -----------------------------------------------------
CREATE VIEW `users_tests_view` (id, login, subject_name, test_name,
 test_time, test_result) AS
SELECT users.id, users.login, subjects.name,
tests.name, users_tests.test_time, users_tests.test_result
FROM users_tests
JOIN users ON users.id= users_tests.users_id
JOIN tests
JOIN subjects ON subjects.id = tests.subject_id
ON tests.id = users_tests.tests_id;
  

