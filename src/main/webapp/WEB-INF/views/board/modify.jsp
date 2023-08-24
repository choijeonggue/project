<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1 class="page-header">수정페이지</h1>
		</div>
	</div>
	
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					
				</div>
				<div class="card-body">
					<form action="${ctxPath}/board/modify" method="post">
						<div class="form-group">
							<label>번호</label>	
							<input class="form-control" name="bno" value="${board.bno}" readonly="readonly"/>
						</div>
						<div class="form-group">
							<label>제목</label>
							<input class="form-control" name="title" value="${board.title}" />
						</div>
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="10" name="content">${board.content}</textarea>
						</div>
						<div class="form-group">
							<label>작성자</label>
							<input class="form-control" name="writer" value="${board.writer }" readonly="readonly"/>
						</div>
						<div class="form-group">
							<label>등록일</label>
							<input class="form-control" readonly="readonly"  name="regDate"
								value="<tf:formatDateTime value="${board.regDate }" pattern="yyyy-MM-dd HH:mm"/>">
						</div>
						<div class="form-group">
							<label>수정일</label>
							<input class="form-control" readonly="readonly" name="updateDate"  
								value="<tf:formatDateTime value="${board.updateDate }" pattern="yyyy-MM-dd HH:mm"/>">
						</div>
						<button type="button" data-oper='modify' class="btn btn-light">수정</button>
						<button type="button" data-oper='remove' class="btn btn-danger">삭제</button>
						<button type="button" data-oper='list' class="btn btn-info">목록</button>
					</form>						
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="../includes/footer.jsp" %>

<script>
$(function(){
	let formObj = $('form')
	$('button').click(function(){
		let operation =$(this).data('oper')
		if(operation=='remove'){
			formObj.attr('action','${ctxPath}/board/remove')
					.submit();
		} else if (operation=='list'){
			self.location='${ctxPath}/board/list'
		} else {
			formObj.submit();
		}		
	});	
})
</script>