function screen(){
	$(".screenLayer .screen li").hover(function(){
		$(this).removeClass("screenNormal");
		$(this).addClass("screenHover");
		var s = $(this);
		var id = s.attr("id");
		$.ajax({
			type: "POST",
			url: "WebFrame/MenuItems.dsr",
			data:{id:$(this).attr("id")},
			success:function(data){
				data = eval(data);
				if(data.menuItems != ""){
					s.append(data.menuItems);
					view(id+"List");
				}
			}
		});
	},function(){
		$(this).removeClass("screenHover");
		$(this).addClass("screenNormal");
		var html = $("#" + $(this).attr("id") + "List");
		if(html != null)
			html.remove();
	});
}
function view(id){
	$(".viewLayer .view li").hover(function(){
		var s = $(this);
		s.removeClass("viewNormal");
		s.addClass("viewHover");
		var id = s.attr("id");
		$.ajax({
			type: "POST",
			url: "WebFrame/MenuItems.dsr",
			data:{id:id},
			success:function(data){
				data = eval(data);
				if(data.menuItems != ""){
					s.append(data.menuItems);
					view(id+"List");
				}
			}
		});
	},function(){
		$(this).removeClass("viewHover");
		$(this).addClass("viewNormal");
		var html = $("#" + $(this).attr("id") + "List");
		if(html != null)
			html.remove();
	});
}