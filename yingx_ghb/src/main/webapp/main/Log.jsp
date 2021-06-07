<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function(){ //延迟加载
        //初始化jqGrid
        $("#logTable").jqGrid({
            url : "${path}/log/queryPage",
           // editurl:"${path}/emp/edits",   //指定增删改操作的url   添加oper=add  修改oper=edit  删除oper=del
            datatype : "json",
            mtype : "post",
            rowNum : 3,
            rowList : [ 3,5,10, 20, 30 ],
            pager : '#logPage',
            viewrecords : true,  //是否展示总条数
            caption : "反馈数据",
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            colNames : [ 'ID', '管理员', '操作时间', '操作方法', '操作状态'],
            colModel : [
                {name : 'id',width : 55},
                {name : 'admin_name',editable:true,width : 90},
                {name : 'option_time',editable:true,width : 100},
                {name : 'method',width : 80},
                {name : 'status',editable:true,width : 150} //editoptions:{value:"男:男生;女:女孩"}


            ]
        }).navGrid("#feedbackPage", //开启增删改查操作  指定分页工具栏的位置
            {edit:true,edittext:"修改",del:true,deltext:"删除",add:true,addtext:"添加",search:true,searchtext:"搜索"},  //是否展示增删改查按钮  {},都展示
            {
                closeAfterEdit:true
            },  //修改之后的额外操作
            {   //在执行添加成功之后进入该括号执行
                closeAfterAdd:true //执行成功之后关闭对话框
            },  //添加之后的额外操作
            {
                closeAfterDel: true
            }   //删除之后的额外操作
        );

        //开启增删改查操作 指定分页工具栏的位置
        /*$("#empTable").jqGrid("navGrid","#empPage",{},{},{},{}); */

    });
</script>

<title>这是主页面</title>
</head>
<body>
<!--面板-->
<div class="panel panel-info">
    <!--面板标题-->
    <div class="panel panel-heading"><strong>日志数据</strong></div>
    <!--标签页-->
    <div>
        <!-- 标签页选项卡 -->
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">日志数据表</a></li>
        </ul>
        <span style="color:red;" id="sId"></span>
    </div>
    <!--面板内容-->
    <div class="panel panel-body" align="left">
        <button class="btn btn-info">导入日志信息</button>&emsp;&emsp;
        <a class="btn btn-danger" href="${path}/excel/exportLog">导出日志信息</a>
    </div>
<%--表格--%>
<table id="logTable"/>

<%--分页工具栏--%>
<div id="logPage" />


</body>