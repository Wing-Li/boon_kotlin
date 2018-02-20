package com.lyl.boon.ui.young;

import android.content.Intent;
import android.view.View;

import com.lyl.boon.R;
import com.lyl.boon.net.Network;
import com.lyl.boon.net.entity.BaseGankEntity;
import com.lyl.boon.net.entity.GankDataEntity;
import com.lyl.boon.ui.base.fragment.BaseRecyclerFragment;
import com.lyl.boon.ui.image.ImageActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 妹子
 */
public class YoungFragment extends BaseRecyclerFragment<GankDataEntity> {

    private ArrayList<String> imgs;

    @Override
    protected void initListType() {
        setMListType(BaseRecyclerFragment.Companion.getTYPE_STAG_V());
    }

    @Override
    protected void initData() {
        setMData(new ArrayList<GankDataEntity>());
        setMAdapter(new YongAdapter(getHolder(), getMData(), R.layout.item_image_v));
    }

    @Override
    protected void setSubscribe(Observer observer) {
        setSubscription(Network.INSTANCE.getGankMenuList().getGankList("福利", getPage()).map(new Func1<BaseGankEntity<List<GankDataEntity>>, List<GankDataEntity>>() {
            @Override
            public List<GankDataEntity> call(BaseGankEntity<List<GankDataEntity>> baseGankEntiry) {
                if (!baseGankEntiry.isError()) {
                    return baseGankEntiry.getResults();
                }
                return null;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer));
    }

    @Override
    protected void ItemClickListener(View itemView, int viewType, int position) {
        setMData(getMAdapter().getList());

        if (imgs == null) imgs = new ArrayList<>();
        imgs.clear();

        for (GankDataEntity entity : getMData()) {
            imgs.add( entity.getUrl() );
        }

        Intent intent = ImageActivity.Companion.getIntent(getHolder(), imgs, position);
        startActivity(intent);
        getHolder().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
