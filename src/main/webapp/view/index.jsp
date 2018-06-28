<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>新海科技集团知识库</title>
	<meta name="renderer" content="webkit">
	<meta name="baidu-site-verification" content="qLDoHdGnb64RHlkm">
	<meta name="alexaVerifyID" content="SIgQikd9LazsFz9M1vPBaQyC4Gw">
	<link rel="dns-prefetch" href="https://avatar.tower.im/">
	<link rel="dns-prefetch" href="https://atttachments.tower.im/">
	<link rel="apple-touch-icon-precomposed" href="">
	<link rel="stylesheet" media="all" href="assets/css/xh_bi_kb.css">
	<script type="text/javascript" async="" src="assets/js/xbtsuf77"></script>
	<script type="text/javascript" async="" src="https://widget.intercom.io/widget/xbtsuf77"></script>
	<script async="" src="assets/js/xh_bi_kb1.js"></script>
	<script src="assets/js/xh_bi_kb2.js"></script>
	<meta name="csrf-param" content="authenticity_token">
	<meta name="csrf-token" content="TjuYqk0qCM+oXX8bdPDWVrYt5EXsw2yGtadwG/EPN2CwFvBFZH427njMwDNq/J1lUcCveBC2Ol67HZ/ZMW3IIQ==">
</head>

<body class="">
  <div class="wrapper">
		<div class="header">
  		<div class="header-container">
    		<h1 class="logo">
        	<a class="header-team-name" data-stack="true" data-stack-root="true" href="">新海科技集团知识库</a>
    		</h1>
		    <ul class="nav">
    		  <li class="active" id="nav-project">
        		<a href="" data-stack="" data-stack-root="">项目</a>
      		</li>
      		<li class="dividing"></li>
      		<li>
        		<a href="" data-stack="" data-stack-root="">我自己</a>
      		</li>
				<li id="nav-upgrade"></li>
    		</ul>

    <div class="command-bar">
      <div class="search-wrap">
        <a href="javascript:;" class="link-search" title="搜索"><i class="twr twr-search"></i></a>
        <form id="form-search" class="form" method="get" action="https://tower.im/teams/66fb82b17f5341cbb0f5f39a1064c013/search">
          <input id="txt-search" type="text" class="keyword no-border" name="keyword" placeholder="搜索" autocomplete="off">
        </form>
			</div>
		</div>
	<div class="container workspace simple-stack simple-stack-transition"><div class="page page-root simple-pjax"><div class="page-inner" id="page-projects" data-page-name="新海科技集团的项目">
	        <div class="projects-tools">
	  <div class="project-groups">
	    <div class="project-tools-right">
	      <a class="link-create-project new " href="javascript:;" data-stack="" data-nocache="">
	        	新建项目
	      </a>
	      <div class="switch-view">
<!-- 	        <a href="javascript:;" class="link-view link-grid-view active" title="网格视图">
	          <i class="twr twr-grid-view"></i>
	        </a>
	        <a href="javascript:;" class="link-view link-list-view" title="列表视图">
	          <i class="twr twr-list-view"></i>
	        </a> -->
	      </div>
	    </div>
	  </div>
	</div>
        <div class="pin-projects ui-sortable">
        </div>

        <div class="projects ui-sortable grid-view">
		<c:forEach var="projects" items="${ProList }">
		<%-- <c:if test="${projects.projectStatus=='progress' }"> --%>
        <a class="project c2 i2" href="javascript:;" data-access-id="20510112" data-group-ids="[]" data-stack="" data-stack-root="" onclick="goto()">
    			<span class="badge"></span>
    			<span class="name">${projects.projectName }</span>
    			<span style="display:none"><input type="text" name="project_code" value="${projects.projectCode }" /></span>
    			<span style="display:none"><input type="text" name="project_level" value="${projects.projectLevel }" /></span>
  		</a>
  		<%-- </c:if> --%>
  		</c:forEach>
        </div>


      <div class="projects-footer">
        <a href="" data-stack="" data-visible-to="member">管理项目模板</a>

          <a href="" data-stack="">管理已归档项目</a>

      </div>


    </div></div></div>
<div class="footer">
	© 新海科技集团
</div>


  </div>

<!--  	<input type="hidden" id="d18n-enabled" value="false">
  	<input type="hidden" id="server-time" value="2018-06-16 13:29:10">
    <input type="hidden" id="team-guid" value="66fb82b17f5341cbb0f5f39a1064c013">
    <input type="hidden" id="team-name" value="新海科技集团">
    <input type="hidden" id="team-enable-pusher" value="true">
    <input type="hidden" id="user-id" value="7379342">
    <input type="hidden" id="user-email" value="czdong@x.xinhai.com">
    <input type="hidden" id="unused-bubbles" value="8,10,12,13,14">
    <input type="hidden" id="member-id" value="12263650">
    <input type="hidden" id="member-guid" value="b9b37caa458b4f72b104aeccf3e4d3fc">
    <input type="hidden" id="member-nickname" value="陈专懂">
    <input type="hidden" id="member-avatar" value="https://avatar.tower.im/9e6896f3112341179a387a42d3b1b423">
    <input type="hidden" id="member-timezone" value="Asia/Shanghai">
    <input type="hidden" id="conn-guid" value="7a19ad2c-a30a-49b8-84c3-41e93f894ac3">
    <input type="hidden" id="electron" value="false">-->

<!--   <script>
//<![CDATA[
window.gon={};gon.oss_direct_upload=true;
//]]>
</script> -->

<!--   <a id="back-to-top" href="javascript:;">
    <i class="twr twr-chevron-up"></i>
  </a> -->
  <a id="link-to-help" data-intercom="true" target="_blank" href="">
    <span class="twr twr-help"></span></a>

  <script type="text/javascript">
(function() {
  setTimeout(function() {
    if (!document.querySelector('iframe#intercom-frame')) {
      var w = window;
      var ic = w.Intercom;
      if (ic.toString().length < 100) {
        var d = document;
        var i = function() { i.c(arguments) };
        i.q = [];
        i.c = function(args) { i.q.push(args) };
        w.Intercom = i;

        function l() {
          var s = d.createElement('script');
          s.type = 'text/javascript';
          s.async = true;
          s.src = 'https://jsintercom.tuanliao.com/widgets/xbtsuf77';
          var x = d.getElementsByTagName('script')[0];
          x.parentNode.insertBefore(s, x);
        }

        l();
      }
    }
  }, 5000)
})()

  	function goto(){
  			var projectCode=document.getElementsByName("project_code")[0].value;
			var projectLevel=document.getElementsByName("project_level")[0].value;
			alert("12"+projectCode+";;;"+projectLevel);
			<%-- var userCode= "<%=session.getAttribute("user_code")%>"; --%>
			window.location.href="file/pfd.do?project_code="+projectCode+"&project_level="+projectLevel;
  	
  	}
</script>

  <script>
  
  
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-100449807-1', 'auto', { userId: '12263650' });
  ga('create', 'UA-100449807-2', 'auto', 'teamTracker', { userId: '593973' });

  ga('set', 'dimension4', '桌面网页');

  ga('send', 'pageview', {
    'userId': '12263650',
    'dimension1': '593973 : 新海科技集团',
    'dimension2': '7379342',
    'dimension3': 'pro2017',
    'dimension4': '桌面网页'
  });

  ga('teamTracker.send', 'pageview', {
    'userId': '593973',
    'dimension1': '新海科技集团',
    'dimension2': 68,
    'dimension3': 'pro2017',
    'dimension4': '桌面网页'
  });
</script>

  


<textarea tabindex="-1" style="position: absolute; top: -999px; left: 0px; right: auto; bottom: auto; border: 0px; box-sizing: content-box; word-wrap: break-word; overflow: hidden; height: 0px !important; min-height: 0px !important;"></textarea><div class="offline-ui offline-ui-up"><div class="offline-ui-content"></div><a href="https://tower.im/teams/66fb82b17f5341cbb0f5f39a1064c013/projects/" class="offline-ui-retry"></a></div></body>
</html>