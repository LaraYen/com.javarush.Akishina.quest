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

        log.debug("Переход на страницу Scene");
        req.getRequestDispatcher("/WEB-INF/quest-scene.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        QuestSession session = new QuestSession(req.getSession(false));
        int actionIndex = Integer.parseInt(req.getParameter("actionIndex"));
        log.debug("Индекс выбранного действия: {}", actionIndex);

        Quest currQuest = session.getCurrentQuest();
        Scene currScene = session.getCurrentScene();
        Action selectedAction = currScene.getActions()[actionIndex];

        int nextSceneId = selectedAction.getNextSceneId();
        currScene = currQuest.getScene(nextSceneId);
        log.info("Изменение текущей Scene на {}", currScene.getId());
        session.setCurrentScene(currScene);

        if (currScene.isFinalScene()) {

            if (currScene.getOutcome() == Outcome.VICTORY) {
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
