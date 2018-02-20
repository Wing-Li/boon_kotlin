package com.lyl.boon.net.api

import com.lyl.boon.net.entity.BaseGankEntity
import com.lyl.boon.net.entity.GankDataEntity

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
interface GankApi {

    //    分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
    //
    //    数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
    //    请求个数： 数字，大于0
    //    第几页：数字，大于0
    @GET("data/{type}/20/{page}")
    fun getGankList(@Path("type") type: String, @Path("page") page: Int): Observable<BaseGankEntity<List<GankDataEntity>>>


    //    每日数据： http://gank.io/api/day/年/月/日
    //
    //    例：
    //    http://gank.io/api/day/2015/08/06


    //    随机数据：http://gank.io/api/random/data/分类/个数
    //
    //    数据类型：福利 | Android | iOS | 休息视频 | 拓展资源 | 前端
    //    个数： 数字，大于0
    //    例：
    //    http://gank.io/api/random/data/Android/20
}
