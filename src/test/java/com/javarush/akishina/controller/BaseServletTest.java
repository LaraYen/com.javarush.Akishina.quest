package com.javarush.akishina.controller;

import com.javarush.akishina.QuestLoader;
import com.javarush.akishina.SessionAttribute;
import com.javarush.akishina.entity.Quest;
import com.javarush.akishina.entity.Scene;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static com.javarush.akishina.SessionAttribute.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public abstract class BaseServletTest {


    @Mock
    protected HttpSession session;

    @Mock
    protected HttpServletRequest request;

    @Mock
    protected HttpServletResponse response;

    @Mock
    RequestDispatcher dispatcher;

    protected Map<String, Quest> questsMapTest;

    /*@BeforeEach
    void setUp() {
        when(request.getSession()).thenReturn(session);
    }*/

    protected void mockForward(String path) {
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
    }

    protected void mockSession() {
        when(request.getSession()).thenReturn(session);
    }

    protected void mockRedirect(String path) {
        when(request.getContextPath()).thenReturn("");
    }

    protected void mockCurrScene(Scene currScene) {
        when(session.getAttribute(CURR_SCENE.getAttributeName())).thenReturn(currScene);
    }

    protected void mockCurrQuest(Quest currQuest) {
        when(session.getAttribute(CURR_QUEST.getAttributeName())).thenReturn(currQuest);

    }

    protected void loadQuests() {
        questsMapTest = new QuestLoader().loadQuests();
    }

}
