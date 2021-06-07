<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function(){ //延迟加载
        //初始化jqGrid
        $("#AdminTable").jqGrid({
            url : "${path}/admin/queryAll",
            editurl:"${path}/admin/edits",   //指定增删改操作的url   添加oper=add  修改oper=edit  删除oper=del
            datatype : "json",
            mtype : "post",
            rowNum : 3,
            rowList : [ 3,5,10, 20, 30 ],
            pager : '#AdminPage',
            viewrecords : true,  //是否展示总条数
            caption : "反馈数据",
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            colNames : [ 'ID', '管理员用户名', '密码', '状态', '级别'],
            colModel : [
                {name : 'id',width : 55},
                {name : 'username',editable:true,width : 90},
                {name : 'password',editable:true,width : 100,},
                {name : 'status',editable:true,width : 100,},
                {name : 'level',width : 80,editable:true},
                //{name : 'salt',editable:true,width : 150} //editoptions:{value:"男:男生;女:女孩"}


            ]
        }).navGrid("#AdminPage", //开启增删改查操作  指定分页工具栏的位置
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

<%--表格--%>
<table id="AdminTable"/>

<%--分页工具栏--%>
<div id="AdminPage" />


</body>