package com.lyl.boon.ui.learn

import android.content.Context
import android.widget.TextView
import com.lyl.boon.R
import com.lyl.boon.net.entity.GankDataEntity
import com.lyl.boon.ui.base.apdter.MyBaseAdapter
import com.lyl.boon.utils.MyUtils
import org.byteam.superadapter.internal.SuperViewHolder

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
class DevelopAdapter(
        context: Context,
        items: List<GankDataEntity>,
        layoutResId: Int
) : MyBaseAdapter<GankDataEntity>(context, items, layoutResId) {

    override fun onBind(holder: SuperViewHolder, viewType: Int, position: Int, item: GankDataEntity) {
        super.onBind(holder, viewType, position, item)

        holder.setText(R.id.item_develop_title, item.desc?.trim())

        var who = item.who
        who = context.getString(R.string.default_who)


        val whoTv = holder.getView<TextView>(R.id.item_develop_tho)
        whoTv.text = who.trim()
        whoTv.setTextColor(context.getResources().getColorStateList(MyUtils.colors))

        holder.setText(R.id.item_develop_date, " - " + item.createdAt!!.substring(0, 10).trim { it <= ' ' })
    }

}
