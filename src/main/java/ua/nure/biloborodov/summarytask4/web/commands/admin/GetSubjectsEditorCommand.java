package ua.nure.biloborodov.summarytask4.web.commands.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.db.repository.SubjectRepository;
import ua.nure.biloborodov.summarytask4.db.entity.Subject;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

public class GetSubjectsEditorCommand extends ActionCommand {
    
    private static final long serialVersionUID = 7120152762524251094L;

    private static final Logger LOG = Logger.getLogger(GetSubjectsEditorCommand.class);

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return CommandPath.GET_SUBJECTS_EDITOR;
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
	LOG.debug("Commands starts");
	HttpSession session = request.getSession(false);
	
	List<Subject> subjects = new SubjectRepository().findAll();

	LOG.trace("Found in DB: subjects --> " + subjects);
	
	if (subjects == null ) {
	    session.setAttribute(Attributes.ERROR,
		    "message.subjectserror");
		return PagePath.PAGE_ADMIN_SUBJECT_EDITOR;
	}
	
	session.setAttribute(Attributes.SUBJECTS_LIST, subjects);
	for (Subject subject : subjects) {
	    LOG.trace("Set the request attribute: subjects --> " + subject.getLangId());
	}
	LOG.debug("Commands finished");
	return PagePath.PAGE_ADMIN_SUBJECT_EDITOR;
    }

}
