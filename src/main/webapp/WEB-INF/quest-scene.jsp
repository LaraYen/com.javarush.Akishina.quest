<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Квест</title>
    <style>
        button {
            margin: 5px;
            padding: 8px 12px;
            font-size: 16px;
            cursor: pointer;
        }
        .quest-button {
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
        }
        .quest-button:hover {
            background-color: #45a049;
        }
        .back-button {
            background-color: #008CBA;
            color: white;
            border: none;
            border-radius: 4px;
        }
        .back-button:hover {
            background-color: #007399;
        }
        .restart-button {
            background-color: #f44336;
            color: white;
            border: none;
            border-radius: 4px;
        }
        .restart-button:hover {
            background-color: #d32f2f;
        }
        .button-group {
            display: flex;
            gap: 10px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<h1>Квест: ${sessionScope.currQuest.title}</h1>
<h2>${sessionScope.currScene.title}</h2>
<div>
    <p>${sessionScope.currScene.description}</p>

    <c:choose>
        <c:when test="${not empty sessionScope.currScene.actionsList}">
            <c:forEach var="action" items="${sessionScope.currScene.actionsList}">
                <button class="quest-button"
                        onclick="location.href='${pageContext.request.contextPath}/quest-scene?questName=${sessionScope.currQuest.title}&sceneId=${action.nextSceneId}'">
                        ${action.text}
                </button>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="button-group">
                <button class="back-button"
                        onclick="location.href='${pageContext.request.contextPath}/quests'">
                    К выбору квестов
                </button>
                <button class="restart-button"
                        onclick="location.href='${pageContext.request.contextPath}/quest-scene?questName=${sessionScope.currQuest.title}'">
                    Начать сначала
                </button>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>