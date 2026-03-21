package com.javarush.akishina.controller;

import com.javarush.akishina.QuestLoader;
import com.javarush.akishina.QuestSession;
import com.javarush.akishina.entity.Quest;
import com.javarush.akishina.entity.Scene;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

@Slf4j
@WebServlet(name = "quests-servlet", value = "/quests")
public class QuestsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        QuestSession session = new QuestSession(req.getSession(false));

        session.removeCurrQuest();

        Map<String, Quest> questsMap = new QuestLoader().loadQuests();
        log.info("Загрузились квесты: {}", questsMap);

        session.setQuestsMap(questsMap);
        req.getRequestDispatcher("/WEB-INF/quests.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        QuestSession session = new QuestSession(req.getSession(false));

        Quest currQuest = session.getQuestsMap().get(req.getParameter("questName"));
        Scene currScene = currQuest.getFirstScene();

        session.setCurrentQuest(currQuest);
        session.setCurrentScene(currScene);

        log.info("Выбран квест: {}", currQuest.getTitle());
        log.info("Идентификатор Scene: {}", currScene.getId());
        resp.sendRedirect(req.getContextPath() + "/quest-scene");

    }
}
