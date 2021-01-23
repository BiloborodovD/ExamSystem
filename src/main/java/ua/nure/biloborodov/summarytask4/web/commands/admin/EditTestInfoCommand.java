package ua.nure.biloborodov.summarytask4.web.commands.admin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.db.DifficultyLevel;
import ua.nure.biloborodov.summarytask4.db.entity.Question;
import ua.nure.biloborodov.summarytask4.db.entity.Test;
import ua.nure.biloborodov.summarytask4.db.repository.QuestionRepository;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.service.QuestionService;
import ua.nure.biloborodov.summarytask4.service.TestService;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

public class EditTestInfoCommand extends ActionCommand {

	private final TestService testService;
	private final QuestionService questionService;

	public EditTestInfoCommand(TestService testService,	QuestionService questionService) {
		this.testService = testService;
		this.questionService = questionService;
	}

	@Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    return CommandPath.GET_TEST_INFO_EDITOR;
  }

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    HttpSession session = request.getSession(false);

    Test test = testService.findById(Integer.parseInt(request.getParameter("test_id")));
    List<Question> questions = questionService.findByTest(test);
    session.setAttribute(Attributes.CURRENT_TEST, test);
    session.setAttribute(Attributes.CURRENT_QUESTIONS_LIST, questions);
    session.setAttribute(Attributes.DIFFICULTIES, DifficultyLevel.values());
    return PagePath.PAGE_ADMIN_TEST_INFO_EDITOR;
  }

}
