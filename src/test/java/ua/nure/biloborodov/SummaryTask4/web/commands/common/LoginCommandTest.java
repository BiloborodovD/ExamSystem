package ua.nure.biloborodov.SummaryTask4.web.commands.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.db.repository.UserRepository;
import ua.nure.biloborodov.SummaryTask4.db.repository.UsersTestsRepository;
import ua.nure.biloborodov.SummaryTask4.db.bean.UsersTests;
import ua.nure.biloborodov.SummaryTask4.db.entity.User;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest extends Mockito{
    
    @Mock HttpServletRequest request;
    @Mock HttpSession session;
    @Mock HttpServletResponse response;
    @Mock
    UserRepository userRepositoryMock;
    @Mock
    UsersTestsRepository usersTestsRepositoryMock;
    @Mock User user;
    
    @InjectMocks
    LoginCommand loginCommand;
    
    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
    }
    
    
    @Test
    public void testDoPostNullUser() throws Exception {
	when(request.getMethod()).thenReturn("POST");
	when(request.getParameter("login")).thenReturn("Dima");
	when(request.getParameter("password")).thenReturn("1");
	when(request.getSession()).thenReturn(session);
	when(userRepositoryMock.findByLogin("login")).thenReturn(null);
	when(usersTestsRepositoryMock.findByUser(null)).thenReturn(new ArrayList<UsersTests>());
	assertEquals(PagePath.PAGE_LOGIN, loginCommand.execute(request, response));
    }

    @Test
    public void testDoGet() throws Exception {
	when(request.getMethod()).thenReturn("GET");
	assertEquals(PagePath.PAGE_ERROR, new LoginCommand().execute(request, response));
    }
}
