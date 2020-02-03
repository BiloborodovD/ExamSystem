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
import ua.nure.biloborodov.summarytask4.db.repository.AnswerRepository;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.service.AnswerService;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

public class DeleteAnswerCommand extends ActionCommand {

  private AnswerService service;

  public DeleteAnswerCommand(AnswerService service) {
    this.service = service;
  }

  @Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {

    HttpSession session = request.getSession(false);
    Question question = (Question) session.getAttribute(Attributes.CURRENT_QUESTION);
    question.setContent(request.getParameter("question_content"));

    List<Answer> answers = new LinkedList<>();
    String[] answersId = request.getParameterValues("answer_id");
    String[] answersContents = request.getParameterValues("answer_content");
    String[] answersCorrect = request.getParameterValues("correct");

    updateAnswersList(answersId, answersContents, answersCorrect, answers);

    int answDel = Integer.parseInt(request.getParameter("delete_answer"));
    service.deleteById(answDel);
    answers.remove(answDel);
    session.setAttribute(Attributes.CURRENT_ANSWERS_LIST, answers);
    return CommandPath.GET_QUESTION_CREATOR;
  }

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response) {
    return PagePath.PAGE_ADMIN_QUESTION_CREATOR;
  }

  private void updateAnswersList(String[] answersId, String[] answersContents,
      String[] answersCorrect,
      List<Answer> answers) {
    if (answersContents != null) {
      for (int i = 0; i < answersContents.length; i++) {
        Answer answer = new Answer();
        answer.setId(Integer.parseInt(answersId[i]));
        answer.setContent(answersContents[i]);
        if (answersCorrect != null) {
          for (String string : answersCorrect) {
            if (Integer.parseInt(string) == i) {
              answer.setCorrect(true);
            }
          }
        }
        answers.add(answer);
      }
    }
  }

}
