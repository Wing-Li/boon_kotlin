package com.lyl.boon.utils

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.lyl.boon.R
import java.io.File

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
object ImgUtils {

    private val placeholderRes = R.drawable.bg_gary
    private val errorRes = R.drawable.error_img

    private val baseOptions = RequestOptions()//
            .placeholder(placeholderRes)//
            .error(errorRes)//
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

    //    DiskCacheStrategy.NONE 什么都不缓存
    //    DiskCacheStrategy.DATA 仅仅只缓存原来的全分辨率的图像。
    //    DiskCacheStrategy.RESOURCE 仅仅缓存最终的图像，即，降低分辨率后的（或者是转换后的）
    //    DiskCacheStrategy.ALL 缓存所有版本的图像
    //    DiskCacheStrategy.AUTOMATIC: 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。

    /**
     * @param context
     * @param url       图片的地址
     * @param imageView ImageView
     * @param thumbnail 简单的缩略图:0.1f 作为参数，Glide 将会显示原始图像的10%的大小
     */
    fun load(context: Context, url: String, imageView: ImageView, thumbnail: Float) {
        // 加载GIF慢
        // https://github.com/bumptech/glide/issues/513#issuecomment-117690923、
        //        Glide glide = Glide.get(context);
        //        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(Network.httpClient);
        //
        //        glide.register(GlideUrl.class, InputStream.class, factory);
        Glide.with(context).load(url).apply(baseOptions).thumbnail(thumbnail).into(imageView)
    }

    fun load(context: Context, url: Int, imageView: ImageView) {
        Glide.with(context).load(url).apply(baseOptions).into(imageView)
    }

    fun load(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).apply(baseOptions).into(imageView)
    }

    fun load(context: Context, url: String, imageView: ImageView, w: Int, h: Int) {
        Glide.with(context).load(url).apply(baseOptions).apply(RequestOptions().override(w, h)).into(imageView)
    }

    /**
     * 加载圆形图片。
     */
    fun loadCircle(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).apply(baseOptions).apply(RequestOptions.circleCropTransform()).into(imageView)
    }

    /**
     * 加载圆形图片。加载 uri ，一般用来加载本地图片
     */
    fun loadCircle(context: Context, uri: Uri, imageView: ImageView) {
        Glide.with(context).load(uri).apply(baseOptions).apply(RequestOptions.circleCropTransform()).into(imageView)
    }

    /**
     * 下载图片
     *
     * @param context
     * @param url
     * @param downloadImage
     */
    fun downloadImg(context: Context, url: String, downloadImage: DownloadImage) {
        Thread(Runnable {
            try {
                val target = Glide.with(context)//
                        .asFile()//
                        .load(url)//
                        .submit()
                val file = target.get()

                val handler = Handler(context.mainLooper)
                handler.post {
                    if (file != null && file.exists()) {
                        downloadImage.downloadImage(file)
                    } else {
                        downloadImage.downloadImage(null)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }).start()
    }

    interface DownloadImage {
        fun downloadImage(imgFile: File?)
    }

    /**
     * 释放内存
     */
    fun clearMemory(context: Context) {
        Glide.get(context).clearMemory()
    }

    /**
     * 取消所有正在下载或等待下载的任务。
     */
    fun cancelAllTasks(context: Context) {
        Glide.with(context).pauseRequests()
    }

    /**
     * 恢复所有任务
     */
    fun resumeAllTasks(context: Context) {
        Glide.with(context).resumeRequests()
    }

    /**
     * 清除磁盘缓存
     */
    fun clearDiskCache(context: Context) {
        Thread(Runnable { Glide.get(context).clearDiskCache() }).start()
    }

    /**
     * 清除所有缓存
     */
    fun clearAll(context: Context) {
        clearDiskCache(context)
        clearMemory(context)
    }
}
