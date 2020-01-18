package ua.nure.biloborodov.SummaryTask4.web.commands.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.biloborodov.SummaryTask4.constants.CommandPath;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.db.repository.SubjectRepository;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

public class DeleteSubjectCommand extends ActionCommand {

    private static final long serialVersionUID = 6471715735786392643L;

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
	int subjectId  = Integer.parseInt(request.getParameter("subject_id"));
	new SubjectRepository().deleteById(subjectId);
	return CommandPath.GET_SUBJECTS_EDITOR;
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return PagePath.PAGE_ADMIN_SUBJECT_EDITOR;
    }

}
