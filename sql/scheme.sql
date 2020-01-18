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
INSERT INTO roles VALUES(2, 'blocked')

-- --------------------------------------------------------------
-- USERS
-- --------------------------------------------------------------

CREATE TABLE users(

id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
login VARCHAR(20) NOT NULL UNIQUE,
password VARCHAR(128) NOT NULL,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
email VARCHAR(30) NOT NULL,
--state BOOLEAN DEFAULT FALSE,

role_id INTEGER NOT NULL REFERENCES roles(id) ON DELETE CASCADE	ON UPDATE RESTRICT);

-- --------------------------------------------------------------
-- SUBJECTS
-- --------------------------------------------------------------
CREATE TABLE subjects (
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL UNIQUE);

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

-- --------------------------------------------------------------
-- DIFFICULTIES
-- --------------------------------------------------------------
CREATE TABLE difficulties(
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL UNIQUE);

-- --------------------------------------------------------------
-- QUESTIONS
-- --------------------------------------------------------------
CREATE TABLE questions(
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
content VARCHAR (2000) NOT NULL,
tests_id INTEGER NOT NULL REFERENCES tests(id) ON DELETE CASCADE);

-- --------------------------------------------------------------
-- ANSWERS
-- --------------------------------------------------------------
CREATE TABLE answers(
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
content VARCHAR (150) NOT NULL,
correct BOOLEAN DEFAULT FALSE,
questions_id INTEGER NOT NULL REFERENCES questions(id)  ON DELETE CASCADE);

-- --------------------------------------------------------------
-- USERS_TESTS
-- --------------------------------------------------------------
CREATE TABLE users_tests(
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
users_id INTEGER NOT NULL REFERENCES users(id)  ON DELETE CASCADE,
tests_id INTEGER NOT NULL REFERENCES tests(id)  ON DELETE CASCADE,
test_date VARCHAR (50) NOT NULL UNIQUE,
test_result INTEGER NOT NULL);
