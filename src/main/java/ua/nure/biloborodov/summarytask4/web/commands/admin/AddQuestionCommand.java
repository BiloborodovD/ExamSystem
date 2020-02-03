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
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

public class AddQuestionCommand extends ActionCommand {

  @Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    HttpSession session = request.getSession(false);
    Test test = (Test) session.getAttribute(Attributes.CURRENT_TEST);

    if (test == null) {
      session.setAttribute(Attributes.ERROR, "message.add.test.before");
      return CommandPath.GET_TEST_INFO_EDITOR;
    }

    request.getSession(false).setAttribute(Attributes.CURRENT_QUESTION, new Question());
    List<Answer> answers = new LinkedList<>();
    answers.add(new Answer());
    session.setAttribute(Attributes.CURRENT_ANSWERS_LIST, answers);
    return CommandPath.GET_QUESTION_CREATOR;
  }

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    return PagePath.PAGE_ADMIN_QUESTION_CREATOR;
  }

}
