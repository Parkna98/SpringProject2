<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="card.js"></script>
</head>
<body>
  <div class="container">
    <div class="row">
      <food_card v-bind:food_list="food_list"></food_card>
    </div>
    <div style="height: 10px"></div>
    <div class="row">
      <div class="text-center">
        <pagination v-bind:curpage="curpage" 
        	v-bind:totalpage="totalpage" 
        	v-bind:startPage="startPage" 
        	v-bind:endPage="endPage" 
        ></pagination>
      </div>
    </div>
  </div>
  <script>
  	const pagination={
  			props:['startPage','endPage','curpage','totalpage'],
  			template:`<ul class="pagination">
					    <li v-if="startPage>1"><a class="link" @click="prev()">&laquo;</a></li>
					    <li v-for="i in range(startPage,endPage)" :class="i===curpage?'active':''"><a class="link" @click="pageChange(i)">{{i}}</a></li>
					    <li v-if="endPage<totalpage"><a class="link" @click="next()">&raquo;</a></li>
					  </ul>`,
			methods:{
				range(start,end){
					let arr=[]
					let leng=end-start
					for(let i=0;i<=leng;i++){
						arr[i]=start++;
					}
					return arr
				}
				
			}
  	}
    let app=Vue.createApp({
    	data(){
    		return{
    			food_list:[],
    			curpage:1,
    			totalpage:0,
    			startPage:0,
    			endPage:0
    		}	
    	},
    	mounted(){
    		this.dataRecv()
    	},
    	methods:{
    		dataRecv(){
    			// 서버와 연결 (스프링)
    			axios.get('../food/list_vue.do',{
    				params:{
    					page:this.curpage
    				}
    			}).then(response=>{
    				console.log(response.data)
    				this.food_list=response.data
    			})
    			
    			axios.get('../food/page_vue.do',{
    				param:{
    					page:this.curpage
    				}
    			}).then(response=>{
    				console.log(response.data)
    				this.curpage=response.data.curpage
    				this.totalpage=response.data.totalpage
    				this.startPage=response.data.startPage
    				this.endPage=response.data.endPage
    			})
    	}
    },
	components:{
		"food_card":foodCard,
		"pagination":pagination
	}
    }).mount(".container")
  </script>
</body>
</html>