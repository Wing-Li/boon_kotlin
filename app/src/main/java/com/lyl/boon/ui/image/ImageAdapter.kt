package com.lyl.boon.ui.image

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lyl.boon.R
import com.lyl.boon.app.MyApp
import com.lyl.boon.utils.FileUtils
import com.lyl.boon.utils.ImgUtils
import uk.co.senab.photoview.PhotoView
import java.io.File

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class ImageAdapter(internal var mContext: Context, internal var imgs: List<String>?) : PagerAdapter() {

    override fun getCount(): Int {
        return if (imgs == null) 0 else imgs!!.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    /**
     * 需要移除该View，避免View重复加载。
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

    /**
     * 加载View
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imgUrl = imgs!![position]

        val photoView = PhotoView(mContext)
        photoView.adjustViewBounds = true
        ImgUtils.load(mContext, imgUrl, photoView)
        photoView.setOnLongClickListener(View.OnLongClickListener {
            if (TextUtils.isEmpty(imgUrl)) {
                Toast.makeText(mContext.applicationContext, R.string.img_err, Toast.LENGTH_SHORT).show()
                return@OnLongClickListener false
            }
            AlertDialog.Builder(mContext).setTitle(mContext.getString(R.string.image_save))//
                    .setMessage(mContext.getString(R.string.image_save_msg))//
                    .setNegativeButton(mContext.getString(R.string.save)) { dialog, which ->
                        ImgUtils.downloadImg(
                                mContext,
                                imgUrl,
                                object :ImgUtils.DownloadImage {

                                    override fun downloadImage(imgFile: File?) {
                                        if (imgFile != null) {
                                            val path = MyApp.appPath
                                            val imgName = "boon_" + System.currentTimeMillis() + ".jpg"
                                            val file = File(path, imgName)

                                            // 移动下载的图片到 目标路径
                                            val moveFile = FileUtils.moveFile(imgFile.absolutePath, file.absolutePath)

                                            if (moveFile) {
                                                mContext.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)))
                                                Toast.makeText(mContext.applicationContext, R.string.save_success, Toast.LENGTH_SHORT).show()
                                            } else {
                                                Toast.makeText(mContext.applicationContext, R.string.save_fail, Toast.LENGTH_SHORT).show()
                                            }
                                        } else {
                                            Toast.makeText(mContext.applicationContext, R.string.save_fail, Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                        )
                    }//
                    .setPositiveButton(mContext.getString(R.string.cancel), null).create().show()
            true
        })

        (container as ViewPager).addView(photoView)
        return photoView
    }

}
