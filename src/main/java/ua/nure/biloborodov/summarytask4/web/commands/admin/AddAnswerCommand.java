package ua.nure.biloborodov.summarytask4.web.commands.admin;

import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.db.entity.Answer;
import ua.nure.biloborodov.summarytask4.db.entity.Question;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * Add new answer
 */
public class AddAnswerCommand extends ActionCommand {

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);

        String result = CommandPath.GET_QUESTION_CREATOR;
        Question question = (Question) session.getAttribute(Attributes.CURRENT_QUESTION);
        String[] answersId = request.getParameterValues("answer_id");
        String[] answersContents = request.getParameterValues("answer_content");
        String[] answersCorrect = request.getParameterValues("correct");

        question.setContent(request.getParameter("question_content"));

        List<Answer> answers = new LinkedList<>();

        if (answersId != null) {
            updateAnswersList(answersId, answersContents, answersCorrect, answers);
            answers.add(new Answer());
            session.setAttribute(Attributes.CURRENT_ANSWERS_LIST, answers);
        }
        return result;
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

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) {
        return PagePath.PAGE_ADMIN_QUESTION_CREATOR;
    }

}
