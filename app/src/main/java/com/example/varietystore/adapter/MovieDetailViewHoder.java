package com.example.varietystore.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.varietystore.R;
import com.example.varietystore.bean.MovieDetailBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by 鱼握拳 on 2017/10/12.
 */

public class MovieDetailViewHoder extends BaseViewHolder<MovieDetailBean.CastsBean> {
    private static final String TAG = "MovieDetailViewHoder";
    private TextView author;
    private ImageView imageView;

    public MovieDetailViewHoder(ViewGroup parent) {
        super(parent, R.layout.movie_detail_cast_item);
        author=$(R.id.item_actor);
        imageView=$(R.id.item_iv);
    }

    @Override
    public void setData(MovieDetailBean.CastsBean data) {
        super.setData(data);
        author.setText(data.getName());
        Glide.with(getContext())
                .load(data.getAvatars().getMedium())
                .animate(R.anim.item_alpha)
                .into(imageView);

    }
}
