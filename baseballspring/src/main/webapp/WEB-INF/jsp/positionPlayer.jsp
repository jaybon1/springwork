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
			<li class="nav-item">
				<a class="nav-link" href="/stadiumAdd">야구장 등록</a>
			</li>
			<li class="nav-item">
				<a class="nav-link " href="/teamAdd">팀 등록</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/playerAdd">선수 등록</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/outPlayerAdd">퇴출선수 등록</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/stadiumList">야구장 관리</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/teamList">팀 관리</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/playerList">선수 관리</a>
			</li>
			<li class="nav-item">
				<a class="nav-link active" href="/positionPlayer">포지션별 선수</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/teamPlayer">팀별 선수</a>
			</li>
		</ul>
	</div>
	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th>포지션</th>
					<th>롯데</th>
					<th>한화</th>
					<th>삼성</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach var="positionDto" items="${positionDtoList}">
					<tr>
						<td>${positionDto.position}</td>
						<td>${positionDto.lotte}</td>
						<td>${positionDto.hanhwa}</td>
						<td>${positionDto.samsung}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
<script>
</script>

</html>