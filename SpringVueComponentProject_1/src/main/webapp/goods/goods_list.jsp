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
<script src="gcard.js"></script>
</head>
<body>
  <div class="container">
    <div class="row">
      <goods_card v-bind:goods_list="goods_list"></goods_card>
    </div>
  </div>
</body>
  <script>
    let app=Vue.createApp({
    	data(){
    		return{
    			goods_list:[],
    			curpage:1,
    			totalpage:0,
    			startPage:0,
    			endPage:0,
    			type:1
    		}
    	},
    	mounted(){
    		this.dataRecv()
    	},
    	methods:{
    		dataRecv(){
    			axios.get('../goods/list_vue.do',{
    				params:{
    					page:this.curpage,
    					typeno:this.type
    				}
    			}).then(response=>{
    				console.log(response.data)
    				this.food_list=response.data
    			})
    			
    			axios.get('../goods/page_vue.do',{
    				param:{
    					page:this.curpage,
    					typeno:this.type
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
    		"goods_card":goodsCard
    	}
    }).mount('.container')
  </script>
</html>