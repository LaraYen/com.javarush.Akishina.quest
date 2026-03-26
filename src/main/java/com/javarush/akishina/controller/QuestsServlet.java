package com.javarush.akishina.controller;

import com.javarush.akishina.QuestSession;
import com.javarush.akishina.entity.Quest;
import com.javarush.akishina.entity.Scene;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "quests-servlet", value = "/quests")
public class QuestsServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        QuestSession session = new QuestSession(req.getSession(false));
        session.removeCurrQuest();

        req.getRequestDispatcher("/WEB-INF/quests.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        QuestSession session = new QuestSession(req.getSession(false));

        String questId = req.getParameter("questId");
        Quest currQuest = questService.getQuestById(questId);
        Scene currScene = questService.getFirstSceneByQuestId(questId);

        session.setCurrentQuest(currQuest);
        session.setCurrentScene(currScene);

        log.info("Выбран квест: {}", currQuest.getTitle());
        log.info("Идентификатор Scene: {}", currScene.getId());
        resp.sendRedirect(req.getContextPath() + "/quest-scene");

    }
}
