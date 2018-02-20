package com.lyl.boon.ui.superboon

import android.content.Context
import android.view.View
import android.widget.ImageView

import com.lyl.boon.R
import com.lyl.boon.net.entity.SuperImageEntity
import com.lyl.boon.ui.base.apdter.MyBaseAdapter
import com.lyl.boon.utils.ImgUtils
import com.lyl.boon.utils.NetStatusUtil

import org.byteam.superadapter.internal.SuperViewHolder

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class SuperGalleryAdapter(
        context: Context,
        items: List<SuperImageEntity.ListBean>,
        layoutResId: Int
) : MyBaseAdapter<SuperImageEntity.ListBean>(context, items, layoutResId) {

    override fun onBind(holder: SuperViewHolder, viewType: Int, position: Int, item: SuperImageEntity.ListBean) {
        super.onBind(holder, viewType, position, item)

        if (NetStatusUtil.isWifi(context)) {
            ImgUtils.load(context, //
                    item.qhimg_url.toString(), //
                    holder.getView<View>(R.id.item_image_h) as ImageView, //
                    item.pic_width, //
                    item.pic_height)
        } else {
            ImgUtils.load(context, //
                    item.qhimg_thumb_url.toString(), //
                    holder.getView<View>(R.id.item_image_h) as ImageView, //
                    item.qhimg_thumb_width, //
                    item.qhimg_thumb_height)
        }
    }

}
