window.onload = function () {
    _initBtn();
    _getTotalPage();
}
function _initBtn() {
    $(".deleteBtn").unbind();
    $(".deleteBtn").click(function () {
        $("#deleteModel").attr("data-id",$(this).parent().parent().children().eq(1).text());
    })
    $(".modifyBtn").unbind();
    $(".modifyBtn").click(function(){
        var testid = $(this).parent().parent().children().eq(1).text();
        var type = $(this).parent().parent().children().eq(1).attr("data-type")
        $("#modifyModel").attr("data-code",testid);
        axios.get('/TestPaperManager/getTestPaperInfo',{params:{
                testid:testid,
                type:type
            }
        }).then(function (response) {
            if("1" == type){
                $("#testtitle").val(response.testPaper.testtitle);
                $("#testremark").val(response.testPaper.testremark);
                $("#testcon").val(response.testPaper.testdesc);
            }else if("2" == type){
                $("#testtitle").val(response.testPaper.testtitle);
                $("#testremark").val(response.testPaper.testremark);
                // $("#testcon").show();
                // ue.setContent(response.testPaper.testdesc);
            }
        })
    });
    $("#modifyTestPaper").unbind();
    $("#modifyTestPaper").click(function () {
        var testPaperList = new Array();
        var testid = $("#modifyModel").attr("data-code");
        var testtitle = $("#testtitle").val();
        var testremark = $("#testremark").val();
        var testPaper = {
            testid:testid,
            testtitle:testtitle,
            testremark:testremark
        }
        testPaperList.push(testPaper);
        var data = {
            testPaperList:testPaperList,
            operation:"modify"
        }
        $.ajax({
            url:"/TestPaperManager/saveTestPaper",
            type:"post",
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(data),
            beforeSend:function(XMLHttpRequest){
                $(".mark").show();
            },
            success:function (res) {
                if('1' == res.data.code){
                    setTimeout(function(){  //使用  setTimeout（）方法设定版定时2000毫秒
                        alert("修改试卷成功");
                        window.location.reload();//页面权刷新
                    },2000)
                }
                else{
                    alert("修改试卷失败:"+res.data.msg);
                    $(".mark").hide();
                }
            }
        })

    })

    $("#deleteTestPaper").unbind();
    $("#deleteTestPaper").click(function () {
        var testPaper = {
            testid:$("#deleteModel").attr("data-id")
        }
        var testPaperList = new Array();
        testPaperList.push(testPaper);
        var data={
            testPaperList:testPaperList,
            operation:"delete"
        }
        $.ajax({
            url:"/TestPaperManager/saveTestPaper",
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
                        alert("删除试卷成功");//使用  setTimeout（）方法设定版定时2000毫秒
                        window.location.reload();//页面权刷新
                    },2000)
                }
                else{
                    alert("删除试卷失败:"+res.data.msg);
                    $(".mark").hide();
                }
            }
        })
    });
    $("#deleteTestPapers").unbind();
    $("#deleteTestPapers").click(function () {
        var testPaperList = new Array();
        $("#testPaperTable input:checkbox:checked").each(function () {
            var testPaper ={
                testid:$(this).attr("data-code")
            }
            testPaperList.push(testPaper);
        });
        var data={
            testPaperList:testPaperList,
            operation:"delete"
        }
        $.ajax({
            url:"/TestPaperManager/saveTestPaper",
            type:"post",
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(data),
            beforeSend:function(XMLHttpRequest){
                $(".mark").show();
            },
            success:function (res) {
                if('1' == res.data.code){
                    setTimeout(function(){  //使用  setTimeout（）方法设定版定时2000毫秒
                        alert("删除试题成功");
                        window.location.reload();//页面权刷新
                    },2000)
                }
                else{
                    alert("删除试题失败:"+res.data.msg);
                    $(".mark").hide();
                }
            }
        })
    });
}

function _getTotalPage(){
    axios.get('/TestPaperManager/getAllTestPaper').then(res => {
        var options={
            selector: '#page1',
            recTotal: res.pager.recTotal,
            recPerPage: res.pager.recPerPage,
            fetch: function (page) {
                // 请求数据
                axios.get('/TestPaperManager/getAllTestPaper',{
                    params:{
                        page:page-1,
                    }
                }).then(function (response) {
                    var html = template('list_temp', {testPaper:  response.data})
                    $('#testPaperTable').html(html);
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