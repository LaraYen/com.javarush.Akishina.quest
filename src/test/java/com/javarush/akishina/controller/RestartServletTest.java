package com.javarush.akishina.controller;

import com.javarush.akishina.entity.Quest;
import com.javarush.akishina.entity.Scene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.javarush.akishina.SessionAttribute.*;
import static org.mockito.Mockito.*;

class RestartServletTest extends BaseServletTest {

    private final RestartServlet restartServlet = new RestartServlet();

    private Scene finalScene;

    private Scene notFinalScene;

    private Quest testQuest;

    @BeforeEach
    void setUp() {

        loadQuests();
        testQuest = questsMapTest.get("тест");

        finalScene = testQuest.getScene(2);
        notFinalScene = testQuest.getScene(1);

        mockSession();
        mockCurrQuest(testQuest);
        mockRedirect("");
    }

    @Test
    void doGetCorrectActionWithFinalScene() throws IOException {
        mockCurrScene(finalScene);

        restartServlet.doGet(request, response);

        verify(session, never()).setAttribute(eq(DEFEAT_COUNT.getAttributeName()), anyInt());
        verify(session).setAttribute(CURR_SCENE.getAttributeName(), testQuest.getFirstScene());
        verify(response).sendRedirect("/quest-scene");
    }

    @Test
    void doGetCorrectActionWithNotFinalScene() throws IOException {
        mockCurrScene(notFinalScene);

        restartServlet.doGet(request, response);

        verify(session).setAttribute(eq(DEFEAT_COUNT.getAttributeName()), anyInt());
        verify(session).setAttribute(CURR_SCENE.getAttributeName(), testQuest.getFirstScene());
        verify(response).sendRedirect("/quest-scene");
    }
}