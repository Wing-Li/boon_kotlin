package com.lyl.boon.net.entity

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class GankDataEntity : BaseEntity() {

    /**
     * _id : 56e630f0677659174524a187
     * _ns : ganhuo
     * createdAt : 2016-03-14T11:33:04.228Z
     * desc : CircleMenu is a simple, elegant menu with a circular layout.
     * publishedAt : 2016-03-16T11:24:01.505Z
     * source : chrome
     * type : iOS
     * url : https://github.com/Ramotion/circle-menu
     * used : true
     * who : __weak_Point
     */
    var _id: String? = null
    var _ns: String? = null
    var createdAt: String? = null
    var desc: String? = null
    var publishedAt: String? = null
    var source: String? = null
    var type: String? = null
    var url: String? = null
    var isUsed: Boolean = false
    var who: String? = null

}
