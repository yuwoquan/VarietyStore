package com.example.varietystore.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.example.varietystore.R;
import com.example.varietystore.bean.Ganhuo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by 鱼握拳 on 2017/10/18.
 */

public class GankViewholder extends BaseViewHolder<Ganhuo.ResultsBean> {
    private TextView title;
    private TextView who;
    private TextView time;

    public GankViewholder(ViewGroup parent) {
        super(parent, R.layout.item);
        title = $(R.id.title);
        who = $(R.id.who);
        time = $(R.id.time);
    }

    @Override
    public void setData(Ganhuo.ResultsBean data) {
        super.setData(data);
        title.setText(data.getDesc());
        who.setText(data.getWho());
        time.setText(data.getCreatedAt());
    }
}


