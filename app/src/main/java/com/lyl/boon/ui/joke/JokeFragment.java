package com.lyl.boon.ui.joke;

import android.view.View;

import com.lyl.boon.R;
import com.lyl.boon.net.Network;
import com.lyl.boon.net.entity.ZhuangbiEntity;
import com.lyl.boon.ui.base.fragment.BaseRecyclerFragment;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func4;
import rx.schedulers.Schedulers;

/**
 * Author: lyl
 * Date Created : 2018/2/10.
 */
public class JokeFragment extends BaseRecyclerFragment<ZhuangbiEntity> {

    @Override
    protected void setSubscribe(Observer observer) {
        setSubscription(Observable.zip(Network.INSTANCE.getZhuangbi().search("可爱"), Network.INSTANCE.getZhuangbi()
                .search("110"), Network.INSTANCE.getZhuangbi().search("在下"), Network.INSTANCE.getZhuangbi().search
                ("装逼"), new Func4<List<ZhuangbiEntity>, List<ZhuangbiEntity>, List<ZhuangbiEntity>,
                List<ZhuangbiEntity>, List<ZhuangbiEntity>>() {
            @Override
            public List<ZhuangbiEntity> call(List<ZhuangbiEntity> zhuangbiEntiries, List<ZhuangbiEntity>
                    zhuangbiEntiries2, List<ZhuangbiEntity> zhuangbiEntiries3, List<ZhuangbiEntity> zhuangbiEntiries4) {
                List<ZhuangbiEntity> entiryList = new ArrayList<ZhuangbiEntity>();
                entiryList.addAll(zhuangbiEntiries);
                entiryList.addAll(zhuangbiEntiries2);
                entiryList.addAll(zhuangbiEntiries3);
                entiryList.addAll(zhuangbiEntiries4);
                return entiryList;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer));
    }

    @Override
    protected void initListType() {
        setMListType(BaseRecyclerFragment.Companion.getTYPE_GRID());
    }

    @Override
    protected void initData() {
        setMData(new ArrayList<ZhuangbiEntity>());
        setMAdapter(new JokeListAdapter(getHolder(), getMData(), R.layout.item_grid));
    }

    @Override
    protected void ItemClickListener(View itemView, int viewType, int position) {

    }

}
