<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="stylesheet" href="/res/css/board/list.css?ver=4">

<div>
    <form action="/board/list" method="get">
        <div>
            <select name="searchType">
                <option value="1" ${param.searchType == 1 ? 'selected' : ''}>제목</option>
                <option value="2" ${param.searchType == 2 ? 'selected' : ''}>내용</option>
                <option value="3" ${param.searchType == 3 ? 'selected' : ''}>제목/내용</option>
                <option value="4" ${param.searchType == 4 ? 'selected' : ''}>글쓴이</option>
                <option value="5" ${param.searchType == 5 ? 'selected' : ''}>전체</option>
            </select>
            <input type="search" name="searchText" value="${param.searchText}">
            <input type="submit" value="검색">
        </div>
    </form>
</div>
<c:choose>
    <c:when test="${requestScope.maxPageNum == 0}">
        <div>글이 없습니다.</div>
    </c:when>
    <c:otherwise>

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
                    <c:set var = "eachTitle" value="${fn:replace(fn:replace(item.title, '>' , '&gt'),'<' ,'&lt;')}"/>
                    <c:if test="${param.searchType == 1 || param.searchType== 3 || param.searchType== 5}">
                        <c:set var="eachTitle" value="${fn:replace(eachTitle, param.searchText, '<mark>' += param.searchText += '</mark>')}"/>
                    </c:if>

                    <c:set var = "eachWriterNm" value="${item.writerNm}"/> <!--이름엔 장난칠수없음 회원가입 단계에서 막힘-->
                    <c:if test="${param.searchType == 4 || param.searchType == 5}">
                        <c:set var="eachWriterNm" value="${fn:replace(eachWriterNm, param.searchText, '<mark>' += param.searchText += '</mark>' )}"/>
                    </c:if>
                    <tr class="record" onclick="moveToDetail(${item.iboard})">
                        <!--
                        c:out 적는이유 >> 보안때문에.
                        내용에 자바스크립트 문법을 적었을때 작동하지 않게 함.
                        c:out 없을때 자바스크립트를 적으면 출력값 나옴.
                        -->
                        <td>${item.iboard}</td>
                        <td>${eachTitle}</td>
                        <td>${item.hit}</td>
                        <td>${eachWriterNm}</td>
                        <td>${item.rdt}</td>
                    </tr>
                </c:forEach>
            </table>

        </div>
        <div class="pageContainer">
            <c:set var = "selectedPage" value="${param.page == null ? 1 :param.page}"/>
            <c:forEach var= "i" begin="1" end="${requestScope.maxPageNum}" >
                <a href="/board/list?page=${i}&searchType=${param.searchType}&searchText=${param.searchText}"><span class="${selectedPage == i ? 'selected' : ''}"><c:out value="${i}"/></span></a>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>
<script src="/res/js/board/list.js"></script>
