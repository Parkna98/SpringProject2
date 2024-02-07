const goodsCard={
  		props:['goods_list'],
  		template:`<div class="col-md-3" v-for="vo in goods_list">
				    <div class="thumbnail">
				    <a href="#">
				      <img :src="vo.goods_poster" style="width:100%">
				      <div class="caption">
				        <p style="font-size: 8px">{{vo.goods_price}}</p>
				      </div>
				    </a>
				  </div>
				</div>`
  	}