<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<div class="wrapper row3" id="foodRecommApp">
  <main class="container clear"> 
    <h2 class="sectiontitle">맛집 추천</h2>
    <table class="table">
      <tr>
        <td class="text-center inline">
          <input type="button" class="btn-lg btn-info" value="상황">&nbsp;
          <input type="button" class="btn-lg btn-danger" value="감성">&nbsp;
          <input type="button" class="btn-lg btn-warning" value="스타일">&nbsp;
          <input type="button" class="btn-lg btn-success" value="날씨/계절">
        </td>
      </tr>
    </table>
  </main>
</div>
</body>
</html>