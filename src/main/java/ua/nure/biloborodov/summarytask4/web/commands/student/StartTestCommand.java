package ua.nure.biloborodov.summarytask4.web.commands.student;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.db.entity.Answer;
import ua.nure.biloborodov.summarytask4.db.entity.Question;
import ua.nure.biloborodov.summarytask4.db.entity.Test;
import ua.nure.biloborodov.summarytask4.db.repository.AnswerRepository;
import ua.nure.biloborodov.summarytask4.db.repository.QuestionRepository;
import ua.nure.biloborodov.summarytask4.db.repository.TestRepository;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

public class StartTestCommand extends ActionCommand {

	public static final String TEST_ID = "test_id";
  private AnswerRepository answerRepository;
  private TestRepository testRepository;
  private QuestionRepository questionRepository;

  public StartTestCommand(AnswerRepository answers, TestRepository tests,
			QuestionRepository questions) {
    this.answerRepository = answers;
    this.testRepository = tests;
    this.questionRepository = questions;
  }

  @Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    return CommandPath.START_TEST;
  }

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response)
      throws AppException {

    HttpSession session = request.getSession(false);

    int testId = Integer.parseInt(request.getParameter(TEST_ID));
    Test test = testRepository.findById(testId);

    List<String> answerPattern = new ArrayList<>();
    Map<Question, List<Answer>> mapTest = new LinkedHashMap<>();
    List<Question> questions = questionRepository.findByTest(test);

    for (int i = 0; i < questions.size(); i++) {
      List<Answer> answers = answerRepository.findByQuestion(questions.get(i));
      for (int j = 0; j < answers.size(); j++) {
        if (answers.get(j).isCorrect()) {
          answerPattern.add(i + "_" + j);
        }
      }
      mapTest.put(questions.get(i), answers);

    }

    session.setAttribute(Attributes.CURRENT_TEST, test);
    session.setAttribute(Attributes.CURRENT_QUESTIONS_LIST, questions);
    session.setAttribute(Attributes.MAP_TEST, mapTest);
    session.setAttribute(Attributes.CORRECT_ANSWERS, answerPattern);

    return PagePath.PAGE_STUDENT_TEST_START;
  }

}
