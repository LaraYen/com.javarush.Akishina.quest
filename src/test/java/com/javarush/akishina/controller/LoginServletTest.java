package com.javarush.akishina.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static com.javarush.akishina.SessionAttribute.CLIENT_IP;
import static com.javarush.akishina.SessionAttribute.USER_NAME;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServletTest extends BaseServletTest {

    private LoginServlet loginServlet;

    @BeforeEach
    void setUp() {
        loginServlet = new LoginServlet();
    }

    @Test
    void doGetCorrectActionTest() throws Exception {

        mockForward("/WEB-INF/index.jsp");

        loginServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher("/WEB-INF/index.jsp");
        verify(dispatcher, times(1)).forward(request, response);

    }

    @ParameterizedTest
    @CsvSource("name, someIp")
    void doPostCorrectActionTest(String testName, String testIp) throws IOException {

        mockSession();
        mockRedirect("");
        when(request.getParameter(USER_NAME.getAttributeName())).thenReturn(testName);
        when(request.getRemoteAddr()).thenReturn(testIp);

        loginServlet.doPost(request, response);

        verify(session).setAttribute(USER_NAME.getAttributeName(), testName);
        verify(session).setAttribute(CLIENT_IP.getAttributeName(), testIp);

        verify(response).sendRedirect("/quests");
    }

}