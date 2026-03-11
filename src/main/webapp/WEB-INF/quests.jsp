<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>Добро пожаловать, ${sessionScope.userName}</h1>
<h2>Список доступных квестов:</h2>

<c:forEach var="entry" items="${questsMap}">
    <div class="quest-card">
        <h3>${entry.key}</h3>
        <p>${entry.value.getDescription()}</p>
        <form action="${pageContext.request.contextPath}/quests" method="post" style="display: inline;">
            <input type="hidden" name="questName" value="${entry.key}">
            <button class="button" type="submit" >Играть</button>
        </form>
    </div>
</c:forEach>

<footer style="margin-top: 40px; padding: 10px; background-color: #f5f5f5; border-top: 1px solid #ddd; font-size: 12px; color: #666; text-align: left;">
    <div>
        <span>Игрок: ${sessionScope.userName != null ? sessionScope.userName : 'Гость'}</span>
        <span style="margin-left: 20px;">IP: ${sessionScope.clientIp != null ? sessionScope.clientIp : pageContext.request.remoteAddr}</span>
        <span style="margin-left: 20px;">Завершено квестов: ${sessionScope.completedQuests != null ? sessionScope.completedQuests : 0}</span>
        <span style="margin-left: 20px;">Количество побед: ${sessionScope.winCount != null ? sessionScope.winCount : 0}</span>
        <span style="margin-left: 20px;">Количество поражений: ${sessionScope.defeatCount != null ? sessionScope.defeatCount : 0}</span>
    </div>
</footer>

<style>
    .button {
        display: inline-block;
        padding: 10px 20px;
        background-color: #4CAF50;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        margin: 5px;
    }
    .button:hover {
        background-color: #45a049;
    }
    .quest-card {
        border: 1px solid #ccc;
        padding: 15px;
        margin: 10px;
        border-radius: 5px;
    }
</style>
</body>
<head>
    <title>Quest</title>
</head>
</html>
