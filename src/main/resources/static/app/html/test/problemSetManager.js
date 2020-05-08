window.onload = function () {
   _getTotalPage();
   _searchProblemSet();
   _getChapter();
}


function _getTotalPage(){
        axios.get('/ProblemSet/getAllProblem').then(res => {
                var options={
                    selector: '#page1',
                    recTotal: res.pager.recTotal,
                    recPerPage: res.pager.recPerPage,
                    fetch: function (page) {
                        // 请求数据
                        axios.get('/ProblemSet/getAllProblem',{
                            params:{
                                page:page-1,
                            }
                        }).then(function (response) {
                            var html = template('list_temp', {problemSet:  response.data})
                            $('#problemTable').html(html);
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

function _searchProblemSet(){
    $("#search").click(function () {
        var chapter = $("#chapterList").val();
        var problem = $("#problem").val();
        var problemType = $("#problemType").val();
        var difficulty = $("#difficulty").val();

        axios.get('/ProblemSet/getAllProblem',{
            params:{
                cid:chapter,
                problem:problem,
                problemType:problemType,
                difficulty:difficulty
            }
        }).then(res => {
            var options={
                selector: '#page1',
                recTotal: res.pager.recTotal,
                recPerPage: res.pager.recPerPage,
                fetch: function (page) {
                    // 请求数据
                    axios.get('/ProblemSet/getAllProblem',{
                        params:{
                            page:page-1,
                            cid:chapter,
                            problem:problem,
                            problemType:problemType,
                            difficulty:difficulty
                        }
                    }).then(function (response) {
                        var html = template('list_temp', {problemSet:  response.data})
                        $('#problemTable').html(html);
                        _initBtn();
                    })
                }
            }
            new $.Pagination(options)
        })
            .catch(function (error) {
                console.log("获取数据失败"+error);
            });
    })

}
function _getChapter() {
    axios.get('/getAllChapter',{
    }).then(function (response) {
        var html = template('chapter_list', {chapter:  response})
        $('#chapterList').html(html);
        $('#problemChapter').html(html);
        $('#problemChapter2').html(html);
    })
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

    $(".deleteBtn").click(function () {
        $("#deleteModel").attr("data-id",$(this).parent().parent().children().eq(1).text());
    })

    $("#newProblem").click(function () {
        $("#problemChapter").val("");
        $("#problemType1").val("");
        $("#problemDifficulty").val("");
        $("#problemTitle").val("");
        $("#probmlemScore").val("");
        $("#problemAnswer").val("");
        ue.setContent("");
    })

    $("#saveProblem").click(function () {
        var problemSet ={
            qask: $("#problemTitle").val(),
            qcon:ue.getContent(),
            qanswer:$("#problemAnswer").val(),
            cid:$("#problemChapter").val(),
            qtype:$("#problemType1").val(),
            qlevel:$("#problemDifficulty").val(),
            qscore:$("#problemScore").val(),
        }
        var problemSetList = new Array();
        problemSetList.push(problemSet);
        var data={
            problemSetList:problemSetList,
            operation:"insert"
        }
        $.ajax({
            url:"/ProblemSetManager/saveProblem",
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
                        alert("新增试题成功");
                        //使用  setTimeout（）方法设定版定时2000毫秒
                        window.location.reload();//页面权刷新
                    },2000)
                }
                else{
                    alert("新增试题失败:"+res.data.msg);
                    $(".mark").hide();
                }
            }
        })
    });

    $("#deleteProblem").click(function () {
        var problemSet = {
            qid:$("#deleteModel").attr("data-id")
        }
        var problemSetList = new Array();
        problemSetList.push(problemSet);
        var data={
            problemSetList:problemSetList,
            operation:"delete"
        }
        $.ajax({
            url:"/ProblemSetManager/saveProblem",
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
                        alert("删除试题成功");//使用  setTimeout（）方法设定版定时2000毫秒
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

    $(".modifyBtn").click(function(){
        var qid = $(this).parent().parent().children().eq(1).text();
        $("#modifyModel").attr("data-code",qid);
        axios.get('/ProblemSetManager/getProblemInfo',{params:{
                qid:qid
            }
        }).then(function (response) {
            var chapter = response.cid;
            var type = response.qtype;
            var difficulty = response.qlevel;
            var score = response.qscore;
            var content = response.qcon;
            var ask = response.qask;
            var answer = response.qanswer;

            $("#problemTitle2").val(ask);
            $("#probmlemScore2").val(score);
            $("#problemAnswer2").val(answer);
            $("#problemChapter2 option").each(function () {
                if($(this).attr("value") == chapter){
                    $("#problemChapter2").val(chapter);
                }
            });
            $("#problemType2 option").each(function () {
                if($(this).attr("value") == type){
                    $("#problemType2").val(type);
                }
            });
            $("#problemDifficulty2 option").each(function () {
                if($(this).attr("value") == difficulty){
                    $("#problemDifficulty2").val(difficulty);
                }
            });
            ue2.setContent(content);
        })
    });

    $("#modifyProblem").click(function () {
        var problemSetList = new Array();
        var qid = $("#modifyModel").attr("data-code");
        var cid = $("#problemChapter2").val();
        var qtype = $("#problemType2").val();
        var qlevel = $("#problemDifficulty2").val();
        var qask = $("#problemTitle2").val();
        var qscore = $("#probmlemScore2").val();
        var qcon = ue2.getContent();
        var qanswer = $("#problemAnswer2").val();
        var problemSet = {
            qid:qid,
            cid:cid,
            qtype:qtype,
            qlevel:qlevel,
            qask:qask,
            qscore:qscore,
            qcon:qcon,
            qanswer:qanswer
        }
        problemSetList.push(problemSet);
        var data = {
            problemSetList:problemSetList,
            operation:"modify"
        }
        $.ajax({
            url:"/ProblemSetManager/saveProblem",
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
                        alert("修改试题成功");
                        window.location.reload();//页面权刷新
                    },2000)
                }
                else{
                    alert("修改试题失败:"+res.data.msg);
                    $(".mark").hide();
                }
            }
        })

    })
    $("#deleteproblems").click(function () {
        var problemSetList = new Array();
        $("#problemTable input:checkbox:checked").each(function () {
            var problemSet ={
                qid:$(this).attr("data-code")
            }
            problemSetList.push(problemSet);
        });
        var data={
            problemSetList:problemSetList,
            operation:"delete"
        }
        $.ajax({
            url:"/ProblemSetManager/saveProblem",
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

