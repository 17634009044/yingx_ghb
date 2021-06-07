<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $.ajax({
        url:"${path}/feedback/queryAll",
        type:"POST",
        dataType:"JSON",
        success:function (result) {
            $.each(result,function (index,l){
                $("#tableId").append(
                    "<tr>" +
                        "<td>"+l.id+"</td>"+
                        "<td>"+l.user.name+"</td>"+
                        "<td>"+l.title+"</td>"+
                        "<td>"+l.content+"</td>"+
                        "<td>"+l.feed_time+"</td>"+
                        "<td><a href='' class='btn btn-danger'>"+"删除"+"</a></td>"+
                     "</tr>"
                )
            });
        }
    })

/*
    $.ajax({
        url:"${pageContext.request.contextPath}/vegetable/queryAll",
        type:"POST",
        dataType:"JSON",
        success:function(data){
            $.each(data,function (index,l){
                $("#table").append(""+
                    "<tr>"+
                    "<th scope='row'>"+l.id+"</th>"+
                    "<td>"+l.name+"</td>"+
                    "<td>< img style='width: 200px;height: 120px;' src='${pageContext.request.contextPath}"+l.photo+"' class='img-thumbnail' alt=''></td>"+
                    "<td>"+l.createDate+"</td>"+
                    "<td>"+l.createPerson+"</td>"+
                    "<td>"+l.digest+"</td>"+
                    "<td>"+l.step+"</td>"+
                    "<td>< a href=' "+l.id+"' class='btn btn-danger'>"+"删除"+"</ a>&nbsp;&nbsp;"+
                    "< a href='${pageContext.request.contextPath}/vegetable/queryOne?id="+l.id+"' class='btn btn-info'>"+"修改"+"</ a></td>"+
                    "</tr>"
                )
            })
        }
    });
    */






























</script>

<!--面板-->
<div class="panel panel-info" style="background-size: 200px;">
    <!--面板标题-->
    <div class="panel panel-heading"><strong>反馈管理</strong></div>
    <!--面板内容-->
    <div class="panel panel-body">
        <!--标签页-->
        <div>
            <!-- 标签页选项卡 -->
            <ul class="nav nav-tabs nav-pills" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">反馈信息</a></li>
            </ul>

            <!-- 选项卡对应的内容 -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <table class="table table-bordered" style="text-align: center" id="tableId">
                        <%--<tr>
                            <td colspan="7">
                                姓名：<input type="text" name="name"/>
                                <button class="btn btn-danger">查询</button>
                            </td>
                        </tr>--%>
                        <tr>
                            <td>ID</td>
                            <td>用户名</td>
                            <td>标题</td>
                            <td>内容</td>
                            <td>反馈时间</td>
                            <td>操作</td>
                        </tr>
                       <%-- <c:forEach items="${backs}" var="l">
                            <tr>
                                <td>${l.id}</td>
                                <td>${l.user.name}</td>
                                <td>${l.title}</td>
                                <td>${l.content}</td>
                                <td>${l.feed_time}</td>
                                <td><a href="" class="btn btn-danger">删除</a></td>
                            </tr>
                        </c:forEach>--%>
                    </table>
                    <!--分页工具栏-->
                    <div align="center">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <li>
                                    <a href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li>
                                    <a href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
