package ua.nure.biloborodov.summarytask4.web.commands.student;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.db.Language;
import ua.nure.biloborodov.summarytask4.db.repository.SubjectRepository;
import ua.nure.biloborodov.summarytask4.db.entity.Subject;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

public class GetSubjectsCommand extends ActionCommand {

    private static final long serialVersionUID = 9009931168679281100L;
    
    private static final Logger LOG = Logger.getLogger(GetSubjectsCommand.class);

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return doGet(request, response);
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
	LOG.debug("Commands starts");
	HttpSession session = request.getSession(false);
	session.setAttribute(Attributes.CURRENT_SUBJECT_ID, null);
	
	if (session.getAttribute(Attributes.LANGUAGE) == null) {
	    session.setAttribute(Attributes.LANGUAGE, new Locale("en"));
	    LOG.trace("locale was set en");
	}
	Locale locale = (Locale) session.getAttribute(Attributes.LANGUAGE);
	Language lang = Language.valueOf(locale.getLanguage().toUpperCase());
	
	LOG.trace("lang" + lang.getName());
	
	List<Subject> subjects = new SubjectRepository().findSubjectsByLanguage(lang);

	LOG.trace("Found in DB: subjects --> " + subjects);
	
	if (subjects.isEmpty()) {
	    session.setAttribute(Attributes.ERROR,
		    "message.subjectserror");
		return PagePath.PAGE_STUDENT_SUBJECT_LIST;
	}
	
	session.setAttribute(Attributes.SUBJECTS_LIST, subjects);
	LOG.trace("Set the request attribute: subjects --> " + subjects);
	LOG.debug("Commands finished");
	
	return PagePath.PAGE_STUDENT_SUBJECT_LIST;
    }

}
