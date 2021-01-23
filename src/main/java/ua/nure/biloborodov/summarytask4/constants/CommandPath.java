package ua.nure.biloborodov.summarytask4.constants;

public class CommandPath {

    // common commands
    public static final String LOGIN = "http://localhost:8080/ExamSystem/controller?command=login";
    public static final String LOGOUT = "http://localhost:8080/ExamSystem/controller?command=logout";
    public static final String EMPTY = "http://localhost:8080/ExamSystem/controller?command=empty";

    public static final String REGISTRATION = "http://localhost:8080/ExamSystem/controller?command=register";
    public static final String REGISTRATION_PAGE = "http://localhost:8080/ExamSystem/controller?command=registerPage";
    public static final String CHANGE_LOCALE = "http://localhost:8080/ExamSystem/controller?command=language";
    public static final String GET_SETTINGS = "http://localhost:8080/ExamSystem/controller?command=getSettings";
    public static final String CHANGE_SETTINGS = "http://localhost:8080/ExamSystem/controller?command=changeSettings";

    // student commands
    public static final String PROFILE_STUDENT = "http://localhost:8080/ExamSystem/controller?command=studentProfile";
    public static final String GET_SUBJECTS = "http://localhost:8080/ExamSystem/controller?command=getSubjects";
    public static final String GET_TESTS = "http://localhost:8080/ExamSystem/controller?command=getTests";
    public static final String START_TEST = "http://localhost:8080/ExamSystem/controller?command=startTest";
    public static final String FINISH_TEST = "http://localhost:8080/ExamSystem/controller?command=finishTest";
    public static final String RUN_TEST = "http://localhost:8080/ExamSystem/controller?command=runTest";
    public static final String ORDER_TESTS_BY = "http://localhost:8080/ExamSystem/controller?command=orderTestsBy";

    // admin commands
    public static final String PROFILE_ADMIN = "http://localhost:8080/ExamSystem/controller?command=adminProfile";
    public static final String USERS_LIST = "http://localhost:8080/ExamSystem/controller?command=usersList";
    public static final String GET_SUBJECTS_EDITOR = "http://localhost:8080/ExamSystem/controller?command=getSubjectsEditor";
    public static final String BLOCK_USER = "http://localhost:8080/ExamSystem/controller?command=blockUser";
    public static final String GET_ADMIN_TESTS = "http://localhost:8080/ExamSystem/controller?command=getAdminTests";
    public static final String DELETE_TEST = "http://localhost:8080/ExamSystem/controller?command=deleteTest";
    // test info
    public static final String SUBMIT_TEST_INFO = "http://localhost:8080/ExamSystem/controller?command=submitTestInfo";
    public static final String EDIT_TEST_INFO = "http://localhost:8080/ExamSystem/controller?command=editTestInfo";
    public static final String GET_TEST_INFO_EDITOR = "http://localhost:8080/ExamSystem/controller?command=getTestInfoEditor";
    public static final String ADD_TEST_INFO = "http://localhost:8080/ExamSystem/controller?command=addTestInfo";

    public static final String SUBMIT_QUESTION = "http://localhost:8080/ExamSystem/controller?command=submitQuestion";
    public static final String GET_QUESTION_CREATOR = "http://localhost:8080/ExamSystem/controller?command=getQuestionCreator";
    public static final String EDIT_QUESTION = "http://localhost:8080/ExamSystem/controller?command=editQuestion";
    public static final String DELETE_QUESTION = "http://localhost:8080/ExamSystem/controller?command=deleteQuestion";
    public static final String ADD_ANSWER = "http://localhost:8080/ExamSystem/controller?command=addAnswer";
    public static final String DELETE_ANSWER = "http://localhost:8080/ExamSystem/controller?command=deleteAnswer";
    public static final String ADD_QUESTION = "http://localhost:8080/ExamSystem/controller?command=addQuestion";
}
