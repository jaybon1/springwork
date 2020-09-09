<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>선수 관리</title>
</head>
<body>
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link" href="/stadiumAdd">야구장
					등록</a></li>
			<li class="nav-item"><a class="nav-link " href="/teamAdd">팀
					등록</a></li>
			<li class="nav-item"><a class="nav-link" href="/playerAdd">선수
					등록</a></li>
			<li class="nav-item"><a class="nav-link active"
				href="/outPlayerAdd">퇴출선수 등록</a></li>
			<li class="nav-item"><a class="nav-link" href="/stadiumList">야구장
					관리</a></li>
			<li class="nav-item"><a class="nav-link" href="/teamList">팀
					관리</a></li>
			<li class="nav-item"><a class="nav-link" href="/playerList">선수
					관리</a></li>
			<li class="nav-item"><a class="nav-link" href="/positionPlayer">포지션별
					선수</a></li>
			<li class="nav-item"><a class="nav-link" href="/teamPlayer">팀별
					선수</a></li>
		</ul>
	</div>
	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th>id</th>
					<th>선수 이름</th>
					<th>팀</th>
					<th>퇴출사유</th>
					<th>퇴출일</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach var="player" items="${playerList}">
					<tr>
						<td>${player.id}</td>
						<td>${player.name}</td>
						<c:choose>
							<c:when test="${player.team.name == null}">
								<td>없음</td>
							</c:when>
							<c:otherwise>
								<td>${player.team.name}</td>
							</c:otherwise>
						</c:choose>
						<form action="outPlayer" method="post">
							<td><input name="reason" type="text" required="required"></td>
							<td><input name="day" type="date" required="required"></td>
							<td><input name="playerId" type="hidden" value="${player.id}"></td>
							<td><button class="btn btn-danger btn-delete">퇴출</button></td>
						</form>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
<script>
</script>

</html>