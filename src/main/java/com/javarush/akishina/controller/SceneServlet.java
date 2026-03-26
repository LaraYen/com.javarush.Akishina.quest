package com.javarush.akishina.controller;

import com.javarush.akishina.QuestSession;
import com.javarush.akishina.entity.Action;
import com.javarush.akishina.entity.Outcome;
import com.javarush.akishina.entity.Scene;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "sceneServlet", value = "/quest-scene")
public class SceneServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        QuestSession session = new QuestSession(req.getSession(false));
        Scene currScene = session.getCurrentScene();
        Action[] actions = questService.getActionsByArrayId(currScene.getActions());
        req.setAttribute("actions", actions);

        log.debug("Переход на страницу Scene");
        req.getRequestDispatcher("/WEB-INF/quest-scene.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        QuestSession session = new QuestSession(req.getSession(false));

        String actionId = req.getParameter("actionId");
        log.debug("Id выбранного действия: {}", actionId);

        Action selectedAction = questService.getActionById(actionId);

        String nextSceneId = selectedAction.getNextSceneId();
        Scene newScene = questService.getSceneById(nextSceneId);
        log.info("Изменение текущей Scene на {}", newScene.getId());

        session.setCurrentScene(newScene);

        if (newScene.isFinalScene()) {

            if (newScene.getOutcome() == Outcome.VICTORY) {
                session.incrementWinQuest();
                log.info("Увеличение счетчика побед.");
            } else {
                session.incrementDefeatQuest();
                log.info("Увеличение счетчика поражений.");
            }

        }

        log.info("Переход на новую Scene");
        resp.sendRedirect(req.getContextPath() + "/quest-scene");
    }
}
