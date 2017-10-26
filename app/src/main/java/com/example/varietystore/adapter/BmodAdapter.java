package com.example.varietystore.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.varietystore.bean.User;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by 鱼握拳 on 2017/9/14.
 */

public class BmodAdapter extends RecyclerArrayAdapter<User> {

    public BmodAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BmodViewHolder(parent);
    }

}
