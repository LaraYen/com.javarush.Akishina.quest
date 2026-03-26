<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Квесты</title>
    <link href="<c:url value='/static/main.css'/>" rel="stylesheet">
</head>
<body>
<div class="container container-flex">
    <h1>Добро пожаловать, ${sessionScope.userName}</h1>
    <h2>Список доступных квестов:</h2>

    <c:forEach var="quest" items="${applicationScope.questService.allQuests}">
        <div class="quest-card">
            <h3>${quest.title}</h3>
            <p>${quest.description}</p>
            <form action="${pageContext.request.contextPath}/quests" method="post" style="display: inline;">
                <input type="hidden" name="questId" value="${quest.id}">
                <button type="submit" class="btn-primary">Играть</button>
            </form>
        </div>
    </c:forEach>
</div>

<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>