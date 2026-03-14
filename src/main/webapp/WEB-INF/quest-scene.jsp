<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Квест</title>
    <link href="<c:url value='/static/main.css'/>" rel="stylesheet">
</head>
<body>
<div class="container container-flex">
    <h1>Квест: ${sessionScope.currQuest.title}</h1>
    <h2>${sessionScope.currScene.title}</h2>
    <p>${sessionScope.currScene.description}</p>

    <c:if test="${not empty sessionScope.currScene.actions}">
        <h3>Выберите действие:</h3>
        <div>
            <c:forEach var="action" items="${sessionScope.currScene.actions}" varStatus="status">
                <form action="${pageContext.request.contextPath}/quest-scene" method="post" style="display: inline;">
                    <input type="hidden" name="actionIndex" value="${status.index}">
                    <button type="submit" class="btn-primary">${action.text}</button>
                </form>
            </c:forEach>
        </div>
    </c:if>

    <hr class="separator">

    <h3>Навигация:</h3>
    <div style="display: flex; gap: 15px;">
        <button class="btn-secondary" onclick="location.href='${pageContext.request.contextPath}/quests'">
            ← К выбору квестов
        </button>
        <button class="btn-secondary" onclick="location.href='${pageContext.request.contextPath}/restart-quest'">
            ↻ Начать сначала (Защитается поражение!)
        </button>
    </div>
</div>

<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>