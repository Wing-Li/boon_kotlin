package com.lyl.boon.net.entity

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class SuperGalleryEntity : BaseEntity() {

    var isEnd: Boolean = false
    var count: Int = 0
    var lastid: Int = 0
    var list: List<ListBean>? = null

    class ListBean : BaseEntity() {
        var id: String? = null
        var imageid: String? = null
        var group_title: String? = null
        var tag: String? = null
        var label: String? = null
        var grpseq: Int = 0
        var cover_imgurl: String? = null
        var cover_height: Int = 0
        var cover_width: Int = 0
        var total_count: Int = 0
        var index: Int = 0
        var qhimg_url: String? = null
        var qhimg_thumb_url: String? = null
        var qhimg_width: Int = 0
        var qhimg_height: Int = 0
        var dsptime: String? = null
    }
}
