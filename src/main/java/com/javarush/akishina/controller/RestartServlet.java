package com.javarush.akishina.controller;

import com.javarush.akishina.QuestSession;
import com.javarush.akishina.entity.Quest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "restart-servlet", value = "/restart-quest")
public class RestartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        QuestSession session = new QuestSession(req.getSession(false));
        Quest currQuest = session.getCurrentQuest();

        if (!session.getCurrentScene().isFinalScene()) {
            session.incrementDefeatQuest();
        }

        session.setCurrentScene(currQuest.getFirstScene());
        log.info("Квест {} сброшен.", currQuest);

        resp.sendRedirect(req.getContextPath() + "/quest-scene");

    }
}
