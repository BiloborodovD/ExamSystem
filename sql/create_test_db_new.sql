USE exam_system;

-- --------------------------------------------------------------
-- ROLES
-- users roles
-- --------------------------------------------------------------
INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'student');
INSERT INTO roles VALUES(2, 'blocked');

-- --------------------------------------------------------------
-- LANGUAGES
-- --------------------------------------------------------------
INSERT INTO language VALUES(0, 'en');
INSERT INTO language VALUES(1, 'ru');

-- --------------------------------------------------------------
-- USERS
-- --------------------------------------------------------------
INSERT into USERS values(default,'admin','admin','Dmytro','Biloborodov','dmwhiteb@gmail.com',0);
INSERT into USERS values(default,'PPetrov','password1','Petr','Petrov','petrov@mail.ru',1);
INSERT into USERS values(default,'IIvanov','password2','Ivan','Ivanov','ivanov@mail.ru',1);

-- --------------------------------------------------------------
-- SUBJECTS
-- --------------------------------------------------------------
INSERT into subjects values(default,'Mathematics',0);
INSERT into subjects values(default,'Physics',0);
INSERT into subjects values(default,'English',0);

-- --------------------------------------------------------------
-- DIFFICULTIES
-- --------------------------------------------------------------
INSERT into difficulties values(0,'elementary');
INSERT into difficulties values(1,'intermediate');
INSERT into difficulties values(2,'advanced');

-- --------------------------------------------------------------

-- --------------------------------------------------------------
-- TESTS
-- --------------------------------------------------------------
INSERT into tests values(default,'Operations',1,0, 500);
INSERT into tests values(default,'Numbers',1,1, 300);
INSERT into tests values(default,'Integrals',1,2,  400);

INSERT into tests values(default,'Optics',2,0, 10);
INSERT into tests values(default,'Mechanics',2,1,  10);
INSERT into tests values(default,'Nuclear physics',2,2, 10);

INSERT into tests values(default,'Pronouns',3,0, 10);
INSERT into tests values(default,'Articles',3,1, 10);
INSERT into tests values(default,'Nouns',3,2, 10);


-- QUESTIONS
-- --------------------------------------------------------------
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
INSERT into answers values(default,'Answer1Question1MathBasic',0,1);
INSERT into answers values(default,'Answer2Question1MathBasic',1,1);
INSERT into answers values(default,'Answer3Question1MathBasic',0,1);
INSERT into answers values(default,'Answer4Question1MathBasic',1,1);
INSERT into answers values(default,'Answer5Question1MathBasic',0,1);

INSERT into answers values(default,'Answer1Question2MathBasic',0,2);
INSERT into answers values(default,'Answer2Question2MathBasic',1,2);
INSERT into answers values(default,'Answer3Question2MathBasic',0,2);
INSERT into answers values(default,'Answer4Question2MathBasic',1,2);
INSERT into answers values(default,'Answer5Question2MathBasic',0,2);

INSERT into answers values(default,'Answer1Question3MathBasic',0,3);
INSERT into answers values(default,'Answer2Question3MathBasic',1,3);
INSERT into answers values(default,'Answer3Question3MathBasic',1,3);
INSERT into answers values(default,'Answer4Question3MathBasic',1,3);
INSERT into answers values(default,'Answer5Question3MathBasic',0,3);

INSERT into answers values(default,'Answer1Question4MathBasic',0,4);
INSERT into answers values(default,'Answer2Question4MathBasic',1,4);
INSERT into answers values(default,'Answer3Question4MathBasic',0,4);
INSERT into answers values(default,'Answer4Question4MathBasic',1,4);
INSERT into answers values(default,'Answer5Question4MathBasic',0,4);

INSERT into answers values(default,'Answer1Question1MathAdvanced',0,5);
INSERT into answers values(default,'Answer2Question1MathAdvanced',1,5);
INSERT into answers values(default,'Answer3Question1MathAdvanced',0,5);
INSERT into answers values(default,'Answer4Question1MathAdvanced',1,5);
INSERT into answers values(default,'Answer5Question1MathAdvanced',0,5);

INSERT into answers values(default,'Answer1Question2MathAdvanced',0,6);
INSERT into answers values(default,'Answer2Question2MathAdvanced',1,6);
INSERT into answers values(default,'Answer3Question2MathAdvanced',0,6);
INSERT into answers values(default,'Answer4Question2MathAdvanced',1,6);
INSERT into answers values(default,'Answer5Question2MathAdvanced',0,6);

INSERT into answers values(default,'Answer1Question3MathAdvanced',0,7);
INSERT into answers values(default,'Answer2Question3MathAdvanced',1,7);
INSERT into answers values(default,'Answer3Question3MathAdvanced',1,7);
INSERT into answers values(default,'Answer4Question3MathAdvanced',1,7);
INSERT into answers values(default,'Answer5Question3MathAdvanced',0,7);

INSERT into answers values(default,'Answer1Question1MathMaster',0,8);
INSERT into answers values(default,'Answer2Question1MathMaster',1,8);
INSERT into answers values(default,'Answer3Question1MathMaster',0,8);
INSERT into answers values(default,'Answer4Question1MathMaster',1,8);
INSERT into answers values(default,'Answer5Question1MathMaster',0,8);

INSERT into answers values(default,'Answer1Question2MathMaster',0,9);
INSERT into answers values(default,'Answer2Question2MathMaster',1,9);
INSERT into answers values(default,'Answer3Question2MathMaster',0,9);
INSERT into answers values(default,'Answer4Question2MathMaster',1,9);
INSERT into answers values(default,'Answer5Question2MathMaster',0,9);

INSERT into answers values(default,'Answer1Question3MathMaster',0,10);
INSERT into answers values(default,'Answer2Question3MathMaster',1,10);
INSERT into answers values(default,'Answer3Question3MathMaster',1,10);
INSERT into answers values(default,'Answer4Question3MathMaster',1,10);
INSERT into answers values(default,'Answer5Question3MathMaster',0,10);


