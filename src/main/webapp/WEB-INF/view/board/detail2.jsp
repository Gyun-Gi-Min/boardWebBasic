<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/res/css/board/detail.css">
<div>
    <c:if test="${sessionScope.loginUser.iuser == requestScope.aaa.writer}">
        <div>
            <a href="/board/del?iboard=${requestScope.aaa.iboard}"><button>삭제</button></a>
            <a href="/board/regmod?iboard=${requestScope.aaa.iboard}"><button>수정</button></a>
        </div>
    </c:if>
<c:if test="${sessionScope.loginUser != null}">
        <div>
        <c:choose>
            <c:when test="${requestScope.isHeart == 1}">
               <a href="/board/heart?proc=2&iboard=${requestScope.aaa.iboard}"><i class="fas fa-heart"></i></a>
            </c:when>
            <c:otherwise>
                <a href="/board/heart?proc=1&iboard=${requestScope.aaa.iboard}"><i class="far fa-heart"></i></a>
            </c:otherwise>
        </c:choose>
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
    <%--여기에 javascript 배운걸 활용하여 리스트 뿌려줄것.--%>


    <div id="cmtListContainer" data-iboard="${requestScope.aaa.iboard}"
         data-loginuserpk="${sessionScope.loginUser.iuser}"></div>



</div>
<div class="cmtModContainer">
    <div class="cmtBody">
        <form id="cmtModFrm" onsubmit="return false">

            <input type="hidden" name="icmt">
            <div><input type="text" name="ctnt" placeholder="내용"></div>
            <div>
                <input type="submit" value="수정">
                <input type="button" value="취소" id="btnCancel">
            </div>
        </form>
    </div>
</div>


<script src="/res/js/board/detail2.js?ver=1"></script>