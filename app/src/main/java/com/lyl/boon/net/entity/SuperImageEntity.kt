package com.lyl.boon.net.entity

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class SuperImageEntity : BaseEntity() {

    var dspcnt: Int = 0
    var group_title: String? = null
    var website_dspname: String? = null
    var group_pageurl: String? = null
    var isLiving: Boolean = false
    var isSummary: Boolean = false
    var isEnd: Boolean = false
    var lastid: Int = 0
    var count: Int = 0
    var commercial_ads: String? = null
    var list: List<ListBean>? = null

    class ListBean {
        var imageid: String? = null
        var group_id: String? = null
        var pic_url: String? = null
        var pic_pageurl: String? = null
        var pic_height: Int = 0
        var pic_width: Int = 0
        var imgfeature: Int = 0
        var else_query: String? = null
        var pic_size: String? = null
        var pic_title: String? = null
        var pic_desc: String? = null
        var ins_time: String? = null
        var index: String? = null
        var qhimg_url: String? = null
        var qhimg_thumb_url: String? = null
        var qhimg_thumb_width: Int = 0
        var qhimg_thumb_height: Int = 0
        var dsptime: String? = null
        var downurl: String? = null
        var imgurl_dkey: String? = null
    }
}
