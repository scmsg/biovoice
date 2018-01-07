$(function(){  
	$(".tab-head li").mouseover(function(){  
    	$(this).attr("class","select").siblings().attr("class","");  
    	$(".tab-panel").css("left",-$(this).index()+"00%");  
	})  
})