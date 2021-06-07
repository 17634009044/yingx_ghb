<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>



<script>
    $(function(){ //延迟加载
        //初始化jqGrid
        $("#UserTable").jqGrid({
            url : "${path}/user/queryAll",
            editurl:"${path}/user/edits",   //指定增删改操作的url   添加oper=add  修改oper=edit  删除oper=del
            datatype : "json",
            mtype : "post",
            rowNum : 3,
            rowList : [ 3,5,10, 20, 30 ],
            pager : '#UserPage',
            viewrecords : true,  //是否展示总条数
            caption : "用户列表",
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            colNames : [ 'ID', '手机号', '头像', '网名', '个性签名','绑定微信','状态','注册时间'],
            colModel : [
                {name : 'id',width : 55},
                {name : 'phone',editable:true,width : 90},
                {name : 'head_img',editable:true,width : 100,edittype:"file",
                    formatter:function (cellvalue){
                        return "<img src='${path}/upload/headImg/"+cellvalue+"' width=200px height=100px'>";
                    }

                },
                {name : 'name',editable:true,width : 80},
                {name : 'sign',editable:true,width : 80},
                {name : 'wechat',width : 80},
                {name : 'status',width : 80,
                    formatter:function (cellvalue, options, rowObject){
                        //console.log(rowObject.status);
                        if(cellvalue==1){
                            //状态:0 冻结   button  解除冻结  红色
                            return "<button class='btn btn-info' onclick='updateStatus(\""+rowObject.id+"\",\""+rowObject.status+"\")'>正常</button>";
                        }else{
                            //状态1  正常    button  冻结   绿色
                            return "<button class='btn btn-danger' onclick='updateStatus(\""+rowObject.id+"\",\""+rowObject.status+"\")'>冻结</button>";
                        }
                    }
                },
                {name : 'regist_time',width : 150,edittype:"select"}, //editoptions:{value:"男:男生;女:女孩"},


            ]
        }).navGrid("#UserPage", //开启增删改查操作  指定分页工具栏的位置
            {edit:true,edittext:"修改",del:true,deltext:"删除",add:true,addtext:"添加",search:true,searchtext:"搜索"},  //是否展示增删改查按钮  {},都展示
            {
                closeAfterEdit:true
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
                        url: "${path}/user/fileUpload",
                        type:"post",
                        dataType:"json",
                        data:{"userId":data.responseText},
                        fileElementId:"head_img",     //需要上传文件的ID  即<input>的ID
                        success:function (data){
                            //文件上传成功返回
                            $("#sId").text(data.message);
                        }
                    });
                    return "hello"; //必须要有一个返回值
                }


            },  //添加之后的额外操作
            {
                closeAfterDel: true
            }   //删除之后的额外操作
        );

        //开启增删改查操作 指定分页工具栏的位置
        /*$("#empTable").jqGrid("navGrid","#empPage",{},{},{},{}); */

    });


    //点击状态按钮修改状态
    function updateStatus(id,status) {
        /*alert(id);
        alert(status);*/
        if(status==1){
            $.get("${path}/user/updateStatus",{"id":id,"status":"0"},function () {
                $("#UserTable").trigger("reloadGrid");
            })
        }else{
            $.get("${path}/user/updateStatus",{"id":id,"status":"1"},function () {
                $("#UserTable").trigger("reloadGrid");
            })
        }
    }
</script>


<title>这是主页面</title>
</head>
<body>
<!--面板-->
<div class="panel panel-info">
    <!--面板标题-->
    <div class="panel panel-heading"><strong>用户数据</strong></div>
        <!--标签页-->
        <div>
            <!-- 标签页选项卡 -->
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">用户信息表</a></li>
            </ul>
            <span style="color:red;" id="sId"></span>
        </div>
    <!--面板内容-->
    <div class="panel panel-body" align="left">
        <button class="btn btn-info">导入用户信息</button>&emsp;&emsp;
        <a class="btn btn-danger" href="${path}/excel/exportUser">导出用户信息</a>
    </div>

        <%--表格--%>
         <table id="UserTable"/>

         <%--分页工具栏--%>
        <div id="UserPage" />
    </div>
</div>


</body>