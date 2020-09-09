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
			<li class="nav-item"><a class="nav-link active"
				href="/playerAdd">선수 등록</a></li>
			<li class="nav-item"><a class="nav-link" href="/outPlayerAdd">퇴출선수
					등록</a></li>
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
		<form action="/player" method="post">
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">선수 이름</span>
				</div>
				<input name="name" type="text" class="form-control"
					style="width: 50%" placeholder="선수 이름을 입력해주세요" required="required">
				<div class="input-group-prepend">
					<span class="input-group-text">포지션</span>
				</div>
				<select name="position" required="required">
					<option value="">선택</option>
					<option value="투수">투수</option>
					<option value="내야수">내야수</option>
					<option value="외야수">외야수</option>
					<option value="포수">포수</option>
				</select>
				<div class="input-group-prepend">
					<span class="input-group-text">팀 이름</span>
				</div>
				<select name="teamId" required="required">
					<option value="">선택</option>
					<c:forEach var="team" items="${teamList}">
						<option value="${team.id}">${team.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="text-center">
				<input class="btn btn-outline-primary" type="submit" value="등록하기">
			</div>
		</form>
	</div>
</body>
<script>
</script>

</html>