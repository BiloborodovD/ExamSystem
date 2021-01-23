package ua.nure.biloborodov.summarytask4.web.commands;

import ua.nure.biloborodov.summarytask4.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Main interface for the Command pattern implementation.
 */
public abstract class ActionCommand implements Serializable {

    private static final String GET = "GET";
    private static final String POST = "POST";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

        String forward = null;
        String method = request.getMethod();
        if (GET.equals(method)) {
            forward = doGet(request, response);
        } else if (POST.equals(method)) {
            forward = doPost(request, response);
        }
        return forward;
    }

    protected abstract String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException;

    protected abstract String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException;
}