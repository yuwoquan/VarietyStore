package com.example.varietystore.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.varietystore.R;
import com.example.varietystore.bean.DoubanBookBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by 鱼握拳 on 2017/10/16.
 */

public class BookViewHolder extends BaseViewHolder<DoubanBookBean.BooksBean> {
    private static final String TAG = "BookViewHolder";
    private TextView name;
    private ImageView imageView;
    public BookViewHolder(ViewGroup itemView) {
        super(itemView, R.layout.recycler_book_item);
        name=$(R.id.name);
        imageView=$(R.id.image);
    }

    @Override
    public void setData(DoubanBookBean.BooksBean data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getImage())
                .into(imageView);
        name.setText(data.getTitle());
    }
}
