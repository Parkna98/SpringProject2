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
          <input type="button" class="btn-lg btn-info" value="상황" @click="submenu(1)">&nbsp;
          <input type="button" class="btn-lg btn-danger" value="감성" @click="submenu(2)">&nbsp;
          <input type="button" class="btn-lg btn-warning" value="스타일" @click="submenu(3)">&nbsp;
          <input type="button" class="btn-lg btn-success" value="날씨/계절" @click="submenu(4)">
        </td>
      </tr>
    </table>
    <div style="height: 5px"></div>
    <table class="table">
      <tr>
        <td class="text-center">
          <span style="margin-left: 2px" v-for="m in sub_list" class="btn btn-sm btn-info" 
          	@click="recommend(m)">{{m}}</span>
        </td>
      </tr>
    </table>
    <div class="content"> 
      <div id="gallery">
        <figure>
          <header class="heading inline">
          </header>
          <ul class="nospace clear">
            <li v-for="(vo,index) in food_list" :class="index%4==0?'one_quarter first':'one_quarter'"><img class="img_click" :src="'http://www.menupan.com'+vo.poster" :title="vo.name"></li>
          </ul>
          <figcaption>Gallery Description Goes Here</figcaption>
        </figure>
      </div>
    </div>
  </main>
</div>
<script>
  let foodRecommApp=Vue.createApp({
	  data(){
		  return{
			  no:1,
			  sub_list:[],
			  food_list:[]
		  }
	  },
	  mounted(){
		  
	  },
	  methods:{
		 submenu(no){
			 this.no=no
			 axios.get('food_recommend_sub.do',{
				 params:{
					 no:this.no
				 }
			 }).then(res=>{
				 console.log(res.data)
				 this.sub_list=res.data
			 })
		 },
		 recommend(fd){
			 axios.get('food_recommend_data.do',{
				 params:{
					 fd:fd
				 }
			 }).then(res=>{
				 console.log(res.data)
				 this.food_list=res.data
			 })
		 }
	  }
  }).mount('#foodRecommApp')
</script>
</body>
</html>