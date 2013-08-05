<%@ page pageEncoding="utf-8"%>
<c:choose>
	<c:when test="${page != null}">
		<input type="hidden" value="${page.number}" id="pageIndex" />
		<input type="hidden" value="${page.totalPages}" id="totalPage" />
	</c:when> 
	<c:otherwise>
		<input type="hidden" value="0" id="pageIndex" />
		<input type="hidden" value="0" id="totalPage" />
	</c:otherwise>
</c:choose>
<div class="pagination pagination-right">
	<c:choose>
		<c:when test="${page == null}">
		</c:when>
		<c:when test="${page.totalPages <= 7}">
			<ul>
				<li class="<c:if test="${page.totalPages < 2}">hidden</c:if><c:if test="${page.number == 0}"> active</c:if>"><a class="toPage" pageIndex="0">1</a></li>
				<li class="<c:if test="${page.totalPages < 2}">hidden</c:if><c:if test="${page.number == 1}"> active</c:if>"><a class="toPage" pageIndex="1">2</a></li>
				<li class="<c:if test="${page.totalPages < 3}">hidden</c:if><c:if test="${page.number == 2}"> active</c:if>"><a class="toPage" pageIndex="2">3</a></li>
				<li class="<c:if test="${page.totalPages < 4}">hidden</c:if><c:if test="${page.number == 3}"> active</c:if>"><a class="toPage" pageIndex="3">4</a></li>
				<li class="<c:if test="${page.totalPages < 5}">hidden</c:if><c:if test="${page.number == 4}"> active</c:if>"><a class="toPage" pageIndex="4">5</a></li>
				<li class="<c:if test="${page.totalPages < 6}">hidden</c:if><c:if test="${page.number == 5}"> active</c:if>"><a class="toPage" pageIndex="5">6</a></li>
				<li class="<c:if test="${page.totalPages < 7}">hidden</c:if><c:if test="${page.number == 6}"> active</c:if>"><a class="toPage" pageIndex="6">7</a></li>
			</ul>
		</c:when>
		<c:when test="${page.number <= 4}">
			<ul>
				<li class="<c:if test="${page.number == 0}">disabled</c:if>"><a class="toPage" pageIndex="${page.number-1}">«</a></li>
				<li class="<c:if test="${page.number == 0}">active</c:if>"><a class="toPage" pageIndex="0">1</a></li>
				<li class="<c:if test="${page.number == 1}">active</c:if>"><a class="toPage" pageIndex="1">2</a></li>
				<li class="<c:if test="${page.number == 2}">active</c:if>"><a class="toPage" pageIndex="2">3</a></li>
				<li class="<c:if test="${page.number == 3}">active</c:if>"><a class="toPage" pageIndex="3">4</a></li>
				<li class="<c:if test="${page.number == 4}">active</c:if>"><a class="toPage" pageIndex="4">5</a></li>
				<li><a class="toPage" pageIndex="5">6</a></li>
				<li><a>...</a></li>
				<li><a class="toPage" pageIndex="${page.totalPages-1}">${page.totalPages}</a></li>
				<li><a class="toPage" pageIndex="${page.number+1}">»</a></li>
				<li><span>到第<input id="pageInput" style="width: 25px" value="${page.number+2}" />页<input class="sure" id="sure" type="button" value="确定" /></span></li>
			</ul>
		</c:when>
		<c:when test="${(page.totalPages - 4) <= page.number}">
			<ul>
				<li><a class="toPage" pageIndex="${page.number-1}">«</a></li>
				<li><a class="toPage" pageIndex="0">1</a></li>
				<li><a>...</a></li>
				<li class="<c:if test="${page.number == (page.totalPages-5)}">active</c:if>"><a class="toPage" pageIndex="${page.totalPages-5}">${page.totalPages-4}</a></li>
				<li class="<c:if test="${page.number == (page.totalPages-4)}">active</c:if>"><a class="toPage" pageIndex="${page.totalPages-4}">${page.totalPages-3}</a></li>
				<li class="<c:if test="${page.number == (page.totalPages-3)}">active</c:if>"><a class="toPage" pageIndex="${page.totalPages-3}">${page.totalPages-2}</a></li>
				<li class="<c:if test="${page.number == (page.totalPages-2)}">active</c:if>"><a class="toPage" pageIndex="${page.totalPages-2}">${page.totalPages-1}</a></li>
				<li class="<c:if test="${page.number == (page.totalPages-1)}">active</c:if>"><a class="toPage" pageIndex="${page.totalPages-1}">${page.totalPages}</a></li>
				<li class="<c:if test="${page.number == (page.totalPages-1)}">disabled</c:if>"><a class="toPage" pageIndex="${page.totalPages-1}">»</a></li>
				<li><span>到第<input id="pageInput" style="width: 25px" value="${page.number+2}" />页<input class="sure" id="sure" type="button" value="确定" /></span></li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul>
				<li><a class="toPage" pageIndex="${page.number-1}">«</a></li>
				<li><a class="toPage" pageIndex="0">1</a></li>
				<li><a>...</a></li>
				<li><a class="toPage" pageIndex="${page.number-2}">${page.number-1}</a></li>
				<li><a class="toPage" pageIndex="${page.number-1}">${page.number}</a></li>
				<li class="active"><a class="toPage" pageIndex="${page.number}">${page.number+1}</a></li>
				<li><a class="toPage" pageIndex="${page.number+1}">${page.number+2}</a></li>
				<li><a class="toPage" pageIndex="${page.number+2}">${page.number+3}</a></li>
				<li><a>...</a></li>
				<li><a class="toPage" pageIndex="${page.totalPages-1}">${page.totalPages}</a></li>
				<li><a class="toPage" pageIndex="${page.number+1}">»</a></li>
				<li><span>到第<input id="pageInput" style="width: 25px" value="${page.number+2}" />页<input class="sure" id="sure" type="button" value="确定" /></span></li>
			</ul>
		</c:otherwise>
	</c:choose>
</div>