package ua.nure.biloborodov.summarytask4.web.commands;

import java.util.Map;
import java.util.TreeMap;

import ua.nure.biloborodov.summarytask4.db.repository.AnswerRepository;
import ua.nure.biloborodov.summarytask4.db.repository.QuestionRepository;
import ua.nure.biloborodov.summarytask4.db.repository.SubjectRepository;
import ua.nure.biloborodov.summarytask4.db.repository.TestRepository;
import ua.nure.biloborodov.summarytask4.db.repository.UserRepository;
import ua.nure.biloborodov.summarytask4.db.repository.UsersTestsRepository;
import ua.nure.biloborodov.summarytask4.service.AnswerService;
import ua.nure.biloborodov.summarytask4.service.QuestionService;
import ua.nure.biloborodov.summarytask4.service.SubjectService;
import ua.nure.biloborodov.summarytask4.service.TestService;
import ua.nure.biloborodov.summarytask4.service.UserService;
import ua.nure.biloborodov.summarytask4.service.UsersTestsService;
import ua.nure.biloborodov.summarytask4.web.commands.admin.AddAnswerCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.AddNewSubjectCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.AddQuestionCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.AddTestInfoCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.BlockUserCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.DeleteAnswerCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.DeleteQuestionCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.DeleteSubjectCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.DeleteTestCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.EditQuestionCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.EditTestInfoCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.GetQuestionCreator;
import ua.nure.biloborodov.summarytask4.web.commands.admin.GetSubjectsEditorCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.GetTestInfoEditor;
import ua.nure.biloborodov.summarytask4.web.commands.admin.GetTestsCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.ProfileAdminCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.SubmitQuestionCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.SubmitTestInfoCommand;
import ua.nure.biloborodov.summarytask4.web.commands.admin.UsersListCommand;
import ua.nure.biloborodov.summarytask4.web.commands.common.ChangeLocaleCommand;
import ua.nure.biloborodov.summarytask4.web.commands.common.ChangeSettingsCommand;
import ua.nure.biloborodov.summarytask4.web.commands.common.EmptyCommand;
import ua.nure.biloborodov.summarytask4.web.commands.common.GetSettingsCommand;
import ua.nure.biloborodov.summarytask4.web.commands.common.LoginCommand;
import ua.nure.biloborodov.summarytask4.web.commands.common.LogoutCommand;
import ua.nure.biloborodov.summarytask4.web.commands.common.RegistrationCommand;
import ua.nure.biloborodov.summarytask4.web.commands.common.RegistrationPageCommand;
import ua.nure.biloborodov.summarytask4.web.commands.student.FinishTestCommand;
import ua.nure.biloborodov.summarytask4.web.commands.student.GetSubjectsCommand;
import ua.nure.biloborodov.summarytask4.web.commands.student.GetTestsListCommand;
import ua.nure.biloborodov.summarytask4.web.commands.student.ProfileStudentCommand;
import ua.nure.biloborodov.summarytask4.web.commands.student.RunTestCommand;
import ua.nure.biloborodov.summarytask4.web.commands.student.StartTestCommand;

/**
 * Holder of all commands
 */
public class CommandContainer {
	
	private static Map<String, ActionCommand> commands = new TreeMap<>();
	private static AnswerRepository answerRepository = new AnswerRepository();
	private static UserRepository userRepository = new UserRepository();
	private static UsersTestsRepository usersTestsRepository = new UsersTestsRepository();
	private static TestRepository testRepository = new TestRepository();
	private static QuestionRepository questionRepository = new QuestionRepository();
	private static SubjectRepository subjectRepository = new SubjectRepository();

	private static SubjectService subjectService = new SubjectService(subjectRepository);
	private static UsersTestsService usersTestsService = new UsersTestsService(usersTestsRepository);
	private static UserService userService = new UserService(userRepository);
	private static TestService testService = new TestService(testRepository);
	private static AnswerService answerService = new AnswerService(answerRepository);
	private static QuestionService questionService = new QuestionService(questionRepository);

	
	static {
		// common commands
		commands.put("login", new LoginCommand(userRepository, usersTestsRepository));
		commands.put("logout", new LogoutCommand());
		commands.put("empty", new EmptyCommand());
		commands.put("register", new RegistrationCommand(userRepository));
		commands.put("registerPage", new RegistrationPageCommand());
		commands.put("language", new ChangeLocaleCommand());
		commands.put("getSettings", new GetSettingsCommand());
		commands.put("changeSettings", new ChangeSettingsCommand(userRepository));
		
		// student commands
		commands.put("studentProfile", new ProfileStudentCommand());
		commands.put("getSubjects", new GetSubjectsCommand());
		commands.put("getTests", new GetTestsListCommand());
		commands.put("startTest", new StartTestCommand(answerRepository, testRepository, questionRepository));
		commands.put("runTest", new RunTestCommand());
		commands.put("finishTest", new FinishTestCommand());
		
		// admin commands
		commands.put("adminProfile", new ProfileAdminCommand());
		commands.put("usersList", new UsersListCommand());
		commands.put("getSubjectsEditor", new GetSubjectsEditorCommand());
		commands.put("blockUser", new BlockUserCommand(userService));
		commands.put("getAdminTests", new GetTestsCommand());
		commands.put("deleteTest", new DeleteTestCommand(testService));
		commands.put("deleteSubject", new DeleteSubjectCommand(subjectService));
		commands.put("addNewSubject", new AddNewSubjectCommand(subjectService));
		// test info
		commands.put("submitTestInfo", new SubmitTestInfoCommand());
		commands.put("editTestInfo", new EditTestInfoCommand(testService, questionService));
		commands.put("getTestInfoEditor", new GetTestInfoEditor());
		commands.put("addTestInfo", new AddTestInfoCommand());
		// questions
		commands.put("submitQuestion", new SubmitQuestionCommand(answerService, questionService));
		commands.put("getQuestionCreator", new GetQuestionCreator());
		commands.put("editQuestion", new EditQuestionCommand(answerService));
		commands.put("deleteQuestion", new DeleteQuestionCommand(questionService));
		commands.put("addQuestion", new AddQuestionCommand());
		//answer
		commands.put("addAnswer", new AddAnswerCommand());
		commands.put("deleteAnswer", new DeleteAnswerCommand(answerService));
		
	}

	public static ActionCommand get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			return commands.get("empty");
		}
		
		return commands.get(commandName);
	}
	
}