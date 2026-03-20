package com.javarush.akishina.controller;

import com.javarush.akishina.SessionAttribute;
import com.javarush.akishina.entity.Quest;
import com.javarush.akishina.entity.Scene;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static com.javarush.akishina.SessionAttribute.*;
import static net.bytebuddy.matcher.ElementMatchers.none;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.yaml.snakeyaml.DumperOptions.LineBreak.WIN;

class SceneServletTest extends BaseServletTest {

    private final SceneServlet sceneServlet = new SceneServlet();
    private Quest currQuestTest;

    @Test
    void doGetCorrectActionTest() throws ServletException, IOException {
        mockForward("/WEB-INF/quest-scene.jsp");

        sceneServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher("/WEB-INF/quest-scene.jsp");
        verify(dispatcher, times(1)).forward(request, response);
    }

    @Nested
    class postTest {

        @BeforeEach
        void setUp() {
            mockSession();
            loadQuests();
            currQuestTest = questsMapTest.get("тест");

            mockCurrQuest(currQuestTest);
            mockCurrScene(currQuestTest.getScene(1));
            mockRedirect("/quest-scene");

        }

        @Test
        void doPostCorrectActionWithFinalWinSceneTest() throws IOException {

            String finalWinSceneIndex = "2";
            when(request.getParameter("actionIndex")).thenReturn(finalWinSceneIndex);

            sceneServlet.doPost(request, response);

            verify(session).setAttribute(CURR_SCENE.getAttributeName(), currQuestTest.getScene(3));
            verify(session).setAttribute(eq(WIN_COUNT.getAttributeName()), anyInt());
            verify(response).sendRedirect("/quest-scene");
        }

        @Test
        void doPostCorrectActionWithFinalDefeatSceneTest() throws IOException {

            String finalDefeatSceneIndex = "1";
            when(request.getParameter("actionIndex")).thenReturn(finalDefeatSceneIndex);

            sceneServlet.doPost(request, response);

            verify(session).setAttribute(CURR_SCENE.getAttributeName(), currQuestTest.getScene(2));
            verify(session).setAttribute(eq(DEFEAT_COUNT.getAttributeName()), anyInt());
            verify(response).sendRedirect("/quest-scene");

        }
        @Test
        void doPostCorrectActionWithNotFinalSceneTest() throws IOException {

            String notFinalSceneIndex = "0";
            when(request.getParameter("actionIndex")).thenReturn(notFinalSceneIndex);

            sceneServlet.doPost(request, response);

            verify(session).setAttribute(CURR_SCENE.getAttributeName(), currQuestTest.getScene(1));
            verify(session, never()).setAttribute(eq(COMPLETED_QUESTS.getAttributeName()), anyInt());
            verify(response).sendRedirect("/quest-scene");

        }

    }


}