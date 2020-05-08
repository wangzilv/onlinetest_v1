window.onload = function () {
   _getTotalPage()
}

function _getTotalPage(){
    axios.get('/ChapterManager/getAllChapterByPage').then(res => {
        var options={
            selector: '#page1',
            recTotal: res.pager.recTotal,
            recPerPage: res.pager.recPerPage,
            fetch: function (page) {
                // 请求数据
                axios.get('/ChapterManager/getAllChapterByPage',{
                    params:{
                        page:page-1,
                    }
                }).then(function (response) {
                    var html = template('chapter_list', {chapter:  response.data})
                    $('#chapterTable').html(html);
                    _initBtn();
                })
            }
        }
        new $.Pagination(options)
    })
        .catch(function (error) {
            console.log("获取数据失败"+error);
        });
}

function _initBtn() {
    var cks = $(":checkbox:gt(0)");
    var fk = $(":checkbox:first").click(function(){
        cks.prop("checked", $(this).prop("checked"));
    });
    cks.click(function(){
        if(!$(this).prop("checked")){
            fk.prop("checked",false);
        }else{
            if(cks.filter(":not(:checked)").length == 0){
                fk.prop("checked",true);
            }
        }
    });

    $(".modifyBtn").click(function () {
        $("#modifyModel").attr("data-id",$(this).parent().parent().children().eq(1).text());
        $("#chapterTitle").val($(this).parent().parent().children().eq(3).text());
        $("#chapterName").val($(this).parent().parent().children().eq(2).text())
    })
    $(".deleteBtn").click(function () {
        $("#deleteModel").attr("data-id",$(this).parent().parent().children().eq(1).text());
    })

    $("#modifyChapter").click(function () {
        var chapter = {
            cid:$("#modifyModel").attr("data-id"),
            cname:$("#chapterName").val(),
            ctitle:$("#chapterTitle").val()
        }
        var chapterList = new Array();
        chapterList.push(chapter);
        var data={
            chapterList:chapterList,
            operation:"modify"
        }
        $.ajax({
            url:"/ChapterManager/saveChapter",
            type:"post",
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(data),
            beforeSend:function(XMLHttpRequest){
                $(".mark").show();
            },
            success:function (res) {
                if('1' == res.data.code){
                    alert("修改章节成功");
                    setTimeout(function(){  //使用  setTimeout（）方法设定版定时2000毫秒
                        window.location.reload();//页面权刷新
                    },2000)
                }
                else{
                    alert("修改章节失败:"+res.data.msg);
                    $(".mark").hide();
                }
            }
        })
    });

    $("#saveChapter").click(function () {
        var chapter ={
            ctitle: $("#chapterTitle1").val(),
            cname:$("#chapterName1").val(),
        }
        var chapterList = new Array();
        chapterList.push(chapter);
        var data={
            chapterList:chapterList,
            operation:"insert"
        }
        $.ajax({
            url:"/ChapterManager/saveChapter",
            type:"post",
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(data),
            beforeSend:function(XMLHttpRequest){
                $(".mark").show();
            },
            success:function (res) {
                if('1' == res.data.code){
                    alert("新增章节成功");
                    setTimeout(function(){  //使用  setTimeout（）方法设定版定时2000毫秒
                        window.location.reload();//页面权刷新
                    },2000)
                }
                else{
                    alert("新增章节失败:"+res.data.msg);
                    $(".mark").hide();
                }
            }
        })
    });
    $("#deleteChapter").click(function () {
        var chapter = {
            cid:$("#deleteModel").attr("data-id")
        }
        var chapterList = new Array();
        chapterList.push(chapter);
        var data={
            chapterList:chapterList,
            operation:"delete"
        }
        $.ajax({
            url:"/ChapterManager/saveChapter",
            type:"post",
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(data),
            beforeSend:function(XMLHttpRequest){
                $(".mark").show();
            },
            success:function (res) {
                if('1' == res.data.code){
                    alert("删除章节成功");
                    setTimeout(function(){  //使用  setTimeout（）方法设定版定时2000毫秒
                        window.location.reload();//页面权刷新
                    },2000)
                }
                else{
                    alert("删除章节失败:"+res.data.msg);
                    $(".mark").hide();
                }
            }
        })
    });
    $("#deleteChapters").click(function () {
        var chapterList = new Array();
        $("#chapterTable input:checkbox:checked").each(function () {
            var chapter ={
                cid:$(this).attr("data-code")
            }
            chapterList.push(chapter);
        });
        var data={
            chapterList:chapterList,
            operation:"delete"
        }
        $.ajax({
            url:"/ChapterManager/saveChapter",
            type:"post",
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(data),
            beforeSend:function(XMLHttpRequest){
                $(".mark").show();
            },
            success:function (res) {
                if('1' == res.data.code){
                    setTimeout(function(){
                        alert("删除章节成功");//使用  setTimeout（）方法设定版定时2000毫秒
                        window.location.reload();//页面权刷新
                    },2000)
                }
                else{
                    alert("删除章节失败:"+res.data.msg);
                    $(".mark").hide();
                }
            }
        })
    });
}