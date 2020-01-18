-- ==============================================================
-- Database creation script for MySQL
-- ==============================================================

SET NAMES utf8;

DROP DATABASE IF EXISTS exam_system;
CREATE DATABASE exam_system CHARACTER SET utf8 COLLATE utf8_bin;

USE exam_system;

-- --------------------------------------------------------------
-- ROLES
-- users roles
-- --------------------------------------------------------------

CREATE TABLE roles(
id INTEGER NOT NULL PRIMARY KEY,
name VARCHAR(10) NOT NULL UNIQUE);

INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'student');

-- --------------------------------------------------------------
-- USERS
-- --------------------------------------------------------------

CREATE TABLE users(

id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
login VARCHAR(20) NOT NULL UNIQUE,
password VARCHAR(20) NOT NULL,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
email VARCHAR(30) NOT NULL,
state BOOLEAN DEFAULT FALSE,
role_id INTEGER NOT NULL REFERENCES roles(id) ON DELETE CASCADE	ON UPDATE RESTRICT);

INSERT into USERS values(default,'admin','passwordAdmin','Dmytro','Biloborodov','dmwhiteb@gmail.com',default,0);
INSERT into USERS values(default,'PPetrov','password1','Petr','Petrov','petrov@mail.ru',default,1);
INSERT into USERS values(default,'IIvanov','password2','Ivan','Ivanov','ivanov@mail.ru',true,1);


-- --------------------------------------------------------------
-- SUBJECTS
-- --------------------------------------------------------------
CREATE TABLE subjects (
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL UNIQUE);

INSERT into subjects values(default,'Mathematics');
INSERT into subjects values(default,'Physics');
INSERT into subjects values(default,'English');

-- --------------------------------------------------------------
-- TESTS
-- --------------------------------------------------------------
CREATE TABLE tests(
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
subject_id INTEGER NOT NULL REFERENCES subject(id) ON DELETE CASCADE,
difficulty_id VARCHAR(20) NOT NULL REFERENCES difficulties(id) ON DELETE CASCADE,
questions_count INTEGER NOT NULL DEFAULT 0,
test_time INTEGER NOT NULL);

INSERT into tests values(default,'Operations',1,1, 4, 500);
INSERT into tests values(default,'Numbers',2,1, 3, 300);
INSERT into tests values(default,'Integrals',3,1, 3, 400);

INSERT into tests values(default,'Optics',1,2, 3, 10);
INSERT into tests values(default,'Mechanics',2,2, 3, 10);
INSERT into tests values(default,'Nuclear physics',3,2, 3, 10);

INSERT into tests values(default,'Pronouns',1,3, 3, 10);
INSERT into tests values(default,'Articles',2,3, 3, 10);
INSERT into tests values(default,'Nouns',3,3, 3, 10);

-- --------------------------------------------------------------
-- DIFFICULTIES
-- --------------------------------------------------------------
CREATE TABLE difficulties(
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL UNIQUE);

INSERT into difficulties values(default,'advanced');
INSERT into difficulties values(default,'elementary');
INSERT into difficulties values(default,'proficient');

-- --------------------------------------------------------------
-- QUESTIONS
-- --------------------------------------------------------------
CREATE TABLE questions(
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
content VARCHAR (2000) NOT NULL,
tests_id INTEGER NOT NULL REFERENCES tests(id) ON DELETE CASCADE);

INSERT into questions values(default,'Question 1MathElementary',1);
INSERT into questions values(default,'Question 2MathElementary',1);
INSERT into questions values(default,'Question 3MathElementary',1);
INSERT into questions values(default,'Question 4MathElementary',1);

INSERT into questions values(default,'Question 1MathAdvanced',2);
INSERT into questions values(default,'Question 2MathAdvanced',2);
INSERT into questions values(default,'Question 3MathAdvanced',2);

INSERT into questions values(default,'Question 1MathMaster',3);
INSERT into questions values(default,'Question 2MathMaster',3);
INSERT into questions values(default,'Question 3MathMaster',3);

INSERT into questions values(default,'Question 1PhysicsElementary',4);
INSERT into questions values(default,'Question 2PhysicsElementary',4);
INSERT into questions values(default,'Question 3PhysicsElementary',4);

INSERT into questions values(default,'Question 1PhysicsAdvanced',5);
INSERT into questions values(default,'Question 2PhysicsAdvanced',5);
INSERT into questions values(default,'Question 3PhysicsAdvanced',5);

INSERT into questions values(default,'Question 1PhysicsMaster',6);
INSERT into questions values(default,'Question 2PhysicsMaster',6);
INSERT into questions values(default,'Question 3PhysicsMaster',6);

INSERT into questions values(default,'Question 1EnglishElementary',7);
INSERT into questions values(default,'Question 2EnglishElementary',7);
INSERT into questions values(default,'Question 3EnglishElementary',7);

INSERT into questions values(default,'Question 1EnglishAdvanced',8);
INSERT into questions values(default,'Question 2EnglishAdvanced',8);
INSERT into questions values(default,'Question 3EnglishAdvanced',8);

INSERT into questions values(default,'Question 1EnglishMaster',9);
INSERT into questions values(default,'Question 2EnglishMaster',9);
INSERT into questions values(default,'Question 3EnglishMaster',9);


-- --------------------------------------------------------------
-- ANSWERS
-- --------------------------------------------------------------
CREATE TABLE answers(
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
content VARCHAR (150) NOT NULL,
correct BOOLEAN DEFAULT FALSE,
questions_id INTEGER NOT NULL REFERENCES questions(id)  ON DELETE CASCADE);

INSERT into answers values(default,'Answer1Question1MathBasic',false,1);
INSERT into answers values(default,'Answer2Question1MathBasic',true,1);
INSERT into answers values(default,'Answer3Question1MathBasic',false,1);
INSERT into answers values(default,'Answer4Question1MathBasic',true,1);
INSERT into answers values(default,'Answer5Question1MathBasic',false,1);

INSERT into answers values(default,'Answer1Question2MathBasic',false,2);
INSERT into answers values(default,'Answer2Question2MathBasic',true,2);
INSERT into answers values(default,'Answer3Question2MathBasic',false,2);
INSERT into answers values(default,'Answer4Question2MathBasic',true,2);
INSERT into answers values(default,'Answer5Question2MathBasic',false,2);

INSERT into answers values(default,'Answer1Question3MathBasic',false,3);
INSERT into answers values(default,'Answer2Question3MathBasic',true,3);
INSERT into answers values(default,'Answer3Question3MathBasic',true,3);
INSERT into answers values(default,'Answer4Question3MathBasic',true,3);
INSERT into answers values(default,'Answer5Question3MathBasic',false,3);

INSERT into answers values(default,'Answer1Question4MathBasic',false,4);
INSERT into answers values(default,'Answer2Question4MathBasic',true,4);
INSERT into answers values(default,'Answer3Question4MathBasic',false,4);
INSERT into answers values(default,'Answer4Question4MathBasic',true,4);
INSERT into answers values(default,'Answer5Question4MathBasic',false,4);

INSERT into answers values(default,'Answer1Question1MathAdvanced',false,5);
INSERT into answers values(default,'Answer2Question1MathAdvanced',true,5);
INSERT into answers values(default,'Answer3Question1MathAdvanced',false,5);
INSERT into answers values(default,'Answer4Question1MathAdvanced',true,5);
INSERT into answers values(default,'Answer5Question1MathAdvanced',false,5);

INSERT into answers values(default,'Answer1Question2MathAdvanced',false,6);
INSERT into answers values(default,'Answer2Question2MathAdvanced',true,6);
INSERT into answers values(default,'Answer3Question2MathAdvanced',false,6);
INSERT into answers values(default,'Answer4Question2MathAdvanced',true,6);
INSERT into answers values(default,'Answer5Question2MathAdvanced',false,6);

INSERT into answers values(default,'Answer1Question3MathAdvanced',false,7);
INSERT into answers values(default,'Answer2Question3MathAdvanced',true,7);
INSERT into answers values(default,'Answer3Question3MathAdvanced',true,7);
INSERT into answers values(default,'Answer4Question3MathAdvanced',true,7);
INSERT into answers values(default,'Answer5Question3MathAdvanced',false,7);

INSERT into answers values(default,'Answer1Question1MathMaster',false,8);
INSERT into answers values(default,'Answer2Question1MathMaster',true,8);
INSERT into answers values(default,'Answer3Question1MathMaster',false,8);
INSERT into answers values(default,'Answer4Question1MathMaster',true,8);
INSERT into answers values(default,'Answer5Question1MathMaster',false,8);

INSERT into answers values(default,'Answer1Question2MathMaster',false,9);
INSERT into answers values(default,'Answer2Question2MathMaster',true,9);
INSERT into answers values(default,'Answer3Question2MathMaster',false,9);
INSERT into answers values(default,'Answer4Question2MathMaster',true,9);
INSERT into answers values(default,'Answer5Question2MathMaster',false,9);

INSERT into answers values(default,'Answer1Question3MathMaster',false,10);
INSERT into answers values(default,'Answer2Question3MathMaster',true,10);
INSERT into answers values(default,'Answer3Question3MathMaster',true,10);
INSERT into answers values(default,'Answer4Question3MathMaster',true,10);
INSERT into answers values(default,'Answer5Question3MathMaster',false,10);

-- --------------------------------------------------------------
-- USERS_TESTS
-- --------------------------------------------------------------

CREATE TABLE users_tests(
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
users_id INTEGER NOT NULL REFERENCES users(id)  ON DELETE CASCADE,
tests_id INTEGER NOT NULL REFERENCES tests(id)  ON DELETE CASCADE,
test_date VARCHAR (50) NOT NULL UNIQUE,
test_result INTEGER NOT NULL);
