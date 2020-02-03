package ua.nure.biloborodov.summarytask4.web.commands.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.db.Language;
import ua.nure.biloborodov.summarytask4.db.entity.Subject;
import ua.nure.biloborodov.summarytask4.db.repository.SubjectRepository;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.service.SubjectService;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

public class AddNewSubjectCommand extends ActionCommand {

  private final SubjectService service;

  public AddNewSubjectCommand(SubjectService service) {
    this.service = service;
  }

  @Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {

    HttpSession session = request.getSession(false);

    String subjectName = request.getParameter("subject_name");
    String lang = request.getParameter("language");
    Subject newSubject = new Subject();
    newSubject.setName(subjectName);
    newSubject.setLangId(Language.valueOf(lang.toUpperCase()));
    service.create(newSubject);

    session.setAttribute(Attributes.SUCCESS, "message.success.subject.create");

    return CommandPath.GET_SUBJECTS_EDITOR;
  }

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response) {
    return PagePath.PAGE_ADMIN_SUBJECT_EDITOR;
  }

}
