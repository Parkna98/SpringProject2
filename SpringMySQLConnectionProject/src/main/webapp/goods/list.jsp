<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 960px;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
     <c:forEach var="vo" items="${list }">
      <div class="col-md-3">
		<div class="thumbnail">
		    <!-- :src  클론 잊지않기!!! -->
		        <img src="${vo.goods_poster}"style="width:100%" class="images">
		        <div class="caption">
		          <p>${vo.goods_price }</p>
		        </div>
		</div>
	  </div>
	 </c:forEach>
    </div>
    <div class="row">
      <div class="text-center">
        <a href="list.do?page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-success">이전</a>
        ${curpage } page / ${totalpage } pages
        <a href="list.do?page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-sm btn-success">다음</a>
      </div>
    </div>
  </div>
</body>
</html>