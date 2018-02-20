package com.lyl.boon.ui.superboon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.lyl.boon.R;
import com.lyl.boon.net.Network;
import com.lyl.boon.net.entity.SuperImageEntity;
import com.lyl.boon.net.entity.SuperImageEntity.ListBean;
import com.lyl.boon.ui.base.fragment.BaseRecyclerFragment;
import com.lyl.boon.ui.image.ImageActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 超级福利 点击之后 进入图库的页面
 */
public class SuperGalleryFragment extends BaseRecyclerFragment<ListBean> {

    private int menu;
    private String galleryId;// 当前页面显示的分类id
    private String title;

    /**
     * 每页显示多少个
     */
    private static final int ROWS = 20;

    private ArrayList<String> imgs;

    /**
     * 是否加载过
     */
    private boolean isLoaded;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        menu = bundle.getInt("menu");
        galleryId = bundle.getString("id");
        title = bundle.getString("title");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (TextUtils.isEmpty(title)) {
            title = getString(R.string.app_name);
        }
        setTitle(title);

        getMSwipeRefreshLayout().setEnabled(false);
    }

    @Override
    protected void initListType() {
        setMListType(BaseRecyclerFragment.Companion.getTYPE_STAG_H());
    }

    @Override
    protected void initData() {
        setMData(new ArrayList<ListBean>());
        setMAdapter(new SuperGalleryAdapter(getHolder(), getMData(), R.layout.item_image_h));
    }

    @Override
    protected void setSubscribe(Observer observer) {
        //TODO
        //这里设计不太合理
        //因为 父类 ，无法判断 下一次是否还有数据，每一次到这里都会重复加载数据。
        //提供接口的服务器并没有判断，下一次是否还有数据。
        if (isLoaded) {
            setRefreshing(false);
            return;
        }

        setSubscription(Network.INSTANCE.getTngou().getGalleryInfo(menu, galleryId).map(new Func1<SuperImageEntity,
                List<ListBean>>() {
            @Override
            public List<ListBean> call(SuperImageEntity superImageEntirty) {
                if (superImageEntirty != null) {
                    List<ListBean> listBeen = superImageEntirty.getList();
                    isLoaded = true;
                    return listBeen;
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

        for (ListBean listBean : getMData()) {
            imgs.add(listBean.getQhimg_url());
        }

        Intent intent = ImageActivity.Companion.getIntent(getHolder(), imgs, position);
        startActivity(intent);
        getHolder().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
