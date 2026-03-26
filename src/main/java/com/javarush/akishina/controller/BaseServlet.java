package com.javarush.akishina.controller;

import com.javarush.akishina.service.QuestService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;

public class BaseServlet extends HttpServlet {
    protected QuestService questService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        questService = (QuestService) ctx.getAttribute("questService");
    }
}
