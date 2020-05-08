window.onload = function () {
    var qid = _getParameter("qid");
    _getProblemInfo(qid);
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

function _getProblemInfo(qid) {
    axios.get('/ProblemSet/getProblemInfo',{
        params:{
            qid:qid
        }
    }).then(function (response) {
        var html = template('list_temp', {probleminfo:  response})
        $('#problemInfo').html(html);
        _initBtn();
    })
}
function _initBtn(){
    $('#answer').hide();
    $('#show_btn').click(function () {
        if($(this).hasClass("active")){
            $(this).removeClass("active");
            $('#answer').hide();
        }else{
            console.log($(this).class);
            $(this).addClass("active");
            $('#answer').show();
        }
    })
}
