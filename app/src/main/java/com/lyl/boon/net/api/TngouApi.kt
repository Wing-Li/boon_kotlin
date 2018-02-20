package com.lyl.boon.net.api

import com.lyl.boon.net.entity.SuperGalleryEntity
import com.lyl.boon.net.entity.SuperImageEntity

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
interface TngouApi {

    /**
     * http://image.so.com/zj?ch=beauty&t1=596&sn=30&listtype=new&temp=1
     * 取得 图库 列表，也可以用分类id作为参数
     */
    @GET("zj?ch=beauty&listtype=new&temp=1")
    fun getGalleryList(@Query("t1") menu: Int, @Query("sn") count: Int): Observable<SuperGalleryEntity>

    /**
     * http://image.so.com/zvj?ch=beauty&t1=596&id=77455f9644cda030c8e2a3a6135c0ca9
     * 取得热点图片详情，通过热点id取得该对应详细内容信息
     */
    @GET("zvj?ch=beauty")
    fun getGalleryInfo(@Query("t1") menu: Int, @Query("id") id: String): Observable<SuperImageEntity>

}
