package com.lyl.boon.ui.young

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.lyl.boon.R
import com.lyl.boon.net.entity.GankDataEntity
import com.lyl.boon.ui.base.apdter.MyBaseAdapter
import com.lyl.boon.utils.ImgUtils
import org.byteam.superadapter.internal.SuperViewHolder

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class YongAdapter(
        context: Context,
        items: List<GankDataEntity>,
        layoutResId: Int
) : MyBaseAdapter<GankDataEntity>(context, items, layoutResId) {

    override fun onBind(holder: SuperViewHolder, viewType: Int, position: Int, item: GankDataEntity) {
        super.onBind(holder, viewType, position, item)
        ImgUtils.load(context, item.url.toString(), holder.getView<View>(R.id.item_image_v) as ImageView)
    }

}
