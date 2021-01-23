package ua.nure.biloborodov.summarytask4.web.commands.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.db.entity.Question;
import ua.nure.biloborodov.summarytask4.db.entity.Test;
import ua.nure.biloborodov.summarytask4.db.repository.QuestionRepository;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.service.QuestionService;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

public class DeleteQuestionCommand extends ActionCommand {

	private final QuestionService service;

	public DeleteQuestionCommand(QuestionService service) {
		this.service = service;
	}

	@Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    HttpSession session = request.getSession(false);
    Question question = (Question) session.getAttribute(Attributes.CURRENT_QUESTION);
    service.deleteById(question.getId());
    Test test = (Test) session.getAttribute(Attributes.CURRENT_TEST);
    session.setAttribute(Attributes.CURRENT_QUESTIONS_LIST, service.findByTest(test));
    session.setAttribute(Attributes.CURRENT_QUESTION, null);
    session.setAttribute(Attributes.CURRENT_ANSWERS_LIST, null);
    return CommandPath.GET_TEST_INFO_EDITOR;
  }

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    return PagePath.PAGE_ADMIN_TEST_INFO_EDITOR;
  }

}
