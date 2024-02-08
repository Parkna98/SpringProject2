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
a.link:hover{
	cursor:pointer;
}
</style>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear"> 
   <h2 class="sectiontitle">맛집 검색</h2>
     <div class="content" id="findApp"> 
      <div id="gallery">
        <figure>
          <header class="heading inline">
            <input type="text" ref="fd" size="20" class="input-sm" v-model="fd" @keyup.enter="find()">
            <input type="button" value="검색" class="btn-sm btn-primary" @click="find()">
          </header>
          <ul class="nospace clear">
            <li v-for="(vo,index) in food_list" :class="index%4==0?'one_quarter first':'one_quarter'">
              <a href="#"><img :src="'https://www.menupan.com'+vo.poster" :title="vo.name"></a>
            </li>
          </ul>
          <figcaption>Gallery Description Goes Here</figcaption>
        </figure>
      </div>
      <nav class="pagination">
        <ul>
          <li v-if="startPage>1"><a class="link" @click="prev()">&laquo; Previous</a></li>
          <li v-for="i in range(startPage,endPage)" :class="i===curpage?'current':''"><a class="link" @click="pageChange(i)">{{i}}</a></li>
          <li v-if="endPage<totalpage"><a class="link" @click="next()">Next &raquo;</a></li>
        </ul>
      </nav>
    </div>
    <div class="clear"></div>  
  </main>
</div>
<script>
  let findApp=Vue.createApp({
	  data(){
		return {
			food_list:[],
			fd:'마포',
			food_detail:{},
			page_list:{},
			fno:1,
			curpage:1,
			totalpage:0,
			startPage:0,
			endPage:0
			
		}  
	  },
	  mounted(){
		  this.dataRecv()
	  },
	  updated(){
		  
	  },
	  methods:{
		  dataRecv(){
			  axios.get('../food/find_vue.do',{
				  params:{
					  page:this.curpage,
					  fd:this.fd
				  }
			  }).then(response=>{
				  console.log(response.data)
				  this.food_list=response.data
			  })
			  
			  axios.get('../food/page_vue.do',{
				  params:{
					  page:this.curpage,
					  fd:this.fd
				  }
			  }).then(response=>{
				  console.log(response.data)
				  this.page_list=response.data
				  this.curpage=response.data.curpage
				  this.totalpage=response.data.totalpage
				  this.startPage=response.data.startPage
				  this.endPage=response.data.endPage
			  })
		  },
		  range(start,end){
			let arr=[]
			let len=end-start
			for(let i=0;i<=len;i++){
				arr[i]=start++;
			}
			return arr
		  },
		  prev(){
		    this.curpage=this.startPage-1
		    this.dataRecv()
		  },
		  next(){
			this.curpage=this.endPage+1
			this.dataRecv()
		  },
		  pageChange(page){
			this.curpage=page
			this.dataRecv()
		  },
		  find(){
			  this.curpage=1
			  this.dataRecv()
		  }
	  },
	  components:{
		  // 상세보기 => dialog
	  }
  }).mount('#findApp')
</script>
</body>
</html>