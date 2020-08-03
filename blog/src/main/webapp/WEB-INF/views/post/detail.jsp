<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp" %>

<h1>상세페이지 입니다.</h1>

<div class="container">    
  <table class="table table-hover">
    <thead>
      <tr>
        <th>번호</th>
        <th>타이틀</th>
        <th>내용</th>
        <th>작성자</th>
      </tr>
    </thead>
    <tbody>
	      <tr>
	      	<td><input id="id" type="text" value="${postDetailRespDto.id}" readonly="readonly"></td>
	      	<td><input id="title" type="text" value="${postDetailRespDto.title}" readonly="readonly"></td>
	      	<td><input id="content" type="text" value="${postDetailRespDto.content}" readonly="readonly"></td>
	      	<td><input id="username" type="text" value="${postDetailRespDto.username}" readonly="readonly"></td>
	      </tr>
    </tbody>
  </table>
  <button id="btn-update-mode" class="btn btn-warning">수정</button>
  <button id="btn-delete" class="btn btn-danger">삭제</button>
</div>

<script src="/js/post.js"></script>
<%@include file="../layout/footer.jsp" %>

