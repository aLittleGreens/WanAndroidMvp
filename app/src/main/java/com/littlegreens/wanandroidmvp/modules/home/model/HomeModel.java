package com.littlegreens.wanandroidmvp.modules.home.model;


import com.littlegreens.wanandroidmvp.api.Api;
import com.littlegreens.wanandroidmvp.bean.WXarticle;
import com.littlegreens.wanandroidmvp.modules.home.contract.HomeContract;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by caiyuk on 2019/1/30.
 */

public class HomeModel implements HomeContract.Model {
    @Override
    public Observable<WXarticle> getArticle() {
        return Api.getInstance().getArticle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
