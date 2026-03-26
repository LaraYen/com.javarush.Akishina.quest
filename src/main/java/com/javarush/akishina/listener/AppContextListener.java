package com.javarush.akishina.listener;

import com.javarush.akishina.service.QuestService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext ctx = sce.getServletContext();
        QuestService questService = new QuestService();
        ctx.setAttribute("questService", questService);

        log.info("Загрузились данные квестов.");

        log.debug("Загрузились квесты: {}", questService.getAllQuests());
        log.debug("Загрузились сцены: {}", questService.getAllScenes());
        log.debug("Загрузились действия: {}", questService.getAllActions());

    }
}
