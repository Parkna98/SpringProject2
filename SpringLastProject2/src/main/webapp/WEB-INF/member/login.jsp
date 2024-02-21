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
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<style type="text/css">
.row5{
	margin: 0px auto;
	width: 400px;
}
</style>
<script type="text/javascript">
$(function(){
	$('#logBtn').click(function(){
		let id=$('#id').val()
		if(id.trim()===""){
			$('#id').focus()
			return
		}
		let pwd=$('#pwd').val()
		if(pwd.trim()===""){
			$('#pwd').focus()
			return
		}
		
		$('#frm').submit()
	})
})
</script>
</head>
<body>
<div class="wrapper row3" id="loginApp">
  <main class="container clear"> 
    <h2 class="sectiontitle">로그인</h2>
    <div class="row row5">
     <form method="post" action="../member/login.do" id="frm">
      <table class="table">
        <tr>
          <td width="20%" class="text-right">ID</td>
          <td width="80%">
            <input type="text" name="userId" class="input-sm" id="id">
          </td>
        </tr>
        <tr>
          <td width="20%" class="text-right">Password</td>
          <td width="80%">
            <input type="password" name="userPwd" class="input-sm" id="pwd">
          </td>
        </tr>
        <tr>
          <td colspan="2" class="inline">
            <input type="checkbox" name="remember-me">자동로그인
            <%-- 체크박스 => true/false --%>
          </td>
        </tr>
        <tr>
        	<td colspan="2" class="text-center" style="color: red">${message }</td>
        </tr>
        <tr>
          <td colspan="2" class="text-center inline">
            <input type="button" value="로그인" class="btn-info btn-sm" id="logBtn">
            <input type="button" value="취소" class="btn-danger btn-sm" onclick="javascript:history.back()"> 
          </td>
        </tr>
      </table>
     </form> 
    </div>
  </main>
</div>
<script>
 /*  let loginApp=Vue.createApp({
	  data(){
		  return{
			  id:'${userId}',
			  pwd:'',
			  ck:true
		  }
	  },
	  methods:{
		  login(){
			  if(this.id===''){
				  alert('ID를 입력하세요.')
				  this.$refs.id.focus()
				  return
			  }
			  if(this.pwd===''){
				  alert('비밀번호를 입력하세요.')
				  this.$refs.pwd.focus()
				  return
			  }
			  axios.get('../member/login_ok_vue.do',{
				  params:{
					  userId:this.id,
					  userPwd:this.pwd,
					  ck:this.ck
				  }
			  }).then(response=>{
				  // NOID, NOPWD, OK
				  if(response.data==='NOID'){
					  alert('ID가 존재하지 않습니다!')
					  this.id=''
					  this.pwd=''
					  this.ck=false
					  this.$refs.id.focus()
				  }
				  else if(response.data==='NOPWD'){
					  alert('비밀번호가 틀립니다')
					  this.pwd=''
					  this.$refs.pwd.focus()
				  }
				  else{
					  location.href='../main/main.do'					  
				  }
			  })
				  
		  }
	  }
	  
  }).mount('#loginApp') */
</script>
</body>
</html>