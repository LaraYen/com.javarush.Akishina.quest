<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alino
  Date: 04.03.2026
  Time: 1:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>Список доступных квестов:</h1>

<c:forEach var="entry" items="${quests}">
    <div class="quest-card">
        <h3>${entry.key}</h3>
        <p>${entry.value.getDescription()}</p>
        <button onclick="location.href='${pageContext.request.contextPath}/quest-scene?questName=${entry.key}'">
            Играть
        </button>
    </div>
</c:forEach>

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
