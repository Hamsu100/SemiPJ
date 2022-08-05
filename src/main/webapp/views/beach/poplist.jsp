<%@page import="com.kh.beach.model.vo.Beach"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/views/common/header.jsp"%>
<%--
뭘 보내 줘야 하냐면 
1. 팝 비치 리스트
2. ajax 설계
 --%>

<%
	List<Beach> bpList = (List<Beach>)request.getAttribute("bpList");
	String[] img =((String)request.getAttribute("img")).split(",");
%>

<section class="hero-home2 dark-overlay mb-5"><img class="bg-image" src="<%=path %>/resources/resources/images/back2.jpg" alt="">
        <div class="container py-7">
            <div class="overlay-content text-center text-white">
                <h1 class="display-3 text-shadow mb-0">Escape the city today</h1>
            </div>
        </div>
    </section>
    <!-- Our picks section-->
    <section class="py-6">
        <div class="container">
            <div class="row mb-3">
                <div class="col-md-8">
                    <p class="subtitle text-primary">Most popular cities</p>
                    <h2>What's trending</h2>
                </div>
                <div class="col-md-4 d-md-flex align-items-center justify-content-end"><a class="text-muted text-sm" href="<%=path%>/beach/list?locName=전국">
           See all cities<i class="fas fa-angle-double-right ms-2"></i></a></div>
            </div>
            <div class="row">
                <div class="d-flex align-items-lg-stretch mb-4 col-lg-12 ">
                    <div class="card shadow-lg border-0 w-100 border-0 hover-animate" style="background: center center url(<%=img[0] %>) no-repeat; background-size: cover;">
                        <a class="tile-link" href="<%=path%>/beach/pop"> </a>
                        <div class="d-flex align-items-center h-100 text-white justify-content-center py-6 py-lg-7">
                            <h1 class="text-shadow text-uppercase mb-0">전국</h1>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="d-flex align-items-lg-stretch mb-4 col-lg-3">
                    <div class="card shadow-lg border-0 w-100 border-0 hover-animate" style="background: center center url(<%=img[1] %>) no-repeat; background-size: cover;">
                        <a class="tile-link" href="<%=path%>/beach/pop?locName=동해"> </a>
                        <div class="d-flex align-items-center h-100 text-white justify-content-center py-6 py-lg-7">
                            <h1 class="text-shadow text-uppercase mb-0">동해</h1>
                        </div>
                    </div>
                </div>
                <div class="d-flex align-items-lg-stretch mb-4 col-lg-3">
                    <div class="card shadow-lg border-0 w-100 border-0 hover-animate" style="background: center center url(<%=img[2] %>) no-repeat; background-size: cover;">
                        <a class="tile-link" href="<%=path%>/beach/pop?locName=서해"> </a>
                        <div class="d-flex align-items-center h-100 text-white justify-content-center py-6 py-lg-7">
                            <h1 class="text-shadow text-uppercase mb-0">서해</h1>
                        </div>
                    </div>
                </div>
                <div class="d-flex align-items-lg-stretch mb-4 col-lg-3">
                    <div class="card shadow-lg border-0 w-100 border-0 hover-animate" style="background: center center url(<%=img[3] %>) no-repeat; background-size: cover;">
                        <a class="tile-link" href="<%=path%>/beach/pop?locName=남해"> </a>
                        <div class="d-flex align-items-center h-100 text-white justify-content-center py-6 py-lg-7">
                            <h1 class="text-shadow text-uppercase mb-0">남해</h1>
                        </div>
                    </div>
                </div>
                <div class="d-flex align-items-lg-stretch mb-4 col-lg-3">
                    <div class="card shadow-lg border-0 w-100 border-0 hover-animate" style="background: center center url(<%=img[4] %>) no-repeat; background-size: cover;">
                        <a class="tile-link" href="<%=path%>/beach/pop?locName=제주"> </a>
                        <div class="d-flex align-items-center h-100 text-white justify-content-center py-6 py-lg-7">
                            <h1 class="text-shadow mb-0">제주</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <hr>
    <!--  -->
    <section class="py-4">
        <div class="container">
            <div class="mb-5">
                <div class="col-md-8">
                    <h3 class="text-primary"> MOST POPULAR CITIES</h3>
                </div>
            </div>
            <div class="row ">
            	
            <%
            for (int i = 0 ; i < 3;i++){
            %>
                <div class="d-flex align-items-lg-stretch me-2 col-lg-3 height-1 ">
                    <div class="card border-0 w-100 border-0 " style="background: center center url(<%=bpList.get(i).getBEACH_IMG().split(",")[0].length() ==0? bchNoImg : bpList.get(i).getBEACH_IMG().split(",")[0] %>) no-repeat; background-size: cover;">
                        <a class="tile-link" href="<%=path%>/beach/view?beachCode=<%=bpList.get(i).getBEACH_CODE()%>"> </a>
                        <div class="d-flex align-items-center h-100 text-white justify-content-center py-6 py-lg-7">
                        </div>
                    </div>
                </div>
                <div class="d-flex align-items-lg-stretch mb-4 col-lg-9-1 height-1">
                    <div class="card  border-0 w-100 border-0">
                        <a class="tile-link" href="<%=path%>/beach/view?beachCode=<%=bpList.get(i).getBEACH_CODE()%>"> </a>
                        <div class="d-flex text-black">
                            <h3 class="ms-3"><%=bpList.get(i).getBEACH_NAME() %></h3>
                        </div>
                        <div>
  <p class="ms-3">주소 : <%=bpList.get(i).getBEACH_ADDRESS() %></p>
                            <p class="ms-3 text-muted" style=" overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 4;
  -webkit-box-orient: vertical;"><%=bpList.get(i).getBEACH_CONTENT() %></p>
                        </div>
                    </div>
                </div>
            <%
            }
            %>
                
                
            </div>
            <div class="collapse" id="collapseExample">
                <div class="row">
                
                <%for (int i = 3 ; i < 10;i++){ %>
                    <div class="d-flex align-items-lg-stretch me-2 col-lg-3 height-1 ">
                        <div class="card border-0 w-100 border-0 " style="background: center center url(<%=bpList.get(i).getBEACH_IMG().split(",")[0].length() ==0? bchNoImg : bpList.get(i).getBEACH_IMG().split(",")[0] %>) no-repeat; background-size: cover;">
                            <a class="tile-link" href="<%=path%>/beach/view?beachCode=<%=bpList.get(i).getBEACH_CODE()%>"> </a>
                            <div class="d-flex align-items-center h-100 text-white justify-content-center py-6 py-lg-7">
                            </div>
                        </div>
                    </div>
                    <div class="d-flex align-items-lg-stretch mb-4 col-lg-9-1 height-1">
                        <div class="card  border-0 w-100 border-0">
                            <a class="tile-link" href="<%=path%>/beach/view?beachCode=<%=bpList.get(i).getBEACH_CODE()%>"> </a>
                            <div class="d-flex text-black">
                                <h3 class="ms-3"><%=bpList.get(i).getBEACH_NAME() %></h3>
                            </div>
                            <div>
  <p class="ms-3">주소 : <%=bpList.get(i).getBEACH_ADDRESS() %></p>
                                <p class="ms-3" style=" overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 4;
  -webkit-box-orient: vertical;"><%=bpList.get(i).getBEACH_CONTENT() %></p>
                            </div>
                        </div>
                    </div>
                <%
                }
                %>
                    
                </div>
            </div>
        </div>
        <button class="btn btn-primary disp-block " onclick="resize();" id="resize" style="float:none;" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">More</button>
    </section>
</body>

<script>
    var toggle = true;

    function resize() {
        var btn = document.getElementById('resize');
        if (toggle == true) {
            btn.innerText = '다시접기';
            toggle = false;
        } else {
            btn.innerText = 'More';
            toggle = true;
        }
    }
</script>

<%@include file="/views/common/footer.jsp"%>