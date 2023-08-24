<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>
<nav class="navbar navbar-expand-lg bg-light navbar-light fixed-top">
  	<div class="container">
    	<a class="navbar-brand" href="/project/">중고월드</a>
    	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
    			<span class="navbar-toggler-icon"></span>      <!-- 화면이 좁아지면 메뉴들을 숨기고 ||| 모양의 버튼이 나오도록 만들었음 -->
    	</button>
	   	<div class="collapse navbar-collapse" id="navbarResponsive">
	      	<ul class="navbar-nav ml-auto">
                <!--로그인 안했을 때의 상단 메뉴-->
	      		<c:if test="${userForm == null }">           
					<li class="nav-item">
	  					<a class="nav-link" href="login">로그인
	    					<span class="sr-only">(current)</span>
	  					</a>
					</li>
					<li class="nav-item">
	  					<a class="nav-link" href="member/join">회원가입</a>
					</li>          
				</c:if>
                <!--일반회원일 때의 상단 메뉴-->
				<c:if test="${userForm != null && userForm.isManager==0 }">
	 				<li class="nav-item">
	   					<a class="nav-link" href="${ctxPath}/mypage">마이페이지</a>
	 				</li>
	 				
					<li class="nav-item">
	    				<a class="nav-link" href="/user/Logout">로그아웃</a>
					</li>
				</c:if>          
                <!--관리자일 때의 상단메뉴-->
				<c:if test="${userForm != null && userForm.isManager==1 }">
					<li class="nav-item">
				   		<a class="nav-link" href="/user/userList?userNum=1">회원 관리</a>
					</li>
					<li class="nav-item">
				   		<a class="nav-link" href="/order/userOrder?userOrderNum=1">주문 관리</a>
					</li>
					<li class="nav-item">
				   		<a class="nav-link" href="/bbs/bbsWrite">제품 등록</a>
					</li>
					<li class="nav-item">
				    	<a class="nav-link" href="/user/Logout">로그아웃</a>
					</li>
				</c:if>
	      	</ul>
	   	</div>
  	</div>
</nav>

<div class="col-lg-3">
	<br>
	<h1 class="my-4"><img class="d-block img-fluid" width="300" height="100" src="${pageContext.request.contextPath}/resources/images/로고.JPG"></h1>		<br>
   	<div class="menu">
      	<ul class="accordion">
      		<li><a href="#menu1">의류</a>
      			<ul>
	      			<li><a href="#">신발</a></li>
	     			<li><a href="#">악세사리</a></li>
	      			<li><a href="#">캐주얼</a></li>
					<li><a href="#">모자</a></li>
	      		</ul>
	      	</li>
	     	<li><a href="#menu2">생활용품</a>
	    		<ul>
	      			<li><a href="#">주방</a></li>
		      		<li><a href="#">청소</a></li>
					<li><a href="#">다용도</a></li>
	      		</ul>
	      	</li>
	      	<li><a href="#menu3">가전</a>
			    <ul>
			    	<li><a href="#">스마트폰</a></li>
			      	<li><a href="#">TV</a></li>
			      	<li><a href="#">기타</a></li>
			      </ul>
			</li>
			<li><a href="#menu4">탈 것</a>
			      <ul>
			      	<li><a href="#">자전거</a></li>
			      	<li><a href="#">퀵보드</a></li>
			      </ul>
			</li>
			<li><a href="#">공지사항&#38;게시판</a>
				<ul>
					<li><a href="#">공지사항</a></li>
					<li><a href="board/list">상품 후기 게시판</a></li>
				</ul>
			</li>
		</ul>
	</div>				
</div>

<%@ include file="includes/footer.jsp"%>