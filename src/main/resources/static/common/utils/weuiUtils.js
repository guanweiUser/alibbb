
//每页几条
var limit = 10;
//当前页
var offset = 0;

//总条数
var total = 0;


/**
 * GuanWeiMail@163.com
 * @type {{startPager: WeuiUtils.startPager}}
 */
var WeuiUtils = {

    /**
     * 设置分页
     * <!-- 分页 -->
     <div id="weui_pager" style="margin-bottom: 88px;margin-top: 20px;">
     需要添加这个元素
     </div>
     */
    startPager : function (listMethods) {
        //添加html
        $('#weui_pager').html(`<div class="pager">
                         <div class="pager-left" style="background-color: rgb(246, 244, 236);">
                             <div class="pager-first" ><a href="#" class="pager-nav" pager="first">首页</a></div>
                             <div class="pager-pre"  ><a href="#" class="pager-nav" pager="pre">上一页</a></div>
                         </div>
                         <div class="pager-cen" id="pager-cen">*/*</div>
                         <div class="pager-right" style="background-color: rgb(246, 244, 236);">
                             <div class="pager-next"  ><a href="#" class="pager-nav" pager="next">下一页</a></div>
                             <div class="pager-end" ><a href="#" class="pager-nav" pager="end">尾页</a></div>
                         </div>
                     </div>`);


        //分页添加事件
        $('.pager-nav').click(function () {
            //$('#searchInputUserName').val('');

            var pager = $(this).attr('pager');

            switch (pager) {
                case 'next':	//下一页
                    var sumOffset = 0;
                    if(total){
                        sumOffset = Math.ceil(total / limit) - 1;
                    }

                    if(offset < sumOffset){
                        offset = offset + 1;
                    }else{
                        $.alert('已经是最后一页了!');
                        return false;
                    }
                    break;
                case 'pre':		//上一页
                    if (offset >= 1) {
                        offset = offset - 1;
                    } else {
                        $.alert('已经是第一页了!');
                        return false;
                    }
                    break;
                case 'first':
                    offset = 0;
                    break;
                case 'end': //尾页
                    offset = 0;
                    if(total){
                        offset = Math.ceil(total / limit) - 1;
                    }
                    break;
            }
            // $('#searchBar').attr('class', 'weui-search-bar weui-search-bar_focusing');

            //回调方法
            listMethods();

        })


    },
    setPagerCen: function(){    //设置页数显示

        // 根据arguments.length，对不同的值进行不同的操作
        switch(arguments.length) {
            case 0:
                /*操作1的代码写在这里*/
                break;
            case 1:
                total = arguments[0];
                /*操作2的代码写在这里*/
                break;
            case 2:
                offset = arguments[0];
                total = arguments[1];
            /*操作3的代码写在这里*/

            //后面还有很多的case......
        }

        var totalStr = '*';
        if(total){
            totalStr = Math.ceil(total / limit);
        }
        //调整页数
        $('#pager-cen').text((offset+1) + '/' + totalStr);
    }

}