package ua.nure.biloborodov.summarytask4.web.commands.student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.db.entity.UsersTests;
import ua.nure.biloborodov.summarytask4.db.entity.Question;
import ua.nure.biloborodov.summarytask4.db.entity.Test;
import ua.nure.biloborodov.summarytask4.db.entity.User;
import ua.nure.biloborodov.summarytask4.db.repository.UsersTestsRepository;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.exception.DBException;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

/**
 * Analyse the test answers and calculate the result
 */
public class FinishTestCommand extends ActionCommand {

  @Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {

    HttpSession session = request.getSession(false);

    long finishTime = (long) session.getAttribute(Attributes.FINISH_TIME);
    long time = System.currentTimeMillis();
    if (finishTime < time) {
      compileResult(session, 0);
      return CommandPath.PROFILE_STUDENT;
    }

    String[] userAnswers = request.getParameterValues("answer_correct");
    if (userAnswers == null) {
      compileResult(session, 0);
      return CommandPath.PROFILE_STUDENT;
    }

    List<String> answerPattern = (List<String>) session.getAttribute(Attributes.CORRECT_ANSWERS);
    List<String> userAnswerList = Arrays.asList(userAnswers);

    List<String> uncorrectAnswers = new ArrayList<>();
    for (String string : answerPattern) {
      if (!userAnswerList.contains(string)) {
        uncorrectAnswers.add(string);
      }
    }
    for (String string : userAnswerList) {
      if (!answerPattern.contains(string)) {
        uncorrectAnswers.add(string);
      }
    }

    Set<String> uncorrectedQuestion = new HashSet<>();
    for (String string : uncorrectAnswers) {
      uncorrectedQuestion.add(string.substring(0, string.indexOf('_')));
    }

    List<Question> questions = (List<Question>) session
				.getAttribute(Attributes.CURRENT_QUESTIONS_LIST);

    int result = ((questions.size() - uncorrectedQuestion.size()) * 100) / questions.size();

    compileResult(session, result);

    return CommandPath.PROFILE_STUDENT;
  }

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    HttpSession session = request.getSession(false);

    long finishTime = (long) session.getAttribute(Attributes.FINISH_TIME);
    long time = System.currentTimeMillis();
    if (finishTime < time) {
      compileResult(session, 0);
      return PagePath.PAGE_STUDENT;
    }
    return PagePath.PAGE_STUDENT_TEST_RUN;
  }

  /**
   * Compile UsersTests entity.
   */
  private void compileResult(HttpSession session, int result) throws DBException {
    UsersTestsRepository usersTestsRepository = new UsersTestsRepository();
    UsersTests usersTests = new UsersTests();
    User user = (User) session.getAttribute(Attributes.CURRENT_USER);
    Test test = (Test) session.getAttribute(Attributes.CURRENT_TEST);
    usersTests.setUserId(user.getId());
    usersTests.setTestId(test.getId());
    usersTests.setTestResult(result);
    usersTestsRepository.create(usersTests);
    session.setAttribute(Attributes.CURRENT_TEST, null);
    session
        .setAttribute(Attributes.CURRENT_USERS_TESTS, new UsersTestsRepository().findByUser(user));
  }

}
