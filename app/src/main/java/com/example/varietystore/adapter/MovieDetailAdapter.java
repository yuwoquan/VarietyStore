package com.example.varietystore.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.varietystore.bean.MovieDetailBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by 鱼握拳 on 2017/10/12.
 */

public class MovieDetailAdapter extends RecyclerArrayAdapter<MovieDetailBean.CastsBean> {
    public MovieDetailAdapter(Context context) {
        super(context);
    }

    @Override

    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieDetailViewHoder (parent);
    }



}
