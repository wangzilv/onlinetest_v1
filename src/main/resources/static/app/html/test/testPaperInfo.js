window.onload = function () {
    var testid = _getParameter("testid");
    var type = _getParameter("type");
    _getProblemInfo(testid,type);
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

function _getProblemInfo(testid,type) {
    axios.get('/TestPaperManager/getTestPaperInfo',{
        params:{
            testid:testid,
            type:type
        }
    }).then(function (response) {
        if(null!=response&&""!=response){
            console.log(response);
            if("1" == response.code){
                var html = template('list_temp', {testPaperInfo:  response})
                $('#testPaperInfo').html(html);
                _initBtn();
            }else{
                $('#testPaperInfo').html("获取数据失败:"+response);
            }
        }else{
            $('#testPaperInfo').html("未查询到数据");
        }

    })
}

function _initBtn() {
    $(".answer").hide();
    $(".show_btn").click(function () {
        if($(this).hasClass("active")){
            $(this).removeClass("active");
            $(this).next('.answer').hide();
        }else{
            console.log($(this).class);
            $(this).addClass("active");
            $(this).next('.answer').show();
        }
    })
}

