package com.example.varietystore.api;

import com.example.varietystore.bean.DoubanBookBean;
import com.example.varietystore.bean.DoubanMovieBean;
import com.example.varietystore.bean.Ganhuo;
import com.example.varietystore.bean.MovieDetailBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 鱼握拳 on 2017/8/13.
 */

public interface ApiService {
    @GET("api/data/{type}/{count}/{page}")
    Observable<Ganhuo> getGanHuo(
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page
    );
    @GET("book/search")
    Observable<DoubanBookBean> getBook(
            @Query("tag") String tag,
            @Query("start") int start,
            @Query("count") int count);
    @GET("movie/{type}")
    Observable<DoubanMovieBean> getTopMovie(@Path("type") String type,
                                            @Query("start") int start,
                                            @Query("count") int count);
    //        @GET("top250")
//        Observable<DoubanMovieBean> getTopMovie(@Query("start")int start, @Query("count")int count);
    @GET("{number}")
    Observable<MovieDetailBean> getNumber(@Path("number") String number);

}

