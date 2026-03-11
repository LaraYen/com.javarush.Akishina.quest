package com.javarush.akishina.controller;

import com.javarush.akishina.QuestSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet("")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
        log.info("Запуск приложения, перевод на страницу входа.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String userName = req.getParameter("userName");
        QuestSession questSession = new QuestSession(req.getSession());
        questSession.setUserName(userName);
        questSession.setClientIp(req.getRemoteAddr());

        resp.sendRedirect(req.getContextPath() + "/quests");
        log.info("Пользователь ввел имя: {}, перенаправление на страницу квестов.", userName);

    }

}
