<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1 class="page-header">등록</h1>
		</div>
	</div>
	
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header"></div>
				<div class="card-body">
					<form action="${ctxPath}/board/register" method="post">
						<div class="form-group">
							<label>제목 </label>
							<input class="form-control" name="title"/>
						</div>
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="10" name="content"></textarea>
						</div>
						<div class="form-group">
							<label>작성자</label>
							<input class="form-control" name="writer"/>
						</div>
						<button type="button" class="btn btn-outline-primary register">등록</button>
						<button type="button" class="btn btn-outline-info list">목록</button>						
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="row my-5">
		<div class="col-lg-12">
			<div class="card">
				<div class="card-header">
					<h4>파일 첨부</h4>
				</div>
				<div class="card-body">
					<div class="uploadDiv">
						<input type="file" name="uploadFile" multiple="multiple">
					</div>
					<div class="uploadResultDiv mt-3"> <!-- 파일업로드 결과 보여주기  -->
						<ul class="list-group"></ul>
					</div>
				</div> <!-- card-body -->
			</div> <!-- card end -->
		</div> <!-- col end -->
	</div><!-- row end -->

<input type="hidden" name="pageNum" value="${param.pageNum }" >
<input type="hidden" name="amount" value="${param.amount }" >
<input type="hidden" name="type" value="${param.type }" >
<input type="hidden" name="keyword" value="${param.keyword }" >

<script src="${ctxPath}/resources/js/register.js"></script>

<%@ include file="../includes/footer.jsp" %>
