package com.littlegreens.wanandroidmvp.api


import com.littlegreens.baselibary.basebean.BaseResponse
import com.littlegreens.wanandroidmvp.bean.LoginBean
import com.littlegreens.wanandroidmvp.bean.RegisterBean
import com.littlegreens.wanandroidmvp.bean.WXarticle
import com.littlegreens.wanandroidmvp.bean.WxArticle
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean.ArticleBeanTopList
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean.BannerBeanList
import com.littlegreens.wanandroidmvp.modules.main.fragment.home.model.bean.HomeArticalBeanList
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*


interface ApiService {


    /**
     * post 和 get 都可以传全路径
     * 缺点 路径是写死的
     *
     * @return
     */
    @get:GET("http://wanandroid.com/wxarticle/chapters/json")
    val article: Observable<WxArticle>

    /**
     * 可监控下载进度
     *
     * @param downloadUrl
     * @return
     */
    @Streaming
    @GET
    fun downloadFile(@Url downloadUrl: String): Observable<ResponseBody>


    /**
     * 参数动态更新请求URL。替换块是由{和包围的字母数字字符串}。
     * 必须@Path使用相同的字符串注释相应的参数。
     *
     * @param id 可动态替换
     */
    @GET("wxarticle/{id}/{json}")
    fun getArticle(@Path("id") id: String): Observable<WXarticle>

    /**
     * post 和 get 都可以传全路径
     * 也可以用拦截器拦截head，不需要改变URl的不需要设置，拦截器通过拦截head，重新设置URL
     *
     * @param url 可以动态改变
     */
    @Headers("Cache-Control:public ,max-age=60")
    @GET
    fun getArticleUrl(@Url url: String): Observable<WXarticle>

    /********************* Start WanAndroid api  */

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<LoginBean>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/register")
    fun register(
        @Field("username") username: String, @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Observable<RegisterBean>

    /**
     * 退出
     * 访问了 logout 后，服务端会让客户端清除 Cookie（即cookie max-Age=0），
     * 如果客户端 Cookie 实现合理，可以实现自动清理，如果本地做了用户账号密码和保存，及时清理。
     */
    @GET("user/logout/json")
    fun logout(): Observable<BaseResponse<Any>>


    //首页
    /**
     * 首页banner
     */
    @GET("banner/json")
    fun getBanner(): Observable<BannerBeanList>

    /**
     * 置顶文章
     * 方法：GET
     */
    @GET("article/top/json")
    fun getTopArticleList(): Observable<ArticleBeanTopList>

    /**
     * 首页文章列表
     * 方法：GET
     * 参数：页码，拼接在连接中，从0开始。
     */
    @GET("article/list/{page}/json")
    fun getArticleList(@Path("page") page: Int): Observable<HomeArticalBeanList>
}
