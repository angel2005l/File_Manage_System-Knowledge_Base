$(".pin-icon,.pin-icon-c").click(function(event) {

			var collectObj = $(this);
			var mainProjectCode = collectObj.attr("main_code");

			var className = collectObj.attr("class");
			className = className == 'pin-icon' ? "pin-icon-c" : "pin-icon"
			var isCollect = className == 'pin-icon-c' ? "Y" : "N"
			if (mainProjectCode != undefined && mainProjectCode != '') {
				$.ajax({
					url : 'pro/collect.do',
					type : 'post',
					data : {
						"is_collect" : isCollect,
						"project_main_code" : mainProjectCode
					},
					dateType : 'json',
					success : function(result) {
						collectObj.attr("class", className);
					},
					error : function() {
						alert("服务器未响应");
					}
				})
			}
			//阻止冒泡
			event.stopPropagation();
			event.preventDefault();
		})
		function intoclick(){
			$("#thediv").toggle();
		}
		function losePoint(){
			$("#thediv").hide();
		}
		function allread(){
			var list=[];
			for(var i=0;i<$(".adviceCode").length;i++){
				list.push($(".adviceCode")[i].innerHTML)
			};
			$.ajax({
				url:'pro/isRead.do',
				type:'post',
				data:"list="+JSON.stringify(list),
				dateType:'json',
				success:function(){
					toload();
				}
			})
		}
		function toload(){
			$.ajax({
				url:'index/getAllMsg.do',
				type:'post',
				data:"",//发送服务器的数据
    			dataType:"json",//返会值的类型
				success:function(data){
			        var str="";
					if(data.data.adNum!=0){
						$('#num').html(data.data.adNum);
						$("#notification-count").attr("class", "label unread");
						for(var i=0;i<data.data.adviceMsg.length;i++){
							str+="<span class='title'><span class='target'>"+data.data.adviceMsg[i].logMsg+"</span><span class='adviceCode' style='display: none'>"+data.data.adviceMsg[i].adviceCode+"</span></span><hr style='margin:0px 0px 3px 0px'>";
						};
						$("#msg").html(str);
					}else{
						$("#notification-count").attr("class", "label");
						$("#msg").html("<span style='width:100%;text-align:center;display:block;'>- 没有新通知 -</span>");
					}
				}
			})	
		}
		$(document).bind('click', function(e) {
				var e = e || window.event; //浏览器兼容性 
				var elem = e.target || e.srcElement;
				while (elem) { //循环判断至跟节点，防止点击的是div子元素 
					if (elem.id && elem.id == 'both') {
						return;
					}
					elem = elem.parentNode;
				}
				$('#thediv').css('display', 'none'); //点击的不是div或其子元素 
			});