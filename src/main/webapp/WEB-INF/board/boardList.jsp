<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록 페이지</title>
<style type="text/css">
div.container {
	width: 1000px;
}

p.title {
	border: 1px solid #000000;
	padding: 20px 10px;
	text-align: center;
	font-weight: bold;
	background: #f9f9f9;
}

table.form-table {
	width: 100%;
	border: 1px solid #000000;
	border-collapse: collapse;
	margin-top: 20px;
	margin-bottom: 10px;
}

table.form-table th, table.form-table td {
	border: 1px solid #000000;
}

table.form-table th {
	padding: 2px 5px 3px 5px;
	text-align: center;
}

div.button-area {
	text-align: right;
}

div.button-area a.button {
	font-size: 13px;
	border: 1px solid #000000;
	border-radious: 3px;
	padding: 3px 7px;
	text-decoration: none;
	color: #000000;
	background: #f9f9f9;
	cursor: pointer;
}

div.button-area a.button:hover {
	font-weight: bold;
	background: #e7e7e7;
}

<
style type ="text /css ">div.paging {
	text-align: center;
}

div.paging a.current {
	font-weight: bold;
}
</style>

</head>
<body>
	<div class="container">
		<p class="title">Spring boot 로 만들어 보는 게시판</p>

		<div class="button-area">
			<a class="button" href="/board/form">게시물 등록</a>
		</div>
		<table class="form=table">
			<thead>
				<tr>
					<th style="width: 50px">#</th>
					<th>제목</th>
					<th style="width: 200px">작성자</th>
					<th style="width: 80px">조회수</th>
					<th style="width: 140px">등록일시</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}">
					<tr>
						<td>${board.no}</td>
						<td style="text-align: left;">
							<a href="/board/detail?boardSeq=${board.boardSeq}">${board.title}</a>
						</td>
						<td>${board.writer}</td>
						<td>${board.hits}</td>
						<td>${board.regDt}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			<c:choose>
				<c:when test="${startUnitNum gt 1}">
					<c:set var="beforePage" value="${startUnitNum-1}" />
				</c:when>
				<c:otherwise>
					<c:set var="beforePage" value="1" />
				</c:otherwise>
			</c:choose>
			<a href="/board/list?pageNum=${beforePage}">&lt;</a>
			
			<c:forEach var="page" begin="${startUnitNum}" end="${endUnitNum}">
				<a <c:if test="${page eq pageNum}">class="current"</c:if> href= "/board/list?pageNum=${page}">${page}</a>
			</c:forEach>
			<c:choose>
				<c:when test="${endUnitNum lt totalPagingNum}">
					<c:set var="afterPage" value="${endUnitNum+1}" />
				</c:when>
				<c:otherwise>
					<c:set var="afterPage" value="${endUnitNum}" />
				</c:otherwise>
			</c:choose>
				<a href="/board/list?pageNum=${afterPage}">&gt;</a>
		</div>
	</div>
</body>
</html>