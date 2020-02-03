package ua.nure.biloborodov.summarytask4.db;

/**
 * Holder for fields names of DB tables and beans.
 */
public class Fields {
    
 //entities
 	public static final String ENTITY_ID = "id";
 	public static final String NAME = "name";
 	public static final String CONTENT = "content";
 	
 //user
 	public static final String USER_LOGIN = "login";
 	public static final String USER_PASSWORD = "password";
 	public static final String USER_FIRST_NAME = "first_name";
 	public static final String USER_LAST_NAME = "last_name";
 	public static final String USER_EMAIL = "email";
 	public static final String USER_ROLE_ID = "role_id";
 //answer
 	public static final String CORRECT = "correct";
 	public static final String QUESTION_ID = "questions_id";
 //question
 	public static final String TEST_ID = "test_id";
 //subject
 	public static final String LANG_ID = "lang_id";
 //test
 	public static final String SUBJECT_ID = "subject_id";
 	public static final String DIFFICULTY_ID = "difficulty_id";
 	public static final String TIME = "time";
 	public static final String QUESTIONS_COUNT = "questions_count";
 //user_tests
 	public static final String USERS_ID = "users_id";
 	public static final String SUBJECT_NAME = "subject_name";
 	public static final String TEST_NAME = "test_name";
 	public static final String TEST_TIME = "test_time";
 	public static final String RESULT = "test_result";
 	
 	
}
