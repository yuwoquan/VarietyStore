package com.example.varietystore.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.example.varietystore.R;
import com.example.varietystore.bean.User;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by 鱼握拳 on 2017/9/14.
 */

public class BmodViewHolder extends BaseViewHolder<User> {
    private boolean animationsLocked = false;
    private int lastAnimatedPosition=-1;
    private boolean delayEnterAnimation = true;
    private TextView text,name,time;
    public BmodViewHolder(ViewGroup parent) {
        super(parent, R.layout.message_board_iten);
        text=$(R.id.downtext);
        name=$(R.id.name);
        time=$(R.id.time);
    }

    @Override
    public void setData(User data) {
        super.setData(data);
        text.setText(data.getDetails());
        name.setText(data.getName());
        time.setText(data.getCreatedAt());
    }
}
