package com.example.varietystore.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.varietystore.fragment.Douban_BookFragment;

/**
 * Created by 鱼握拳 on 2017/10/16.
 */

public class BookFragmentAdapter extends FragmentPagerAdapter {
    public final int COUNT=6;
    private String[] titles=new String[]{"文化","生活","流行","青春","奋斗","科技"};

    public BookFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return Douban_BookFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}

