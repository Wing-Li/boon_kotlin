package com.lyl.boon.ui.superboon

import android.content.Context
import android.view.View
import android.widget.ImageView

import com.lyl.boon.R
import com.lyl.boon.net.entity.SuperGalleryEntity
import com.lyl.boon.ui.base.apdter.MyBaseAdapter
import com.lyl.boon.utils.ImgUtils

import org.byteam.superadapter.internal.SuperViewHolder

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class SuperBoonListAdapter(
        context: Context,
        items: List<SuperGalleryEntity.ListBean>,
        layoutResId: Int
) : MyBaseAdapter<SuperGalleryEntity.ListBean>(context, items, layoutResId) {

    override fun onBind(holder: SuperViewHolder, viewType: Int, position: Int, item: SuperGalleryEntity.ListBean) {
        super.onBind(holder, viewType, position, item)
        ImgUtils.load(context, item.qhimg_thumb_url.toString(), holder.getView<View>(R.id.item_image_v) as ImageView, item.qhimg_width, item.qhimg_height)
    }

}
