<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
a.link:hover,img.img_click:hover{
	cursor:pointer;
}
</style>
</head>
<body>
<sec:authorize access="isAuthenticated()"> 
  <sec:authentication property="principal" var="principal"></sec:authentication>
</sec:authorize>
<div class="wrapper row3" id="recipeApp">
  <main class="container clear"> 
   <h3 class="sectiontitle">레시피 상세보기</h3>
   <table class="table">
     <tr>
      <td colspan="3" class="text-center">
        <img :src="recipe_data.poster" style="width: 700px; height: 500px">
      </td>
     </tr>
     <tr>
       <td colspan="3" class="text-center">
         <h3>{{recipe_data.title}}</h3>
       </td>
     </tr>
     <tr>
       <td colspan="3">
         <pre style="white-space: pre-wrap;background-color: white;border: none">{{recipe_data.content}}</pre>
       </td>
     </tr>
     <tr>
       <td class="text-center"><img src="../images/icon/a1.png"></td>
       <td class="text-center"><img src="../images/icon/a2.png"></td>
       <td class="text-center"><img src="../images/icon/a3.png"></td>
     </tr>
     <tr>
       <td class="text-center">{{recipe_data.info1}}</td>
       <td class="text-center">{{recipe_data.info2}}</td>
       <td class="text-center">{{recipe_data.info3}}</td>
     </tr>
     <tr>
       <td colspan="3">
         <div><h3>[재료]</h3></div>
         <div v-for="s in stuff"><a class="link" @click="find(s)">{{s}}</a></div>
       </td>
     </tr>
     <tr>
       <td colspan="3">
         <h3>조리순서</h3>
       </td>
     </tr>
     <tr>
       <td colspan="3"> 
         <table class="table" v-for="(m,index) in make">
           <tr>
             <td width="70%">{{m}}</td>
             <td width="30%">
               <img :src="posterPrint(index)" style="width: 250px;height: 100px" class="img-rounded">
             </td>
           </tr>
         </table>
       </td>
     </tr>
     <tr>
       <td colspan="3">
         <table class="table">
           <caption><h3>레시피 작성자</h3></caption>
           <tr>
             <td width="20%" class="text-center" rowspan="2">
               <img :src="recipe_data.chef_poster" style="width: 150px;height: 100px" class="class-circle">
             </td>
             <td width="80%"><b>{{recipe_data.chef}}</b></td>
           </tr>
           <tr>
             <td width="80%"><b>{{recipe_data.chef_profile}}</b></td>
           </tr>
         </table>
       </td>
     </tr>
   </table>
   <div style="height: 10px"></div>
   <table class="table">
     <tr>
       <td>
         <table class="table" v-for="rvo in reply_list">
           <tr>
             <td class="text-left">(●{{rvo.userName}}{{rvo.dbday}})</td>
             <td class="text-right">
              <span class="inline">
               <input type="button" class="btn-xs btn-info" value="수정" @click="updateForm(rvo.no)" :id="'up'+rvo.no">&nbsp;
               <input type="button" class="btn-xs btn-danger" value="삭제" @click="replyDelete(rvo.no)">
              </span>
             </td>
           </tr>
           <tr>
             <td colspan="2" class="text-left" valign="top">
               <pre style="white-space: pre-wrap;background-color: white;border:none">{{rvo.msg}}</pre>
             </td>
           </tr>
		     <tr style="display: none;" :id="'u'+rvo.no" class="ups">
		       <td>
		         <textarea rows="4" cols="85" :id="'u_msg'+rvo.no" style="float:left;">{{rvo.msg}}</textarea>
		         <input type="button" value="댓글수정" class="btn-danger" style="float: left;width: 110px;height: 91px"
		          @click="replyUpdate(rvo.no)">
		       </td>
		     </tr>
         </table>
       </td>
     </tr>
   </table>
   <table class="table" v-if="sessionId">
     <tr>
       <td>
         <textarea rows="4" cols="85" ref="msg" style="float:left;" v-model="msg"></textarea>
         <input type="button" value="댓글쓰기" class="btn-danger" style="float: left;width: 110px;height: 91px"
          @click="replyInsert()">
       </td>
     </tr>
   </table>
   
  </main>
  <div id="dialog" title="레시피 관련 상품" v-show="isShow">
    <goods-data v-bind:goods="goods"></goods-data>
  </div>
</div>
<script>
  const goods={
		  props:['goods'],
		  template:`<div class="container">
  					 <div class="row">
  						<div class="col-md-4" v-for="g in goods">
  				    	 <div class="thumbnail">
  				           <img :src="g.goods_poster" :title="g.goods_name" style="width:100%">
  				        	<div class="caption">
  				          	  <p>{{g.goods_price}}</p>
  					      	</div>
  					     </div>
 	  				    </div>
  					 </div>
  					</div>`
  }
  
  let recipeApp=Vue.createApp({
	  data(){
		return{
			recipe_data:{},
			no:${no},
			stuff:[],
			make:[],
			poster:[],
			goods:[],
			isShow:false,
			reply_list:[],
			sessionId:'${principal.username}',
			msg:'',
			u:0
		}
		 
	  },
	  mounted(){
		this.detailRecv()  
	  },
	  methods:{
		  replyUpdate(no){
			let msg=$('#u_msg'+no).val()
			axios.post('../recipe/reply_update_vue.do',null,{
				params:{
					no:no,
					rno:this.no,
					msg:msg
				}
			}).then(res=>{
				this.reply_list=res.data
				$('#u'+no).hide('slow')
				$('#up'+no).val('수정')
			})
		  },
		  updateForm(no){
			  $('.ups').hide();
			  $('#up'+no).val('수정')
			  if(this.u==0){
				  this.u=1;
				  $('#u'+no).show();
				  $('#up'+no).val('취소');
			  }else{
				  this.u=0;
				  $('#u'+no).hide();
				  $('#up'+no).val('수정');
			  }
		  },
		  replyInsert(){
			  if(this.msg===''){
				  this.$refs.focus()
				  return
			  }
			  axios.post('../recipe/reply_insert_vue.do',null,{
					params:{
						rno:this.no,
						msg:this.msg
					}  
			  }).then(res=>{
				  this.reply_list=res.data
				  this.msg=''
			  })  
		  },
		  replyDelete(no){
			  axios.get('../recipe/reply_delete_vue.do',{
	    		  params:
	    			  {
	    			      no:no,
	    			      rno:this.no
	    			  }
	    			
	    		}).then(res=>{
	    			this.reply_list=res.data
	    		})   
		  },
		  
		  
		  detailRecv(){
			  axios.get('../recipe/recipe_detail_vue.do',{
				  params:{
					  no:this.no
				  }
			  	
			  }).then(res=>{
				  console.log(res.data)
				  this.recipe_data=res.data.detail_data
				  this.stuff=res.data.detail_data.stuff.split(',')
				  let makedata=res.data.detail_data.foodmake.split("\n")
				  this.reply_list=res.data.reply_list
				  
				  let p=[]
				  let m=[]
				  for(let i=0;i<makedata.length-1;i++){
					  let aa=makedata[i].split("^")
					  this.make[i]=aa[0]
					  this.poster[i]=aa[1]
				  }
			  })
		  },
		  posterPrint(i){
			  return this.poster[i]
		  },
		  find(s){
			  let ss=s.substring(0,s.indexOf(' '))
			  //alert(ss)
			  axios.get('../recipe/goods_vue.do',{
				  params:{
					  goods_name:ss
				  }
			  }).then(res=>{
				  console.log(res.data)
				  this.goods=res.data
				  
				  this.isShow=true
				  $('#dialog').dialog({
					  autoOpen:false,
					  modal:true,
					  width:700,
					  height:500
				  }).dialog('open')
			  })
		  }
	  },
	  components:{
		 'goods-data':goods  
	  }
  }).mount('#recipeApp')
</script>
</body>
</html>