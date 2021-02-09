<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template.html"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String memNo = (String) request.getSession().getAttribute("memNo");
 %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>방탈출 HOME</title>
	
	<link rel="stylesheet" type="text/css" href="resources/css/index.css?v=2">
</head>
<body>
	<div id="colorlib-page">
		<a href="#" class="js-colorlib-nav-toggle colorlib-nav-toggle"><i></i></a>
		<aside id="colorlib-aside" role="complementary" class="js-fullheight">
			<a href="javascript:void(0);" onclick="changePage('main.do',0,1)"><img src="resources/images/logo.png" alt="logo" id="logoImg"></a>
			<nav id="colorlib-main-menu" role="navigation">
				<ul>
					<li id="menu1" ><a href="javascript:void(0);" onclick="changePage('main.do',0,1);">Home</a></li>
					<li id="menu2" ><a href="javascript:void(0);" onclick="changePage('views/board/notice/listNoticeBoard.jsp',2,2);">공지사항</a></li>
					<li id="menu3" ><a href="javascript:void(0);" onclick="changePage('qa/list.do',3,3);">질문게시판</a></li>
					<li id="menu4" ><a href="javascript:void(0);" onclick="changePage('views/board/item/listItem.do',5,4);">상점</a></li>
					<li id="menu5" ><a href="javascript:void(0);" onclick="changePage('views/board/event/listEvent.do',1,5);">이벤트</a></li>
				</ul>
				<% if("ADMIN".equals(memNo)){
				%>
				<p class="menuTitle">ADMIN</p>
				<ul>
					<li id="menu6"><a href="javascript:void(0);" onclick="changePage('views/admin/management.jsp',7,6);">회원관리</a></li>
	  				<li id="menu7"><a href="javascript:void(0);" onclick="changePage('report/list.do',4,7);">신고게시판</a></li>
				</ul>
				<%
				}
				%>
			</nav>

			<div class="colorlib-footer">
				<div class="mb-4">
					<div class="register">
						<%
							if(session.getAttribute("memId") == null){%>
								<a href="views/member/loginMember.do">Login</a>
								<a href="views/member/newMember.do">Join</a>
							<%	
							} else if(session.getAttribute("memId") != null){%>
								<h3><%=session.getAttribute("memId") %></h3>
								<%if(session.getAttribute("memId") != null && !session.getAttribute("memId").equals("TEST")) {%>
									<a href="javascript:void(0)" onclick="changePage('views/member/myPageMember.do',6,8);">MyPage</a>
								<%} %>
								<a href="views/member/logoutMember.do">Logout</a>
							<%
							}
						%>
					</div>
				</div>
				<p class="pfooter"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib.com</a>
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
			</div>
		</aside> <!-- END COLORLIB-ASIDE -->
	</div>
	<div id="colorlib-main">
		<iframe name="ifr" src="main.do"></iframe>
	</div>
	<script type="text/javascript" src="resources/js/common.js"></script>
	<script>
   	var boardGrNo = getCookie("boardGrNo");
    if(boardGrNo && boardGrNo != 'undefined'){
    	if(boardGrNo==0){
		    $("iframe[name='ifr']").attr("src", 'main.do');
    	}else if(boardGrNo==1){
		    $("iframe[name='ifr']").attr("src", 'views/board/event/listEvent.do');
    	}else if(boardGrNo==2){
		    $("iframe[name='ifr']").attr("src", 'views/board/notice/listNoticeBoard.jsp');
    	}else if(boardGrNo==3){
		    $("iframe[name='ifr']").attr("src", 'qa/list.do');
    	}else if(boardGrNo==4){
		    $("iframe[name='ifr']").attr("src", 'report/list.do?boardGrNo=4');
    	}else if(boardGrNo==5){
		    $("iframe[name='ifr']").attr("src", 'views/board/item/listItem.do');
    	}else if(boardGrNo==6){
    		$("iframe[name='ifr']").attr("src", 'views/member/myPageMember.do');
    	}else if(boardGrNo==7){
    		$("iframe[name='ifr']").attr("src", 'views/admin/management.do');
    	}
    }else{
    	document.cookie = "boardGrNo=0";
    	document.cookie = "menu=1";
    	document.cookie = "pageNo=1";
    }
   	changeMenu();
    
	function changePage(page, boardGrNo, menu){
		// 쿠키 생성(boardGrNo 값 저장)
		document.cookie="boardGrNo="+boardGrNo;
		// 메뉴쿠키생성
		document.cookie="menu="+menu;
		// 페이지 초기화
		initPageParam();
		
		$("iframe[name='ifr']").attr("src", page);
		
		changeMenu();
	}
	
    function changeMenu(){
    	var menu = getCookie("menu");
    	// class activate설정
		$("li").removeClass();
		$('#menu'+menu).addClass("colorlib-active");
    }
    
	</script>
</body>
</html>