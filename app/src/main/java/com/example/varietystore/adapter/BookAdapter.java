package com.example.varietystore.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.varietystore.bean.DoubanBookBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by 鱼握拳 on 2017/10/16.
 */

public class BookAdapter extends RecyclerArrayAdapter<DoubanBookBean.BooksBean> {
            public BookAdapter(Context context){
                super(context);
            }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(parent);
    }
}
