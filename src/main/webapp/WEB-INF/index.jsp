<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Сайт с квестами</title>
    <link href="<c:url value='/static/main.css'/>" rel="stylesheet">
</head>
    <body>
        <h1>Сайт с квестами!</h1>
        <div class="container">
            <form action="${pageContext.request.contextPath}" method="post" id="questForm">
                <label for="nameInput">Введите имя</label>
                <input type="text" id="nameInput" name="name" placeholder="Например, Иван">
                <br>
                <button type="submit">
                    Отправить
                </button>
            </form>
        </div>
    </body>
</html>
