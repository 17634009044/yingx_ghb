<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>应学后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body ><%--background="img/36.jpg" style="background-size:50%"--%>

    <%--<h1 align="center">什么都没有了,全靠你们造啦O(∩_∩)O哈哈~</h1>--%>
    <!--顶部导航-->

    <!--栅格系统-->
        <!--左边手风琴部分-->
        <!--巨幕开始-->
        <!--右边轮播图部分-->
        <!--页脚-->
    <!--栅格系统-->
    <!--导航条-->

   <!--role="button"  class="dropdown-toggle" data-toggle="dropdown" -->

    <!--容器-->
    <div class="container-fluid">
        <!--栅格系统-->
        <div class="row">

            <div class="col-xs-12">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="#">应学视频App后台管理系统<small>V1.0</small></a>
                        </div>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#">您好: ${sessionScope.login.username}</a></li>
                            <li class="dropdown">
                                <a href="${path}/admin/loginOut" class="btn-info">退出管理系统 <span class="glyphicon glyphicon-log-out"></span></a>
                            </li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
<%--            </div><!-- /.container-fluid -->--%>
            </nav>

            </div>

            <!--左侧手风琴-->
            <div class="col-xs-2" align="center">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-info">
                                <a role="button" data-toggle="collapse"  data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    用户管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                            <div class="panel-body">
                                <a class="btn btn-info" href="javascript:$('#mainId').load('User.jsp')">用户列表</a>
                            </div>
                            <div class="panel-body">
                                <a class="btn btn-info" href="javascript:$('#mainId').load('')">用户统计</a>
                            </div>
                            <div class="panel-body">
                                <a class="btn btn-info" href="javascript:$('#mainId').load('')">用户分布</a>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingTwo">
                            <h4 class="panel-info">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    类别管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">
                            <div class="panel-body">

                                <a class="btn btn-info" href="javascript:$('#mainId').load('Cartgory.jsp')">类别列表</a>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingThree">
                            <h4 class="panel-info">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    视频管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingThree">
                            <div class="panel-body">
                                <a class="btn btn-info" href="javascript:$('#mainId').load('Video.jsp')">视频展示</a>
                            </div>
                        </div>
                    </div>
                    <c:if test="${sessionScope.login.level==1}">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingFor">
                                <h4 class="panel-info">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                       href="#collapseFor" aria-expanded="false" aria-controls="collapseFor">
                                        日志管理
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseFor" class="panel-collapse collapse in" role="tabpanel"
                                 aria-labelledby="headingFor">
                                <div class="panel-body">
                                    <a class="btn btn-info" href="javascript:$('#mainId').load('Log.jsp')">日志展示</a>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="heading1">
                                <h4 class="panel-info">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                                       href="#collapse1" aria-expanded="false" aria-controls="collapse1">
                                        管理员管理
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse1" class="panel-collapse collapse" role="tabpanel"
                                 aria-labelledby="heading1">
                                <div class="panel-body">
                                    <a class="btn btn-info" href="javascript:$('#mainId').load('Admin.jsp')">管理员展示</a>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading2">
                            <h4 class="panel-info">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse2" aria-expanded="false" aria-controls="collapse2">
                                    反馈管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapse2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading2">
                            <div class="panel-body">
                                <a class="btn btn-info" href="javascript:$('#mainId').load('FeedBack1.jsp')">反馈展示</a>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
            <!--巨幕-->
            <div class="col-xs-10" id="mainId" align="center">
                <div class="jumbotron">
                    <font size="20px">欢迎来到应学视频App后台管理系统</font>
                </div>
                <!--轮播图-->
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" role="listbox">
                        <div class="item active">
                            <img src="../bootstrap/img/pic1.jpg" alt="...">
                            <div class="carousel-caption">
                            </div>
                        </div>
                        <div class="item">
                            <img src="../bootstrap/img/pic2.jpg" alt="...">
                            <div class="carousel-caption">
                            </div>
                        </div>
                        <div class="item" style="size: 600px">
                            <img src="../bootstrap/img/pic3.jpg" alt="...">
                            <div class="carousel-caption">
                            </div>
                        </div>
                        <div class="item">
                            <img src="../bootstrap/img/pic4.jpg" alt="...">
                            <div class="carousel-caption">
                            </div>
                        </div>
                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#carousel-example-generic" role="button"
                       data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" role="button"
                       data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-default navbar-fixed-bottom">
        <div class="panel panel-footer" align="center" style="float: bottom">
            <div>
                <h5>@郭彬的专属后台管理系统 guohb@zprkhr.com</h5>
            </div>
        </div>
    </div>
</body>
</html>
