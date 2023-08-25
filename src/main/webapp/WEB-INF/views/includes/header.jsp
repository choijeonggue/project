<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tf" tagdir="/WEB-INF/tags" %>

<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>project</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
let ctxPath = '${ctxPath}'

function checkExtension(fileName, fileSize){
	let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");	// 업로드 불가능한 파일 형식 지정
	let maxSize = 10485760; // 10MB
	if(fileSize > maxSize) {
		alert('파일크기는 최대 10MB까지 업로드 가능합니다.');
		return false; 
	}
	
	if(regex.test(fileName)) {
		alert('해당 종류의 파일은 업로드 할 수 없습니다.');
		return false; 
	}
	return true;
}	
</script>

</head>
<body>
<nav class="navbar navbar-expand-sm bg-light">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="${ctxPath == '' ? '/': ctxPath}">메인페이지</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${ctxPath}/board/list">게시판</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#"></a>
        </li>
    </ul>
</nav>




