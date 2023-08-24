<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1 class="page-header">조회</h1>
		</div>
	</div>
	
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					조회
				</div>
				<div class="card-body">
					<div class="form-group">
						<label>번호</label>	
						<input class="form-control" name="bno" value="${board.bno}" readonly="readonly"/>
					</div>
					<div class="form-group">
						<label>제목</label>
						<input class="form-control" name="title" value="${board.title}" readonly="readonly"/>
					</div>
					<div class="form-group">
						<label>내용</label>
						<textarea class="form-control" rows="10" name="content" readonly="readonly">${board.title}</textarea>
					</div>
					<div class="form-group">
						<label>작성자</label>
						<input class="form-control" name="writer" value="${board.writer }" readonly="readonly"/>
					</div>
					<button class="btn btn-light modify">수정</button>
					<button class="btn btn-info list">목록</button>						
				</div>
			</div>
		</div>
	</div>
</div>

<form>
	<input type="hidden" name="bno"  value="${board.bno}">
</form>

<%@ include file="../includes/footer.jsp" %>

<script>
$(function(){
	let getForm = $('form');
	
	
	// 수정페이지 
	$('.modify').click(function(){
		getForm.attr('action','${ctxPath}/board/modify')
				.attr('method','get')
				.submit();
	});
	
	// 목록으로
	$('.list').click(function(){
		getForm.find('[name="bno"]').remove();
		getForm.attr('action','${ctxPath}/board/list')
				.attr('method','get')
				.submit();
	});
});
</script>