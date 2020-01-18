package ua.nure.biloborodov.SummaryTask4.web.commands.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ua.nure.biloborodov.SummaryTask4.constants.CommandPath;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.web.commands.common.RegistrationPageCommand;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationPageCommandTest extends Mockito {
    
    @Mock HttpServletRequest request;
    @Mock HttpSession session;
    @Mock HttpServletResponse response;
    
    @Test
    public void testDoPost() throws Exception {
	when(request.getMethod()).thenReturn("POST");
	assertEquals(CommandPath.REGISTRATION_PAGE, new RegistrationPageCommand().execute(request, response));
    }

    @Test
    public void testDoGet() throws Exception {
	when(request.getMethod()).thenReturn("GET");
	assertEquals(PagePath.PAGE_STUDENT_REGISTRATION, new RegistrationPageCommand().execute(request, response));
    }
    
    

}
