package ua.nure.biloborodov.summarytask4.web.commands.admin;

import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.db.DifficultyLevel;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddTestInfoCommand extends ActionCommand {

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response)
            throws AppException {
        return CommandPath.GET_TEST_INFO_EDITOR;
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response)
            throws AppException {
        HttpSession session = request.getSession(false);
        session.setAttribute(Attributes.CURRENT_TEST, null);
        session.setAttribute(Attributes.CURRENT_QUESTIONS_LIST, null);
        session.setAttribute(Attributes.CURRENT_QUESTION, null);
        session.setAttribute(Attributes.CURRENT_ANSWERS_LIST, null);
        session.setAttribute(Attributes.DIFFICULTIES, DifficultyLevel.values());
        return PagePath.PAGE_ADMIN_TEST_INFO_EDITOR;
    }

}
