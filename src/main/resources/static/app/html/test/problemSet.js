window.onload = function () {
    var cid = _getParameter("cid");
   _getTotalPage(cid);
   _searchProblemSet();
   _getChapter();
}


/**
 * 根据参数名获取url中的对应参数值
 * 支持多参数形式，参数名相同取第一个
 * @param param 参数名
 * @returns
 */
function _getParameter(param)
{
    var query = window.location.href;
    var iLen = param.length;
    var iStart = query.indexOf(param);
    if (iStart == -1)
        return "";
    iStart += iLen + 1;
    var iEnd = query.indexOf("&", iStart);
    if (iEnd == -1)
        return query.substring(iStart);
    return query.substring(iStart, iEnd);
}

function _getTotalPage(cid){
        axios.get('/ProblemSet/getAllProblem',{
                params:{
                    cid:cid
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
                                cid:cid
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
        $('#chapterList1').html(html);
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

}

