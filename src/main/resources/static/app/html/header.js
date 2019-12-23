    document.write("<script type='text/javascript' src='/static/js/jquery-3.3.1.js'></script>");
    //加载初始化数据
    function _initData(){
        _getUserInfo();
        _signout();
    };

    $(window).on('load',function(){
        _initData();
    });

    function _signout(){
        $('#signout').click(function () {
            $.ajax({
                type:'GET',
                url:"/signout",
                success:function(res){
                    if(res.code=="1"){
                        $('#userName').text("请登录");
                    }else{

                    }
                },
                error:function(){
                }
            })
        })
    }
    function _getUserInfo() {
        $.ajax({
            type:'GET',
            url:"/User/getUserInfo",
            success:function(res){
                if(res.code=="1"){
                    var data = res.data;
                    $('#userName').text(data.username);
                }else{
                    $('#userName').text("请登录");
                }
            },
            error:function(){
            }
        });
    }
