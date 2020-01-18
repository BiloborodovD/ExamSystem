package ua.nure.biloborodov.SummaryTask4.web.commands.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.SummaryTask4.constants.Attributes;
import ua.nure.biloborodov.SummaryTask4.constants.CommandPath;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.db.Language;
import ua.nure.biloborodov.SummaryTask4.db.repository.SubjectRepository;
import ua.nure.biloborodov.SummaryTask4.db.entity.Subject;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

public class AddNewSubjectCommand extends ActionCommand {

    private static final long serialVersionUID = 1733612664608257157L;
    
    private static final Logger LOG = Logger.getLogger(AddNewSubjectCommand.class);

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {

	HttpSession session = request.getSession(false);
	String subjectName = request.getParameter("subject_name");
	String lang = request.getParameter("language");
	LOG.trace("language" + lang);
	Subject newSubject = new Subject();
	newSubject.setName(subjectName);
	newSubject.setLangId(Language.valueOf(lang.toUpperCase()));
	LOG.trace("language subj" + newSubject.getLangId());
	new SubjectRepository().create(newSubject);
	
	session.setAttribute(Attributes.SUCCESS, "message.success.subject.create");
	
	return CommandPath.GET_SUBJECTS_EDITOR;
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return PagePath.PAGE_ADMIN_SUBJECT_EDITOR;
    }

}
