package ua.nure.biloborodov.summarytask4.web.commands.admin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.db.entity.Answer;
import ua.nure.biloborodov.summarytask4.db.entity.Question;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.service.AnswerService;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

public class EditQuestionCommand extends ActionCommand {

	private final AnswerService answerService;

	public EditQuestionCommand(AnswerService answerService) {
		this.answerService = answerService;
	}

	@Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response) {
    return CommandPath.GET_QUESTION_CREATOR;
  }

  @Override
  @SuppressWarnings("unchecked")
  protected String doGet(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    HttpSession session = request.getSession(false);

    int questionId = Integer.parseInt(request.getParameter("question"));

    List<Question> questions = (List<Question>) session
        .getAttribute(Attributes.CURRENT_QUESTIONS_LIST);

    Question question = questions.get(questionId - 1);
		session.setAttribute(Attributes.CURRENT_QUESTION, question);

		List<Answer> answers = answerService.findByQuestion(question);
		session.setAttribute(Attributes.CURRENT_ANSWERS_LIST, answers);

    return PagePath.PAGE_ADMIN_QUESTION_CREATOR;
  }
}
