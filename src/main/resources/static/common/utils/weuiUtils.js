
//每页几条
var limit = 10;
//当前页
var offset = 0;


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
                             <div class="pager-first" ><a class="pager-nav" pager="first">首页</a></div>
                             <div class="pager-pre"  ><a class="pager-nav" pager="pre">上一页</a></div>
                         </div>
                         <div class="pager-cen" id="pager-cen">1/120</div>
                         <div class="pager-right" style="background-color: rgb(246, 244, 236);">
                             <div class="pager-next"  ><a class="pager-nav" pager="next">下一页</a></div>
                             <div class="pager-end" ><a class="pager-nav" pager="end">尾页</a></div>
                         </div>
                     </div>`);


        //分页添加事件
        $('.pager-nav').click(function () {
            //$('#searchInputUserName').val('');

            var pager = $(this).attr('pager');

            switch (pager) {
                case 'next':	//下一页
                    offset = offset + 1;
                    break;
                case 'pre':		//上一页
                    if (offset >= 1) {
                        offset = offset - 1;
                    } else {
                        return false;
                    }
                    break;
                case 'first':
                    offset = 0;
                    break;
                case 'end':

                    break;
            }
            // $('#searchBar').attr('class', 'weui-search-bar weui-search-bar_focusing');

            listMethods();
            //调整页数
            $('#pager-cen').text((offset+1) + '/' + '120');
        })


    }

}