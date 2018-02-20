package com.lyl.boon.net.api;


import com.lyl.boon.net.entity.ZhuangbiEntity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import rx.Observable
import java.util.List

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
interface ZhuangbiApi {

    @GET("search")
    fun search(@Query("q") id: String): Observable<List<ZhuangbiEntity>>

    @GET
    fun downloadFileWithDynamicUrlSync(@Url filrUrl: String): Call<ResponseBody>
}
