(function ($) {
    class Pagination {
        constructor (options) {
            var totalPage = Math.ceil(options.recTotal/options.recPerPage);
            var pageLength;
            if(totalPage<10){
                pageLength = totalPage;
            }else{
                pageLength = 10;
            }
            this.page = {
                currPage:1, //当前页面，初始化为1
                recTotal:options.recTotal,//数据总数
                recPerPage:options.recPerPage,//每页数据数
                totalPage:totalPage,//总页面，通过计算获得
                pageLength:pageLength,//按钮数量，可以自己把控
                startPage:1,//当前显示的第一个按钮的值
                endPage:10//当前显示的最后一个按钮的值
            }
            this.selector = options.selector;
            this.fetch = options.fetch || (() => {});
            this.init()
        }
        init () {
            var _this = this;
            //这里假设由异步返回了信息，然后我们计算出了页数
            // this.page.totalPage = 17; //假设是17页
            this.fetch(this.page.currPage);
            //初始化按钮，即第一次网页面上添加按钮
            this.addButtons(this.page.startPage , this.page.pageLength);
            $("" + this.selector + " .page-btn").first().addClass("curr-page");
            //禁用"上一页"按钮，因为默认是第一页，第一页没有上一页
            this.isDisabled(this.page.currPage,this.page.totalPage);

            //绑定“上一页”按钮的点击事件
            $(document).on("click","" + this.selector + " .prev",function () {
                _this.page.currPage =  parseInt(_this.page.currPage) - 1 ;
                _this.pageIteration(_this.page.currPage);
                _this.isDisabled(_this.page.currPage,_this.page.totalPage);
            })

            //绑定“下一页”按钮点击事件
            $(document).on("click","" + this.selector + " .next",function () {
                _this.page.currPage =  parseInt(_this.page.currPage) + 1 ;
                _this.pageIteration(_this.page.currPage);
                _this.isDisabled(_this.page.currPage,_this.page.totalPage);
            })

            //绑定“页码”按钮的点击事件
            $(document).on("click","" + this.selector + " .page-btn",function () {
                var $this = $(this);
                _this.page.currPage = parseInt($this.text());
                _this.pageIteration(_this.page.currPage);
                _this.isDisabled(_this.page.currPage,_this.page.totalPage);
            })
        }
        addButtons(startPage,pageLength){
            $("" + this.selector + "").html(" ");
            var _html = "<center><button class='btn btn-default prev' ><<上一页</button> ";
            for(var i = startPage ; i < startPage + pageLength ; i++){
                _html += "<button class='btn btn-default page-btn' data-pageno="+i+">"+ i +"</button> ";
            }
            _html += " <button class='btn btn-default next'>下一页>></button></center>";
            $("" + this.selector + "").html(_html);
        }
        isDisabled(pagenum,totalpage){
            if(totalpage ==0 || totalpage ==1){
                $("" + this.selector + " .prev").attr("disabled",true);
                $("" + this.selector + " .next").attr("disabled",true);
            }else if(pagenum ==1){
                $("" + this.selector + " .prev").attr("disabled",true);
                $("" + this.selector + " .next").removeAttr("disabled");
            }else if(pagenum == totalpage){
                $("" + this.selector + " .next").attr("disabled",true);
                $("" + this.selector + " .prev").removeAttr("disabled");
            }else{
                $("" + this.selector + " .next").removeAttr("disabled");
                $("" + this.selector + " .prev").removeAttr("disabled");
            }
        }
        pageIteration(currPage){
            if(this.page.currPage != 1 && this.page.currPage == this.page.startPage ){
                this.page.startPage = this.page.currPage - this.page.pageLength/2 ;
                this.page.endPage = this.page.currPage + this.page.pageLength/2 -1;
                this.addButtons(this.page.startPage , this.page.pageLength);
                $("" + this.selector + " .page-btn").eq(this.page.pageLength/2).addClass("curr-page");
            }else if(this.page.currPage != this.page.totalPage && this.page.currPage == this.page.endPage){
                this.page.startPage = this.page.currPage - this.page.pageLength/2  + 1;
                this.page.endPage = (this.page.currPage + this.page.pageLength/2) >= this.page.totalPage ?  this.page.totalPage : (this.page.currPage + this.page.pageLength/2);
                this.addButtons(this.page.startPage , this.page.pageLength /2 + (this.page.endPage == this.page.totalPage ? (this.page.endPage - this.page.currPage) : this.page.pageLength/2));
                $("" + this.selector + " .page-btn").eq(this.page.pageLength/2 -1).addClass("curr-page");
            }
            $("" + this.selector + " .page-btn").removeClass("curr-page").parent().find("[data-pageno="+currPage+"]").addClass("curr-page");
            this.fetch(currPage)
        }
    };
    $.Pagination = Pagination
})(jQuery)