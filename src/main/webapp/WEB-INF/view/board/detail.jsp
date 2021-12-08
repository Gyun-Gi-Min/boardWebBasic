<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>

    <c:if test="${sessionScope.loginUser.iuser == requestScope.aaa.writer}">
        <div>
            <a href="/board/del?iboard=${requestScope.aaa.iboard}"><button>삭제</button></a>
            <a href="/board/regmod?iboard=${requestScope.aaa.iboard}"><button>수정</button></a>
        </div>
    </c:if>
        <div>글번호 :<c:out value="${requestScope.aaa.iboard}"/></div>
        <div>글제목 :<c:out value="${aaa.title}"/></div>
        <div>내용 : <c:out value=" ${requestScope.aaa.ctnt}"/></div>
        <div>조회수 :<c:out value=" ${requestScope.aaa.hit}"/></div>
        <div>작성자 : <c:out value=" ${requestScope.aaa.writerNm}"/></div>
        <div>등록일시 : <c:out value=" ${requestScope.aaa.rdt}"/></div>

    <c:if test="${sessionScope.loginUser != null}">
        <div>
            <form action="/board/cmt/reg" method="post">
                <input type="hidden" name="iboard" value="${requestScope.aaa.iboard}">
                <input type="text" name="ctnt" placeholder="댓글 내용~">
                <input type="submit" value="댓글쓰기">
            </form>
        </div>
    </c:if>

    <div>
        <table>
            <tr>
                <th>내용</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>비고</th>
            </tr>
            <c:forEach items="${requestScope.cmtList}" var="item">
                <tr>
                    <td><c:out value="${item.ctnt}"/></td>
                    <td>${item.writerNm}</td>
                    <td>${item.rdt}</td>
                    <td>
                        <c:if test="${sessionScope.loginUser.iuser == item.writer}">
                            <button>수정</button>
                            <button onclick="isDelCmt(${requestScope.aaa.iboard}, ${item.icmt});">삭제</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<script src="/res/js/board/detail.js?ver=2"></script>