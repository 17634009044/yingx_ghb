<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>



<script>
    $(function(){ //延迟加载
        //初始化jqGrid
        $("#VideoTable").jqGrid({
            url : "${path}/video/queryPage",
            editurl:"${path}/video/edits",   //指定增删改操作的url   添加oper=add  修改oper=edit  删除oper=del
            datatype : "json",
            mtype : "post",
            rowNum : 3,
            rowList : [ 3,5,10, 20, 30 ],
            pager : '#VideoPage',
            viewrecords : true,  //是否展示总条数
            caption : "视频列表",
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            colNames : [ 'ID', '标题', '简介', '封面链接', '视频链接','发布时间','所属类别','分组ID','用户ID'],
            colModel : [
                {name : 'id',width : 55},
                {name : 'title',editable:true,width : 90},
                {name : 'description',editable:true,width : 100},
                {name : 'cover_path',width : 80,hidden:true
                    /*formatter:function (cellvalue,row,options){

                        return "<img src='"+cellvalue+"'  height='100px' width='100%' poster='"+row.cover_path+"'/>";
                    }*/
                },
                {name : 'video_path',editable:true,width : 80,edittype:"file",
                    formatter:function (cellvalue,options,row){
                        return "<video src='"+cellvalue+"' width='200px' height='100px' controls poster='"+row.cover_path+"'/>";
                    }

                },

                {name : 'upload_time',width : 80},
                {name : 'category.cate_name',editable:true,width : 80},
                {name : 'group_id',editable:true,width : 150},
                {name : 'user_id',editable:true,width : 150}

            ]
        }).navGrid("#VideoPage", //开启增删改查操作  指定分页工具栏的位置
            {edit:true,edittext:"修改",del:true,deltext:"删除",add:true,addtext:"添加",search:false,searchtext:"搜索"},  //是否展示增删改查按钮  {},都展示
            {
                closeAfterEdit:true,
                afterSubmit:function (data) {
                    //console.log(data.responseText)添加数据返回的ID
                    //文件上传 异步上传
                    console.log(data)

                    $.ajaxFileUpload({
                        url: "${path}/video/fileUpload",
                        type:"post",
                        dataType:"json",
                        data:{"id":data.responseJSON.id},
                        fileElementId:"video_path",     //需要上传文件的ID  即<input>的ID
                        success:function (data){
                            //文件上传成功返回
                            $("#sId").text(data.message);
                            $("#VideoTable").trigger("reloadGrid");
                        }
                    });
                    return "hello"; //必须要有一个返回值
                }
            },  //修改之后的额外操作
            {   //在执行添加成功之后进入该括号执行
                closeAfterAdd:true,     //执行成功之后关闭对话框
                //1.添加数据
                //2.在数据添加之后文件上传 异步上传
                afterSubmit:function (data) {
                    //console.log(data.responseText)添加数据返回的ID
                    //文件上传 异步上传
                    console.log(data)

                    $.ajaxFileUpload({
                        url: "${path}/video/fileUpload",
                        type:"post",
                        dataType:"json",
                        data:{"id":data.responseJSON.id},
                        fileElementId:"video_path",     //需要上传文件的ID  即<input>的ID
                        success:function (data){
                            //文件上传成功返回
                            $("#sId").text(data.message);
                            $("#VideoTable").trigger("reloadGrid");
                        }
                    });
                    return "hello"; //必须要有一个返回值
                }


            },  //添加之后的额外操作
            {
                closeAfterDel: true,
                afterSubmit:function () {
                    $("#VideoTable").trigger("reloadGrid");
                    return "hello"; //必须要有一个返回值
                }
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
    <div class="panel panel-heading"><strong>视频数据</strong></div>
        <!--标签页-->
        <div>
            <!-- 标签页选项卡 -->
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">视频信息表</a></li>
            </ul>
            <span style="color:red;" id="sId"></span>
        </div>+
        <%--表格--%>
         <table id="VideoTable"/>

         <%--分页工具栏--%>
        <div id="VideoPage" />
    </div>
</div>


</body>