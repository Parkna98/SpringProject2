<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
a.plink:hover,img.img_click:hover{
	cursor:pointer;
}
</style>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear"> 
   <h3 class="sectiontitle">쉐프의 레시피 목록</h3>
    <div class="content" id="chefApp"> 
      <div id="gallery">
        <figure>
          <header class="heading inline">
            <input type="text" class="input-sm" ref="ss" v-model="ss" size=20 placeholder="검색어 입력" @keyup.enter="findRecipe()">&nbsp;
            <input type="button" class="btn-sm btn-success" value="검색" @click="findRecipe()">&nbsp;
            <input type="button" class="btn-sm btn-info" value="전체" @click="recipeTotal()">
          </header>
          <ul class="nospace clear">
            <li v-for="(vo,index) in chef_recipe_list"  :class="index%4==0?'one_quarter first':'one_quarter'">
              <a :href="'../recipe/recipe_before_detail.do?no='+vo.no"><img :src="vo.poster" :title="vo.title"></a>
            </li>
          </ul>
          <figcaption></figcaption>
        </figure>
      </div>
      <nav class="pagination">
        <ul>
          <li v-if="startPage>1"><a class="plink" @click="prev()">&laquo; Previous</a></li>
          <li v-for="i in range(startPage,endPage)" :class="i==curpage?'current':''"><a class="plink" @click="pageChange(i)">{{i}}</a></li>
          <li v-if="endPage<totalpage"><a class="plink" @click="next()">Next &raquo;</a></li>
        </ul>
      </nav>
    </div>
    <div class="clear"></div>
  </main>
</div>
<script>
  let foodApp=Vue.createApp({
	  // 데이터관리 => 멤버변수 => this.
	data(){
		return{
			chef_recipe_list:[],
			curpage:1,
			totalpage:0,
			startPage:0,
			endPage:0,
			cno:${cno},
			ss:''
		}
	},
	mounted(){
		// 브라우저 화면에 HTML이 실행된 경우에 처리 => 자동 호출 함수
		/*
			자동 호출 함수 => Vue 생명주기
			beforeCreate()
			created()
			---------------- Vue객체 생성
			beforeMount() => mount : 가상 메모리에 HTML을 올리는 경우
			***mounted() => window.onload, $(function(){}), componentDidMount()
														  => HOOKS
														  => useEffect()
														  => class / function = 권장
			beforeUpdate()
			***updated()
			
			
		*/
		this.dataRecv()
	},
	updated(){
		
	},
	methods:{
		// 공통모듈 => 반복제거
		dataRecv(){
			axios.get('../recipe/chef_detail_vue.do',{
				params:{
					page:this.curpage,
					cno:this.cno,
					ss:this.ss
				}
			}).then(res=>{
				console.log(res.data)
				this.chef_recipe_list=res.data
			})
			
			// 페이지
			axios.get('../recipe/chef_detail_page_vue.do',{
				params:{
					page:this.curpage,
					cno:this.cno,
					ss:this.ss
				}	
			}).then(res=>{
				console.log(res.data)
				this.curpage=res.data.curpage
				this.totalpage=res.data.totalpage
				this.startPage=res.data.startPage
				this.endPage=res.data.endPage
			})
			
		},
		range(start,end){
			let arr=[]
			let len=end-start
			for(let i=0;i<=len;i++){
				arr[i]=start++
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
		findRecipe(){
			if(this.ss===''){
				this.$refs.ss.focus()
				return
			}
			this.curpage=1;
			this.dataRecv()
		},
		recipeTotal(){
			this.curpage=1
			this.ss=''
			this.dataRecv()
		}
	}
  }).mount("#chefApp")
</script>
</body>
</html>