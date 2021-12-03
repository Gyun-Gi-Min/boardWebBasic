<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>

    <c:if test="${sessionScope.loginUser.iuser == requestScope.aaa.writer}">
        <div>
            <a href="/board/del?iboard=${requestScope.aaa.iboard}"><button>삭제</button></a>
            <a href="/board/regmod?iboard=${requestScope.aaa.iboard}"><button>수정</button></a>
        </div>
    </c:if>

    <table>
        <div>글번호 :<c:out value="${requestScope.aaa.iboard}"/></div>
        <div>글제목 :<c:out value="${aaa.title}"/></div>
        <div>내용 : <c:out value=" ${requestScope.aaa.ctnt}"/></div>
        <div>조회수 :<c:out value=" ${requestScope.aaa.hit}"/></div>
        <div>작성자 : <c:out value=" ${requestScope.aaa.writerNm}"/></div>
        <div>등록일시 : <c:out value=" ${requestScope.aaa.rdt}"/></div>
    </table>


</div>
