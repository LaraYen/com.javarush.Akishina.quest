package com.javarush.akishina.controller;

import com.javarush.akishina.QuestSession;
import com.javarush.akishina.entity.Action;
import com.javarush.akishina.entity.Outcome;
import com.javarush.akishina.entity.Quest;
import com.javarush.akishina.entity.Scene;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "sceneServlet", value = "/quest-scene")
public class SceneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/quest-scene.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        QuestSession session = new QuestSession(req.getSession());
        int actionIndex = Integer.parseInt(req.getParameter("actionIndex"));

        Quest currQuest = session.getCurrentQuest();
        Scene currScene = session.getCurrentScene();
        Action selectedAction = currScene.getActions()[actionIndex];

        int nextSceneId = selectedAction.getNextSceneId();
        currScene = currQuest.getScene(nextSceneId);
        session.setCurrentScene(currScene);

        if (currScene.isFinalScene()) {

            if (currScene.getOutcome() == Outcome.VICTORY) {
                session.incrementWinQuest();
            } else {
                session.incrementDefeatQuest();
            }

        }

        resp.sendRedirect(req.getContextPath() + "/quest-scene");
    }
}
