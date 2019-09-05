package com.littlegreens.wanandroidmvp.modules.home.contract;

import com.littlegreens.baselibary.base.mvp.BaseModel;
import com.littlegreens.baselibary.base.mvp.BasePresenter;
import com.littlegreens.baselibary.base.mvp.BaseView;
import com.littlegreens.wanandroidmvp.bean.WXarticle;
import io.reactivex.Observable;

/**
 * Created by caiyuk on 2019/1/30.
 */

public interface HomeContract {

    interface View extends BaseView {
        public void returnArticle(WXarticle wXarticle);
    }

    interface Model extends BaseModel {
        public Observable<WXarticle> getArticle();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getArticleRequest();
    }


}
