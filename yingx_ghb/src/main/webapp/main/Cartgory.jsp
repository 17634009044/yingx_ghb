<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    $(function(){
        pageInit();
    });

    //设置表格的参数  父表格
    function pageInit(){
        jQuery("#CateTable").jqGrid(
            {
                url : "${path}/category/queryPageOne",
                editurl:"${path}/category/edits",
                datatype : "json",
                mtype : "post",
                rowNum : 3,
                rowList : [ 3,5,10, 20, 30 ],
                pager : '#CatePage',
                viewrecords : true,  //是否展示总条数
                caption : "类别列表",
                styleUI:"Bootstrap",
                autowidth:true,
                height:"auto",
                subGrid:true,
                colNames : [ 'ID', '类别名', '级别', '父类别ID'],
                colModel : [
                    {name : 'id',width : 55},
                    {name : 'cate_name',editable:true,width : 90},
                    {name : 'levels',width : 100},
                    {name : 'parent_id',width : 80,hidden:true},

                ],



                /*
                *  subgrid_id:创建表数据时的Div的Id
                *   row_id:该行的id
                * */
                //当点击左侧黑色三角  表示要展示一级类别下的二级类别时触发
                subGridRowExpanded : function(subgrid_id, row_id) {
                    addSubGrid(subgrid_id, row_id);
                }
            })
        //表格的分页工具栏 父表格
        //jQuery("#CateTable").jqGrid('navGrid', '#CatePage', {add: true, edit: true, del: true,search:false});
            .navGrid("#CatePage", //开启增删改查操作  指定分页工具栏的位置
                {edit:true,edittext:"修改",del:true,deltext:"删除",add:true,addtext:"添加",search:true,searchtext:"搜索"},  //是否展示增删改查按钮  {},都展示
                {
                    closeAfterEdit:true
                },  //修改之后的额外操作
                {   //在执行添加成功之后进入该括号执行
                    closeAfterAdd:true,     //执行成功之后关闭对话框
                },  //添加之后的额外操作
                {
                    closeAfterDel: true
                }   //删除之后的额外操作
            );
    }


    //初始化子表格的方法
    function addSubGrid(subgrid_id, rowId){
        var subgrid_table_id, pager_id;
        subgrid_table_id = subgrid_id + "_t";
        pager_id = "p_" + subgrid_table_id;

        $("#" + subgrid_id).html("<table id='" + subgrid_table_id + "' class='scroll'></table><div id='" + pager_id + "' class='scroll'></div>");


        jQuery("#" + subgrid_table_id).jqGrid(
            {
                url : "${path}/category/queryPageTwo?id="+rowId,
                editurl:"${path}/category/edits?parentId="+rowId,
                datatype : "json",
                mtype : "post",
                rowNum : 3,
                rowList : [ 3,5,10, 20, 30 ],
                pager : '#CatePage',
                viewrecords : true,  //是否展示总条数
                caption : "类别列表",
                styleUI:"Bootstrap",
                autowidth:true,
                height:"auto",
                subGrid:true,
                colNames : [ 'ID', '类别名', '级别', '父类别ID'],
                colModel : [
                    {name : 'id',width : 55},
                    {name : 'cate_name',editable:true,width : 90},
                    {name : 'levels',width : 100},
                    {name : 'parent_id',width : 80},

                ],
                styleUI:"Bootstrap",
                rowNum : 20,
                pager : pager_id,
                sortname : 'num',
                sortorder : "asc",

                autowidth:true,
                height:"auto",
            })

        //设置子表格的分页工具栏
        //jQuery("#" + subgrid_table_id).jqGrid('navGrid', "#" + pager_id, {edit : true, add : true, del : true, search: false});
    .navGrid("#" + pager_id, //开启增删改查操作  指定分页工具栏的位置
            {edit:true,edittext:"修改",del:true,deltext:"删除",add:true,addtext:"添加",search:true,searchtext:"搜索"},  //是否展示增删改查按钮  {},都展示
            {
                closeAfterEdit:true
            },  //修改之后的额外操作
            {   //在执行添加成功之后进入该括号执行
                closeAfterAdd:true,     //执行成功之后关闭对话框
            },  //添加之后的额外操作
            {
                closeAfterDel: true
            }   //删除之后的额外操作
        );

    }
</script>
<title>这是主页面</title>
</head>
<body>
<!--面板-->
<div class="panel panel-info">
    <!--面板标题-->
    <div class="panel panel-heading"><strong>类别数据</strong></div>
        <!--标签页-->
        <div>
            <!-- 标签页选项卡 -->
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">类别信息表</a></li>
            </ul>
        </div>
    <%--<!--面板内容-->
    <div class="panel panel-body" align="left">
        <button class="btn btn-info">导入用户信息</button>&emsp;&emsp;
        <button class="btn btn-danger">导出用户信息</button>
    </div>--%>

        <%--表格--%>
         <table id="CateTable"/>

         <%--分页工具栏--%>
        <div id="CatePage" />
    </div>
</div>


</body>