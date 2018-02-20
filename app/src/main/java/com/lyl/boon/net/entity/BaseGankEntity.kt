package com.lyl.boon.net.entity

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class BaseGankEntity<T> : BaseEntity() {
    var isError: Boolean = false
    var results: T? = null
    var category: List<String>? = null

}
