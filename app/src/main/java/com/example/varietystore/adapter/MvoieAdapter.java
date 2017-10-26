package com.example.varietystore.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.varietystore.bean.DoubanMovieBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by 鱼握拳 on 2017/9/10.
 */

public class MvoieAdapter extends RecyclerArrayAdapter<DoubanMovieBean.SubjectsBean> {



    public MvoieAdapter(Context context) {
        super(context);
    }


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(parent);
    }


}
