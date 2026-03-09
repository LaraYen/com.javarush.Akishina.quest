package com.javarush.akishina.controller;

import com.javarush.akishina.QuestLoader;
import com.javarush.akishina.entity.Quest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "quests-servlet", value = "/quests")
public class QuestsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Quest> questsMap = new HashMap<>();
        HttpSession session = req.getSession();

        session.removeAttribute("currQuest");
        session.removeAttribute("currScene");

        questsMap = new QuestLoader().loadQuests();

        req.getSession().setAttribute("quests", questsMap);
        req.getRequestDispatcher("/WEB-INF/quests.jsp").forward(req, resp);
    }

}
