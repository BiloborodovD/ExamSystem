package ua.nure.biloborodov.summarytask4.web.commands.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.db.DifficultyLevel;
import ua.nure.biloborodov.summarytask4.db.entity.Test;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.service.TestService;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

public class SubmitTestInfoCommand extends ActionCommand {

	private TestService service;

	public SubmitTestInfoCommand(TestService service) {
		this.service = service;
	}

	@Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {

    HttpSession session = request.getSession(false);

    Test test = (Test) session.getAttribute(Attributes.CURRENT_TEST);
    if (test == null) {
      test = new Test();
    }

    test.setName(request.getParameter("test_name"));
    test.setSubjectId((Integer) session.getAttribute(Attributes.CURRENT_SUBJECT_ID));
    test.setDifficulty(DifficultyLevel.valueOf(request.getParameter("diff_level").toUpperCase()));
    test.setTime(Integer.parseInt(request.getParameter("test_time")) * 60);

    if (test.getId() == 0) {
      service.create(test);
    } else {
      service.update(test);
    }


    return CommandPath.GET_ADMIN_TESTS + "&subject_id=" + session
        .getAttribute(Attributes.CURRENT_SUBJECT_ID);
  }

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    return PagePath.PAGE_ADMIN_TESTS_LIST;
  }

}
