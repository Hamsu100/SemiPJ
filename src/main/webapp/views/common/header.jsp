<%@page import="com.kh.member.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String bchNoImg ="https://cdn.imweb.me/thumbnail/20210712/61009d196445a.jpg";
User loginUser = (User) session.getAttribute("loginUser");
%>



<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="robots" content="all,follow">
<title>BADADA</title>
<!-- Price Slider Stylesheets -->
<link rel="stylesheet" href="<%=path %>/resources/vendor/nouislider/nouislider.css">
<!-- Google fonts - Playfair Display-->
<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-round.css"
	rel="stylesheet">
<!-- Google fonts - Poppins-->
<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-round.css"
	rel="stylesheet">
<!-- swiper-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.4.1/css/swiper.min.css">
<!-- Magnigic Popup-->
<link rel="stylesheet"
	href="<%=path %>/resources/vendor/magnific-popup/magnific-popup.css">
<!-- theme stylesheet-->
<link rel="stylesheet" href="<%=path %>/resources/resources/css/style.sea.css"
	id="theme-stylesheet">
<!-- Custom stylesheet - for your changes-->
<link rel="stylesheet" href="<%=path %>/resources/resources/css/custom.css">
<!-- Font Awesome CSS-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
<!-- 타이틀 옆 아이콘-->
<link rel="shortcut icon" href="<%=path %>/resources/resources/images/wave.png">
</head>

<body style="padding-top: 72px;">
	<!-- 헤더 시작 -->
	<header class="header">
		<nav
			class="navbar navbar-expand-lg fixed-top shadow navbar-light bg-white">
			<div class="container-fluid">
				<div class="d-flex align-items-center">
					<a class="navbar-brand py-0" href="<%=path%>/index"><img class="logo"
						src="<%=path %>/resources/resources/images/logo5.png" alt="wave logo"></a>
					<!-- 로고를 누르면 홈으로 -->
				</div>
				<div class="collapse navbar-collapse" id="navbarCollapse">
					<ul class="navbar-nav ms-auto">
						<li class="nav-item"><a class="nav-link" href="<%=path%>/index">HOME</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" id="beachDropdownMenuLink"
							href="" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> BEACH</a>
							<div class="dropdown-menu dropdown-menu-end"
								aria-labelledby="beachDropdownMenuLink">
								<a class="dropdown-item" href="<%=path%>/beach/search">- 해수욕장 검색하기 </a> <a
									class="dropdown-item" href="<%=path%>/beach/pop">- 인기지역 </a> <a
									class="dropdown-item" href="">- 주변시설 찾아보기 </a>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" id="maketDropdownMenuLink"
							href="main.html" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> MARKET</a>
							<div class="dropdown-menu dropdown-menu-end"
								aria-labelledby="maketDropdownMenuLink">
								<h6 class="dropdown-header fw-normal">당신이 찾는 그 모든 것</h6>
								<a class="dropdown-item" href="">- 마켓 모아보기 </a> <a
									class="dropdown-item" href="">- 용품샵 </a>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" id="boardDropdownMenuLink"
							href="main.html" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> COMMUNITY</a>
							<div class="dropdown-menu dropdown-menu-end"
								aria-labelledby="boardDropdownMenuLink">
								<h6 class="dropdown-header fw-normal">게시판들</h6>
								<a class="dropdown-item" href="<%=path%>/board/list">- 게시판 모아보기 </a> <a
									class="dropdown-item" href="otherblog.html">- 블로그 파도타기</a> <a
									class="dropdown-item" href="myblog.html">- MY Blog</a>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" id="blogDropdownMenuLink"
							href="main.html" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> MY PAGE</a>
							<div class="dropdown-menu dropdown-menu-end"
								aria-labelledby="blogDropdownMenuLink">
								<a class="dropdown-item" href="otherblog.html">- 블로그 파도타기</a> <a
									class="dropdown-item" href="myblog.html">- MY Blog</a>
							</div></li>
					<%if (loginUser == null) {%>
						<li class="nav-item"><a class="nav-link" href="<%=path %>/user/login">SIGN
								IN</a></li>
						<li class="nav-item"><a class="nav-link" href="<%=path %>/user/signup">SIGN
								UP</a></li>
					<%} else {%>
						<li class="nav-item"><a class="nav-link" href="<%=path %>/user/logout">LOG OUT</a></li>
					<%} %>
					</ul>
				</div>
		</nav>
	</header>
	<!-- 헤더 끝 -->