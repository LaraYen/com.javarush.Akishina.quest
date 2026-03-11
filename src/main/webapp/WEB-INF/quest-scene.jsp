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

        .separator {
            margin: 30px 0 20px 0;
            border: 1px solid #ddd;
        }
        .nav-buttons {
            display: flex;
            gap: 15px;
            justify-content: left;
        }
        .nav-button {
            background-color: transparent;
            color: #666;
            border: 1px solid #ccc;
            border-radius: 4px;
            padding: 8px 16px;
            font-size: 14px;
            cursor: pointer;
            transition: all 0.3s;
        }
        .nav-button:hover {
            background-color: #f0f0f0;
            color: #333;
        }
    </style>
</head>
<body>
    <h1>Квест: ${sessionScope.currQuest.title}</h1>
    <h2>${sessionScope.currScene.title}</h2>
    <div>
        <p>${sessionScope.currScene.description}</p>

        <c:if test="${not empty sessionScope.currScene.actions}">
            <h3>Выберите действие:</h3>
            <div>
                <c:forEach var="action" items="${sessionScope.currScene.actions}" varStatus="status">
                    <form action="${pageContext.request.contextPath}/quest-scene" method="post" style="display: inline;">
                        <input type="hidden" name="actionIndex" value="${status.index}">
                        <button type="submit" class="quest-button">${action.text}</button>
                    </form>
                </c:forEach>
            </div>
        </c:if>

        <hr class="separator">

        <h3>Навигация:</h3>
        <div class="nav-buttons">
            <button class="nav-button"
                    onclick="location.href='${pageContext.request.contextPath}/quests'">
                ← К выбору квестов
            </button>
            <button class="nav-button"
                    onclick="location.href='${pageContext.request.contextPath}/restart-quest'">
                ↻ Начать сначала (Защитается поражение!)
            </button>
        </div>
    </div>
    <footer style="margin-top: 40px; padding: 10px; background-color: #f5f5f5; border-top: 1px solid #ddd; font-size: 12px; color: #666; text-align: left;">
        <div>
            <span>Игрок: ${sessionScope.userName != null ? sessionScope.userName : 'Гость'}</span>
            <span style="margin-left: 20px;">IP: ${sessionScope.clientIp != null ? sessionScope.clientIp : pageContext.request.remoteAddr}</span>
            <span style="margin-left: 20px;">Завершено квестов: ${sessionScope.completedQuests != null ? sessionScope.completedQuests : 0}</span>
            <span style="margin-left: 20px;">Количество побед: ${sessionScope.winCount != null ? sessionScope.winCount : 0}</span>
            <span style="margin-left: 20px;">Количество поражений: ${sessionScope.defeatCount != null ? sessionScope.defeatCount : 0}</span>
        </div>
    </footer>
</body>
</html>