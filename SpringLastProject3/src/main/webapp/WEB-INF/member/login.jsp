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
<style type="text/css">
.row5{
	margin: 0px auto;
	width: 400px;
}
</style>
</head>
<body>
<div class="wrapper row3" id="loginApp">
  <main class="container clear"> 
    <h2 class="sectiontitle">로그인</h2>
    <div class="row row5">
     <form method="post" action="../member/login.do" ref="frm">
      <table class="table">
        <tr>
          <td width="20%" class="text-right">ID</td>
          <td width="80%">
            <input type="text" name="id" class="input-sm" ref="id" v-model="id">
          </td>
        </tr>
        <tr>
          <td width="20%" class="text-right">Password</td>
          <td width="80%">
            <input type="password" name="pwd" class="input-sm" ref="pwd" v-model="pwd">
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
            <input type="button" value="로그인" class="btn-info btn-sm" @click="login()">
            <input type="button" value="취소" class="btn-danger btn-sm" onclick="javascript:history.back()"> 
          </td>
        </tr>
      </table>
     </form> 
    </div>
  </main>
</div>
<script>
let logApp=Vue.createApp({
	data(){
		return{
			id:'',
			pwd:''
		}
	},
	mounted(){
		
	},
	methods:{
		login(){
			if(this.id===''){
				this.$refs.id.focus()
				return
			}
			if(this.pwd===''){
				this.$refs.pwd.focus()
				return
			}
			this.$refs.frm.submit()
		}
	}	
}).mount('#loginApp')
</script>
</body>
</html>