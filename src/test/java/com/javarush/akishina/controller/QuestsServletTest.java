package com.javarush.akishina.controller;

import com.javarush.akishina.entity.Quest;
import com.javarush.akishina.entity.Scene;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Map;

import static com.javarush.akishina.SessionAttribute.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestsServletTest extends BaseServletTest {

    QuestsServlet questsServlet;

    @BeforeEach
    void setUp() {
        questsServlet = new QuestsServlet();
        mockSession();
    }

    @Test
    void doGetCorrectActionTest() throws ServletException, IOException {

        mockForward("/WEB-INF/quests.jsp");

        questsServlet.doGet(request, response);

        verify(session).removeAttribute(CURR_QUEST.getAttributeName());
        verify(session).removeAttribute(CURR_SCENE.getAttributeName());
        verify(session).setAttribute(eq(QUESTS_MAP.getAttributeName()), any(Map.class));
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/quests.jsp");
        verify(dispatcher, times(1)).forward(request, response);

    }

    @Test
    void doPostCorrectActionTest() throws IOException {

        loadQuests();

        when(request.getParameter("questName")).thenReturn("тест");
        when(session.getAttribute(QUESTS_MAP.getAttributeName())).thenReturn(questsMapTest);
        mockRedirect("");

        questsServlet.doPost(request, response);

        verify(session).setAttribute(eq(CURR_QUEST.getAttributeName()), any(Quest.class));
        verify(session).setAttribute(eq(CURR_SCENE.getAttributeName()), any(Scene.class));
        verify(response).sendRedirect("/quest-scene");

    }

}