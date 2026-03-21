package com.javarush.akishina.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter("/")
public class ExceptionHandlerFIlter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        try {
            filterChain.doFilter(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write("<html><body>");
            resp.getWriter().write("<h1>Упс! Что-то пошло не так.</h1>");
            resp.getWriter().write("<p>Мы уже знаем об ошибке и скоро её исправим.</p>");
            resp.getWriter().write("<a href=\"" + req.getContextPath() + "/\">На главную</a>");
            resp.getWriter().write("</body></html>");
        }

    }
}
