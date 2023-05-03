<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE DETAIL" />
<%@ include file="../common/head.jspf"%>
<hr />
<!-- <iframe src="http://localhost:8081/usr/article/doIncreaseHitCountRd?id=2" frameborder="0"></iframe> -->
<script>
	const params = {}
	params.id = parseInt('${param.id}');
</script>

<!-- 조회수 관련 -->
<script>
	function ArticleDetail__increaseHitCount() {
		const localStorageKey = 'article__' + params.id + '__alreadyView';
		if (localStorage.getItem(localStorageKey)) {
			return;
		}
		localStorage.setItem(localStorageKey, true);
		$.get('../article/doIncreaseHitCountRd', {
			id : params.id,
			ajaxMode : 'Y'
		}, function(data) {
			$('.article-detail__hit-count').empty().html(data.data1);
		}, 'json');
	}
	$(function() {
		// 실전코드
		// 		ArticleDetail__increaseHitCount();
		// 연습코드
		setTimeout(ArticleDetail__increaseHitCount, 2000);
	})
	
	function ArticleDetail__goodReaction() {
		
		$.get('../reactionPoint/doGoodReaction', {
			relId : params.id,
			relTypeCode : 'article',
			ajaxMode : 'Y'
		}, function(data) {
			
			$('.btn-cancel-good').addClass('btn-secondary');
			$('.btn-cancel-good').removeClass('btn');
		}, 'json');
	}
	$('.btn-good').click(function() {
		ArticleDetail__goodReaction();
		location.replace('/usr/article/detail?${param.id}')
	})
</script>

<script type="text/javascript">
	let ReplyWrite__submitFormDone = false;
	function ReplyWrite__submitForm(form) {
		if (ReplyWrite__submitFormDone) {
			return;
		}
		form.body.value = form.body.value.trim();
		if (form.body.value.length < 3) {
			alert('3글자 이상 입력하세요');
			form.body.focus();
			return;
		}
		ReplyWrite__submitFormDone = true;
		form.submit();
	}
</script>

<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
				<div class="table-box-type-1">
						<table border="1">
								<colgroup>
										<col width="200" />
								</colgroup>

								<tbody>
										<tr>
												<th>번호</th>
												<td>
														<div class="badge">${article.id}</div>
												</td>

										</tr>
										<tr>
												<th>작성날짜</th>
												<td>${article.regDate }</td>
										</tr>
										<tr>
												<th>수정날짜</th>
												<td>${article.updateDate }</td>
										</tr>
										<tr>
												<th>작성자</th>
												<td>${article.extra__writer }</td>
										</tr>
										<tr>
												<th>조회수</th>
												<td>
														<span class="article-detail__hit-count">${article.hitCount }</span>
												</td>
										</tr>

										<tr>
												<th>추천</th>
												<td>
														<span>&nbsp;좋아요 : ${article.goodReactionPoint }&nbsp;</span>
														<span>&nbsp;싫어요 : ${article.badReactionPoint }&nbsp;</span>
														<c:if test="${actorCanMakeReaction }">
																<div>
																		<span>
																				<span>&nbsp;</span>
																				<a
																						href="/usr/reactionPoint/doGoodReaction?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri}"
																						class="btn btn-xs btn-good">좋아요 👍</a>
																		</span>
																		<span>
																				<span>&nbsp;</span>
																				<a
																						href="/usr/reactionPoint/doBadReaction?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri}"
																						class="btn btn-xs">싫어요 👎</a>
																		</span>
																</div>
														</c:if>
														<c:if test="${actorCanCancelGoodReaction }">
																<div>
																		<span>
																				<span>&nbsp;</span>
																				<a
																						href="/usr/reactionPoint/doCancelGoodReaction?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri}"
																						class="btn btn-xs btn-cancel-good">좋아요 👍</a>
																		</span>
																		<span>
																				<span>&nbsp;</span>
																				<a onclick="alert(this.title); return false;" title="좋아요를 먼저 취소해" class="btn btn-xs">싫어요 👎</a>
																		</span>
																</div>
														</c:if>


														<c:if test="${actorCanCancelBadReaction }">
																<div>
																		<span>
																				<span>&nbsp;</span>
																				<a onclick="alert(this.title); return false;" title="싫어요를 먼저 취소해" class="btn btn-xs">좋아요 👍</a>
																		</span>
																		<span>
																				<span>&nbsp;</span>
																				<a
																						href="/usr/reactionPoint/doCancelBadReaction?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri}"
																						class="btn btn-xs btn-cancel-bad">싫어요 👎</a>
																		</span>
																</div>
														</c:if>
												</td>
										</tr>

										<tr>
												<th>제목</th>
												<td>${article.title }</td>
										</tr>
										<tr>
												<th>내용</th>
												<td>${article.body }</td>
										</tr>
								</tbody>

						</table>
				</div>
				<div class="btns">
						<button class="btn-text-link btn btn-active btn-ghost" type="button" onclick="history.back();">뒤로가기</button>

						<c:if test="${article.actorCanModify }">
								<a class="btn-text-link btn btn-active btn-ghost" href="../article/modify?id=${article.id }">수정</a>
						</c:if>
						<c:if test="${article.actorCanDelete }">
								<a class="btn-text-link btn btn-active btn-ghost" onclick="if(confirm('정말 삭제하시겠습니까?')==false) return false;"
										href="../article/doDelete?id=${article.id }">삭제</a>
						</c:if>
				</div>
		</div>
</section>

<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
				<div class="table-box-type-1">
						<c:if test="${rq.logined }">
								<form action="../reply/doWrite" method="POST" onsubmit="ReplyWrite__submitForm(this); return false;">
										<input type="hidden" name="relTypeCode" value="article" />
										<input type="hidden" name="relId" value="${article.id }" />
										<table>
												<colgroup>
														<col width="200" />
												</colgroup>

												<tbody>
														<tr>
																<th>댓글</th>
																<td>
																		<textarea class="input input-bordered w-full max-w-xs" type="text" name="body"
																				placeholder="내용을 입력해주세요" /></textarea>
																</td>
														</tr>
														<tr>
																<th></th>
																<td>
																		<button type="submit" value="작성" />
																		댓글 작성
																		</button>
																</td>
														</tr>
												</tbody>

										</table>
								</form>
						</c:if>
						<c:if test="${rq.notLogined }">
								<a class="btn-text-link btn btn-active btn-ghost" href="/usr/member/login">로그인</a> 후 이용해줘
			</c:if>
				</div>

		</div>
</section>

<section class="mt-5">
	<div class="container mx-auto px-3">
		<h1 class="text-3xl">댓글 리스트(${repliesCount })</h1>
	</div>
</section>

<c:forEach var="reply" items="${replies }">
		<tr class="hover">
				<td>
						<div class="badge">${reply.id}</div>
				</td>
				<td>${reply.regDate.substring(2,16)}</td>

				<td>${reply.body}</td>

				<td>${reply.extra__writer }</td>

		</tr>
</c:forEach>



<%@ include file="../common/foot.jspf"%>