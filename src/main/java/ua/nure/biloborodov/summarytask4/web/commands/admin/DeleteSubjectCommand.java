package ua.nure.biloborodov.summarytask4.web.commands.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.db.repository.SubjectRepository;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.service.SubjectService;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

public class DeleteSubjectCommand extends ActionCommand {

  private final SubjectService service;

  public DeleteSubjectCommand(SubjectService service) {
    this.service = service;
  }

  @Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    int subjectId = Integer.parseInt(request.getParameter("subject_id"));
    service.deleteById(subjectId);
    return CommandPath.GET_SUBJECTS_EDITOR;
  }

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    return PagePath.PAGE_ADMIN_SUBJECT_EDITOR;
  }

}
