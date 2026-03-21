<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="footer">
    <span>Игрок: ${sessionScope.userName != null ? sessionScope.userName : 'Гость'}</span>
    <span>IP: ${sessionScope.clientIp != null ? sessionScope.clientIp : pageContext.request.remoteAddr}</span>
    <span>Завершено квестов: ${sessionScope.completedQuests != null ? sessionScope.completedQuests : 0}</span>
    <span>Побед: ${sessionScope.winCount != null ? sessionScope.winCount : 0}</span>
    <span>Поражений: ${sessionScope.defeatCount != null ? sessionScope.defeatCount : 0}</span>
</div>