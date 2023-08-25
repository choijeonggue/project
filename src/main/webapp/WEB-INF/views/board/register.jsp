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
						<button class="btn btn-outline-primary">등록</button>
						<button type="button" class="btn btn-outline-info list">목록</button>						
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<input type="hidden" name="pageNum" value="${param.pageNum }" >
<input type="hidden" name="amount" value="${param.amount }" >
<input type="hidden" name="type" value="${param.type }" >
<input type="hidden" name="keyword" value="${param.keyword }" >

<script>

$(function(){
	$('.list').click(function(){
		let form = $('<form/>')
		let type = $('[name="type"]');
		let keyword = $('[name="keyword"]');
		if(type.val()&&keyword.val()){
			form.append(type).append(keyword);				
		}
		form.attr('action','${ctxPath}/board/list')
			.append($('[name="pageNum"]'))
			.append($('[name="amount"]'))
			.appendTo('body')
			.submit();
	})
})

</script>

<%@ include file="../includes/footer.jsp" %>
