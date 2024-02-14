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
<div class="wrapper row3 row1" id="fboardApp">
  <main class="container clear"> 
    <h2 class="sectiontitle">수정하기</h2>
    <table class="table">
     <tr>
        <th width="10%">이름</th>
        <td width="90%"><input type="text" ref="name" size=15 v-model="name" class="input-sm"></td>
      </tr>
      <tr>
        <th width="10%">제목</th>
        <td width="90%"><input type="text" ref="subject" size=50 v-model="subject" class="input-sm"></td>
      </tr>
      <tr>
        <th width="10%">내용</th>
        <td width="90%"><textarea rows="10" cols="52" ref="content" v-model="content"></textarea></td>
      </tr>
      <tr>
        <th width="10%">비밀번호</th>
        <td width="90%"><input type="password" ref="pwd" size=15 v-model="pwd" class="input-sm"></td>
      </tr>
      <tr>
        <td colspan="2" class="text-center inline">
          <input type="button" value="수정" class="btn-sm btn-primary" @click="update()">
          <input type="button" value="취소" class="btn-sm btn-danger" @click="goback()">
        </td>
      </tr>
    </table>
  </main>
</div>
<script>
  let fboardApp=Vue.createApp({
	  data(){
		  return{
			  no:${no},
			  update_data:{},
			  name:'',
			  subject:'',
			  content:'',
			  pwd:''
		  }
	  },
	  mounted(){
		  axios.get('../freeboard/update_vue.do',{
			  params:{
				  no:this.no,
			  }
		  }).then(res=>{
			  console.log(res.data)
			  this.update_data=res.data
			  this.name=res.data.name
			  this.subject=res.data.subject
			  this.content=res.data.content
		  })
	  },
	  methods:{
		  update(){
			  if(this.name===''){
				  this.$refs.name.focus()
				  return
			  }
			  if(this.subject===''){
				  this.$refs.subject.focus()
				  return
			  }
			  if(this.content===''){
				  this.$refs.content.focus()
				  return
			  }
			  if(this.pwd===''){
				  this.$refs.pwd.focus()
				  return
			  }
			  
			  axios.post('../freeboard/update_ok_vue.do',null,{
				  params:{
					  no:this.no,
					  name:this.name,
					  subject:this.subject,
					  content:this.content,
					  pwd:this.pwd
					  
				  }
			  }).then(res=>{
				  console.log(res.data)
				  if(res.data==='yes'){
					  alert('게시물이 수정되었습니다')
					  location.href='../freeboard/detail.do?no='+this.no
				  }else{
					  alert('비밀번호가 틀립니다!')
					  this.pwd=''
					  this.$refs.pwd.focus()
				  }
			  })
		  },
		  goback(){
			  window.history.back()
			  // this.$router.go(-1) => 라우터 플러그인 설치후 사용가능
		  }
	  }
  }).mount('#fboardApp')
</script>
</body>
</html>