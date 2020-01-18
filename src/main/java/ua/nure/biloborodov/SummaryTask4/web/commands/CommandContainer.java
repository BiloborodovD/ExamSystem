package ua.nure.biloborodov.SummaryTask4.web.commands;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.SummaryTask4.db.repository.AnswerRepository;
import ua.nure.biloborodov.SummaryTask4.db.repository.UserRepository;
import ua.nure.biloborodov.SummaryTask4.db.repository.UsersTestsRepository;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.AddAnswerCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.AddNewSubjectCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.AddQuestionCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.AddTestInfoCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.BlockUserCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.DeleteAnswerCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.DeleteQuestionCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.DeleteSubjectCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.DeleteTestCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.EditQuestionCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.EditTestInfoCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.GetQuestionCreator;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.GetSubjectsEditorCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.GetTestInfoEditor;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.GetTestsCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.ProfileAdminCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.SubmitQuestionCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.SubmitTestInfoCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.admin.UsersListCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.common.ChangeLocaleCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.common.ChangeSettingsCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.common.EmptyCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.common.GetSettingsCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.common.LoginCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.common.LogoutCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.common.RegistrationCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.common.RegistrationPageCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.student.FinishTestCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.student.GetSubjectsCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.student.GetTestsListCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.student.ProfileStudentCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.student.RunTestCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.student.StartTestCommand;


/**
 * Holder of all commands
 */
public class CommandContainer {
	
	private static Map<String, ActionCommand> commands = new TreeMap<>();
	private static AnswerRepository answerRepository = new AnswerRepository();
	private static UserRepository userRepository = new UserRepository();
	private static UsersTestsRepository usersTestsRepository = new UsersTestsRepository();

	
	static {
		// common commands
		commands.put("login", new LoginCommand(userRepository, usersTestsRepository));
		commands.put("logout", new LogoutCommand());
		commands.put("empty", new EmptyCommand());
		commands.put("register", new RegistrationCommand());
		commands.put("registerPage", new RegistrationPageCommand());
		commands.put("language", new ChangeLocaleCommand());
		commands.put("getSettings", new GetSettingsCommand());
		commands.put("changeSettings", new ChangeSettingsCommand(userRepository));
		
		// student commands
		commands.put("studentProfile", new ProfileStudentCommand());
		commands.put("getSubjects", new GetSubjectsCommand());
		commands.put("getTests", new GetTestsListCommand());
		commands.put("startTest", new StartTestCommand());
		commands.put("runTest", new RunTestCommand());
		commands.put("finishTest", new FinishTestCommand());
		
		// admin commands
		commands.put("adminProfile", new ProfileAdminCommand());
		commands.put("usersList", new UsersListCommand());
		commands.put("getSubjectsEditor", new GetSubjectsEditorCommand());
		commands.put("blockUser", new BlockUserCommand());
		commands.put("getAdminTests", new GetTestsCommand());
		commands.put("deleteTest", new DeleteTestCommand());
		commands.put("deleteSubject", new DeleteSubjectCommand());
		commands.put("addNewSubject", new AddNewSubjectCommand());
		// test info
		commands.put("submitTestInfo", new SubmitTestInfoCommand());
		commands.put("editTestInfo", new EditTestInfoCommand());
		commands.put("getTestInfoEditor", new GetTestInfoEditor());
		commands.put("addTestInfo", new AddTestInfoCommand());
		// questions
		commands.put("submitQuestion", new SubmitQuestionCommand());
		commands.put("getQuestionCreator", new GetQuestionCreator());
		commands.put("editQuestion", new EditQuestionCommand());
		commands.put("deleteQuestion", new DeleteQuestionCommand());
		commands.put("addQuestion", new AddQuestionCommand());
		//answer
		commands.put("addAnswer", new AddAnswerCommand());
		commands.put("deleteAnswer", new DeleteAnswerCommand());
		
	}

	public static ActionCommand get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			return commands.get("empty");
		}
		
		return commands.get(commandName);
	}
	
}