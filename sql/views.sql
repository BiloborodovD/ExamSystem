CREATE VIEW `tests_view` (id, name, subject_id, difficulty_id, time, questions_count) AS
SELECT tests.id, tests.name, tests.subject_id, tests.difficulty_id, tests.time, COUNT(questions.test_id)
FROM tests
JOIN questions ON tests.id= questions.test_id
GROUP BY tests.name;

CREATE VIEW `users_tests_view` (id, login, subject_name, test_name,
 test_time, test_result) AS
SELECT users.id, users.login, subjects.name,
tests.name, users_tests.test_time, users_tests.test_result
FROM users_tests
JOIN users ON users.id= users_tests.users_id
JOIN tests
JOIN subjects ON subjects.id = tests.subject_id
ON tests.id = users_tests.tests_id;