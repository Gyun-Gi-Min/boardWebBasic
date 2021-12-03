<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/res/css/board/list.css">
    <div>
        <table id="boardTable">
            <tr>
                <th>no</th>
                <th>title</th>
                <th>hit</th>
                <th>writer</th>
                <th>reg-datetime</th>
            </tr>
            <c:forEach items="${requestScope.list}" var="item">
                <tr class="record" onclick="moveToDetail(${item.iboard})">
                    <!--
                    c:out 적는이유 >> 보안때문에.
                    내용에 자바스크립트 문법을 적었을때 작동하지 않게 함.
                    c:out 없을때 자바스크립트를 적으면 출력값 나옴.
                    -->
                    <td>${item.iboard}</td>
                    <td><c:out value="${item.title}"/></td>
                    <td>${item.hit}</td>
                    <td><c:out value="${item.writer}"/></td>
                    <td>${item.rdt}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
<script src="/res/js/board/list.js"></script>
