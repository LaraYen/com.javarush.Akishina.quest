package com.javarush.akishina.controller;

import com.javarush.akishina.entity.Quest;
import com.javarush.akishina.entity.Scene;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "sceneServlet", value = "/quest-scene")
public class SceneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Quest currQuest = (Quest) session.getAttribute("currQuest");

        if (currQuest == null) {
            currQuest = getCurrQuest(req, session);
        }

        setCurrSceneToSession(req, session, currQuest);

        req.getRequestDispatcher("/WEB-INF/quest-scene.jsp").forward(req, resp);

    }

    private Map<String, Quest> getQuestMap(HttpSession currentSession) {
        Object questsAttribute = currentSession.getAttribute("quests");

        if (questsAttribute.getClass() != HashMap.class) {
            currentSession.invalidate();
            throw new RuntimeException("Session is broken, try one more time");
        }

        return (HashMap<String, Quest>) questsAttribute;
    }

    private Quest getCurrQuest(HttpServletRequest req, HttpSession session) {

        HashMap<String, Quest> questMap = new HashMap<>(getQuestMap(session));
        Quest currQuest = questMap.get(req.getParameter("questName"));
        session.setAttribute("currQuest", currQuest);

        return currQuest;
    }

    private void setCurrSceneToSession(HttpServletRequest req, HttpSession session, Quest currQuest) {

        Scene currScene;

        if (req.getParameter("sceneId") != null) {
            currScene = currQuest.getScene(Integer.parseInt(req.getParameter("sceneId")));
            session.setAttribute("currScene", currScene);
        } else {
            currScene = currQuest.getFirstScene();
            session.setAttribute("currScene", currScene);
        }

    }
}
