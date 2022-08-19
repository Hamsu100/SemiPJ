<%@page import="com.kh.beach.model.vo.Beach"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp"%>
<%
List<Beach> bList = (List<Beach>)request.getAttribute("bList");

%>
<section class="d-flex align-items-center dark-overlay bg-cover" style="background-image: url(<%=path%>/resources/resources/images/back3.jpg);">
        <div class="container py-6 py-lg-7 text-white overlay-content text-center">
            <div class="row">
                <div class="col-xl-10 mx-auto">
                    <h2 class="display-3  text-shadow">해수욕장 상세검색</h2>
                </div>
            </div>
        </div>
    </section>
    <div class="container">
        <div class="search-bar rounded p-3 p-lg-4 position-relative mt-n5 z-index-20">
            <form action="<%=path %>/beach/list" method="get">
                <div class="row">
                    <div class="col-lg-7 d-flex align-items-center form-group ">
                        <input class="form-control border-0 shadow-0" type="search" name="searchValue" placeholder="What are you searching for?">
                    </div>
                    <!-- 지역으로 선택 value에 지역코드 적용-->
                    <div class="col-md-6 col-lg-3 d-flex align-items-center form-group no-divider">
                        <select class="selectpicker" title="지역" name="locName" data-style="btn-form-control">
                            <option value="전국" selected>전국</option>
                                        <option value="서해">서해</option>
                                        <option value="남해">남해</option>
                                        <option value="동해">동해</option>
                                        <option value="제주도">제주</option>
                        </select>
                    </div>
                    <div class="col-lg-2 form-group mb-0">
                        <button class="btn btn-primary btn-block h-100" type="submit">Search </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <section class="py-6">
        <div class="container">
            <!-- 이부분은 몇몇 인기 해수욕장을 보여주고 바로 해수욕장 상세보기로 넘어가는 섹션입니다!! -->
            <!-- 이미지는 각 해수욕장 이미지로 바꿔주시고,(조금 화질좋은애로..좀...ㅠ) -->
            <!-- bde.html이 해수욕장 디테일 html 입니다 넘어가서 각기 해수욕장을 출력하면 좋을 것 같아요! -->
            <!-- 일단 아무 5개 해수욕장 넣습니다! 다른걸로 바꿔도 무방합니다! -->
            <%--나중에 데이터 들어오면 해봄 --%>
            <div class="row">
                <div class="d-flex align-items-lg-stretch mb-4 col-lg-8">
                    <div class="card shadow-lg border-0 w-100 border-0 hover-animate" style="background: center center url(<%=bList.get(0).getBEACH_IMG().split(",")[0] %>) no-repeat; background-size: cover;">
                        <a class="tile-link" href="<%=path%>/beach/view?beachCode=<%=bList.get(0).getBEACH_CODE()%>"> </a>
                        <div class="d-flex align-items-center h-100 text-white justify-content-center py-6 py-lg-7">
                            <h3 class="text-shadow text-uppercase mb-0"><%=bList.get(0).getBEACH_NAME() %></h3>
                        </div>
                    </div>
                </div>
                <div class="d-flex align-items-lg-stretch mb-4 col-lg-4">
                    <div class="card shadow-lg border-0 w-100 border-0 hover-animate" style="background: center center url(<%=bList.get(1).getBEACH_IMG().split(",")[0] %>) no-repeat; background-size: cover;">
                        <a class="tile-link" href="<%=path%>/beach/view?beachCode=<%=bList.get(1).getBEACH_CODE()%>"> </a>
                        <div class="d-flex align-items-center h-100 text-white justify-content-center py-6 py-lg-7">
                            <h3 class="text-shadow text-uppercase mb-0"><%=bList.get(1).getBEACH_NAME() %></h3>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="d-flex align-items-lg-stretch mb-4 col-lg-4">
                    <div class="card shadow-lg border-0 w-100 border-0 hover-animate" style="background: center center url(<%=bList.get(2).getBEACH_IMG().split(",")[0] %>) no-repeat; background-size: cover;">
                        <a class="tile-link" href="<%=path%>/beach/view?beachCode=<%=bList.get(2).getBEACH_CODE()%>"> </a>
                        <div class="d-flex align-items-center h-100 text-white justify-content-center py-6 py-lg-7">
                            <h3 class="text-shadow text-uppercase mb-0"><%=bList.get(2).getBEACH_NAME() %></h3>
                        </div>
                    </div>
                </div>
                <div class="d-flex align-items-lg-stretch mb-4 col-lg-4">
                    <div class="card shadow-lg border-0 w-100 border-0 hover-animate" style="background: center center url(<%=bList.get(3).getBEACH_IMG().split(",")[0] %>) no-repeat; background-size: cover;">
                        <a class="tile-link" href="<%=path%>/beach/view?beachCode=<%=bList.get(3).getBEACH_CODE()%>"> </a>
                        <div class="d-flex align-items-center h-100 text-white justify-content-center py-6 py-lg-7">
                            <h3 class="text-shadow text-uppercase mb-0"><%=bList.get(3).getBEACH_NAME() %></h3>
                        </div>
                    </div>
                </div>
                <div class="d-flex align-items-lg-stretch mb-4 col-lg-4">
                    <div class="card shadow-lg border-0 w-100 border-0 hover-animate" style="background: center center url(<%=bList.get(4).getBEACH_IMG().split(",")[0] %>) no-repeat; background-size: cover;">
                        <a class="tile-link" href="<%=path%>/beach/view?beachCode=<%=bList.get(4).getBEACH_CODE()%>"> </a>
                        <div class="d-flex align-items-center h-100 text-white justify-content-center py-6 py-lg-7">
                            <h3 class="text-shadow text-uppercase mb-0"><%=bList.get(4).getBEACH_NAME() %> </h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>



<%@include file="/views/common/footer.jsp"%>