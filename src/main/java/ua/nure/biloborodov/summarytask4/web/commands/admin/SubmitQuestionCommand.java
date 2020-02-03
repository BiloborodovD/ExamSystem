package ua.nure.biloborodov.summarytask4.web.commands.admin;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.db.entity.Answer;
import ua.nure.biloborodov.summarytask4.db.entity.Question;
import ua.nure.biloborodov.summarytask4.db.entity.Test;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.service.AnswerService;
import ua.nure.biloborodov.summarytask4.service.QuestionService;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

public class SubmitQuestionCommand extends ActionCommand {

	private final AnswerService answerService;
	private final QuestionService questionService;

	public SubmitQuestionCommand(AnswerService answerService, QuestionService questionService) {
		this.answerService = answerService;
		this.questionService = questionService;
	}

	@Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
		String result = CommandPath.GET_TEST_INFO_EDITOR;
		HttpSession session = request.getSession(false);
		Test test = (Test) session.getAttribute(Attributes.CURRENT_TEST);

		List<Answer> answers = new LinkedList<>();
		String[] answersId = request.getParameterValues("answer_id");
		String[] answersContents = request.getParameterValues("answer_content");
		String[] answersCorrect = request.getParameterValues("correct");

		if (answersContents == null || answersCorrect == null) {
			result = CommandPath.GET_QUESTION_CREATOR;
		} else {
			updateAnswersList(answersId, answersContents, answersCorrect, answers);
			Question question = (Question) session.getAttribute(Attributes.CURRENT_QUESTION);
			question.setContent(request.getParameter("question_content"));
			question.setTestId(test.getId());
			if (question.getId() == 0) {
				question = questionService.create(question);
			} else {
				question = questionService.update(question);
			}
			answerService.addList(answers, question);
			session.setAttribute(Attributes.CURRENT_QUESTIONS_LIST, questionService.findByTest(test));
			session.setAttribute(Attributes.CURRENT_ANSWERS_LIST, null);
			session.setAttribute(Attributes.CURRENT_QUESTION, null);
		}
		return result;
	}

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response) {
    return PagePath.PAGE_ADMIN_TEST_INFO_EDITOR;
  }

  private void updateAnswersList(String[] answersId, String[] answersContents,
      String[] answersCorrect, List<Answer> answers) {

    for (int i = 0; i < answersContents.length; i++) {
      Answer answer = new Answer();
      answer.setId(Integer.parseInt(answersId[i]));
      answer.setContent(answersContents[i]);
      for (String string : answersCorrect) {
        if (Integer.parseInt(string) == i) {
          answer.setCorrect(true);
        }
      }
      answers.add(answer);
    }
  }

}
